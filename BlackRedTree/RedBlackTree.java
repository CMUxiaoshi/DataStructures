public class RedBlackTree {
    
    private RedBlackNode root;
    private static final RedBlackNode nil = new RedBlackNode(null, -1, 0, null, null, null);

    public RedBlackNode() {
        root = nil;
    }

    public void insert(String key) {
        pre = nil;
        cur = root;
        RedBlackNode newNode = new RedBlackNode(key, 0, 1, null, nil, nil); // TODO: change the index
        
        while (cur != nil) {
            pre = cur;
            if (key.compareTo(cur.getKey()) < 0) {
                cur = cur.getLc();
            } else {
                cur = cur.getRc();
            }
        }

        newNode.setP(pre);

        if (pre == nil) {
            root = newNode;
        } else {
            if (newNode.getData().compareTo(pre.getData) < 0) {
                pre.setLc(newNode);
            } else {
                pre.setRc(newNode);
            }
        }
        // TODO: RB-Insert-fixup(T,z)
    }
    
    /**
     * leftRotate() performs a single left rotation.
     * Three stages for rotation: firstly, make the left child of the new parent to the proper position.
     * Secondly, handle the relation between grandparent and the new parent, and the old parent.
     * Thirdly, handle the relation betwwen the old parent and the new parent.
     * Details are provided in the method.
     * @param oldP The old parent that require a rotation.
     * @precondition The right child of the old parent is not nil.
     * @precondition The parent of the root is nil.
    */
    private void leftRotate(RedBlackNode oldP) {
        // When left rotate, the right child of the old parent will be the new parent.
        newP = oldP.getRc();

        // The new parent's left child is bigger than the old parent but smaller than the new parent. 
        // Thus, add it to the right child of the old parent to keep order.
        oldP.setRc(newP.getLc());
        newP.getLc().setP(oldP);
        
        // Connect new parent's parent to the old parent's parent.
        newP.setP(oldP.getP());
        // If the rotate change the root, update the root.
        if (oldP.getP() == nil) {
            root = newP;
        } else {
            // If old parent is the left child, set the new parent to be the left child. 
            if (oldP == oldP.getP().getLc()) {
                oldP.getP().setLc(newP);
            // Else, set the new parent the child right child.
            } else {
                oldP.getP().setRc(newP);
            }
        }
        // Rotate, old parent now is the left child of the new parent
        newP.setLc(oldP);
        oldP.setP(newP);
    }

    /**
     * RightRotate() performs a single right rotation.     
     * Three stages for rotation: firstly, make the right child of the new parent to the proper position.
     * Secondly, handle the relation between grandparent and the new parent, and the old parent.
     * Thirdly, handle the relation betwwen the old parent and the new parent.
     * Details are provided in the method.     
     * @param oldP The old parent that require a rotation.
     * @precondition The left child of the old parent is not nil.
     * @precondition The parent of the root is nil.
    */
    public void rightRotate(RedBlackNode oldP) {
        // When left rotate, the left child of the old parent will be the new parent.        
        newP = oldP.getLc();
        
        // The new parent's right child is smaller than the old parent but bigger than the new parent. 
        // Thus, add it to the left child of the old parent to keep order.
        oldP.setLc(newP.getRc());
        newP.getRc(setP(oldP));
        
        // Connect new parent's parent to the old parent's parent.
        newP.setP(oldP.getP());
        // If the rotate change the root, update the root.
        if (oldP.getP() == nil) {
            root = newP;
        } else {
            // If old parent is the left child, set the new parent to be the left child. 
            if (oldP == oldP.getP().getLc()) {
                oldP.getP().setLc(newP);
            // Else, set the new parent the child right child.
            } else {
                oldP.getP().setRc(newP);
            }
        }
        // Rotate, old parent now is the left child of the new parent
        newP.setRc(oldP);
        oldP.setP(newP);
    }
}
