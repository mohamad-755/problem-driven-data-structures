package lab3_2;
import java.util.ArrayList;

public class IntLinkedList {

    // public for testing purpose
    public IntNode head;

    public IntLinkedList() {
        head = null;
    }

    // ---------------------- Basic Methods from Lecture ---------------------

    public void addFirst(int value) {
        IntNode v = new IntNode(value);
        v.next = head;
        head = v;
    }

    public int first() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        return head.elem;
    }

    public void removeFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        head = head.next;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public String toString() {
        if (head == null) {
            return "[]";
        } else {
            String result = "[" + head.elem;
            IntNode current = head.next;
            while (current != null) {
                result += ", " + current.elem;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }

    // ---------------------- Required Additional Methods ---------------------

    /**
     * 1. deleteAll(int n):
     * Deletes all nodes whose element equals the given integer n.
     * For example, if n is 7 and the list is [1,3,7,4,3,7,2],
     * the resulting list is [1,3,4,3,2].
     */
    public void deleteAll(int n) {
        // Remove matching nodes at the head.
        while (head != null && head.elem == n) {
            head = head.next;
        }
        // Remove matching nodes after the head.
        IntNode current = head;
        while (current != null && current.next != null) {
            if (current.next.elem == n) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    /**
     * 2. indexesOf(int n):
     * Returns an ArrayList containing the zero-based positions of all occurrences
     * of n in the list. For example, if n is 5 and the list is [2,4,5,8,1,5,3],
     * the result is [2, 5].
     */
    public ArrayList<Integer> indexesOf(int n) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int index = 0;
        IntNode current = head;
        while (current != null) {
            if (current.elem == n) {
                indexes.add(index);
            }
            index++;
            current = current.next;
        }
        return indexes;
    }

    /**
     * 3. join(IntNode n):
     * Given another sorted linked list (whose head is n), merge (join) the current list
     * with the other list into a new sorted list that includes all elements (duplicates allowed).
     * For example, joining [2,3,4,8,12] with [1,4,5,7,9] produces [1,2,3,4,4,5,7,8,9,12].
     * This method creates and returns the head of the new list.
     */
    public IntNode join(IntNode n) {
        IntNode dummy = new IntNode(0);  // Dummy node to ease list construction.
        IntNode tail = dummy;
        IntNode p = this.head;
        IntNode q = n;
        // Merge by choosing the smaller element at each step.
        while (p != null && q != null) {
            if (p.elem <= q.elem) {
                tail.next = new IntNode(p.elem);
                tail = tail.next;
                p = p.next;
            } else {
                tail.next = new IntNode(q.elem);
                tail = tail.next;
                q = q.next;
            }
        }
        // Attach any remaining nodes.
        while (p != null) {
            tail.next = new IntNode(p.elem);
            tail = tail.next;
            p = p.next;
        }
        while (q != null) {
            tail.next = new IntNode(q.elem);
            tail = tail.next;
            q = q.next;
        }
        return dummy.next;
    }

    /**
     * 4. union(IntNode n):
     * Given another sorted linked list (whose head is n), return a new sorted linked list
     * that represents the union (set union) of the two lists.
     * Each distinct element appears only once in the resulting list.
     * For example, the union of [1,3,4,7,7,12] and [1,5,7,9] is [1,3,4,5,7,9,12].
     */
    public IntNode union(IntNode n) {
        IntNode dummy = new IntNode(0);  // Dummy node for result list.
        IntNode tail = dummy;
        IntNode p = this.head;
        IntNode q = n;
        // We'll use 'last' to keep track of the last inserted element to avoid duplicates.
        int last = Integer.MIN_VALUE;
        boolean hasLast = false; // Indicates whether any element has been added.

        while (p != null && q != null) {
            if (p.elem < q.elem) {
                if (!hasLast || p.elem != last) {
                    tail.next = new IntNode(p.elem);
                    tail = tail.next;
                    last = p.elem;
                    hasLast = true;
                }
                p = p.next;
            } else if (p.elem > q.elem) {
                if (!hasLast || q.elem != last) {
                    tail.next = new IntNode(q.elem);
                    tail = tail.next;
                    last = q.elem;
                    hasLast = true;
                }
                q = q.next;
            } else { // p.elem == q.elem
                if (!hasLast || p.elem != last) {
                    tail.next = new IntNode(p.elem);
                    tail = tail.next;
                    last = p.elem;
                    hasLast = true;
                }
                p = p.next;
                q = q.next;
            }
        }
        // Append the remaining elements from list p.
        while (p != null) {
            if (!hasLast || p.elem != last) {
                tail.next = new IntNode(p.elem);
                tail = tail.next;
                last = p.elem;
                hasLast = true;
            }
            p = p.next;
        }
        // Append the remaining elements from list q.
        while (q != null) {
            if (!hasLast || q.elem != last) {
                tail.next = new IntNode(q.elem);
                tail = tail.next;
                last = q.elem;
                hasLast = true;
            }
            q = q.next;
        }
        return dummy.next;
    }

    /**
     * 5. printRecursive():
     * Recursively prints all the numbers in the list (space separated).
     */
    public void printRecursive() {
        printRecursiveHelper(head);
        System.out.println(); // Move to a new line after printing.
    }

    // Helper method for recursion.
    private void printRecursiveHelper(IntNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.elem + " ");
        printRecursiveHelper(node.next);
    }

    /**
     * 6. hasCycle():
     * Uses Floyd’s Cycle-Finding algorithm to detect if there is a cycle in the list.
     * Returns true if a cycle is found; otherwise, returns false.
     */
    public boolean hasCycle() {
        if (head == null) {
            return false;
        }
        IntNode slow = head;
        IntNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Moves one step.
            fast = fast.next.next;      // Moves two steps.
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 7. getIntersectionNode(IntNode n):
     * Given the head (n) of a second linked list, returns the node at which the two linked lists
     * intersect. Intersection is defined by reference, not by value.
     * If the lists do not intersect, returns null.
     * This method assumes there are no cycles in either list.
     */
    public IntNode getIntersectionNode(IntNode otherHead) {
        if (this.head == null || otherHead == null) {
            return null;
        }
        // Get tail and length for the first list.
        IntNode curr1 = this.head;
        int len1 = 1;
        while (curr1.next != null) {
            curr1 = curr1.next;
            len1++;
        }
        IntNode tail1 = curr1;

        // Get tail and length for the second list.
        IntNode curr2 = otherHead;
        int len2 = 1;
        while (curr2.next != null) {
            curr2 = curr2.next;
            len2++;
        }
        IntNode tail2 = curr2;

        // If the two lists do not share the same tail, they do not intersect.
        if (tail1 != tail2) {
            return null;
        }

        // Set pointers to the start of each list.
        curr1 = this.head;
        curr2 = otherHead;

        // Advance the pointer for the longer list by the length difference.
        int diff = Math.abs(len1 - len2);
        if (len1 > len2) {
            for (int i = 0; i < diff; i++) {
                curr1 = curr1.next;
            }
        } else {
            for (int i = 0; i < diff; i++) {
                curr2 = curr2.next;
            }
        }

        // Move both pointers until they collide at the intersection node.
        while (curr1 != curr2) {
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return curr1;  // (or curr2—they are equal)
    }
}