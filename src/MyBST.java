/**
 * Created by Mehdi on 01/10/2018 for the MyIntegerBST project.
 */
public class MyBST implements A1Tree { //Implement a Binary Search Tree ADT (called MyIntegerBST) for Integer elements
    private Node root;
    private int size;

    public MyBST(){
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    // TODO: attach a pdf document where you analyze the worst-case time complexity of operations mostSimilarValue and printByLevels.
    @Override public void insert(Integer value){
        //The left subtree of a node contains only nodes with keys lesser than the node’s key.
        //The right subtree of a node contains only nodes with keys greater than the node’s key.
        //The left and right subtree each must also be a binary search tree.
    }

    @Override public Integer mostSimilarValue(Integer value) {
        return null;
    }
    @Override public void printByLevels(){ }

    // A utility function to search a given key in BST
    public Node search(Node root, int key) {
        if (root==null || root.key==key) return root;
        if (root.key > key) return search(root.left, key);
        return search(root.right, key);
    }

    public boolean contains(){return false;}
    public int getSize() { return size; }

    private class Node {
        Node left = null;
        Node right = null;
        boolean visited = false;
        public Integer key;
    }
}
