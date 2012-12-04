;;Licensing Terms:
;; This software is Copyright (c) by Christian Schuhegger, 2003/07/10.
;; Christian Schuhegger grants you the rights to distribute
;; and use this software as governed by the terms
;; of the Lisp Lesser GNU Public License
;; (http://opensource.franz.com/preamble.html),
;; known as the LLGPL.


(in-package #:cssb-data-compression :nicknames '("CSSB.DATA-COMPRESSION"))

(eval-when (compile load eval)
  (defvar *symbol-size-in-bits*             8) ;; use bytes as the symbols to encode
  (defvar *arithmetic-with-number-of-bits* 64)
  (defvar *debug-arithmetic-coding*       nil)

  (defvar *special-exports* nil)
  (defvar *exports* nil)
  (defvar *other-exports* nil)
  )
  
;;;(defmacro with-debug-output ((debug-wanted) &body b)
;;;  (when (eval debug-wanted)
;;;    `(progn ,@b)))

(defmacro with-debug-output ((debug-wanted) &body b)
  `(when ,debug-wanted ,@b))

(defun handle-progress ()
  (with
   var bytecount   = 0
   var bcovcount   = 0
   var bclimit     = 10000
   var percent     = 'N/A
   do
   (lambda (character)
     (declare (ignorable character))
     (incf bytecount)
     (when (eql bytecount bclimit)
       (setf bytecount 0)
       (incf bcovcount)
       (format t "Processed ~A bytes~%" (* bclimit bcovcount)))
     (values (+ (* bclimit bcovcount) bytecount) percent))))

(defun ac-cum-count (p-table byte)
  (with
   var i = 0
   var sum-2
   =
   (+ (loop while (not (eql (car (svref p-table i)) byte)) sum
	    (progn
	      (incf i)
	      (cdr (svref p-table i))))
      (cdr (svref p-table 0)))
   var sum-1 = (- sum-2 (cdr (svref p-table i)))
   do
   (with-debug-output
    (*debug-arithmetic-coding*)
    (format t "cum-count-1: ~A, cum-count-2: ~A~%" sum-1 sum-2)
    ;;(break)
    )
   (p-table-incf-bottom p-table i)
   (values sum-1 sum-2)))
	
(defun mk-p-table (count)
  (make-array count :element-type 'integer :initial-contents 
	      (loop for i from 0 to (1- count) collect
		    (cons i 1))))

(defun p-table-incf-bottom (p-table i)
  (with
   var tmp   = (svref p-table i)
   var value = (incf (cdr tmp))
   var j     = (1- i)
   do
   (loop while (and (>= j 0)
		    (> value (cdr (svref p-table j))))
	 do
	 (setf (svref p-table (1+ j))
	       (svref p-table j))
	 (decf j))
   (setf (svref p-table (1+ j)) tmp)
   value))

(defun p-table-decode-value (p-table value)
  (with
   var k           = 0
   var cum-count-1 = 0
   var cum-count-2 = (cdr (svref p-table k))
   var r-value     = 0
   do
   (loop while (>= value cum-count-2) do
	 (progn
	   (incf k)
	   (setf cum-count-1 cum-count-2)
	   (setf cum-count-2 (+ cum-count-2 (cdr (svref p-table k))))))
   (setf r-value (car (svref p-table k)))
   (with-debug-output
    (*debug-arithmetic-coding*)
    (format t "cum-count-1: ~A, cum-count-2: ~A~%" cum-count-1 cum-count-2)
    ;;(break)
    )
   (p-table-incf-bottom p-table k)
   (values cum-count-1 cum-count-2 r-value)))
   
(defun ac-encode (input-file-name)
  (with
   var *debug-arithmetic-coding* = nil
   declare (special *debug-arithmetic-coding*)
   var pathname            = (parse-namestring input-file-name)
   var output-file-name    = (make-pathname :directory (pathname-directory pathname)
					    :name (pathname-name pathname)
					    :type "ac");;(pathname-type pathname)
   open-file input-stream  = (input-file-name  :direction :input
					       :if-does-not-exist :error
					       :element-type `(unsigned-byte ,*symbol-size-in-bits*))
   open-file output-stream = (output-file-name :direction :output :if-exists :supersede :element-type 'bit)
   do
   (ac-encode-algorithm output-stream (ac-en-model input-stream))))

(defun ac-en-model (input-stream)
  (with
   ;; last symbol in p-table is the eof symbol
   var eof         = (expt 2 *symbol-size-in-bits*)
   var total-count = (+ eof 1)
   var p-table     = (mk-p-table total-count)
   var hp          = (handle-progress)
   do
   (lambda ()
     (with
      var byte          = (read-byte input-stream nil eof)
      vars (cumulative-count-low cumulative-count-high)
      =
      (ac-cum-count p-table byte)
      var r-total-count = total-count
      var bc            = (funcall hp byte)
      declare (ignorable bc)
      do
      ;;(when (>= bc 58766) (setf *debug-arithmetic-coding* t))
      (incf total-count)
      (values (eql byte eof) cumulative-count-low cumulative-count-high r-total-count)))))

(defun ac-encode-algorithm-rec (output-stream
				model
				l u e3-scale
				eof)
  (with
   var msb                 = (expt 2 (- *arithmetic-with-number-of-bits* 1))
   var msb-1               = (expt 2 (- *arithmetic-with-number-of-bits* 2))
   var int-slot            = (byte *arithmetic-with-number-of-bits* 0)
   do
   (if* (eql (logand l msb) (logand u msb));;condition u, l high-bit equal
	then
	(with
	 var b  = (logand (ash l (* -1 (1- *arithmetic-with-number-of-bits*))) 1)
	 var nb = (logxor b 1)
	 do
	 (write-byte b output-stream)
	 (loop for i from 1 to e3-scale do
	       (write-byte nb output-stream))
	 (ac-encode-algorithm-rec output-stream model
				  (ldb int-slot (ash l 1))
				  (ldb int-slot (logior (ash u 1) 1))
				  0
				  eof))
	elseif (and (eql 0 (logand u msb-1)) (not (eql 0 (logand l msb-1))));; condition e3, need rescale
	then
	(ac-encode-algorithm-rec output-stream model
				 (ldb int-slot (logxor (ash l 1) msb))
				 (ldb int-slot (logxor (logior (ash u 1) 1) msb))
				 (1+ e3-scale)
				 eof)
	else
	(if eof
	    (with
	     var b  = (logand (ash l (* -1 (1- *arithmetic-with-number-of-bits*))) 1)
	     var nb = (logxor b 1)
	     do
	     (write-byte b output-stream)
	     (loop for i from 1 to e3-scale do
		   (write-byte nb output-stream))
	     (loop for i from (- *arithmetic-with-number-of-bits* 2) downto 0 do
		   (write-byte (logand (ash l (* -1 i)) 1) output-stream)))
	  (with
	   var  range                                                        = (1+ (- u  l))
	   vars (eof cumulative-count-low cumulative-count-high total-count) = (funcall model)
	   do
	   (ac-encode-algorithm-rec output-stream model
				    (+ l (floor (* range cumulative-count-low)
						total-count))
				    (+ l (floor (* range cumulative-count-high)
						total-count) -1)
				    e3-scale
				    eof))))))

(defun ac-encode-algorithm (output-stream model)
  (declare (optimize (speed 3)));;(safety 0) (space 0) (debug 0) 
  (ac-encode-algorithm-rec output-stream model
			   0 (1- (expt 2 *arithmetic-with-number-of-bits*)) 0 nil))

(defun ac-decode (file-name)
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
					  :buffered t)
   do
   (ac-decode-algorithm input-stream (ac-de-model output-stream))))

(defun ac-de-model (output-stream)
  (with
   ;; last symbol in p-table is the eof symbol
   var eof         = (expt 2 *symbol-size-in-bits*)
   var total-count = (+ eof 1)
   var p-table     = (mk-p-table total-count)
   var hp          = (handle-progress)
   do
   (lambda (fn-value)
     (with
      var value = (funcall fn-value total-count)
      vars (cumulative-count-low cumulative-count-high byte)
      =
      (p-table-decode-value p-table value)
      var r-total-count = total-count
      var bc            = (funcall hp byte)
      declare (ignorable bc)
      do
      (incf total-count)
      (when (not (eql byte eof))
	(write-byte byte output-stream))
      ;;(when (>= bc 58766) (break))
      (values (eql byte eof) cumulative-count-low cumulative-count-high r-total-count)))))

(defun ac-decode-algorithm-rec (input-stream
				model
				l u tag
				eof)
  (with
   var msb                 = (expt 2 (- *arithmetic-with-number-of-bits* 1))
   var msb-1               = (expt 2 (- *arithmetic-with-number-of-bits* 2))
   var int-slot            = (byte *arithmetic-with-number-of-bits* 0)
   do
   (if* (eql (logand l msb) (logand u msb));;condition u, l high-bit equal
	then
	(with
	 var b = (read-byte input-stream nil 'beof)
	 do
	 (when (eql b 'beof)
	   (error "beof 2 received, should never happen!1!"))
	 (ac-decode-algorithm-rec input-stream
				  model
				  (ldb int-slot (ash l 1))
				  (ldb int-slot (logior (ash u 1) 1))
				  (ldb int-slot (logior (ash tag 1) b))
				  eof))
	elseif (and (eql 0 (logand u msb-1)) (not (eql 0 (logand l msb-1))));; condition e3, need rescale
	then
	(with
	 var b = (read-byte input-stream nil 'beof)
	 do
	 (when (eql b 'beof)
	   (error "beof 2 received, should never happen!2!"))
	 (ac-decode-algorithm-rec input-stream
				  model
				  (ldb int-slot (logxor (ash l 1) msb))
				  (ldb int-slot (logxor (logior (ash u 1) 1) msb))
				  (ldb int-slot (logxor (logior (ash tag 1) b) msb))
				  eof))
	else
	(when (not eof)
	  (with
	   var range = (1+ (- u  l))
	   rec value = (lambda (count) (floor (1- (* (1+ (- tag  l)) count)) range))
	   ;;total-count is the count before inserting the currently decoded symbol into the probability model
	   vars (eof cumulative-count-low cumulative-count-high total-count)
	   =
	   (funcall model #'value)
	   do
	   (ac-decode-algorithm-rec input-stream
				    model
				    (+ l (floor (* range cumulative-count-low)  total-count))
				    (+ l (floor (* range cumulative-count-high) total-count) -1)
				    tag
				    eof))))))
  
(defun ac-decode-algorithm (input-stream model)
  (ac-decode-algorithm-rec input-stream model
			   0 (1- (expt 2 *arithmetic-with-number-of-bits*))
			   (apply #'logior
				  (loop for i from (1- *arithmetic-with-number-of-bits*) downto 0 collect
					(with
					 var byte = (read-byte input-stream nil 'beof)
					 do
					 (when (eql byte 'beof)
					   (error "beof received, should never happen!0!"))
					 (ash byte i))))
			   nil))

(defvar *lzw-max-bit-size* 16)

(defclass lzw-encode-interface ()
  ((find-index-for-input  :accessor lzw-encode-interface-find-index-for-input
                          :initform nil
                          :initarg  :find-index-for-input)
   (add-new-input         :accessor lzw-encode-interface-add-new-input
                          :initform nil
                          :initarg  :add-new-input)
   (index-bit-size        :accessor lzw-encode-interface-index-bit-size
                          :initform 9
                          :initarg  :index-bit-size)
   (previous-encode-index :accessor lzw-encode-interface-previous-encode-index
                          :initform nil
                          :initarg  :previous-encode-index)))

(defclass default-lzw-encode-interface (lzw-encode-interface)
  ((lookup-table :accessor default-lzw-encode-interface-lookup-table
                 :initform (adjust-array
                            (make-array 257
                                        :element-type 'list
                                        :initial-contents (nconc (list nil) (loop for i from 0 to 255 collect (list i)))
                                        :fill-pointer t
                                        :adjustable t)
                            (expt 2 *lzw-max-bit-size*))
                 :initarg :lookup-table)
   (array-access-pointer :accessor default-lzw-encode-interface-array-access-pointer
                         :initform 256
                         :initarg  :array-access-pointer)
   (current-input        :accessor default-lzw-encode-interface-current-input
                         :initform nil
                         :initarg  :current-input)
   ))

(defun lzw-create-default-lzw-encode-interface ()
  (with
   var default-lzw-encode-interface = (make-instance 'default-lzw-encode-interface)
   slots (find-index-for-input add-new-input index-bit-size previous-encode-index lookup-table array-access-pointer current-input)
   in default-lzw-encode-interface
   do
   (setf find-index-for-input (lambda (current-encode-index byte)
                                (setf current-input (nconc (list byte) (aref lookup-table current-encode-index)))
                                (setf previous-encode-index current-encode-index)
                                (loop for i from previous-encode-index to array-access-pointer thereis
                                      (when (equal current-input (aref lookup-table i)) i))))
   (setf add-new-input (lambda ()
                         (setf previous-encode-index (if (eql (car current-input) 'eof) 'eof (1+ (car current-input))))
                         (when (< (fill-pointer lookup-table) (expt 2 *lzw-max-bit-size*))
                           (incf array-access-pointer)
                           (incf (fill-pointer lookup-table))
                           (setf index-bit-size (1+ (floor (log array-access-pointer 2))))
                           (setf (aref lookup-table array-access-pointer) current-input))
                         ))
   default-lzw-encode-interface
   ))

(defclass hashtable-lzw-encode-interface (lzw-encode-interface)
  ((hashtable    :accessor hashtable-lzw-encode-interface-hashtable
                 :initform (make-hash-table :test #'equal :size (expt 2 *lzw-max-bit-size*)))
   (array-access-pointer :accessor hashtable-lzw-encode-interface-array-access-pointer
                         :initform 256
                         :initarg  :array-access-pointer)
   (current-input        :accessor hashtable-lzw-encode-interface-current-input
                         :initform nil
                         :initarg  :current-input)
   ))

(defun lzw-create-hashtable-lzw-encode-interface ()
  (with
   var hashtable-lzw-encode-interface = (make-instance 'hashtable-lzw-encode-interface)
   slots (find-index-for-input add-new-input index-bit-size previous-encode-index hashtable array-access-pointer current-input)
   in hashtable-lzw-encode-interface
   do
   (setf (gethash nil hashtable) (cons 0 nil))
   (loop for i from 0 to 255 do
         (setf (gethash (list i) hashtable) (cons (1+ i) (list i))))
   (setf find-index-for-input (lambda (current-encode-index byte)
                                ;;(format t "find-index-for-input: current-encode-index: ~A, byte: ~A~%" current-encode-index byte)
                                (setf current-input (nconc (list byte) current-input))
                                (setf previous-encode-index current-encode-index)
                                (car (gethash current-input hashtable))))
   (setf add-new-input (lambda ()
                         (setf previous-encode-index (if (eql (car current-input) 'eof) 'eof (1+ (car current-input))))
                         (when (< (1+ array-access-pointer) (expt 2 *lzw-max-bit-size*))
                           (incf array-access-pointer)
                           (setf index-bit-size (1+ (floor (log array-access-pointer 2))))
                           (setf (gethash current-input hashtable) (cons array-access-pointer current-input)))
                         (setf current-input (cdr (gethash (list (car current-input)) hashtable)))
                         ;;(format t "add-new-input: previous-encode-index: ~A, current-input: ~A~%" previous-encode-index current-input)
                         ))
   hashtable-lzw-encode-interface
   ))

(defun lzw-encode (input-file-name)
  (with
   var pathname             = (parse-namestring input-file-name)
   var output-file-name     = (make-pathname :directory (pathname-directory pathname)
					     :name (pathname-name pathname)
					     :type "lzw")
   open-file input-stream   = (input-file-name  :direction :input
						:if-does-not-exist :error
						:element-type '(unsigned-byte 8))
   open-file output-stream  = (output-file-name :direction :output :if-exists :supersede :element-type 'bit)
   ;;var lzw-interface        = (lzw-create-default-lzw-encode-interface)
   var lzw-interface        = (lzw-create-hashtable-lzw-encode-interface)
   var byte                 = (read-byte input-stream nil 'eof)
   var hp                   = (handle-progress)
   do
   (when (not (eql byte 'eof))
     (lzw-encode-rec input-stream output-stream
                     lzw-interface
                     (with var byte = (read-byte input-stream nil 'eof) do (funcall hp byte) byte)
                     (funcall (lzw-encode-interface-find-index-for-input lzw-interface) 0 byte)
                     hp))
   ;;(break)
   ))
   

(defun lzw-encode-rec (input-stream output-stream
                                    lzw-interface
                                    byte
                                    current-encode-index
                                    hp)
  (when (not (eql current-encode-index 'eof))
    (if* current-encode-index
         then
         (lzw-encode-rec input-stream output-stream
                         lzw-interface
                         (with var byte = (read-byte input-stream nil 'eof) do (funcall hp byte) byte)
                         (funcall (lzw-encode-interface-find-index-for-input lzw-interface) current-encode-index byte)
                         hp)
         else
         (output-to-bitstream output-stream
                              (lzw-encode-interface-previous-encode-index lzw-interface)
                              (lzw-encode-interface-index-bit-size lzw-interface))
         ;;this call changes previous-encode-index to point to the character(s) not yet treated.
         (funcall (lzw-encode-interface-add-new-input lzw-interface))
         (lzw-encode-rec input-stream output-stream
                         lzw-interface
                         byte
                         (lzw-encode-interface-previous-encode-index lzw-interface)
                         hp)
         )
    ))
   
(defclass lzw-decode-interface ()
  ((find-input-for-index :accessor lzw-decode-interface-find-input-for-index
                         :initform nil
                         :initarg  :find-input-for-index)
   (add-new-input        :accessor lzw-decode-interface-add-new-input
                         :initform nil
                         :initarg  :add-new-input)
   (index-bit-size       :accessor lzw-decode-interface-index-bit-size
                         :initform 9
                         :initarg  :index-bit-size)
   ))

(defclass default-lzw-decode-interface (lzw-decode-interface)
  ((lookup-table :accessor default-lzw-decode-interface-lookup-table
                 :initform (adjust-array
                            (make-array 257
                                        :element-type 'list
                                        :initial-contents (nconc (list nil) (loop for i from 0 to 255 collect (list i)))
                                        :fill-pointer t
                                        :adjustable t)
                            (expt 2 *lzw-max-bit-size*))
                 :initarg :lookup-table)
   (array-access-pointer :accessor default-lzw-decode-interface-array-access-pointer
                         :initform 256
                         :initarg  :array-access-pointer)
   (current-input        :accessor default-lzw-decode-interface-current-input
                         :initform nil
                         :initarg  :current-input)
   (previous-input       :accessor default-lzw-decode-interface-previous-input
                         :initform nil
                         :initarg  :previous-input)))

(defun lzw-create-default-lzw-decode-interface ()
  (with
   var default-lzw-decode-interface = (make-instance 'default-lzw-decode-interface)
   slots (find-input-for-index add-new-input index-bit-size lookup-table array-access-pointer current-input previous-input)
   in default-lzw-decode-interface
   do
   (setf find-input-for-index (lambda (index)
                                (setf previous-input current-input)
                                (setf current-input (if (> index array-access-pointer)
                                                        (nconc (list (car (last previous-input))) previous-input)
                                                      (aref lookup-table index)))
                                (reverse current-input)))
   (setf add-new-input (lambda ()
                         (when (and (< (1+ (fill-pointer lookup-table)) (expt 2 *lzw-max-bit-size*))
                                    previous-input)
                           (incf (fill-pointer lookup-table))
                           (incf array-access-pointer)
                           (setf index-bit-size (1+ (floor (log (1+ array-access-pointer) 2))))
                           (setf (aref lookup-table array-access-pointer) (nconc (list (car (last current-input))) previous-input)))))
   default-lzw-decode-interface
   ))

(defun lzw-decode (file-name)
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
   var index                = (read-from-bitstream input-stream 9)
   var hp                   = (handle-progress)
   do
   (when (not (eql index 'eof))
     (lzw-decode-rec input-stream output-stream
                     (lzw-create-default-lzw-decode-interface)
                     index
                     hp))))

(defun lzw-decode-rec (input-stream output-stream
                                    lzw-interface
                                    index
                                    hp)
  (when (not (eql index 'eof))
    (with
     var plain-text = (funcall (lzw-decode-interface-find-input-for-index lzw-interface) index)
     do
     (mapc #'(lambda (value) (funcall hp value) (write-byte value output-stream)) plain-text))
    (funcall (lzw-decode-interface-add-new-input lzw-interface))
    (lzw-decode-rec input-stream output-stream
                    lzw-interface
                    (read-from-bitstream input-stream (lzw-decode-interface-index-bit-size lzw-interface))
                    hp)))

(defmethod output-to-bitstream ((output-stream stream) (value integer) (bit-size integer))
  ;;(format t "output-to-bitstream: bit-size: ~A, value: ~A~%" bit-size value)
  (loop for i from 0 to (1- bit-size) do
	(write-byte (logand (ash value (* -1 i)) 1) output-stream)))

(defmethod read-from-bitstream ((input-stream stream) (bit-size integer))
  (with
   var value = 0
   var debug = nil
   do
   (loop for i from 0 to (1- bit-size) do
         (with
          var bit = (read-byte input-stream nil 'eof)
          do
          (when debug (break))
          (when (eql bit 'eof)
            (if (> i 0)
                (error "output-to-bitstream: eof received in the middle of a value!")
              (progn
                (setf value 'eof)
                (return))))
          (setf value (logior (ash bit i) value))))
   value))

;;;(asdf:oos 'asdf:load-op        'cssb-data-compression)
;;;(asdf:oos 'asdf:load-source-op 'cssb-data-compression)


;;;(in-package #:cssb-data-compression)
;;;(format t "~16,'0B" u)

;;;(dc:ac-encode "test-file.txt")
;;;(dc:ac-decode "test-file.txt")

;;;(asdf:oos 'asdf:load-op 'metering)
;;;(mon:monitor-form (dc:ac-encode "test-file.txt"))
;;;(mon:monitor-form (dc:ac-decode "test-file.txt"))
;;;(mon:monitor-form (dc:ac-encode "CVSBook.htm"))

;;;(dc:lzw-encode "test-file.txt")
;;;(dc:lzw-decode "test-file.txt")

;;;(setf *print-radix* t)
;;;(setf *print-base* 16)

