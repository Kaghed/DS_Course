
public class Tree {

    private class Node {

        private int value;
        private Node left, right;

        public Node(int value) {
            this.value = value;
        }

    }

    private Node root;

    public void insertLeaf(int value) {

        if (root == null) {
            root = new Node(value);
            return;
        }
        
        var current = root;

        Node node = new Node(value);

        while (true) {

            if (current.value < value) {

                if (current.right == null) {
                    current.right = node;
                    break;
                }

                current = current.right;

            } else if (current.value > value) {

                if (current.left == null) {
                    current.left = node;
                    break;
                }

                current = current.left;
            }

        }

    }

    public void deleteLeaf(int value) {
        Node node = new Node(value);
        deleteLeaf(node);
    }

    private void deleteLeaf(Node node) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        if (root.left != null || root.right != null) {
            System.out.println("It is not a leaf");
            return;
        }
        Node current = root;

        while (current != null) {
            if (current.left.value == node.value) {
                current.left = null;
                return;
            }
            if (current.right.value == node.value) {
                current.right = null;
                return;
            }

            if (node.value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        System.out.println("Node not found");
    }

    public boolean find(int value) {

        var current = root;

        while (current != null) {

            if (current.value < value) {
                current = current.right;
            } else if (current.value > value) {
                current = current.left;
            } else
                return true;
        }
        return false;
    }

    static Node delNode(Node root, int x) {
      
        if (root == null) {
            return root;
        }
 
        if (root.value > x) {
            root.left = delNode(root.left, x);
        } else if (root.value < x) {
            root.right = delNode(root.right, x);
        } else {
           
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            Node succ = getSuccessor(root);
            root.value = succ.value;
            root.right = delNode(root.right, succ.value);
        }
        return root;
    }
 
    static Node getSuccessor(Node curr) {
        curr = curr.right;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }  
   

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root) {

        if (root != null) {

            System.out.println(root.value);
            traversePreOrder(root.left);
            traversePreOrder(root.right);
        }

    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node root) {

        if (root != null) {

            traverseInOrder(root.left);
            System.out.println(root.value);
            traverseInOrder(root.right);
        }

    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root) {

        if (root != null) {

            traversePostOrder(root.left);
            traversePostOrder(root.right);
            System.out.println(root.value);

        }

    }

    private boolean isLeaf(Node node) {

        return node.left == null && node.right == null;
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {

        if (isLeaf(root))
            return 0;

        return 1 + Math.max(
                height(root.left),
                height(root.right));
    }

    public int min() {
        return min(root);
    }

    private int min(Node root) {

        if (isLeaf(root))
            return root.value;

        var left = min(root.left);
        var right = min(root.right);

        return Math.min(root.value, Math.min(left,
                right));
    }

    public int max() {
        return max(root);
    }

    private int max(Node root) {

        if (isLeaf(root))
            return root.value;

        var left = max(root.left);
        var right = max(root.right);

        return Math.max(root.value, Math.max(left,
                right));
    }

    public int bstMin() {
        if (root == null)
            throw new IllegalStateException();

        var current = root;
        var last = current;

        while (current != null) {
            last = current;
            current = current.left;
        }
        return last.value;
    }

    public int bstMax() {
        return bstMax(root);
    }

    private int bstMax(Node root) {

        if (root == null)
            return 0;

        if (root.right == null)
            return root.value;

        return bstMax(root.right);

    }

    public boolean equals(Tree tree) {

        if (tree == null)
            return false;

        return equals(root, tree.root);
    }

    private boolean equals(Node node1, Node node2) {

        if (node1 == null && node2 == null)
            return true;

        if (node1 != null && node2 != null) {
            return (node1.value == node2.value)
                    && equals(node1.left, node2.left)
                    && equals(node1.right, node2.right);
        }

        // if( (node1 == null && node2 != null) || (node1 != null && node2 == null) )
        return false;

    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node root, int min, int max) {
        if (root == null)
            return true;

        if (root.value <= min || root.value >= max) {
            return false;
        }

        return isBinarySearchTree(root.left, min, root.value - 1)
                && isBinarySearchTree(root.right, root.value + 1, max);

    }

    public void swapRoot() {
        var temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public void NodesAtKDistance(int distance) {
        NodesAtKDistance(root, distance);
    }

    private void NodesAtKDistance(Node root, int distance) {

        if (root == null)
            return;

        if (distance == 0) {
            System.out.println(root.value);
            return;
        }
        if(root.left!=null)
        NodesAtKDistance(root.left, distance - 1);
        if(root.right!=null)
        NodesAtKDistance(root.right, distance - 1);

    }

    public void traverseLevelOrder(){
        traverseLevelOrder(root);
    } 
    private void traverseLevelOrder(Node root) {
        for (int i = 0; i <= height(root); i++) {
            NodesAtKDistance(i);
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {

        if (root == null)
            return 0;

        return 1 + size(root.left) +
                size(root.right);

    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node root) {

        if (root == null)
            return 0;
        if (isLeaf(root))
            return 1;

        return countLeaves(root.left) +
                countLeaves(root.right);

    }

    public boolean areSibling(int value1, int value2) {
        return areSibling(root, value1, value2);
    }

    private boolean areSibling(Node root, int value1, int value2) {
        if (root == null)
            return false;

        if (root.left != null && root.right != null) {
            if ((root.left.value == value1 && root.right.value == value2) ||
                    (root.left.value == value2 && root.right.value == value1)) {
                return true;
            }
        }

        return areSibling(root.left, value1, value2) || areSibling(root.right, value1, value2);
    }

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node root, int value) {

        if (root == null)
            return false;

        if (root.value == value)
            return true;
        var left = contains(root.left, value);
        var right = contains(root.right, value);

        return left || right;
    }

    public boolean findInBT(int value) {
        return findInBT(root, value);
    }

    private boolean findInBT(Node root, int value) {

        if (root == null)
            return false;

        if (root.value == value)
            return true;

        return findInBT(root.left, value) || findInBT(root.right, value);
    }

}
