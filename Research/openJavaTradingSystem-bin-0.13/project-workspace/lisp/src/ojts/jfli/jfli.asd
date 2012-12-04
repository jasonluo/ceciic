(defpackage #:jfli.system
  (:use #:cl #:asdf))

(in-package #:jfli.system)

(defsystem #:jfli
    :serial     t
    :components ((:file "packages")
		 (:file "jfli-abcl")
		 ))
