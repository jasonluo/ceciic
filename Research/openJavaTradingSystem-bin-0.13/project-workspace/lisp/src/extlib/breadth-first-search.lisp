#|----------------------------------------------------------------------------
Artificial Intelligence, Second Edition
Elaine Rich and Kevin Knight
McGraw Hill, 1991

This code may be freely copied and used for educational or research purposes.
All software written by Kevin Knight.
Comments, bugs, improvements to knight@cs.cmu.edu
----------------------------------------------------------------------------|#

#|----------------------------------------------------------------------------
			BREADTH-FIRST SEARCH
			     "bfs.lisp"
----------------------------------------------------------------------------|#

;; --------------------------------------------------------------------------
;; Structure BFS-NODE stores nodes of the search tree. Every node in the 
;; breadth-first search has two components: the state and the parent of
;; the node.

(defstruct bfs-node state parent)

;; --------------------------------------------------------------------------
;; Function BFS performs a breadth-first search and returns a solution
;; path from start to goal.  Nodes on the frontier of the search space
;; are kept on NODE-LIST.  When a node is expanded, its successors are 
;; appended to the back of NODE-LIST.
;;
;; The versions of breadth-first search in this file do not take edge 
;; costs into account -- i.e., they expand the search space as though
;; the cost of moving from one state to another were constant.

(defun bfs (start &optional verbose)
  (let ((node-list (list (make-bfs-node :state start :parent nil))))
    (do* ((node (car node-list) (car node-list))
	  (solution-found (if (goal-state? start) node nil)))
	 ((or (null node-list) solution-found)
	  (if (null node-list)
	      "No solution."
	      (extract-bfs-path solution-found)))
      (when verbose (format t "Expanding node ~d~%" (bfs-node-state node)))
      (let ((succs (mapcar #'(lambda (e)
				 (make-bfs-node :state e
						:parent node))
			   (expand (bfs-node-state node)))))
         (setq solution-found (find-if #'(lambda (s)
			                   (goal-state? (bfs-node-state s)))
		      		       succs))
	 (setq node-list (append (cdr node-list) succs))))))

;; --------------------------------------------------------------------------
;; Function EXTRACT-BFS-PATH takes a goal node and follows its parent 
;; pointers back to the initial node, returning the list of all states
;; along the path.

(defun extract-bfs-path (node)
  (do ((path nil)
       (n node (bfs-node-parent n)))
      ((null n) path)
    (setq path (cons (bfs-node-state n) path))))

;; --------------------------------------------------------------------------
;; Function BFS-GRAPH is the same as BFS except duplicate nodes are not
;; added to the search space.  That is, the same state will not be expanded
;; twice.  Nodes already expanded are kept in a list of closed nodes.

(defun bfs-graph (start &optional verbose)
  (let ((node-list (list (make-bfs-node :state start :parent nil))))
    (do* ((node (car node-list) (car node-list))
	  (closed nil)
	  (goal-found (if (goal-state? start) node nil)))
	 ((or (null node-list) goal-found)
	  (if (null node-list)
	      "No solution."
	      (extract-bfs-path goal-found)))
      (when verbose (format t "Expanding node ~d~%" (bfs-node-state node)))
      (let* ((all-succs (mapcar #'(lambda (e)
				     (make-bfs-node :state e
					  	    :parent node))
			        (expand (bfs-node-state node))))
	     (succs (nodes-not-visited all-succs node-list closed)))
         (setq goal-found (find-if #'(lambda (s)
			                (goal-state? (bfs-node-state s)))
		      		   succs))
	 (setq node-list (append (cdr node-list) succs))
	 (setq closed (cons node closed))))))

;; Function NODES-NOT-VISITED returns all nodes on ALL-SUCCS that are not
;; present on NODE-LIST or CLOSED.  That is, all nodes that have not been 
;; previously visited.

(defun nodes-not-visited (all-succs node-list closed)
  (set-difference
    (set-difference all-succs 
		    node-list
                    :test #'(lambda (n1 n2)
				(eq-states (bfs-node-state n1)
					   (bfs-node-state n2))))
    closed
    :test #'(lambda (n1 n2)
               (eq-states (bfs-node-state n1)
	                  (bfs-node-state n2)))))
