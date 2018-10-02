/**
 * Created by Mehdi on 01/10/2018 for the MyIntegerBST project.
 */
public class MyBST implements A1Tree { //Implement a Binary Search Tree ADT (called MyIntegerBST) for Integer elements
    /* The property that makes a binary tree into a binary search tree is that for every node,
     * X, in the tree, the values of all the items in its left subtree are smaller than the item in X,
     * and the values of all the items in its right subtree are larger than the item in X
     */

    private Node root =null;
    private int size = 0;
    public MyBST(){ }

    // TODO: attach a pdf document where you analyze the worst-case time complexity of operations mostSimilarValue and printByLevels.
    @Override public void insert(Integer value){
        try{
            if (value == null)
                throw new IllegalArgumentException("--argument to insert() is null");
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

    @Override public Integer mostSimilarValue(Integer value) {
        if(contains(value))return value;
        return similar(root,value,0);
    }

    private Integer similar(Node comparator, Integer value, Integer s0) {
        if(comparator.key>value && comparator.left != null)
            if(Math.abs(comparator.key - value) >= Math.abs(comparator.left.key - value))
                similar(comparator.left,value, comparator.left.key);
            else
                similar(comparator.left,value,comparator.key);

        else if(comparator.key<value && comparator.right != null )
            if(Math.abs(comparator.right.key - value) >= Math.abs(comparator.key - value))
                similar(comparator.right,value,comparator.key);
            else
                similar(comparator.right,value,comparator.right.key);
        return s0;
    }

    @Override public void printByLevels(){

    }

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
           if (T == null)
               throw new IllegalArgumentException("---argument to contains() is null");
           return search(root, T) != null;
       }catch(IllegalArgumentException e ){
           System.err.println("You are looking for a null reference! debug");
           return false;
       }
    }

    private class Node {
        Integer key; // since we are dealing with integers, there is no need to override the 'compareTo' method for this node class.
        Node left = null;
        Node right = null;

        Node( Integer theElement){
            this( theElement, null, null );
        }
        Node( Integer theElement, Node lt, Node rt ) {
            key = theElement;
            left = lt;
            right = rt;
        }
    }
}
