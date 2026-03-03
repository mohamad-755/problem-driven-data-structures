public class lab5_4 {
    int val;
    lab5_4 left;
    lab5_4 right;

    lab5_4() {
    }

    lab5_4(int val) {
        this.val = val;
    }

    lab5_4(int val, lab5_4 left, lab5_4 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isSymmetric(lab5_4 root) {
        return isSymmetric(root);
    }

    public boolean isSymmetric(lab5_4 node1, lab5_4 node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        if (node1.val != node2.val) {
            return false;
        }

        return isSymmetric(node1.left, node2.right) && isSymmetric(node2.left, node1.right);
    }
}