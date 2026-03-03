package lab3_2;

public class IntNode {
    int elem;
    IntNode next;

    public IntNode() {
        // Alternatively, you could call: this(0, null);
        this.elem = 0;
        this.next = null;
    }

    public IntNode(int data) {
        // Alternatively, you could call: this(data, null);
        this.elem = data;
        this.next = null;
    }

    public IntNode(int data, IntNode next) {
        this.elem = data;
        this.next = next;
    }
}