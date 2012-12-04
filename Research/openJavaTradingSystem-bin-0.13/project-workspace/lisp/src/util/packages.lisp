
(in-package :cl-user)

(defpackage #:cssb-util
  (:nicknames "CSSB.UTIL")
  (:use #:cl #:cl-user)
  (:import-from #:cl-user with if*)
  (:export #:interleave
	   #:create-avl-tree #:container-insert #:container-remove #:dotify-tree))
