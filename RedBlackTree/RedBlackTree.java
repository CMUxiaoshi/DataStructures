/**
 * This is the RedBlackTree class.
 * It is used to represent a RedBlackTree.
 * @author Xiao Shi
 * andrew id xiaoshi
*/
public class RedBlackTree {
    
    private RedBlackNode root;
    private static final RedBlackNode nil = new RedBlackNode(null, -1, 0, null, null, null);
    private int size;

    public RedBlackTree() {
        root = nil;
        size = 0;
    }
    /**
     * Inserts a new node with the given key into the Red-Black Tree. This method maintains the
     * Red-Black Tree properties by performing necessary rotations and color changes after insertion.
     * If the tree already contains a node with the same key, the insertion is ignored to prevent
     * duplicates.
     * @param key The key of the new node to be inserted into the tree. The tree is ordered by these keys.
     */
    public void insert(String key) {
        RedBlackNode pre = nil;
        RedBlackNode cur = root;
        size = size + 1;
        RedBlackNode newNode = new RedBlackNode(key, size, 1, nil, nil, nil);
        
        while (cur != nil) {
            pre = cur;
            if (key.compareTo(cur.getKey()) < 0) {
                cur = cur.getLc();
            } else if (key.compareTo(cur.getKey()) > 0) {
                cur = cur.getRc();
            } else {
                size = size - 1; // If contains, ignore this insertion.
                return;
            }
        }

        newNode.setP(pre);

        if (pre == nil) {
            root = newNode;
        } else {
            if (newNode.getKey().compareTo(pre.getKey()) < 0) {
                pre.setLc(newNode);
            } else {
                pre.setRc(newNode);
            }
        }
        RBInsertFixup(newNode);
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
        RedBlackNode newP = oldP.getRc();

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
        RedBlackNode newP = oldP.getLc();
        
        // The new parent's right child is smaller than the old parent but bigger than the new parent. 
        // Thus, add it to the left child of the old parent to keep order.
        oldP.setLc(newP.getRc());
        newP.getRc().setP(oldP);
        
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

    /**
     * Fixing up the tree so that Red Black Properties are preserved.
     * The tree needs to be fix if parent is red. This includes all possible violations of the red black properties.
     * The first situation is when both parent and uncle are red, this simply requires a color change.
     * The second situation is only parent is red, and the branch is a 'zig-zig' shape. This only requires one rotation.
     * The third situation is when parent is red, and the branch is a 'zig-zag' shape. This requires two rotation. 
     * After fix 'one level' of the Red Black Tree, check the upper branch.
     * Finally, check the root node. Make sure it's black.
     * @param cur The new insert node. This node is a red node.
     */
        private void RBInsertFixup(RedBlackNode cur) {
            RedBlackNode parent = cur.getP();
            RedBlackNode grandParent = parent.getP();
            while (parent.getColor() == 1) {
                if (parent == grandParent.getLc()) { // If the parent node is the left child.
                    RedBlackNode uncle = grandParent.getRc();
                    if (uncle.getColor() == 1) {
                        parent.setColor(0); // If both parent and uncle are red, set both to black.
                        uncle.setColor(0);
                        grandParent.setColor(0); // Set grand parent to black.
                        cur = grandParent; // Check upper level.
                    } else {
                        // if current node is right child, do left rotation first
                        if (cur == parent.getRc()) {
                            cur = parent; // Set the parent to be current node.
                            leftRotate(cur);
                        }
                        parent = cur.getP(); // Update parent and grand parent for current (old parent)
                        grandParent = parent.getP();
                        parent.setColor(0); // Change color first so that after rotation, nodes including grandparent and down follow rules.
                        grandParent.setColor(1);
                        rightRotate(grandParent);
                    }

                } else {
                    RedBlackNode uncle = grandParent.getLc();
                    if (uncle.getColor() == 1) {
                        parent.setColor(0); // If both parent and uncle are red, set both to black.
                        uncle.setColor(0);
                        grandParent.setColor(0); // Set grand parent to black.
                        cur = grandParent; // Check upper level.
                    } else {
                        // if current node is left child, do right rotation first
                        if (cur == parent.getLc()) {
                            cur = parent; // Set the parent to be current node.
                            rightRotate(cur);
                        }
                        parent = cur.getP(); // Update parent and grand parent for current (old parent)
                        grandParent = parent.getP();
                        parent.setColor(0); // Change color first so that after rotation, nodes including grandparent and down follow rules.
                        grandParent.setColor(1);
                        leftRotate(grandParent);
                    }
                }
            }
            root.setColor(0); // Root must be black.
    }
    
    /**
     * Check if the key is inside the tree dictionary.
     * @param key The key to check.
     * @return The boolean contains() returns true if the String v is in the RedBlackTree and false otherwise.
    */
    public boolean contains(String key) {
        RedBlackNode cur = root;
        while (cur != nil) {
            if (key.compareTo(cur.getKey()) == 0) {
                return true;
            } else if (key.compareTo(cur.getKey()) < 0) {
                cur = cur.getLc();
            } else {
                cur = cur.getRc();
            }
        }
        return false;
    }
    
    /**
     * Get the size of the tree.
     * @return the size of the tree, which is the number of nodes in the tree.
    */
    public int getSize() {
        return size;
    }

    /**
     * Get the value of the key.
     * @param key The key to get the value.
     * @return The value of the key.
    */
    public int getValue(String key) {
        RedBlackNode cur = root;
        while (cur != nil) {
            if (key.compareTo(cur.getKey()) == 0) {
                return cur.getData();
            } else if (key.compareTo(cur.getKey()) < 0) {
                cur = cur.getLc();
            } else {
                cur = cur.getRc();
            }
        }
        return -1; // if error
    }

    /**
     * Get the key of the value. This method return an array that contains all the keys in the tree.
     * Value - 1 is the index of the key in the array.
     * @param value The value to get the key.
     * @return The key of the value.
    */
    public String[] arrayofStrings() {
        String[] array = new String[size];
        arrayofStrings(root, array);
        return array;
    }

    /**
     * Get the key of the value. This method return an array that contains all the keys in the tree.
     * Value - 1 is the index of the key in the array. Here I use in-order traversal to get the array.
     * @param cur The current node.
     * @param array The array to store the keys.
     * @return The key of the value.
    */
    private void arrayofStrings(RedBlackNode cur, String[] array) {
        if (cur != nil) {
            arrayofStrings(cur.getLc(), array);
            array[cur.getData() - 1] = cur.getKey();
            arrayofStrings(cur.getRc(), array);
        }
    }

    /**
     * Get the root of the tree.
     * @return The root of the tree.
    */
    public RedBlackNode getRoot() {
        return root;
    }
}
