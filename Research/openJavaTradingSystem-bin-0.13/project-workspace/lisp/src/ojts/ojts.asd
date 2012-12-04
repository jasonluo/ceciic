(defpackage #:ojts.system
  (:use #:cl #:asdf))

(in-package #:ojts.system)

(defsystem #:ojts-wrapper
    :depends-on (#:jfli)
    :serial     t
    :components ((:file "packages.gen")
		 (:file "wrapper-defs-std")
		 (:file "wrapper-defs")
		 ))

(defsystem #:ojts
    :depends-on (#:ojts-wrapper #:cssb-extlib)
    :serial     t
    :components ((:file "packages")
		 (:file "ojts")
		 ))
