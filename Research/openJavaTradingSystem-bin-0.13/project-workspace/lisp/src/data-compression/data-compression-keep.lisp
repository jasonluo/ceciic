;;Licensing Terms:
;; This software is Copyright (c) by Christian Schuhegger, 2003/07/10.
;; Christian Schuhegger grants you the rights to distribute
;; and use this software as governed by the terms
;; of the Lisp Lesser GNU Public License
;; (http://opensource.franz.com/preamble.html),
;; known as the LLGPL.

(in-package #:cssb-data-compression :nicknames '("CSSB.DATA-COMPRESSION"))

;; bit arithmetic
(defun ba-subtract-deprecated (h l)
  (with
   var len       = *arithmetic-with-number-of-bits*
   var result    = (make-array *arithmetic-with-number-of-bits* :element-type 'bit :initial-element 0)
   var carry-old = 0
   do
   (loop for i from 0 to (1- len) do
	 (with
	  var carry-1 = (ash    (+ (svref l i) carry-old) -1)
	  var l-value = (logand (+ (svref l i) carry-old)  1)
	  var r       = (logxor l-value (svref h i))
	  var carry-2 = (if (and (eql (svref h i) 0) (eql l-value 1)) 1 0)
	  var carry   = (+ carry-1 carry-2)
	  do
	  (setf carry-old carry)
	  (setf (svref result i) r)
	  ))
   ))


(defun p-table-ref (p-table index)
  (cdr (find index p-table :key #'car))
  )

;;never used therefore inefficient
(defun p-table-ref-setf (p-table index value)
  (setf (cdr (find index p-table :key #'car)) value)
  (sort p-table #'> :key #'cdr))

(defsetf p-table-ref p-table-ref-setf)

(defun p-table-incf (p-table index)
  (with
   var i = 0
   do
   (loop while (not (eql (car (svref p-table i)) index)) do
	 (incf i))
   (p-table-incf-bottom p-table i)))

(defun ac-encode-v1 (input-name)
  (declare (optimize (speed 3)));;(safety 0) (space 0) (debug 0) 
  (with
   var *debug-arithmetic-coding* = nil
   declare (special *debug-arithmetic-coding*)
   var pathname            = (parse-namestring input-name)
   var output-name         = (make-pathname :directory (pathname-directory pathname)
					    :name (pathname-name pathname)
					    :type "ac");;(pathname-type pathname)
   open-file input-stream  = (input-name  :direction :input :if-does-not-exist :error :element-type `(unsigned-byte ,*symbol-size-in-bits*))
   open-file output-stream = (output-name :direction :output :if-exists :supersede :element-type 'bit)
   var continue  = t
   var l         = 0
   var u         = (1- (expt 2 *arithmetic-with-number-of-bits*))
   var msb       = (expt 2 (- *arithmetic-with-number-of-bits* 1))
   var msb-1     = (expt 2 (- *arithmetic-with-number-of-bits* 2))
   ;;var e3-l     = (ash (expt 2 *arithmetic-with-number-of-bits*) -2);; one   fourth of arithmetic range: 0.25
   ;;var e3-h     = (* e3-l 3)                                        ;; three fourth of arithmetic range: 0.75
   var e3-scale  = 0
   ;; last symbol in p-table is the eof symbol
   var eof       = (expt 2 *symbol-size-in-bits*)
   var count     = (1+ eof)
   var p-table   = (mk-p-table count)
   var bytecount = 0
   var bcovcount = 0
   var bclimit   = 10000
   var format-string       = (format nil "~~~A,'0B~~%~~~A,'0B~~%" *arithmetic-with-number-of-bits* *arithmetic-with-number-of-bits*)
   var condition-u-l-equal = nil
   var condition-e3        = nil
   var b        = 0
   var nb       = 0
   var l-msb    = 0
   var int-slot = (byte *arithmetic-with-number-of-bits* 0)
   do
   format-string;;necessary to avoid compilation warning: variable FORMAT-STRING is not used.
   (loop while continue do
	 (incf bytecount)
	 (if* (= bytecount bclimit)
	      then
	      (setf bytecount 0)
	      (incf bcovcount)
	      (format t "Read ~A bytes~%" (* bclimit bcovcount))
	      )
	 (with
	  var byte      = (read-byte input-stream nil eof)
	  var range     = (1+ (- u  l))
	  vars (cum-count-1 cum-count-2) = (ac-cum-count p-table byte)
	  do
	  (when (and (eql bcovcount 5)
		     (> bytecount 8760))
	    (format t "bytecount: ~A~%" (+ (* bclimit bcovcount) bytecount))
	    (format t "l, u~%")
	    (format t format-string l u)
	    (format t "p1: ~A p2: ~A~%" (float (/ cum-count-1 count)) (float (/ cum-count-2 count)))
	    (setf *debug-arithmetic-coding* t)
	    )
	  (setf u (+ l (floor (* range cum-count-2)
			      count) -1))
	  (setf l (+ l (floor (* range cum-count-1)
			      count)))
	  (with-debug-output
	   (*debug-arithmetic-coding*)
	   (format t "l-nw, u-nw~%")
	   (format t format-string l u)
	   (format t "value: ~A, count: ~A~%" cum-count-2 count)
	   ;;(break)
	   )
	  
	  (if* (eql byte eof)
	       then
	       (with-debug-output
		(*debug-arithmetic-coding*)
		(format t "eof symbol next~%")
		)
	       (setf continue nil)
	       else
	       (with-debug-output
		(*debug-arithmetic-coding*)
		(format t "encoded symbol: ~A = ~A~%" byte (code-char byte)))
	       )

	  ;;integral part of ac-cum-count
	  ;;(p-table-incf p-table byte)
	  (incf count)

	  (loop while (or (setf condition-u-l-equal (eql (setf l-msb (logand l msb)) (logand u msb)))
			  (setf condition-e3 (and      (eql 0 (logand u msb-1))
						       (not (eql 0 (logand l msb-1))))))
		do
		(if* condition-u-l-equal
		     then
		     (setf b  (ash l-msb (* -1 (1- *arithmetic-with-number-of-bits*))))
		     (setf nb (logxor b 1))

		     (write-byte b output-stream)
		     (setf l (ldb int-slot (ash l 1)))
		     (setf u (ldb int-slot (logior (ash u 1) 1)))
		     (loop for i from 1 to e3-scale do
			   (with-debug-output
			    (*debug-arithmetic-coding*)
			    (format t "e3-scale~%"))
			   (write-byte nb output-stream))
		     (setf e3-scale 0)
		     (with-debug-output
		      (*debug-arithmetic-coding*)
		      (format t "l shifted, u shifted~%")
		      (format t format-string l u))
		     
		     ;;condition-e3
		     else
		     (setf l (ldb int-slot (logxor (ash l 1) msb)))
		     (setf u (ldb int-slot (logxor (logior (ash u 1) 1) msb)))
		     (with-debug-output
		      (*debug-arithmetic-coding*)
		      (format t "e3: l shifted, u shifted~%")
		      (format t format-string l u))
		     (incf e3-scale)))
	  (if* (not continue)
	       then
	       (with-debug-output
		(*debug-arithmetic-coding*)
		(format t "eof-final: l shifted, u shifted~%")
		(format t format-string l u)
		)
	       
	       (setf b  (logand (ash l (* -1 (- *arithmetic-with-number-of-bits* 1))) 1))
	       (setf nb (logxor b 1))
	       (write-byte b output-stream)
	       (loop for i from 1 to e3-scale do
		     (with-debug-output
		      (*debug-arithmetic-coding*)
		      (format t "e3-scale~%"))
		     (write-byte nb output-stream))
	       ;;(loop for i from (- *arithmetic-with-number-of-bits* 2) downto 0 do
	       ;;(write-byte (logand (ash l (* -1 i)) 1) output-stream)
	       ;;)
	       )
	  ))
   (with-debug-output
    (*debug-arithmetic-coding*)
    (format t "p-table:~%~A~%count: ~A~%" p-table count))
   ))

(defun ac-decode-v1 (file-name)
  (with
   var *debug-arithmetic-coding* = nil
   declare (special *debug-arithmetic-coding*)
   var pathname            = (parse-namestring file-name)
   var input-name          = (make-pathname :directory (pathname-directory pathname)
					    :name (pathname-name pathname)
					    :type "ac")
   var output-name         = (make-pathname :directory (pathname-directory pathname)
					    :name (format nil "~A-decoded" (pathname-name pathname))
					    :type "txt")
   open-file input-stream  = (input-name  :direction :input :if-does-not-exist :error :element-type 'bit)
   open-file output-stream = (output-name :direction :output :if-exists :supersede :element-type `(unsigned-byte ,*symbol-size-in-bits*)
					  :buffered nil)
   var continue  = t
   var l         = 0
   var u         = (1- (expt 2 *arithmetic-with-number-of-bits*))
   var msb       = (expt 2 (- *arithmetic-with-number-of-bits* 1))
   var msb-1     = (expt 2 (- *arithmetic-with-number-of-bits* 2))
   
   ;; last symbol in p-table is the eof symbol
   var eof       = (expt 2 *symbol-size-in-bits*)
   var count     = (1+ eof)
   var p-table   = (mk-p-table count)
   var beof      = 2
   var byte      = 0
   var bytecount = 0
   var bcovcount = 0
   var bclimit   = 10000
   var tag       = 0
   var condition-u-l-equal = nil
   var condition-e3        = nil
   var format-string       = (format nil "~~~A,'0B~~%~~~A,'0B~~%~~~A,'0B~~%"
				     *arithmetic-with-number-of-bits*
				     *arithmetic-with-number-of-bits*
				     *arithmetic-with-number-of-bits*)
   var b        = 0
   var int-slot = (byte *arithmetic-with-number-of-bits* 0)
   do
   
   format-string
   (loop for i from 0 to (1- *arithmetic-with-number-of-bits*)
	 while (not (eql (setf byte (read-byte input-stream nil beof))
			 beof))
	 do
	 (setf tag (logior (ash tag 1) byte)))

   (with-debug-output
    (*debug-arithmetic-coding*)
    (format t "l-start, u-start, t-start~%")
    (format t format-string l u tag))

   (loop while continue do
	 (with
	  var range = (1+ (- u  l))

	  var value = (floor (1- (* (1+ (- tag  l)) count)) range)
	  ;;var value = (floor (* (1+ (- tag  l)) count) range)
	  vars (cum-count-1 cum-count-2 byte)  = (p-table-decode-value p-table value)
	  
	  do
	  (incf bytecount)
	  (if* (= bytecount bclimit)
	       then
	       (setf bytecount 0)
	       (incf bcovcount)
	       (format t "Wrote ~A bytes~%" (* bclimit bcovcount))
	       )
	 
	  (when (and (eql bcovcount 5)
		     (> bytecount 8750))
	    (format t "bytecount: ~A~%" (+ (* bclimit bcovcount) bytecount))
	    (format t "l, u, t~%")
	    (format t format-string l u tag)
	    (format t "value: ~A, r-value: ~A, count: ~A~%" value (/ (1- (* (1+ (- tag  l)) count)) range) count)
	    (format t "l-value: ~A, u-value: ~A~%" (floor (* (1+ (- l  l)) count) range) (floor (* (1+ (- u  l)) count) range))
	    (format t "p: ~A~%" (float (/ value count)))
	    ;;(when (eql 0 (floor (* (1+ (- l  l)) count) range))	      
	      ;;(break))
	    (setf *debug-arithmetic-coding* t)
	    )
	  (setf u (+ l (floor (* range cum-count-2)
			      count) -1))
	  (setf l (+ l (floor (* range cum-count-1) count)))
	  (with-debug-output
	   (*debug-arithmetic-coding*)
	   (format t "l-nw, u-nw, t~%")
	   (format t format-string l u tag)
	   )
	  (if* (eql byte eof)
	       then
	       (setf continue nil)
	       else
	       (write-byte byte output-stream)
	       ;;(format t "decoded symbol: ~A = ~A~%" byte (code-char byte))
	       (with-debug-output
		(*debug-arithmetic-coding*)
		(format t "decoded symbol: ~A = ~A~%" byte (code-char byte)))
	       )
	  
	  (incf count))
	 
	 (loop while (and continue
			  (or (setf condition-u-l-equal (eql (logand l msb) (logand u msb)))
			      (setf condition-e3 (and      (eql 0 (logand u msb-1))
							   (not (eql 0 (logand l msb-1)))))))
	       do
	       (setf b (read-byte input-stream nil beof))
	       (if* condition-u-l-equal
		    then
		    (when (eql b beof)
		      (error "beof received, should never happen!!")
		      )
		    (setf l (ldb int-slot (ash l 1)))
		    (setf u (ldb int-slot (logior (ash u 1) 1)))
		    (setf tag (ldb int-slot (logior (ash tag 1) b)))
		    (with-debug-output
		     (*debug-arithmetic-coding*)
		     (format t "l-shifted, u-shifted, tag-shifted~%")
		     (format t format-string l u tag))
		     
		     ;;condition-e3
		     else
		     (setf l (ldb int-slot (logxor (ash l 1) msb)))
		     (setf u (ldb int-slot (logxor (logior (ash u 1) 1) msb)))
		     (setf tag (ldb int-slot (logxor (logior (ash tag 1) b) msb)))
		     (with-debug-output
		      (*debug-arithmetic-coding*)
		      (format t "e3: l-shifted, u-shifted, tag-shifted~%")
		      (format t format-string l u tag))
		     ))
	 )))

(defun ac-encode-algorithm-v2 (output-stream model)
  (declare (optimize (speed 3)));;(safety 0) (space 0) (debug 0) 
  (with
   var continue            = t
   var l                   = 0
   var u                   = (1- (expt 2 *arithmetic-with-number-of-bits*))
   var msb                 = (expt 2 (- *arithmetic-with-number-of-bits* 1))
   var msb-1               = (expt 2 (- *arithmetic-with-number-of-bits* 2))
   var e3-scale            = 0
   var condition-u-l-equal = nil
   var condition-e3        = nil
   var b                   = 0
   var nb                  = 0
   var l-msb               = 0
   var int-slot            = (byte *arithmetic-with-number-of-bits* 0)
   do
   (loop while continue do
	 (with
	  var range     = (1+ (- u  l))
	  ;; total-count must never grow larger than (2 ^ *arithmetic-with-number-of-bits*) / 4
	  vars (eof cumulative-count-low cumulative-count-high total-count)
	  =
	  (funcall model)
	  do
	  (setf u (+ l (floor (* range cumulative-count-high)
			      total-count) -1))
	  (setf l (+ l (floor (* range cumulative-count-low)
			      total-count)))
	  
	  (when eof
	    (setf continue nil)))

	 (loop while (or (setf condition-u-l-equal (eql (setf l-msb (logand l msb)) (logand u msb)))
			 (setf condition-e3 (and (eql 0 (logand u msb-1))
						 (not (eql 0 (logand l msb-1))))))
	       do
	       (if* condition-u-l-equal
		    then
		    (setf b  (ash l-msb (* -1 (1- *arithmetic-with-number-of-bits*))))
		    (setf nb (logxor b 1))
		    
		    (write-byte b output-stream)
		    (setf l (ldb int-slot (ash l 1)))
		    (setf u (ldb int-slot (logior (ash u 1) 1)))
		    (loop for i from 1 to e3-scale do
			  (write-byte nb output-stream))
		    (setf e3-scale 0)
		    
		    ;;condition-e3
		    else
		    (setf l (ldb int-slot (logxor (ash l 1) msb)))
		    (setf u (ldb int-slot (logxor (logior (ash u 1) 1) msb)))
		    (incf e3-scale)))
	 )
   
   ;; output final bits of lower boundary
   ;;(when (> e3-scale 0)
   (setf b  (logand (ash l (* -1 (- *arithmetic-with-number-of-bits* 1))) 1))
   (setf nb (logxor b 1))
   (write-byte b output-stream)
   (loop for i from 1 to e3-scale do
	 (write-byte nb output-stream))
   ;;)
     
   (loop for i from (- *arithmetic-with-number-of-bits* 2) downto 0 do
	 (write-byte (logand (ash l (* -1 i)) 1) output-stream)
   ;;(write-byte (logand (ash u (* -1 i)) 1) output-stream)
	 )
   ))

(defun ac-decode-algorithm-v2 (input-stream model)
  (with
   var continue            = t
   var l                   = 0
   var u                   = (1- (expt 2 *arithmetic-with-number-of-bits*))
   var msb                 = (expt 2 (- *arithmetic-with-number-of-bits* 1))
   var msb-1               = (expt 2 (- *arithmetic-with-number-of-bits* 2))   
   var tag                 = 0
   var condition-u-l-equal = nil
   var condition-e3        = nil
   var b                   = 0
   var beof                = 2
   var int-slot            = (byte *arithmetic-with-number-of-bits* 0)
   do
   (loop for i from 0 to (1- *arithmetic-with-number-of-bits*) do
	 (with
	  var byte = (read-byte input-stream nil beof)
	  do
	  (when (eql byte beof)
	    (error "beof 1 received, should never happen!!"))
	  (setf tag (logior (ash tag 1) byte))))
   
   (loop while continue do
	 (with
	  var range = (1+ (- u  l))
	  rec value = (lambda (count) (floor (1- (* (1+ (- tag  l)) count)) range))
	  ;;total-count is the count before inserting the currently decoded symbol into the probability model
	  vars (eof cumulative-count-low cumulative-count-high total-count)
	  =
	  (funcall model #'value)
	  do
	  (setf u (+ l (floor (* range cumulative-count-high) total-count) -1))
	  (setf l (+ l (floor (* range cumulative-count-low)  total-count)))
	  (when eof
	    (setf continue nil)))
	  
	 (loop while (and continue
			  (or (setf condition-u-l-equal (eql (logand l msb) (logand u msb)))
			      (setf condition-e3 (and (eql 0 (logand u msb-1))
						      (not (eql 0 (logand l msb-1)))))))
	       do
	       (setf b (read-byte input-stream nil beof))
	       (if* condition-u-l-equal
		    then
		    (when (eql b beof)
		      (error "beof 2 received, should never happen!!"))
		    (setf l (ldb int-slot (ash l 1)))
		    (setf u (ldb int-slot (logior (ash u 1) 1)))
		    (setf tag (ldb int-slot (logior (ash tag 1) b)))
		     
		    ;;condition-e3
		    else
		    (setf l (ldb int-slot (logxor (ash l 1) msb)))
		    (setf u (ldb int-slot (logxor (logior (ash u 1) 1) msb)))
		    (setf tag (ldb int-slot (logxor (logior (ash tag 1) b) msb)))
		    ))
	 )))

(defun lzw-encode-v1 (input-file-name)
  (with
   var pathname             = (parse-namestring input-file-name)
   var output-file-name     = (make-pathname :directory (pathname-directory pathname)
					     :name (pathname-name pathname)
					     :type "lzw")
   open-file input-stream   = (input-file-name  :direction :input
						:if-does-not-exist :error
						:element-type '(unsigned-byte 8))
   open-file output-stream  = (output-file-name :direction :output :if-exists :supersede :element-type 'bit)
   var max-bit-size         = 14
   var lookup-table         = (adjust-array
			       (make-array 256
					   :element-type 'list
					   :initial-contents (loop for i from 0 to 255 collect (list i))
					   :fill-pointer t
					   :adjustable t)
			       (expt 2 max-bit-size))
   var array-access-pointer = 255;; full ascending
   var index-bit-size       = 9
   var byte                 = nil
   var previous-input       = nil
   var current-input        = nil
   var current-encode-index = 0
   var next-encode-index    = 'NAN
   var hp                   = (handle-progress)
   do
   (loop while (not (eql (setf byte (if byte
					byte
				      (progn
					(with
					 var byte = (read-byte input-stream nil 'eof)
					 do
					 (funcall hp byte)
					 byte))))
			 'eof))
	 do
	 (setf previous-input current-input)
	 (setf current-input (nconc current-input (list byte)))
	 (setf next-encode-index
	       (loop for i from current-encode-index to array-access-pointer thereis
		     (when (equal current-input (aref lookup-table i)) i)))
	 (if* next-encode-index
	      then
	      (setf current-encode-index next-encode-index)
	      (setf byte nil)
	      else
	      (output-to-bitstream output-stream current-encode-index index-bit-size)
	      (when (< (fill-pointer lookup-table) (expt 2 max-bit-size))
		(setf array-access-pointer (fill-pointer lookup-table))
		(incf (fill-pointer lookup-table))
		(setf index-bit-size (1+ (floor (log array-access-pointer 2))))
		(setf (aref lookup-table array-access-pointer)  current-input))
	      (setf current-encode-index 0)
	      (setf byte (car (last current-input)))
	      (setf current-input nil))
	 )
   ;;(format t "lookup-table:~%~A~%" lookup-table)
   (output-to-bitstream output-stream current-encode-index index-bit-size)))

(defun lzw-decode-v1 (file-name)
  (with
   var pathname            = (parse-namestring file-name)
   var input-name          = (make-pathname :directory (pathname-directory pathname)
					    :name (pathname-name pathname)
					    :type "lzw")
   var output-name         = (make-pathname :directory (pathname-directory pathname)
					    :name (format nil "~A-decoded" (pathname-name pathname))
					    :type "txt")
   open-file input-stream  = (input-name  :direction :input :if-does-not-exist :error :element-type 'bit)
   open-file output-stream = (output-name :direction :output :if-exists :supersede :element-type `(unsigned-byte 8)
					  :buffered t)

   var max-bit-size         = 14
   var lookup-table         = (adjust-array
			       (make-array 256
					   :element-type 'list
					   :initial-contents (loop for i from 0 to 255 collect (list i))
					   :fill-pointer t
					   :adjustable t)
			       (expt 2 max-bit-size))
   var array-access-pointer = 255;; full ascending
   var index-bit-size       = 9
   var index                 = nil
   var current-input        = nil
   var previous-input       = nil
   var hp                   = (handle-progress)
   do
   (when (not (eql (setf index (read-from-bitstream input-stream index-bit-size))
		   'eof))
     ;;(format t "index: ~A bit-size: ~A~%" index index-bit-size)
     (setf previous-input (aref lookup-table index))
     (mapc #'(lambda (value) (funcall hp value) (write-byte value output-stream)) previous-input))
   (loop while (not (eql (setf index (read-from-bitstream input-stream index-bit-size))
			 'eof))
	 do
         ;;;(format t "index: ~A bit-size: ~A~%" index index-bit-size)
	 (if (> index array-access-pointer)
	     (setf current-input (append previous-input (list (car previous-input))))
	   (setf current-input (aref lookup-table index)))
	 ;;;(format t "current-input: ~A~%" current-input)
         (mapc #'(lambda (value) (funcall hp value) (write-byte value output-stream)) current-input)
	 (when (< (1+ (fill-pointer lookup-table)) (expt 2 max-bit-size))
	   (setf array-access-pointer (fill-pointer lookup-table))
	   (incf (fill-pointer lookup-table))
	   (setf index-bit-size (1+ (floor (log (1+ array-access-pointer) 2))))
	   (setf (aref lookup-table array-access-pointer) (append previous-input (list (car current-input))))
	   )
         (setf previous-input current-input))
   ;;(format t "lookup-table:~%~A~%" lookup-table)
   ))

(defmethod vector-equal (a b)
  (tree-equal a b))

(defmethod vector-equal ((a vector) (b vector))
  (with
   var len = 0
   do
   (when (eql (setf len (length a))
	      (length b))
     (not (loop for i from 0 to (1- len) thereis (not (vector-equal (aref a i) (aref b i))))))))

(defmethod vector-equal ((a vector) (b list))
  (with
   var len = 0
   do
   (when (eql (setf len (length a))
	      (length b))
     (not (loop for i from 0 to (1- len) thereis (not (vector-equal (aref a i) (nth i b))))))))

(defmethod vector-equal ((a list) (b vector))
  (vector-equal b a))

;;;(in-package :data-compression)
;;;(format t "~16,'0B" u)

;;;(require "repository")
;;;(mk:oos "data-compression" 'compile)
;;;(mk:oos "data-compression" 'load :load-source-instead-of-binary t)
;;;(dc:ac-encode "test-file.txt")
;;;(dc:ac-decode "test-file.txt")

;;;(mon:monitor-form (dc:ac-encode "test-file.txt"))
;;;(mon:monitor-form (dc:ac-decode "test-file.txt"))
;;;(mon:monitor-form (dc:ac-encode "CVSBook.htm"))

;;;(dc:lzw-encode "test-file.txt")
;;;(dc:lzw-decode "test-file.txt")

;;;(setf *print-radix* t)
;;;(setf *print-base* 16)

