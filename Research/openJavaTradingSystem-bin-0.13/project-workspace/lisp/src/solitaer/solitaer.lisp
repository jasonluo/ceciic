;;Licensing Terms:
;; This software is Copyright (c) by Christian Schuhegger, 2005/05/26.
;; Christian Schuhegger grants you the rights to distribute
;; and use this software as governed by the terms
;; of the Lisp Lesser GNU Public License
;; (http://opensource.franz.com/preamble.html),
;; known as the LLGPL.

;;;(asdf:oos 'asdf:load-op 'solitaer)
;;;(solitaer::init-function)

;;;clisp:
;;;(in-package #:solitaer :nicknames '("CSSB.SOLITAER"))
;;;;;;(ext:saveinitmem "pmm.mem" :quiet t :init-function #'production-memory-monitoring::init-function :start-package (find-package "PRODUCTION-MEMORY-MONITORING"))
;;;(cl-user::exit)


;;;sbcl:
;;;(in-package #:solitaer)
;;;(sb-ext:save-lisp-and-die "solitaer.core.sbcl" :toplevel #'solitaer::init-function)
;;;(sb-ext:quit)

(asdf:oos 'asdf:load-op 'cssb-extlib)

;;;(in-package #:solitaer :nicknames '("CSSB.SOLITAER"))
(in-package #:solitaer)

(defun create-initial-set-up ()
  (with
   var init = (make-string 33 :initial-element #\O)
   do
   (setf (aref init 16) #\.)
   init
  ))

(defun create-final-set-up ()
  (with
   var init = (make-string 33 :initial-element #\.)
   do
   (setf (aref init 16) #\O)
   init
  ))

(defparameter *FINAL-SET-UP* (create-final-set-up))

(defun pretty-print (stream field)
  (loop for i from 0 to 1 do
       (progn
	 (format stream "  ")
	 (loop for j from 0 to 2 do
	      (format stream "~A" (aref field (+ (* i 3) j))))
	 (format stream "~%")))
  (loop for i from 0 to 2 do
       (progn
	 (loop for j from 0 to 6 do
	      (format stream "~A" (aref field (+ 6 (* i 7) j))))
	 (format stream "~%")))
  (loop for i from 0 to 1 do
       (progn
	 (format stream "  ")
	 (loop for j from 0 to 2 do
	      (format stream "~A" (aref field (+ 27 (* i 3) j))))
	 (format stream "~%")))       
   )

;;;(pretty-print t (create-initial-set-up))

(defvar *ROWCOUNT* '(3 3 7 7 7 3 3))

(defun convert-index-to-coordinate-rec (index i)
  (with
   var rc-next = (nth i *ROWCOUNT*)
   do
   (if (>= index rc-next)
       (convert-index-to-coordinate-rec (- index rc-next) (1+ i))
     (values i index))))

(defun convert-index-to-coordinate (index)
  (convert-index-to-coordinate-rec index 0))

;;;(convert-index-to-coordinate 16)

(defun convert-coordinate-to-index (i j)
  (+ (loop for k from 0 below i sum (nth k *ROWCOUNT*)) j))

;;;(convert-coordinate-to-index 3 3)


(defun rotate-coordinate-right (i j)
  (with
   var new-j = (if (and (find i '(2 3 4)) (find j '(0 1 5 6))) (- 4 i) (- 6 i))
   var new-i = (if (find i '(0 1 5 6)) (+ 2 j) j)
   do
   (values new-i new-j)
   ))
;;(rotate-coordinate-right 0 0)(rotate-coordinate-right 0 0)

(defvar *POSSIBLE-MOVES-RIGHTWARDS*
  '((0 1 2) (3 4 5)
    (6 7 8)    (7 8 9)    (8 9 10)   (9 10 11)  (10 11 12)
    (13 14 15) (14 15 16) (15 16 17) (16 17 18) (17 18 19)
    (20 21 22) (21 22 23) (22 23 24) (23 24 25) (24 25 26)
    (27 28 29)
    (30 31 32)))

(defgeneric fmap (fn data))
(defmethod fmap (fn data)
  (funcall fn data))

(defmethod fmap (fn (data list))
  (mapcar #'(lambda (x) (fmap fn x)) data))

;;;(fmap #'(lambda (x) (* x x)) '((1 2 3)(4 5 6)(7 8 9)))

(defun rotate-index-right (index)
  (with
   vars (i j) = (convert-index-to-coordinate index)
   vars (new-i new-j) = (rotate-coordinate-right i j)
   do
   (convert-coordinate-to-index new-i new-j)
   ))
;;;(rotate-index-right 0)

(defparameter *POSSIBLE-MOVES-DOWNWARDS* (fmap #'rotate-index-right *POSSIBLE-MOVES-RIGHTWARDS*))
(defparameter *POSSIBLE-MOVES-LEFTWARDS* (fmap #'rotate-index-right *POSSIBLE-MOVES-DOWNWARDS*))
(defparameter *POSSIBLE-MOVES-UPWARDS*   (fmap #'rotate-index-right *POSSIBLE-MOVES-LEFTWARDS*))

(defparameter *POSSIBLE-MOVES* (append *POSSIBLE-MOVES-RIGHTWARDS*
				       *POSSIBLE-MOVES-DOWNWARDS*
				       *POSSIBLE-MOVES-LEFTWARDS*
				       *POSSIBLE-MOVES-UPWARDS*))

(defun move-applicable-p (field move)
  (and (eq (aref field (nth 0 move)) #\O)
       (eq (aref field (nth 1 move)) #\O)
       (eq (aref field (nth 2 move)) #\.)))

(defun get-possible-moves (field)
  (loop for move in *POSSIBLE-MOVES* append
	(when (move-applicable-p field move) (list move))))

;;;(get-possible-moves (create-initial-set-up))

(defun apply-move-to-field (field move)
  (with
   var field-copy = (copy-seq field)
   do
   (setf (aref field-copy (nth 0 move)) #\.)
   (setf (aref field-copy (nth 1 move)) #\.)
   (setf (aref field-copy (nth 2 move)) #\O)
   field-copy))

;;;(pretty-print t (apply-move-to-field (create-initial-set-up) (first (get-possible-moves (create-initial-set-up)))))

(defun apply-moves-to-field (field moves)
  (mapcar #'(lambda (move) (apply-move-to-field field move)) moves))

;;;(mapc #'(lambda (field) (pretty-print t field)) (apply-moves-to-field (create-initial-set-up) (get-possible-moves (create-initial-set-up))))

(defparameter *MISSMATCH-COUNT* 0)

(defparameter *SOLUTIONS* nil)

(defparameter *start-time* 0)

(defun solve (field)
  (solve-rec (list field) (list field) 0))

(defparameter *ALREADY-SEEN-HASH* (make-hash-table :test #'equal))

(defun time-since-start ()
  (- (get-universal-time) *start-time*))

(defun rotate-field-right (field)
  (with
   var new-field = (make-string 33 :initial-element #\X)
   do
   (loop for index from 0 to 32 do
	 (setf (aref new-field (rotate-index-right index)) (aref field index)))
   new-field))

(defun generate-symmetries (field)
  (with
   var r1 = (rotate-field-right field)
   var r2 = (rotate-field-right r1)
   var r3 = (rotate-field-right r2)
   do
   (list field r1 r2 r3)))

(defun mark-as (hashtable field what)
  (with
   var symmetries = (generate-symmetries field)
   do
   (loop for fld in symmetries do
	 (setf (gethash fld hashtable) what))))

(defun mark-as-success (hashtable field solution-so-far)
  (mark-as hashtable field 'SUCCESS)
  (loop for fld in solution-so-far do
	(mark-as hashtable fld 'SUCCESS)))

(defun add-to-solution (field solution-so-far &key (use-hash nil))
  (with
   var solution = (reverse (cons field solution-so-far))
   do
   (push solution *SOLUTIONS*)
   (when use-hash
     (mark-as-success *ALREADY-SEEN-HASH* field solution-so-far))
   (format t "Solution found: ~A~%" (time-since-start))
   (format t "~A~%" solution)
   (pretty-print-several t solution)))

(defun solve-rec-entry (fields solution-so-far level)
  (setf *MISSMATCH-COUNT*  0)
  (setf *SOLUTIONS*      nil)
  (setf *start-time*        (get-universal-time))
  (setf *ALREADY-SEEN-HASH* (make-hash-table :test #'equal))
  (solve-rec fields solution-so-far level))

(defun solve-rec-with-hash (fields solution-so-far level)
  (if (null fields)
      (progn
	(when (eql 0 (mod *MISSMATCH-COUNT* 10000))
	  ;;(pretty-print-several t solution-so-far)
	  ;;(break)	  
	  (format t "*MISSMATCH-COUNT*: ~A time since start: ~A already seen size: ~A~%" *MISSMATCH-COUNT* (time-since-start) (hash-table-count *ALREADY-SEEN-HASH*)))
	(incf *MISSMATCH-COUNT*))
    (if (and (eql (length fields) 1)
	     (equal (first fields) *FINAL-SET-UP*))
	(add-to-solution (first fields) solution-so-far :use-hash t)
      (loop for field in fields do
	    (with
	     var hashentry = (gethash field *ALREADY-SEEN-HASH*)
	     do
	     (case hashentry
	       (SUCCESS (add-to-solution field solution-so-far :use-hash t))
	       (FAILED  nil)
	       (t        (with
			  var possible-moves       = (get-possible-moves field)
			  var next-level-of-fields = (apply-moves-to-field field possible-moves)
			  var solution-so-far      = (cons field solution-so-far)
			  var solution-count       = (length *SOLUTIONS*)
			  do
			  (solve-rec next-level-of-fields solution-so-far (1+ level))
			  (when (eql (length *SOLUTIONS*) solution-count)
			    (setf (gethash field *ALREADY-SEEN-HASH*) 'FAILED)))))
	     )))))

(defun solve-rec (fields solution-so-far level)
  (if (null fields)
      (progn
	(when (eql 0 (mod *MISSMATCH-COUNT* 1000000))
	  ;;(pretty-print-several t solution-so-far)
	  ;;(break)	  
	  (format t "*MISSMATCH-COUNT*: ~A time since start: ~A~%" *MISSMATCH-COUNT* (time-since-start)))
	(incf *MISSMATCH-COUNT*))
    (if (and (eql (length fields) 1)
	     (equal (first fields) *FINAL-SET-UP*))
	(add-to-solution (first fields) solution-so-far)
      (loop for field in fields do
	    (with
	     var possible-moves       = (get-possible-moves field)
	     var next-level-of-fields = (apply-moves-to-field field possible-moves)
	     var solution-so-far      = (cons field solution-so-far)
	     do
	     (solve-rec next-level-of-fields solution-so-far (1+ level)))))))

	       
(defun pretty-print-several (stream fields)
  (loop for field in fields do
	(progn
	  (pretty-print stream field)
	  (format stream "~%"))))
;;;(solve (create-initial-set-up))

;;;(solve-rec-entry '("OOO.OOOO.OOOOO.OOOOOOOOOOOOOOOOOO" "OOOOOOOOOOOOOO.O..OOOOOOOOOOOOOOO") '("OOOOOOOOOOOOOO..OOOOOOOOOOOOOOOOO" "OOOOOOOOOOOOOOOO.OOOOOOOOOOOOOOOO") 1)

(defun init-function ()
  (solve-rec-entry '("OOO.OOOO.OOOOO.OOOOOOOOOOOOOOOOOO" "OOOOOOOOOOOOOO.O..OOOOOOOOOOOOOOO") '("OOOOOOOOOOOOOO..OOOOOOOOOOOOOOOOO" "OOOOOOOOOOOOOOOO.OOOOOOOOOOOOOOOO") 1)
  (format t "finished executing.")
  ;;(sb-ext:quit)
  )
;;   (ext:exit-on-error
;;    (with
;;     var argument-list = ext:*args*
;;     do
;;     (loop for argument in argument-list do
;; 	  (progn
;; 	    (format t "processing argument: ~A~%" argument)
;; 	    (process-file-remote argument)))
;;     (ext:exit))
;;    ))
