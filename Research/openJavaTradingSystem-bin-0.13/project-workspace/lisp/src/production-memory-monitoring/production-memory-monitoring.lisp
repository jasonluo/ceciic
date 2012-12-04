;;Licensing Terms:
;; This software is Copyright (c) by Christian Schuhegger, 2005/05/26.
;; Christian Schuhegger grants you the rights to distribute
;; and use this software as governed by the terms
;; of the Lisp Lesser GNU Public License
;; (http://opensource.franz.com/preamble.html),
;; known as the LLGPL.

;;;(asdf:oos 'asdf:load-op 'production-memory-monitoring)
;;;(in-package #:production-memory-monitoring :nicknames '("CSSB.PMM"))
;;;(process-file-remote "prod@testapp01.somewhere.de:~/stats-collecting/memory.txt")
;;;(process-file-remote "prod@testapp02.somewhere.de:~/stats-collecting/memory.txt")
;;;(ext:saveinitmem "pmm.mem" :quiet t :init-function #'production-memory-monitoring::init-function :start-package (find-package "PRODUCTION-MEMORY-MONITORING"))
;;;execute the written image like (as many arguments as you like):
;;;/usr/bin/clisp -norc -M pmm.mem -- prod@testapp01.somewhere.de:~/stats-collecting/memory.txt prod@testapp02.somewhere.de:~/stats-collecting/memory.txt
;;;cat *.report.txt
;;;(cl-user::exit)

(asdf:oos 'asdf:load-op 'cssb-extlib)

(in-package #:production-memory-monitoring :nicknames '("CSSB.PMM"))

;;;(defvar *processes* (make-hash-table :test #'equal) "A hash table.")
(defvar *processes* nil "A hash table.")

(defun init-function ()
  (ext:exit-on-error
   (with
    var argument-list = ext:*args*
    do
    (loop for argument in argument-list do
	  (progn
	    (format t "processing argument: ~A~%" argument)
	    (process-file-remote argument)))
    (ext:exit))
   ))

(defun split-ssh-string (ssh-string)
  (with
   var at-split    = (pregexp-split "@" ssh-string)
   do
   (if (> (length at-split) 1)
       (with
	var colon-split = (pregexp-split ":" (nth 1 at-split))
	do
	(if (and (> (length colon-split) 1)
		 (not (string= "" (nth 0 colon-split))))
	    (values (nth 0 at-split) (nth 0 colon-split) (nth 1 colon-split))
	  (error "Wrong ssh-string format: ~A" ssh-string)))
     (with
      var colon-split = (pregexp-split ":" (nth 0 at-split))
      do
      (if (> (length colon-split) 1)
	  (values "" (nth 0 colon-split) (nth 1 colon-split))
	(values "" "" (nth 0 colon-split)))))))


(defun process-file-remote (ssh-string)
  (with
   vars (ssh-user ssh-host ssh-remote-path)
   = (split-ssh-string ssh-string)
   declare (ignorable ssh-user ssh-remote-path)
   var ssh-host             = (if (string= "" ssh-host) "localhost" ssh-host)
   var local-filename       = (format nil "./~A-memory.txt" ssh-host)
   var report-filename      = (format nil "./~A-memory.report.txt" ssh-host)
   var scp-command          = (format nil "scp ~A ~A" ssh-string local-filename)
   open-file output-stream  = (report-filename :direction :output :if-exists :supersede)
   do
   (format t "fetching remote memory.txt file: ~A~%" scp-command)
   (ext:run-shell-command scp-command)
   (format t "processing file ~A~%" local-filename)
   (process-file local-filename output-stream)
   (format t "results written to file ~A~%" report-filename)
   ))

(defun process-file (filename output-stream)
  (with
   var *processes* = (make-hash-table :test #'equal) declare (special *processes*)
   open-file memory-log-in-stream = (filename :direction :input :if-does-not-exist :error)
   do
   (loop for line = (read-line memory-log-in-stream nil) while line do
	 (handle-line line))
   (report-memory-leaks *processes* output-stream)
   ))

;;;(process-file "testapp01.somewhere.de-memory.txt" t)
(defun local-read-from-string (input)
  ;;(format t "reading from string:~A~%" input)
  (read-from-string input nil nil))

;;;date       time   command-name      pid  vsize  rss
;;;2005-03-09 08:26  AgencyMng         1376 109076 23748
(defun handle-line (line)
  (handler-case
   (with
    vars (date time command-name pid vsize rss) = (bind-line-values line)
    var command-pid   = (format nil "~A-~A" command-name pid)
    var value-sets    = (gethash command-pid *processes*)
    do
    (if value-sets
	(with
	 var first-set   = (first  value-sets)
	 var second-set  = (second value-sets)
	 var third-set   = (third  value-sets)
	 var starttime   = (convert-time-to-utc (nth 0 first-set) (nth 1 first-set))
	 var currenttime = (convert-time-to-utc date              time)
	 var time-diff-in-hours = (/ (- currenttime starttime) 60 60)
	 do
	 (when (< time-diff-in-hours 1) ;; below one hour
	   (setf (nth 0 second-set) date)
	   (setf (nth 1 second-set) time)
	   (setf (nth 4 second-set) (max (nth 4 second-set) vsize))
	   (setf (nth 5 second-set) (max (nth 5 second-set) vsize)))
	 (setf (nth 0 third-set) date)
	 (setf (nth 1 third-set) time)
	 (setf (nth 4 third-set) (max (nth 4 second-set) vsize))
	 (setf (nth 5 third-set) (max (nth 5 second-set) vsize)))
      (with
       var first-set  = (list date time command-name pid vsize rss)
       var second-set = (list date time command-name pid vsize rss)
       var third-set  = (list date time command-name pid vsize rss)
       do
       (setf (gethash command-pid *processes*) (list first-set second-set third-set)))))
   (malformed-log-entry-error (err-arg) (format t "malformed-log-entry-error occured: ~A~%" (text err-arg)))))

(define-condition malformed-log-entry-error (error)
  ((text :initarg :text :reader text)))

(defun bind-line-values (line)
  (with
   var line-elements = (pregexp-split "\\s+" line)
   do
   (when (/= (length line-elements) 6)
     (error 'malformed-log-entry-error :text (format nil "The line: ~A does not contain 6 elements." line)))
   (with    
    var date          = (nth 0 line-elements)
    var time          = (nth 1 line-elements)
    var command-name  = (nth 2 line-elements)
    var pid           = (local-read-from-string (nth 3 line-elements))
    var vsize         = (local-read-from-string (nth 4 line-elements))
    var rss           = (local-read-from-string (nth 5 line-elements))
    do
    (values date time command-name pid vsize rss))))

(defvar *max-vsize-diff* 20000)
(defvar *max-rss-diff*   20000)
(defun report-memory-leaks (ht output-stream)
  (with
   var memory-leak-list =
   (loop for key being the hash-keys of ht append
	 (with
	  var entry      = (gethash key ht)
	  var first-set  = (second entry) ;;values after one hour of runtime
	  var second-set = (third  entry) ;;either current values or at end of runtime

	  ;;var set-1-date          = (nth 0 first-set)
	  ;;var set-1-time          = (nth 1 first-set)
	  ;;var set-1-command-name  = (nth 2 first-set)
	  ;;var set-1-pid           = (nth 3 first-set)
	  var set-1-vsize         = (nth 4 first-set)
	  var set-1-rss           = (nth 5 first-set)

	  ;;var set-2-date          = (nth 0 second-set)
	  ;;var set-2-time          = (nth 1 second-set)
	  ;;var set-2-command-name  = (nth 2 second-set)
	  ;;var set-2-pid           = (nth 3 second-set)
	  var set-2-vsize         = (nth 4 second-set)
	  var set-2-rss           = (nth 5 second-set)
	 
	  do
	  (when (or (> (- set-2-vsize set-1-vsize) *max-vsize-diff*)
		    (> (- set-2-rss   set-1-rss)   *max-rss-diff*)
		    )
	    (list (list key first-set second-set)))))
   do
   (when
       (not
	(loop for memory-leak in memory-leak-list append
	      (with
	       var key        = (nth 0 memory-leak)
	       var first-set  = (nth 1 memory-leak)
	       var second-set = (nth 2 memory-leak)
		
	       var set-1-date          = (nth 0 first-set)
	       var set-1-time          = (nth 1 first-set)
	       ;;var set-1-command-name  = (nth 2 first-set)
	       ;;var set-1-pid           = (nth 3 first-set)
	       var set-1-vsize         = (nth 4 first-set)
	       var set-1-rss           = (nth 5 first-set)
		
	       var set-2-date          = (nth 0 second-set)
	       var set-2-time          = (nth 1 second-set)
	       ;;var set-2-command-name  = (nth 2 second-set)
	       ;;var set-2-pid           = (nth 3 second-set)
	       var set-2-vsize         = (nth 4 second-set)
	       var set-2-rss           = (nth 5 second-set)
		
	       var delta-time-seconds  = (- (convert-time-to-utc set-2-date set-2-time)
					    (convert-time-to-utc set-1-date set-1-time))
	       var delta-time-hours    = (/ delta-time-seconds 60 60)
		
	       var delta-vsize         = (- set-2-vsize set-1-vsize)
	       var rate-vsize          = (float (/ delta-vsize delta-time-hours 1024))
		
	       var delta-rss           = (- set-2-rss   set-1-rss)
	       var rate-rss            = (float (/ delta-rss delta-time-hours 1024))
		
	       do
	       (when (> delta-time-hours 6) ;; minimum runtime 6 hours
		 (format output-stream
			 "probable memory leak for: ~A start ~AT~A end ~AT~A runtime: ~,1F h~%"
			 key set-1-date set-1-time set-2-date set-2-time delta-time-hours)
		 (format output-stream
			 "                          vsize-start: ~10F kB vsize-end: ~10F kB delta: ~10F kB rate: ~,2F MB/h~%"
			 set-1-vsize set-2-vsize delta-vsize rate-vsize)
		 (format output-stream
			 "                          rss-start  : ~10F kB rss-end  : ~10F kB delta: ~10F kB rate: ~,2F MB/h~%"
			 set-1-rss   set-2-rss   delta-rss rate-rss)
		 (list 1)))))
     (format output-stream "no memory leaks detected"))))




(defun convert-time-to-utc (date time)
  (with
   var date-elements = (pregexp-split "-" date)
   var time-elements = (pregexp-split ":" time)
   var second        = 0
   var minute        = (local-read-from-string (nth 1 time-elements))
   var hour          = (local-read-from-string (nth 0 time-elements))
   var dom           = (local-read-from-string (nth 2 date-elements))
   var month         = (local-read-from-string (nth 1 date-elements))
   var year          = (local-read-from-string (nth 0 date-elements))
   do
   (encode-universal-time second minute hour dom month year);; &optional time-zone
   ))
