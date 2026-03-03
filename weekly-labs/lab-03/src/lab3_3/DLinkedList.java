package lab3_3;

public class DLinkedList {

    // Doubly linked node storing an integer

    public DNode head;
    public DNode tail;
    private int size;

    /** Constructs an initially empty doubly linked list. */
    public DLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Adds an integer to the front of the list. */
    public void addFirst(int data) {
        DNode newNode = new DNode(data, null, head);
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    /** Adds an integer to the end of the list. */
    public void addLast(int data) {
        DNode newNode = new DNode(data, tail, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    /** Returns a string representation of the list. */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        DNode current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // -------------------------------------------------------
    // 1. Reverse the list in place (without extra space)
    // -------------------------------------------------------
    public void reverse() {
        DNode current = head;
        DNode temp = null;
        // Traverse the list and swap the next and prev for each node.
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            // After swapping, move to what was originally next.
            current = current.prev;
        }
        // Swap head and tail.
        temp = head;
        head = tail;
        tail = temp;
    }

    // -------------------------------------------------------
    // 2. Remove the first node holding the given data value.
    // -------------------------------------------------------
    public void remove(int data) {
        DNode current = head;
        while (current != null) {
            if (current.data == data) {
                // Found the node to remove.
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        // List becomes empty.
                        tail = null;
                    }
                } else if (current == tail) {
                    tail = current.prev;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null;
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return; // Only remove the first occurrence.
            }
            current = current.next;
        }
    }

    // -------------------------------------------------------
    // 3. Clean the list by removing duplicate integers.
    // For example, [1 5 9 5 1 3 3 1 1] becomes [1 5 9 3]
    // -------------------------------------------------------
    public void clean() {
        DNode current = head;
        while (current != null) {
            DNode runner = current.next;
            while (runner != null) {
                if (runner.data == current.data) {
                    // Duplicate found; remove runner.
                    DNode nextRunner = runner.next;
                    if (runner == tail) {
                        tail = runner.prev;
                        tail.next = null;
                    } else {
                        runner.prev.next = runner.next;
                        runner.next.prev = runner.prev;
                    }
                    size--;
                    runner = nextRunner;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    // -------------------------------------------------------
    // 4. Merge with another sorted doubly linked list.
    // Merges two sorted lists into a new sorted list without
    // allocating or deleting any nodes. Only one pass is made
    // through each list.
    // @param head2 the head of the second sorted list
    // @return the head of the merged sorted list
    // -------------------------------------------------------
    public DNode merge(DNode head2) {
        DNode p = this.head;
        DNode q = head2;

        // If either list is empty, return the other.
        if (p == null)
            return q;
        if (q == null)
            return p;

        DNode mergedHead, mergedTail;
        // Initialize mergedHead to the smaller of the two first nodes.
        if (p.data <= q.data) {
            mergedHead = p;
            p = p.next;
        } else {
            mergedHead = q;
            q = q.next;
        }
        mergedHead.prev = null; // It becomes the first node.
        mergedTail = mergedHead;

        // Merge the two lists by adjusting pointers.
        while (p != null && q != null) {
            if (p.data <= q.data) {
                mergedTail.next = p;
                p.prev = mergedTail;
                mergedTail = p;
                p = p.next;
            } else {
                mergedTail.next = q;
                q.prev = mergedTail;
                mergedTail = q;
                q = q.next;
            }
        }
        // Append any remaining nodes.
        if (p != null) {
            mergedTail.next = p;
            p.prev = mergedTail;
        } else if (q != null) {
            mergedTail.next = q;
            q.prev = mergedTail;
        }
        return mergedHead;
    }
}
