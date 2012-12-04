(defpackage #:cssb-util.system
  (:use #:cl #:asdf))

(in-package #:cssb-util.system)

(defsystem #:cssb-util
  :depends-on (#:cssb-extlib)
  :components ((:file "packages")
	       (:file "base")
	       (:file "avltree")
	       ))
