package lab3_3;

public class DNode {

    int data;
    DNode prev;
    DNode next;

    public DNode(int data, DNode prev, DNode next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public String toString() {
        return Integer.toString(data);
    }
}
