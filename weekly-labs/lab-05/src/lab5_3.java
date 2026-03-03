import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

// An Node object is one node in a binary tree of ints.
class Node {
    private int element; // data stored at this node
    private Node left; // reference to left subtree
    private Node right; // reference to right subtree
    private Node parent; // a reference to the parent node (if any)

    // Constructs a leaf node with the given data.
    public Node(int data) {
        this(data, null, null, null);
    }

    // Constructs a branch node with the given data and links.
    public Node(int data, Node above, Node leftChild, Node rightChild) {
        element = data;
        parent = above;
        left = leftChild;
        right = rightChild;
    }

    // accessor methods
    public int getElement() {
        return element;
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    // update methods
    public void setElement(int e) {
        element = e;
    }

    public void setParent(Node parentNode) {
        parent = parentNode;
    }

    public void setLeft(Node leftChild) {
        left = leftChild;
    }

    public void setRight(Node rightChild) {
        right = rightChild;
    }

    public boolean isInternal() {
        return numChildren() > 0;
    }

    public boolean isExternal() {
        return numChildren() == 0;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public int numChildren() {
        int count = 0;
        if (left != null)
            count++;
        if (right != null)
            count++;
        return count;
    }

    public Node sibling() {
        if (parent == null)
            return null; // p must be the root
        if (this == parent.left) // p is a left child
            return parent.right; // (right child might be null)
        else // p is a right child
            return parent.left; // (left child might be null)
    }

    public List<Node> children() {
        List<Node> snapshot = new ArrayList<>(2); // max capacity of 2
        if (left != null)
            snapshot.add(left);
        if (right != null)
            snapshot.add(right);
        return snapshot;
    }
}

class LinkedBinaryTree {

    private Node root = null; // root of the tree
    private int size = 0; // number of nodes in the tree

    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // Returns the number of nodes in the tree.
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the root of the tree (or null if tree is empty).
    public Node root() {
        return root;
    }

    // Places element e at the root of an empty tree and returns it.
    public Node addRoot(int e) throws IllegalStateException {
        if (!isEmpty())
            throw new IllegalStateException("Tree is not empty");
        root = new Node(e, null, null, null);
        size = 1;
        return root;
    }

    // Creates a new left child of Position p storing element e and returns its
    // Position.
    public Node addLeft(Node p, int e) throws IllegalArgumentException {
        if (p == null)
            throw new IllegalArgumentException("node does not exist");
        if (p.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node child = new Node(e, p, null, null);
        p.setLeft(child);
        size++;
        return child;
    }

    // Creates a new right child of Position p storing element e and returns its
    // Position.
    public Node addRight(Node p, int e)
            throws IllegalArgumentException {
        if (p == null)
            throw new IllegalArgumentException("node does not exist");
        if (p.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        Node child = new Node(e, p, null, null);
        p.setRight(child);
        size++;
        return child;
    }

    // Attaches trees t1 and t2, respectively, as the left and right subtree of the
    // leaf Position p. As a side effect, t1 and t2 are set to empty trees.
    public void attach(Node p, LinkedBinaryTree t1,
            LinkedBinaryTree t2) throws IllegalArgumentException {
        if (p == null)
            throw new IllegalArgumentException("node does not exist");
        if (p.isInternal())
            throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) { // attach t1 as left subtree of node
            t1.root.setParent(p);
            p.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) { // attach t2 as right subtree of node
            t2.root.setParent(p);
            p.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    // Removes the node p and replaces it with its child, if any.
    public int remove(Node p) throws IllegalArgumentException {
        if (p == null)
            throw new IllegalArgumentException("node does not exist");
        Node node = p;
        if (p.numChildren() == 2)
            throw new IllegalArgumentException("p has two children");
        Node child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null)
            child.setParent(node.getParent()); // child's grandparent becomes its parent
        if (node == root)
            root = child; // child becomes root
        else {
            Node parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        int temp = node.getElement();
        // help garbage collection
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node); // our convention for defunct node
        return temp;
    }

    // return a list of nodes
    public List<Node> nodes() {
        return preorder();
    }

    // Returns a list of nodes of the tree, reported in inorder.
    public List<Node> inorder() {
        List<Node> snapshot = new ArrayList<>();
        if (!isEmpty())
            inorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }

    private void inorderSubtree(Node p, List<Node> snapshot) {
        if (p.getLeft() != null)
            inorderSubtree(p.getLeft(), snapshot);
        snapshot.add(p);
        if (p.getRight() != null)
            inorderSubtree(p.getRight(), snapshot);
    }

    // Returns a list of nodes of the tree, reported in preorder.
    public List<Node> preorder() {
        List<Node> snapshot = new ArrayList<>();
        if (!isEmpty())
            preorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }

    private void preorderSubtree(Node p, List<Node> snapshot) {
        snapshot.add(p); // for preorder, we add position p before exploring subtrees
        for (Node c : p.children())
            preorderSubtree(c, snapshot);
    }

    // Returns a list of nodes of the tree, reported in postorder.
    public List<Node> postorder() {
        List<Node> snapshot = new ArrayList<>();
        if (!isEmpty())
            postorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }

    private void postorderSubtree(Node p, List<Node> snapshot) {
        for (Node c : p.children())
            postorderSubtree(c, snapshot);
        snapshot.add(p); // for postorder, we add position p after exploring subtrees
    }

    void expandExternal(Node v, int lval, int rval) {
        Node left = new Node(lval); // create lef node
        v.setLeft(left); // add a new left child
        left.setParent(v); // v is its parent

        Node right = new Node(rval); // create lef node
        v.setRight(right); // add a new left child
        right.setParent(v); // v is its parent

        size = size + 2; // two more nodes
    }

    // A contruct to build a large tree for testing
    public LinkedBinaryTree(int level) {
        root = buildTree(3, 0, level);

    }

    // Builds a tree with certain number of levels.
    // the data value root at the root is n
    // then left child has value of 2*n
    // then right child has value of 2*n + 1
    public Node buildTree(int n, int counter, int level) {
        if (level == 0)
            return null;
        else if (counter < level) {
            Node left = buildTree(2 * n, counter + 1, level);
            Node right = buildTree(2 * n + 1, counter + 1, level);
            size++;
            return new Node(n, null, left, right);
        } else
            return null;
    }

    // a method to dislay the tree
    public void printSideways() {
        printSideways(root, "");
    }

    // helper recursive method that prints the tree in a sideways indented format.
    private void printSideways(Node r, String indent) {
        if (r != null) {
            printSideways(r.getRight(), indent + "    ");
            System.out.println(indent + r.getElement());
            printSideways(r.getLeft(), indent + "    ");
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null)
            return -1;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node node) {
        if (node == null)
            return 0;
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
        return countLeaves(node.getLeft()) + countLeaves(node.getRight());
    }

    public List<Integer> levelOrder() {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            result.add(current.getElement());

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }

        return result;
    }

    public boolean equals(LinkedBinaryTree other) {
        if (other == null)
            return false;
        return equals(this.root, other.root);
    }

    private boolean equals(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        if (node1.getElement() != node2.getElement()) {
            return false;
        }

        return equals(node1.getLeft(), node2.getLeft()) &&
                equals(node1.getRight(), node2.getRight());
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null)
            return true;

        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());

        if (Math.abs(leftHeight - rightHeight) > 1)
            return false;

        return isBalanced(node.getLeft()) && isBalanced(node.getRight());
    }

    public void mirror() {
        mirror(root);
    }

    private void mirror(Node node) {
        if (node == null)
            return;

        Node temp = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(temp);

        mirror(node.getLeft());
        mirror(node.getRight());
    }
}
