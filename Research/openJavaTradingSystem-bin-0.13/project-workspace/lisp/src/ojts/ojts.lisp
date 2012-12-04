;;Licensing Terms:
;; This software is Copyright (c) by Christian Schuhegger, 2005/05/26.
;; Christian Schuhegger grants you the rights to distribute
;; and use this software as governed by the terms
;; (http://opensource.franz.com/preamble.html),
;; known as the LLGPL.

;;;(asdf:oos 'asdf:load-op 'ojts) (in-package #:ojts) (functionality.init)
;;;
;;;(dbconfiguration.readxmlconfiguration "/home/cs/tmp/OpenJavaTradingSystem-bin-0.13/testread.xml")
;;;(dbconfiguration.readxmlconfiguration "C:/tmp/ojts/OpenJavaTradingSystem-bin-0.13/testread.xml")
;;;
;;;(dump-wrapper-defs-to-file "/home/cs/project-workspace/lisp/src/ojts/wrapper-defs.lisp" (get-jar-classnames "/home/cs/workspace/OpenJavaTradingSystem/ojts.jar" "net/sf/ojts"))

(asdf:oos 'asdf:load-op 'cssb-extlib)

(in-package #:ojts)

(defun create-portfolio (name currency)
  (with
   var portfolio = (portfolio.new)
   do
   (portfolio.setname portfolio name)
   (portfolio.setdefaultcurrency portfolio currency)
   portfolio))

(defun make-new-initialized-array (type list)
  (with
   var len   = (length list)
   var array = (make-new-array type len)
   do
   (loop for i from 0 below len do
     (setf (jref array i) (jfli::get-ref (nth i list))))
   array))

(defgeneric box (box)
  (:documentation "generic function to box java types")
  (:method (value)
           (typecase value
             (jfli::java-ref (make-typed-ref value))
             (t value))))

(defgeneric unbox (value)
  (:documentation "generic function to unbox java types")
  (:method (value)
           (typecase value
             (jfli::java-ref (unbox (make-typed-ref value)))
             (t value))))

(defmethod unbox ((value |java.lang|:INTEGER.))
  (|java.lang|:INTEGER.INTVALUE (jfli::ref value)))

(defmethod unbox ((value |java.lang|:STRING.))
  (java:jobject-lisp-value (jfli::ref value)))

(defmethod unbox ((value |java.lang|:BOOLEAN.))
  (java:jobject-lisp-value (jfli::ref value)))

(defun array-to-list (array)
  (with
   var len = (jlength array)
   do
   (loop for i from 0 below len collect
     (unbox (jref array i)))
   ))

;;;(array-to-list (make-new-initialized-array :int '(1 2 3 4)))
;;;(array-to-list (make-new-initialized-array "java.lang.String" '("a" "b" "c")))

(defun reorder-sequence-base (constructor sequence new-order)
  (with
   var new-order-as-int-array = (make-new-initialized-array :int new-order)
   var result                 = (funcall constructor sequence new-order-as-int-array)
   do
   result))

(defun reorder-sequence (sequence new-order)
  (reorder-sequence-base #'reorder.new sequence new-order))

(defun reorderresult-sequence (sequence new-order)
  (reorder-sequence-base #'reorderresult.new sequence new-order))

(defun enumerate-all-package-symbols ()
  (with
   var all-packages = (list-all-packages)
   do
   (with-package-iterator (generator-fn all-packages :internal :external);; :inherited
     (loop     
       (multiple-value-bind (more? symbol accessibility pkg) 
           (generator-fn) 
         (unless more? (return))
         (when (equal (string symbol) "PRINT-OBJECT")
           (format t "symbol: ~A; package: ~A~%" symbol pkg))
         )))))
  
(defmethod common-lisp:print-object ((obj |java.lang|:OBJECT.) stream)
  (format stream "JavaObject (~A):~%~A~%" (type-of obj) (|java.lang|:OBJECT.TOSTRING obj)))

(defmethod common-lisp:print-object ((obj |net.sf.ojts.math.types|:DOUBLESEQUENCE.) stream)
  (format stream "DoubleSequence:~%~A~%" (|java.lang|:OBJECT.TOSTRING obj)))

(defmethod common-lisp:print-object ((obj |net.sf.ojts.math.types|:DOUBLESEQUENCERESULT.) stream)
  (format stream "DoubleSequenceResult:~%~A~%" (|java.lang|:OBJECT.TOSTRING obj)))

;;;(mop::collect-superclasses* (mop::find-class 'VALUESPERDATECONTAINER$VPDDOUBLESEQUENCE.))

(defun test10-setup-portfolio ()
  (with
   var cur-eur               = (dbaccessconvenience.getunit "EUR")
   var cur-usd               = (dbaccessconvenience.getunit "USD")
   
   var ds-yahoo-csv          = (dbaccessconvenience.getdatasource "yahoo-csv")
   var ds-onvista-htmlshare  = (dbaccessconvenience.getdatasource "onvista-htmlshare")
   var ds-onvista-htmlfund   = (dbaccessconvenience.getdatasource "onvista-htmlfund")
   
   var mt-xetra              = (dbaccessconvenience.getmarketplace "XETRA")
   var mt-undefined          = (dbaccessconvenience.getmarketplace "UNDEFINED")
   
   var sp-share-DE0007500001 = (dbaccessconvenience.getsecuritypaper "DE0007500001")
   var sp-share-EE0000001063 = (dbaccessconvenience.getsecuritypaper "EE0000001063")
   var sp-fund-LU0029865408  = (dbaccessconvenience.getsecuritypaper "LU0029865408")
   
   var portfolio             = (create-portfolio "testportfolio" cur-eur)
   
   var pn-DE0007500001       = (fnportfolio.createposition ds-yahoo-csv         (dateutil.parsedate "2004-05-20") sp-share-DE0007500001 mt-xetra     29 13.39 10   cur-eur)
   var pn-EE0000001063       = (fnportfolio.createposition ds-onvista-htmlshare (dateutil.parsedate "2004-10-01") sp-share-EE0000001063 mt-undefined 50  7.30 10.5 cur-eur)
   var pn-LU0029865408       = (fnportfolio.createposition ds-onvista-htmlfund  (dateutil.parsedate "2004-09-01") sp-fund-LU0029865408  mt-undefined 40 21.71 10.5 cur-usd)
   do
   (portfolio.addposition portfolio pn-DE0007500001)
   (portfolio.addposition portfolio pn-EE0000001063)
   (portfolio.addposition portfolio pn-LU0029865408)
   
   (fnportfolio.sellposition pn-DE0007500001 (dateutil.parsedate "2004-12-20") mt-xetra 16.05 10.0 cur-eur)
   
   portfolio))
   
(defun display-chart (chart-name data-sequence sequence-names &key (autorange-includes-zero-p t) (is-x-date-axis-p nil))
  (with
   var sequence-names = (make-new-initialized-array "java.lang.String" sequence-names)
   var chart          = (dataanalysisandvisualisation.createXYChart chart-name data-sequence sequence-names autorange-includes-zero-p is-x-date-axis-p)
   do
   (dataanalysisandvisualisation.displaychart chart)))

(defun test10 ()
  (with
   var portfolio                       = (test10-setup-portfolio)
   var values-per-date-container       = (fnportfolio.getclosingpricesforportfolio portfolio 2)
   var sequence                        = (valuesperdatecontainer$vpddoublesequence.new values-per-date-container 5 (1+ (jlength (collection.toarray (portfolio.getpositions portfolio)))))
   do
   (aggregatefunctions.continoussum sequence 2 sequence 3)
   (aggregatefunctions.movingaverage (reorder-sequence sequence '(2)) (reorderresult-sequence sequence '(4)) 20)
   (display-chart "ChartName1"  sequence                          '("Portfolio Absolute") :autorange-includes-zero-p t :is-x-date-axis-p t)
   (display-chart "ChartName2" (reorder-sequence sequence '(2 3)) '("log" "sumlog")       :autorange-includes-zero-p t :is-x-date-axis-p nil)
   (box values-per-date-container)
   ))



;;;(setf test (test10))
;;;(jlength (collection.toarray (portfolio.getpositions test)))
;;;(setf test1 (fnportfolio.getclosingpricesforportfolio test 2))
;;;(setf test2 (valuesperdatecontainer$vpddoublesequence.new test1 5 (1+ (jlength (collection.toarray (portfolio.getpositions test))))))