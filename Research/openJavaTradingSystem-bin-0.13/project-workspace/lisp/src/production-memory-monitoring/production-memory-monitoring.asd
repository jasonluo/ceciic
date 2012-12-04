(defpackage #:production-memory-monitoring.system
  (:use #:cl #:asdf))

(in-package #:production-memory-monitoring.system)

(defsystem #:production-memory-monitoring
  :depends-on (#:cssb-extlib)
  :serial     t
  :components ((:file "packages")
	       (:file "production-memory-monitoring")
	       ))
