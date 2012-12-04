;;Licensing Terms:
;; This software is Copyright (c) by Christian Schuhegger, 2005/05/26.
;; Christian Schuhegger grants you the rights to distribute
;; and use this software as governed by the terms
;; of the Lisp Lesser GNU Public License
;; (http://opensource.franz.com/preamble.html),
;; known as the LLGPL.

(in-package #:cssb-util :nicknames '("CSSB.UTIL"))

(defun interleave (list-of-strings str)
  (labels ((interleave-erec (list-of-strings str result)
			    (if (< (length list-of-strings) 2)
				(concatenate 'string result (car list-of-strings))
			      (interleave-erec (cdr list-of-strings) str (concatenate 'string result (car list-of-strings) str)))))
    (interleave-erec list-of-strings str "")))
