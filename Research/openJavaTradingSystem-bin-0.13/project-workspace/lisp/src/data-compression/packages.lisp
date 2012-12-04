

(in-package :cl-user)

(defpackage #:cssb-data-compression
  (:nicknames "CSSB.DATA-COMPRESSION" "DC")
  (:use #:cl #:cl-user)
  (:import-from #:cl-user with if*)
  (:export #:ac-encode #:ac-decode #:lzw-encode #:lzw-decode))
