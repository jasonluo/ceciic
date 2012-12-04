
(defpackage #:cssb-data-compression.system
  (:use #:cl #:asdf))

(in-package #:cssb-data-compression.system)

(defsystem #:cssb-data-compression
  :serial t
  :depends-on (#:cssb-extlib);;#:metering
  :components ((:file "packages")
	       (:file "data-compression")
               (:file "data-compression-keep")
	       ))
