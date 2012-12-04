(defpackage #:solitaer.system
  (:use #:cl #:asdf))

(in-package #:solitaer.system)

(defsystem #:solitaer
  :depends-on (#:cssb-extlib)
  :serial     t
  :components ((:file "packages")
	       (:file "solitaer")
	       ))
