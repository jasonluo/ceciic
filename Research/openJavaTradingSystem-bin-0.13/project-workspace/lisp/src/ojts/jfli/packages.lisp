(in-package :cl-user)

(defpackage :jfli
  (:use :common-lisp :java)
  (:export

   :enable-java-proxies

   ;wrapper generation
   :def-java-class
   :get-jar-classnames
   :dump-wrapper-defs-to-file

   ;object creation etc
   :find-java-class
   :new
   :make-new
   :make-typed-ref
   :jeq

   ;array support
   :make-new-array
   :jlength
   :jref
   :jref-boolean
   :jref-byte
   :jref-char
   :jref-double
   :jref-float
   :jref-int
   :jref-short
   :jref-long

   ;proxy support
   :new-proxy
   :unregister-proxy

   ;conversions
   :box-boolean
   :box-byte
   :box-char
   :box-double
   :box-float
   :box-integer
   :box-long
   :box-short
   :box-string
   :unbox-boolean
   :unbox-byte
   :unbox-char
   :unbox-double
   :unbox-float
   :unbox-integer
   :unbox-long
   :unbox-short
   :unbox-string

;   :ensure-package
;   :member-symbol
;   :class-symbol
;   :constructor-symbol

   :*null*
   :new-class
   :super
   ))
