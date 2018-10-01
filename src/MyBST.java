/**
 * Created by Mehdi on 01/10/2018 for the MyIntegerBST project.
 */
public class MyBST implements A1Tree { //Implement a Binary Search Tree ADT (called MyIntegerBST) for Integer elements
    private Node root;
    private int size;

    public MyBST(){
    }

    // TODO: attach a pdf document where you analyze the worst-case time complexity of operations mostSimilarValue and printByLevels.
    @Override public void insert(Integer value){ }

    @Override public Integer mostSimilarValue(Integer value) {
        return null;
    }
    @Override public void printByLevels(){ }

    private class Node {
        Node left = null;
        Node right = null;
        Node parent = null;
        boolean visited = false;

    }
}
