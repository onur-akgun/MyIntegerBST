import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Mehdi on 01/10/2018 for the MyIntegerBST project.
 */
public class MyBST implements A1Tree {
    //Implement a Binary Search Tree ADT (called MyIntegerBST) for Integer elements
    /* The property that makes a binary tree into a binary search tree is that for every node,
     * X, in the tree, the values of all the items in its left subtree are smaller than the item in X,
     * and the values of all the items in its right subtree are larger than the item in X
     */
    public static void main(String[] args){
        MyBST tree = new MyBST();
        tree.insert(10);
        tree.insert(7);
        tree.insert(4);
        tree.insert(9);
        tree.insert(8);
        tree.insert(20);
        tree.insert(25);
        System.out.println("- Expected Output of mostSimilarValue(18) -> "+tree.mostSimilarValue(18));
        System.out.println("- Expected Output of mostSimilarValue(21) -> "+tree.mostSimilarValue(21));
        System.out.println("- Expected Output of mostSimilarValue(21) -> "+tree.mostSimilarValue(24));
        System.out.println("- Expected Output of mostSimilarValue(1) -> "+tree.mostSimilarValue(1));
        System.out.println("- Expected Output of mostSimilarValue(9) -> "+tree.mostSimilarValue(9));
        System.out.println("- Expected Output of mostSimilarValue(15) -> "+tree.mostSimilarValue(15));

        System.out.println("- Expected Output by PrintByLevels() -> ");
        tree.printByLevels();
    }

    private Node root =null;
    private int size = 0;
    public MyBST(){ }

    // TODO: attach a pdf document where you analyze the worst-case time complexity of operations mostSimilarValue and printByLevels.
    @Override public void insert(Integer value){
        try{
            if (value == null)
                throw new IllegalArgumentException("--argument to insert() is null");
            if (!contains(value))
                this.root = addElement(this.root, value);
            else
                System.out.println("The element exists in the tree already! /.Tree unchanged./");
        }catch(IllegalArgumentException e) { System.err.println(e.getMessage()); }
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
                return similar(comparator.left,value, comparator.left.key);
            else
                return similar(comparator.left,value,comparator.key);

        else if(comparator.key<value && comparator.right != null )
            if(Math.abs(comparator.right.key - value) >= Math.abs(comparator.key - value))
                return similar(comparator.right,value,comparator.key);
            else
                return similar(comparator.right,value,comparator.right.key);
        return s0;
    }

    @Override public void printByLevels(){ //using Breadth First Search traversal.
        if(root == null) return;
        Queue<Node> queue = new LinkedList<Node>();
        queue.clear();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.remove();
            System.out.print(node.key + " ");
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
    }

    private Node search(Node root, Integer key) {
        if (root==null)
            return null;
        if (root.key > key)
           return search(root.left, key);
        else if (root.key < key)
           return search(root.right, key);
        else return root;
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
        Integer key;
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
