import java.util.*;

class BinaryTree<E extends Comparable<E>> {
    private TreeNode<E> root;

    private static class TreeNode<E> {
        E data;
        TreeNode<E> left, right;

        public TreeNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(E data) {
        root = insertRecursively(root, data);
    }

    private TreeNode<E> insertRecursively(TreeNode<E> root, E data) {
        if (root == null) {
            return new TreeNode<>(data);
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insertRecursively(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRecursively(root.right, data);
        }

        return root;
    }

    public List<E> inorderTraversal() {
        List<E> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    private void inorderTraversal(TreeNode<E> root, List<E> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root.data);
            inorderTraversal(root.right, result);
        }
    }
}
