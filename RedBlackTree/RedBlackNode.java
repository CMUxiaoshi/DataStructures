public class RedBlackNode {
    private static int BLACK = 0;
    private static int RED = 1;

    private String key;
    private int index;
    private int color;
    private RedBlackNode parent;
    private RedBlackNode leftChild;
    private RedBlackNode rightChild;


    RedBlackNode(String key, int index, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
        this.key = key;
        this.index = index;
        this.color = color;
        this.parent = p;
        this.leftChild = lc;
        this.rightChild = rc;
    }


    /**
     * The getColor() method returns RED or BLACK.
     * @return If color == BLACK, return BLACK. If color == RED, return RED. Else return -1 representing error.
    */
    public int getColor() {
        if (color == BLACK) {
            return BLACK;

        } else if (color == RED) {
            return RED;

        } else {
            return -1;
        }
    }

    /**
     * The getKey() method returns key of a node.
     * @return the key of a node.
    */
    public String getKey() {
        return key;
    }

    /**
     * The getData() method returns the index of a node.
     * @return the index of a node.
    */
    public int getData() {
        return index;
    }

    /**
     * The getLc() method returns the left child of the RedBlackNode.
     * @return Left child of the current node. If null, return null.
    */
    public RedBlackNode getLc() {
        return leftChild;
    }

    /**
     * The getP() method returns the parent of the RedBlackNode.
     * @return Parent of the current node. If null, return null.
    */
    public RedBlackNode getP() {
        return parent;
    }

    /**
     * The getRc() method returns the right child of the RedBlackNode.
     * @return Right child of the current node. If null, return null.
    */
    public RedBlackNode getRc() {
        return rightChild;
    }

    /**
     * The setColor() method sets the color of the RedBlackNode.
     * @param color The color to set
    */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * The setKey() method sets the key of the RedBlackNode.
     * @param key The key to set;
    */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * The setData() method sets the index of the RedBlackNode.
     * @param data The index to set;
    */
    public void setData(int data) {
        index = data;
    }

    /**
     * The setLc() method sets the left child of the RedBlackNode.
     * @param lc The new left child to set.
    */
    public void setLc(RedBlackNode lc) {
        leftChild = lc;
    }

    /**
     * The setP() method sets the parent of the RedBlackNode.
     * @param p The new parent of the node.
    */
    public void setP(RedBlackNode p) {
        parent = p;
    }

    /**
     * The setRc() method sets the right child of the RedBlackNode.
     * @param rc The new right child of the RedBlackNode.
    */
    public void setRc(RedBlackNode rc) {
        rightChild = rc;
    }

    /**
     * The toString() methods returns a string representation of the RedBlackNode.
     * @return Only return the key for each node.
    */
    public String toString() {
        return key;
    }
}
