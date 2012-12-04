
(defpackage #:cssb-extlib.system
  (:use #:cl #:asdf))

(in-package #:cssb-extlib.system)

(defsystem #:cssb-extlib
  :serial t
  :components ((:file "with")
	       (:file "ifstar")
               (:file "pregexp")
	       ;;(:file "clos-impl")
	       (:file "deep-copy")
	))
