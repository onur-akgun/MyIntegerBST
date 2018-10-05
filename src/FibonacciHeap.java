/**
 * Created by Mehdi on 04/10/2018 for the MyIntegerBST project.
 */
public class FibonacciHeap {
    private Node min;
    private int n;

    // Running time: O(1) exactly what we need for the FindMin in my double linked list implementation.
    public Node min() { return min; }

    private void consolidate() {
        Node[] myHeap = new Node[40];
        Node start = min;
        Node w = min;
        do {
            Node x = w;
            Node nextW = w.right;
            int d = x.degree;
            while (myHeap[d] != null) {
                Node y = myHeap[d];
                if (x.key > y.key) {
                    Node temp = y;
                    y = x;
                    x = temp;
                }
                if (y == start) start = start.right;
                if (y == nextW) nextW = nextW.right;

                y.link(x);
                myHeap[d] = null;
                d++;
            }
            myHeap[d] = x;
            w = nextW;// Move forward through list.
        } while (w != start);

        // Find the minimum key again.
        min = start;
        for (Node a : myHeap)
            if (a != null && a.key < min.key)
                min = a;
    }

    public void decreaseKey(Node x, double k) { decreaseKey(x, k, false); }

    private void decreaseKey(Node x, double k, boolean delete) {
        if (!delete && k > x.key)
            throw new IllegalArgumentException("cannot increase key value");
        x.key = k;
        Node y = x.parent;
        if (y != null && (delete || k < y.key)) {
            y.cut(x, min);
            y.cascadingCut(min);
        }
        if (delete || k < min.key)
            min = x;
    }


    public void delete(Node x) {
        decreaseKey(x, 0, true);
        removeMin();
    }


    // Running time: O(1) Won't have an decrease in performance for the Other data structure.
    public Node insert(Integer x, double key) {
        Node node = new Node(x, key);
        if (min != null) {
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            if (key < min.key)
                min = node;
        } else
            min = node;
        n++;
        return node;
    }

    // unfortunately this will add a O(Log(N)) to other data structure but overall will perform greatly.
    private Integer removeMin() {
        Node z = min;
        if (z == null)
            return null;

        if (z.child != null) {
            z.child.parent = null;
            for (Node x = z.child.right; x != z.child; x = x.right) {
                x.parent = null;
            }

            Node minleft = min.left;
            Node zchildleft = z.child.left;

            min.left = zchildleft;
            zchildleft.right = min;
            z.child.left = minleft;
            minleft.right = z.child;
        }

        z.left.right = z.right;
        z.right.left = z.left;
        if (z == z.right)
            min = null;
        else {
            min = z.right;
            consolidate();
        }
        n--;
        return z.data;
    }


    private static class Node {
        private Integer data;
        private double key;
        private Node parent;
        private Node child;
        private Node right;
        private Node left;
        private int degree;
        private boolean marker;

        Node(Integer data, double key) {
            this.data = data;
            this.key = key;
            right = this;
            left = this;
        }

        void cascadingCut(Node min) {
            Node z = parent;
            if (z != null) {
                if (marker) {
                    z.cut(this, min);
                    z.cascadingCut(min);
                } else
                    marker = true;
            }
        }

        void cut(Node x, Node min) {
            x.left.right = x.right;
            x.right.left = x.left;
            degree--;

            if (degree == 0) child = null;
            else if (child == x) child = x.right;

            x.right = min;
            x.left = min.left;
            min.left = x;
            x.left.right = x;

            x.parent = null;
            x.marker = false;
        }

        void link(Node parent) {
            left.right = right;
            right.left = left;

            this.parent = parent;
            if (parent.child == null) {
                parent.child = this;
                right = this;
                left = this;
            } else {
                left = parent.child;
                right = parent.child.right;
                parent.child.right = this;
                right.left = this;
            }

            parent.degree++;
            marker = false;
        }
    }
}
