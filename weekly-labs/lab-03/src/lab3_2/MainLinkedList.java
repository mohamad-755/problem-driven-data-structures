package lab3_2;

import java.util.ArrayList;

public class MainLinkedList {
    // ---------------------- For Testing Purposes ---------------------
    public static void main(String[] args) {
        // Build a sample list.
        IntLinkedList list = new IntLinkedList();
        list.addFirst(2);
        list.addFirst(7);
        list.addFirst(3);
        list.addFirst(7);
        list.addFirst(1);
        // List now: [1,7,3,7,2]
        System.out.println("Original list: " + list);

        // Test deleteAll: remove all 7's.
        list.deleteAll(7);
        System.out.println("After deleteAll(7): " + list);

        // Test indexesOf: find positions of 3 in list [1,3,?]
        list.addFirst(3); // Now list: [3,1,3,2]
        System.out.println("List after adding 3 at front: " + list);
        ArrayList<Integer> indexes = list.indexesOf(3);
        System.out.println("Indexes of 3: " + indexes);

        // Test join: merge two sorted lists.
        // First sorted list: 2->4->6
        IntLinkedList list1 = new IntLinkedList();
        list1.addFirst(6);
        list1.addFirst(4);
        list1.addFirst(2);
        // Second sorted list: 1->3->5->7
        IntNode list2Head = new IntNode(7, new IntNode(5, new IntNode(3, new IntNode(1))));
        IntNode joinedHead = list1.join(list2Head);
        System.out.print("Joined list: ");
        printList(joinedHead);

        // Test union: union of [1,3,4,7,7,12] and [1,5,7,9]
        // Create first union list:
        IntLinkedList unionList1 = new IntLinkedList();
        unionList1.addFirst(12);
        unionList1.addFirst(7);
        unionList1.addFirst(7);
        unionList1.addFirst(4);
        unionList1.addFirst(3);
        unionList1.addFirst(1);
        // Create second union list manually:
        IntNode unionList2 = new IntNode(9, new IntNode(7, new IntNode(5, new IntNode(1))));
        IntNode unionHead = unionList1.union(unionList2);
        System.out.print("Union list: ");
        printList(unionHead);

        // Test recursive print.
        System.out.print("Recursive print of unionList1: ");
        unionList1.printRecursive();

        // Test hasCycle.
        IntLinkedList cycleTest = new IntLinkedList();
        cycleTest.addFirst(3);
        cycleTest.addFirst(2);
        cycleTest.addFirst(1);
        // Create a cycle manually: point the last node's next to the head.
        IntNode tail = cycleTest.head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = cycleTest.head; // Cycle created.
        System.out.println("Does cycleTest have a cycle? " + cycleTest.hasCycle());

        // For getIntersectionNode, constructing two lists with an intersection.
        // List A: a1->a2->c1->c2->c3
        // List B: b1->b2->b3->c1->c2->c3
        IntNode c3 = new IntNode(30);
        IntNode c2 = new IntNode(20, c3);
        IntNode c1 = new IntNode(10, c2);
        // List A
        IntNode a2 = new IntNode(2, c1);
        IntNode a1 = new IntNode(1, a2);
        // List B
        IntNode b3 = new IntNode(13, c1);
        IntNode b2 = new IntNode(12, b3);
        IntNode b1 = new IntNode(11, b2);
        // Create two IntLinkedList instances.
        IntLinkedList listA = new IntLinkedList();
        listA.head = a1;
        IntLinkedList listB = new IntLinkedList();
        listB.head = b1;
        IntNode intersection = listA.getIntersectionNode(listB.head);
        System.out.println("Intersection node: " + (intersection != null ? intersection.elem : "null"));
    }

    // Helper method to print a list given its head.
    public static void printList(IntNode head) {
        System.out.print("[");
        IntNode current = head;
        while (current != null) {
            System.out.print(current.elem);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }

}