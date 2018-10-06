import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Beautifully Created by Mehdi on 03/10/2018 for A1 (1DV516).
 */
public class SequenceWithMinimum implements A1SequenceWithMinimum {

    public static void main(String[] args){
        SequenceWithMinimum seq = new SequenceWithMinimum();

        seq.insertRight(5); //seq={5}
        seq.insertRight(4); //seq={5,4}
        seq.insertRight(15); //seq={5,4,15}

        System.out.println( seq.toString());

        seq.removeRight(); //returns 15, and seq={5,4}
        System.out.println( seq.toString());
        seq.insertLeft(9); //seq={9,5,4}
        System.out.println( seq.toString());

        seq.removeLeft(); //returns 9, and seq={5,4}
        seq.removeLeft(); //returns 5, and seq={4}
        System.out.println( seq.toString());

        System.out.println( seq.findMinimum()); // returns 4, and seq={5,4,15} (seq remains unchanged)
        System.out.println( seq.toString());
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;
    private Node minEle = null;

    public SequenceWithMinimum() { }

    //O(1)
    @Override public void insertRight(Integer value) {
        Node added = new Node(value,value);
        if (head == null){
            minEle=added;
            head = added;
            tail = added;
        } else{
           if( added.getEncoding() < minEle.getEncoding()) {
               added=new Node(value,2*added.getEncoding() - minEle.getEncoding());
               minEle=new Node(value,value);
           }
           added.prev = tail;
           this.tail.next = added;
           this.tail = added;
        }
        this.size++;
    }

    //O(1)
    @Override public Integer removeRight() {
        if(head == null)
            throw new RuntimeException("__cannot delete");
        Integer rightWing = tail.getValue();

        if( tail.getEncoding() < minEle.getEncoding()) {
            minEle=new Node(tail.getValue(),2*minEle.getEncoding()- tail.getEncoding());
        }

        if(head == tail)
            clear();
        else{
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return rightWing;
    }

    //O(1)
    @Override public void insertLeft(Integer value) {
        Node added = new Node(value,value);
        if (head ==null){
            minEle=added;
            head = added;
            tail = added;
        } else {
            if( added.getEncoding() < minEle.getEncoding()) {
                added=new Node(value, 2*added.getEncoding() - minEle.getEncoding());
                minEle=new Node(value,value);
            }
            added.next = this.head;
            this.head.prev = added;
            this.head = added;
        }
        this.size++;
    }

    //O(1)
    @Override public Integer removeLeft() {
        if(head == null)
            throw new RuntimeException("__cannot delete");
        Integer leftWing = head.getValue();

        if( head.getEncoding() < minEle.getEncoding()) {
            minEle=new Node(head.getValue(),2*minEle.getEncoding()- head.getEncoding());
        }

        if(head == tail) clear();
        else{
            head = head.next;
            head.prev = null;
        }
        size--;
        return leftWing;
    }

    //O(1)
    @Override public Integer findMinimum(){
        if (head == null)
            throw new RuntimeException("List is empty!");
        return minEle.getValue();
    }

    //O(N) unavoidable.
    @Override public String toString() {
        if(head==null)
            return "---empty data structure";

        StringBuilder msg = new StringBuilder();
        msg.append("{");
        Node traversal = head;
        while (traversal.next != null) {
            msg.append(traversal.getValue());
            msg.append(", ");
            traversal = traversal.next;
        }
        msg.append(traversal.getValue());
        msg.append("}");
        return msg.toString();
    }

    /* Helper Methods and Classes*/
    private void clear(){
        head = null;
        tail = null;
    }

    private class Node {
        private Integer value, encoding;
        private Node prev;
        private Node next;
        public Node (Integer x, Integer key) {
            value = x;
            prev = null;
            next = null;
            encoding=key;
        }

        public Integer getEncoding() { return encoding; }
        public Integer getValue() { return value; }
    }

}

