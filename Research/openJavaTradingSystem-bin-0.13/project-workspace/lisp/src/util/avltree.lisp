;;Licensing Terms:
;; This software is Copyright (c) by Christian Schuhegger, 2005/05/26.
;; Christian Schuhegger grants you the rights to distribute
;; and use this software as governed by the terms
;; of the Lisp Lesser GNU Public License
;; (http://opensource.franz.com/preamble.html),
;; known as the LLGPL.

(in-package #:cssb-util :nicknames '("CSSB.UTIL"))

(defclass Tree ()
  ((root :accessor tree-root
	 :initform nil
	 :initarg  :root)
   (size :accessor tree-size
         :initform 0
         :type     fixnum
         :initarg  :size)))

(defmethod print-object ((e Tree) stream)
  (with
   slots (size) in e
   do
   (format stream "#<Tree, size=~D>" size)))

(defclass AVLTree (Tree) nil)

(defmethod order-relation ((e1 number) (e2 number))
  (cond
   ((< e1 e2)   -1)
   ((= e1 e2)    0)
   (t           +1)))

(defmethod order-relation ((node1 AVLNode) (node2 AVLNode))
  (order-relation (avlnode-data node1) (avlnode-data node2)))

(defclass AVLNode ()
  ((ht    :accessor avlnode-ht
	  :initform 1
          :type     fixnum
	  :initarg :ht)
   (left  :accessor avlnode-left
	  :initform nil
          :type     AVLNode
	  :initarg :left)
   (right :accessor avlnode-right
	  :initform nil
          :type     AVLNode
	  :initarg :right)
   (data  :accessor avlnode-data
	  :initform nil
	  :initarg :data)))

(defmethod avlnode-ht ((node (eql nil)))
  0)

(defmethod print-object ((e AVLNode) stream)
  (with
   slots (data ht) in e
   do
   (format stream "#<AVL node, height=~D, ~A>" ht data)))

(defun create-avl-tree ()
  (make-instance 'AVLTree))

(defun create-avl-node (data)
  (make-instance 'AVLNode :data data))

(defmethod container-insert ((tree AVLTree) data)
  (with
   slots (root size) in tree
   do
   (if* (null root)
        then
        (setf root (create-avl-node data))
        (setf size 1)
        else
        (avltree-insert tree root data #'(lambda (node) (setf root node))))))

(defmethod container-remove ((tree AVLTree) data)
  (with
   slots (root size) in tree
   do
   (when (not (null root))
     (avltree-remove tree root data #'(lambda (node) (setf root node))))))

(defun avlnode-recalc-height (node)
  (when (not (null node))
    (with
     slots (ht left right) in node
     do
     (setf ht (1+ (max (avlnode-ht left) (avlnode-ht right)))))))
  
(defmethod avltree-insert (tree node data fn-parentlink)
  (with
   slots ((node-data data) left right) in node
   do
   (case (order-relation data node-data)
     ( 0 (setf node-data data))
     (-1 (with
          var ht-before   = (avlnode-ht left)
          var side-effect = (avltree-insert tree left data #'(lambda (nd) (setf left nd))) declare (ignorable side-effect)
          var ht-after    = (avlnode-ht left)
          do
          (when (not (eql ht-before ht-after))
            (avlnode-rebalance node fn-parentlink))))
     (+1 (with
          var ht-before   = (avlnode-ht right)
          var side-effect = (avltree-insert tree right data #'(lambda (nd) (setf right nd))) declare (ignorable side-effect)
          var ht-after    = (avlnode-ht right)
          do
          (when (not (eql ht-before ht-after))
            (avlnode-rebalance node fn-parentlink))))
     (t  (error "avltree-insert (tree node data fn-parentlink): should never happen")))))

(defmethod avltree-insert (tree (node (eql nil)) data fn-parentlink)
  (incf (tree-size tree))
  (funcall fn-parentlink (create-avl-node data)))

(defun avlnode-rebalance (node fn-parentlink)
  ;; see http://www.eli.sdsu.edu/courses/fall95/cs660/notes/AVL/AVL.html
  ;; to have a picture to orient yourself
  (with
   slots (left right) in node
   var ht-left  = (avlnode-ht left)
   var ht-right = (avlnode-ht right)
   do
   (cond
    ((<= (abs (- ht-left ht-right)) 1)
     nil)
    ((> (- ht-left ht-right) 1);;left branch is too high
     (with
      slots ((ll left) (lr right)) in left
      var ht-ll = (avlnode-ht ll)
      var ht-lr = (avlnode-ht lr)
      do
      (cond
       ((>= ht-ll ht-lr);; single rotation is sufficient
        (with
         slots ((lll left) (llr right)) in ll
         do
         (avlnode-relink fn-parentlink lll ll llr left lr node right)))
       ((<  ht-ll ht-lr);; double rotation required
        (with
         slots ((lrl left) (lrr right)) in lr
         do
         (avlnode-relink fn-parentlink ll left lrl lr lrr node right))))))
    ((> (- ht-right ht-left) 1);;right branch is too high
     (with
      slots ((rl left)  (rr right)) in right
      var ht-rl = (avlnode-ht rl)
      var ht-rr = (avlnode-ht rr)
      do
      (cond
       ((>= ht-rr ht-rl);; single rotation is sufficient
        (with
         slots ((rrl left) (rrr right)) in rr
         do
         (avlnode-relink fn-parentlink left node rl right rrl rr rrr)))
       ((<  ht-rr ht-rl);; double rotation required
        (with
         slots ((rll left) (rlr right)) in rl
         do
         (avlnode-relink fn-parentlink left node rll rl rlr right rr)))))))
   (avlnode-recalc-height node)))

(defun avlnode-relink (fn-parentlink ll l lr c rl r rr)
  (setf (avlnode-left  c) l)
  (setf (avlnode-right c) r)
  (setf (avlnode-left  l) ll)
  (setf (avlnode-right l) lr)
  (setf (avlnode-left  r) rl)
  (setf (avlnode-right r) rr)
  (avlnode-recalc-height l)
  (avlnode-recalc-height r)
  (avlnode-recalc-height c)
  (funcall fn-parentlink c))

(defun avltree-remove (tree node data fn-parentlink)
  (with
   slots ((node-data data) left right) in node
   do
   (case (order-relation data node-data)
     ( 0 (decf (tree-size tree))
         (if (null left)
             (funcall fn-parentlink right)
           (avltree-remove-biggest-and-replace left node #'(lambda (nd) (setf left nd)))))
     (-1 (with
          var ht-before   = (avlnode-ht left)
          var side-effect = (avltree-remove tree left data #'(lambda (nd) (setf left nd))) declare (ignorable side-effect)
          var ht-after    = (avlnode-ht left)
          do
          (when (not (eql ht-before ht-after))
            (avlnode-rebalance node fn-parentlink))))
     (+1 (with
          var ht-before   = (avlnode-ht right)
          var side-effect = (avltree-remove tree right data #'(lambda (nd) (setf right nd))) declare (ignorable side-effect)
          var ht-after    = (avlnode-ht right)
          do
          (when (not (eql ht-before ht-after))
            (avlnode-rebalance node fn-parentlink))))
     (t  (error "avltree-remove (tree node data fn-parentlink): should never happen")))))

(defun avltree-remove-biggest-and-replace (node replace-node fn-parentlink)
  (with
   slots (right) in node
   do
   (if* (null right)
        then
        (setf (avlnode-data replace-node) (avlnode-data node))
        (funcall fn-parentlink (avlnode-left node))
        else
        (with
         var ht-before   = (avlnode-ht right)
         var side-effect = (avltree-remove-biggest-and-replace right replace-node #'(lambda (nd) (setf right nd))) declare (ignorable side-effect)
         var ht-after    = (avlnode-ht right)
         do
         (when (not (eql ht-before ht-after))
           (avlnode-rebalance node fn-parentlink))))))

#|
(defmethod fmapn (fn (obj Tree))
  (fmap fn (tree-root obj)))

(defmethod fmap1n (fn (obj AVLNode))
  (with
   slots (data left right) in obj
   do
   (fmapn fn left)
   (setf data (funcall fn data))
   (fmapn fn right)))
|#

(defvar *nil-nr* 0)
(defun dotify-tree (tree)
  (with
   open-file stream = ("avl-tree.dot" :direction :output :if-exists :supersede)
   var *nil-nr*     = 0 declare (special *nil-nr*)
   do
   (format stream "/* dot -Tps avl-tree.dot -o avl-tree.eps -Gsize=7.5,10.0 -Gratio=fill */~%")
   (format stream "~%digraph G {~%")
   (dotify-avl-node (tree-root tree) stream)
   (format stream "}~%")
   ))

(defun dotify-print-node (node)
  (format nil "~A,~A" (avlnode-data node) (avlnode-ht node)))

(defun dotify-avl-node (node stream)
  (with
   slots (left right) in node
   do
   (if* (not (null left))
        then
        (format stream "  \"~A\" -> \"~A\" ;~%" (dotify-print-node node) (dotify-print-node left))
        (dotify-avl-node left  stream)
        ;;else
        ;;(format stream "  \"~A\" -> \"~A\" ;~%" (dotify-print-node node) (format nil "nil~D" (incf *nil-nr*)))
        )
   (if* (not (null right))
        then
        (format stream "  \"~A\" -> \"~A\" ;~%" (dotify-print-node node) (dotify-print-node right))
        (dotify-avl-node right stream)
        ;;else
        ;;(format stream "  \"~A\" -> \"~A\" ;~%" (dotify-print-node node) (format nil "nil~D" (incf *nil-nr*)))
        )
   ))

(defun avltree-test ()
  (with
   var tree = (create-avl-tree)
   do
   (loop for i from 1 to 32 do
         (container-insert tree i))
   (loop for i from 1 to 15 do
         (container-remove tree i))
   (dotify-tree tree)
   #+clisp
   (ext:run-shell-command "dot -Tps avl-tree.dot -o avl-tree.eps -Gsize=7.5,10.0 -Gratio=fill")
   ;;:INPUT nil :OUTPUT final-output-file-name :IF-OUTPUT-EXISTS :overwrite :WAIT t)
   ))

;;;(asdf:oos 'asdf:load-op 'cssb-util)
;;;(cssb-util::avltree-test)