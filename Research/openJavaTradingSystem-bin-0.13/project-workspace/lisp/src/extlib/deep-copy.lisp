
(in-package :common-lisp-user)

(defvar *deep-copy-all-objects* (make-hash-table))

(defun deep-copy (obj)
  (declare (special *deep-copy-all-objects*))
  (let ((*deep-copy-all-objects* (make-hash-table)))
    (deep-copy* obj)))

(defun deep-copy* (obj)
  (cond ((gethash obj *deep-copy-all-objects*))
	(t (let ((empty (empty-copy obj)))
	     (setf (gethash obj *deep-copy-all-objects*) empty) 
	     (copy-object obj empty)
	     empty))))

(defmethod empty-copy ((obj t))
  (error "No empty-copy method defined for ~A" (class-name (class-of obj))))

(defmethod copy-object (from to)
  (error "No copy-object method defined for (~A,~A)" (class-name (class-of from)) (class-name (class-of to))))
  
;; special cases -------------------

(defmethod empty-copy ((s symbol)) (copy-symbol s))
(defmethod copy-object ((from symbol) to)
  (declare (ignorable from to)))

(defmethod empty-copy ((n number)) n)
(defmethod copy-object ((from number) to)
  (declare (ignorable from to)))

(defmethod empty-copy ((x null)) x)
(defmethod copy-object ((from null) to)
  (declare (ignorable from to)))  

(defmethod empty-copy ((c cons)) (cons nil nil))
(defmethod copy-object ((from cons) to)
  (setf (car to)(deep-copy* (car from))
	(cdr to)(deep-copy* (cdr from))))

(defmethod empty-copy ((s string)) (map 'string #'identity s))
(defmethod copy-object ((from string) to)
  (declare (ignorable from to)))


(defmethod empty-copy ((c character)) c)
(defmethod copy-object ((from character) to) to)

(defmethod empty-copy ((h hash-table))
  (make-hash-table :test (hash-table-test h)
		   :size (hash-table-size h)
		   :rehash-size (hash-table-rehash-size h)
		   :rehash-threshold (hash-table-rehash-threshold h)))
(defmethod copy-object ((from hash-table) to)
  (maphash (lambda (key value)
	     (setf (gethash (deep-copy* key) to) 
	       (deep-copy* value)))
	   from))
		   
(defmethod empty-copy ((a array)) 
  (multiple-value-bind (other index)(array-displacement a)
    (when other (setq other (deep-copy* other)))
    (make-array (array-dimensions a)
		:displaced-to other
		:displaced-index-offset index
		:element-type (array-element-type a)
		:adjustable (adjustable-array-p a)
		:fill-pointer (array-has-fill-pointer-p a))))

(defmethod copy-object ((from array) to)
  (unless (array-displacement from)
    (dotimes (i (array-total-size from))
      (setf (row-major-aref to i)
	(deep-copy* (row-major-aref from i))))))

(defmethod empty-copy ((a standard-object))
  (make-instance (class-of a)))

(defmethod empty-copy ((a structure-object))
  (apply (intern (format nil "COPY-~a" (class-name (class-of a)))) a nil))

#+(and clisp (not pcl))
(progn
					;(defun allocate-instance (class)
					;  (clos::allocate-std-instance 
					;   class (length (class-slots class))))
  (defun class-slots (class)
    (clos::class-slots class))
  (defun class-precedence-list (class)
    (clos::class-precedence-list class))
  (defun class-direct-superclasses (class)
    (clos::class-direct-superclasses class))     
  (defun slot-definition-name (slot)
    (clos::slotdef-name slot))
  (defun slot-definition-initargs (slot)
    (clos::slotdef-initargs slot))
  (defun slot-definition-initform (slot)
    (let ((? (clos::slotdef-initer slot)))
      (and ? (if (car ?) (funcall (car ?)) (cdr ?)))))
  (defun slot-definition-readers (slot)
    (declare (ignore slot))
    (error "slot-definition-readers undefined in CLISP"))
  (defun slot-definition-writers (slot) 
    (declare (ignore slot))
    (error "slot-definition-writers undefined in CLISP"))
  (defmacro slot-value-using-class (class object slot)
    (declare (ignore class))
    `(slot-value ,object (slot-definition-name ,slot)))
  (defun slot-boundp-using-class (class object slot)
    (declare (ignore class))
    (slot-boundp object (slot-definition-name slot)))
  (defun slot-makunbound-using-class (class object slot)
    (declare (ignore class))
    (slot-makunbound object (slot-definition-name slot)))
  ;;(defun change-class (object new-class)
  ;;  (declare (ignore object new-class))
  ;;  (error "CLISP CLOS does not implement change-class."))
  ;;(defvar %clisp-prototypes% (make-hash-table))
  ;;(defun class-prototype (class)
  ;;  (or (gethash class %clisp-prototypes% )
  ;;      (setf (gethash class %clisp-prototypes% )
  ;;            (clos::allocate-std-instance
  ;;            class (length (class-slots class))))))
  )

(defmethod copy-object ((from standard-object) to)
  (let ((meta (class-of from)))
    (dolist (slot (class-slots meta));;(clos:class-slots meta));;(port:class-slot-list meta))
      (when (slot-boundp-using-class meta from slot)
	(setf (slot-value-using-class meta to slot)
	      (deep-copy* 
	       (slot-value-using-class meta from slot)))))))
			  
(defmethod copy-object ((from structure-object) to)
  (let ((meta (class-of from)))
    (dolist (slot (class-slots meta))
      (setf (slot-value-using-class meta to slot)
	    (deep-copy* 
	     (slot-value-using-class meta from slot))))))
			  
(defmethod empty-copy ((a random-state)) (make-random-state a))
(defmethod copy-object ((from random-state) to)
  (declare (ignorable from to)))

(defmethod empty-copy ((a readtable)) (copy-readtable a))
(defmethod copy-object ((from readtable) to)
  (declare (ignorable from to)))

