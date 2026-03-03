package lab3_3;

public class MainDLinkedList {
    // -------------------------------------------------------
    // Test client for the DLinkedList methods.
    // -------------------------------------------------------
    public static void main(String[] args) {
        // ----- Test reverse() -----
        DLinkedList list = new DLinkedList();
        list.addLast(3);
        list.addLast(9);
        list.addLast(5);
        list.addLast(1);
        System.out.println("Original list: " + list); // [3 9 5 1]
        list.reverse();
        System.out.println("Reversed list: " + list); // [1 5 9 3]

        // ----- Test remove(int data) -----
        DLinkedList list2 = new DLinkedList();
        list2.addLast(10);
        list2.addLast(20);
        list2.addLast(30);
        list2.addLast(20);
        list2.addLast(40);
        System.out.println("Before remove(20): " + list2); // [10 20 30 20 40]
        list2.remove(20);
        System.out.println("After remove(20): " + list2); // [10 30 20 40] (first 20 removed)

        // ----- Test clean() -----
        DLinkedList list3 = new DLinkedList();
        list3.addLast(1);
        list3.addLast(5);
        list3.addLast(9);
        list3.addLast(5);
        list3.addLast(1);
        list3.addLast(3);
        list3.addLast(3);
        list3.addLast(1);
        list3.addLast(1);
        System.out.println("Before clean(): " + list3); // [1 5 9 5 1 3 3 1 1]
        list3.clean();
        System.out.println("After clean(): " + list3); // [1 5 9 3]

        // ----- Test merge() -----
        // Create first sorted list: [2, 4, 6, 8]
        DLinkedList list4 = new DLinkedList();
        list4.addLast(2);
        list4.addLast(4);
        list4.addLast(6);
        list4.addLast(8);

        // Create second sorted list: [1, 3, 5, 7, 9]
        DLinkedList list5 = new DLinkedList();
        list5.addLast(1);
        list5.addLast(3);
        list5.addLast(5);
        list5.addLast(7);
        list5.addLast(9);

        // Merge the two lists.
        // Note: The merge method “consumes” the nodes of both lists by re‐wiring
        // pointers.
        DNode merged = list4.merge(list5.head);
        System.out.print("Merged sorted list: ");
        DNode current = merged;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
