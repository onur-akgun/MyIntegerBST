import java.util.DuplicateFormatFlagsException;

/**
 * Created by Mehdi on 01/10/2018 for the MyIntegerBST project.
 */
public class MyBST implements A1Tree { //Implement a Binary Search Tree ADT (called MyIntegerBST) for Integer elements
    private Node root =null;
    private int size;

    /* The property that makes a binary tree into a binary search tree is that for every node,
     * X, in the tree, the values of all the items in its left subtree are smaller than the item in X,
     * and the values of all the items in its right subtree are larger than the item in X
     */
    public MyBST(){ }
    public boolean isEmpty() { return this.getSize() == 0; }

    // TODO: attach a pdf document where you analyze the worst-case time complexity of operations mostSimilarValue and printByLevels.
    /* The left subtree of a node contains only nodes with keys lesser than the node’s key.
     * The right subtree of a node contains only nodes with keys greater than the node’s key.
     * The left and right subtree each must also be a binary search tree.*/
    @Override public void insert(Integer value){
        try{
            if (value == null)
                throw new IllegalArgumentException("--argument to insert() is null");
        //To insert X into tree T, proceed down the tree as you would with a contains. If X is found, do nothing (or “update” something).
            if (!contains(value)) {
                this.root = addElement(this.root, value);
            } else {
                System.out.println("The element exists in the tree already! /.Tree unchanged./");
            }
        }catch(IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private Node addElement(Node walker, Integer value) {
        if(walker==null)
            walker = new Node(value);
        else if( walker.key > value)
            walker.left=addElement(walker.left, value);
        else if(walker.key < value)
            walker.right=addElement(walker.right,value);
        return walker;
    }

    @Override public Integer mostSimilarValue(Integer value) { return null; }
    @Override public void printByLevels(){ }

    // A utility function to search a given key in BST
    private Node search(Node root, Integer key) {
        if (root==null) return null;
        else {
            if (root.key > key)
                this.search(root.left, key);
            else if (root.key < key)
                this.search(root.right, key);
            return root;
        }
    }

    /* If T is empty, then we can just return false.
     * Otherwise, we make a recursive call on a subtree of T, either left or right, depending on the relationship of X to the item stored in T
     */
    public boolean contains(Integer T){
       try{
           if (T == null) throw new IllegalArgumentException("---argument to contains() is null");
           return search(root, T) != null;
       }catch(IllegalArgumentException e ){
           System.err.println("You are looking for a null reference! debug");
           return false;
       }
    }

    public int getSize() { return size; }

    private class Node {
        Integer key; // since we are dealing with integers, there is no need to override the 'compareTo' method for this node class.
        Node left = null;
        Node right = null;
        boolean visited;

        Node( Integer theElement){
            this( theElement, null, null );
        }
        Node( Integer theElement, Node lt, Node rt ) {
            key = theElement;
            left = lt;
            right = rt;
        }
        public Node getLeft() {return left;}

        public Node getRight() {return right;}

        public void setKey(Integer k) {key = k;}

        public void setLeft(Node lt) {left = lt;}

        public void setRight(Node rt) {right = rt;}
    }
}
