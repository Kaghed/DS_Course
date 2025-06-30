public class AVLTree {

    private class Node {

        private int value;
        private Node left, right;
        private int height = 0;
        
        public Node(int value) {
            this.value = value;
        }

    }

    private Node root;

    public void insertLeaf(int value) {
        root = insertLeaf(root, value);
    }

    private Node insertLeaf(Node root, int value) {

        if (root == null) {

            return new Node(value);
        }

        if (root.value > value) {
            root.left = insertLeaf(root.left, value);

        } else if (root.value < value) {
            root.right = insertLeaf(root.right, value);

        }

        
        root.height = 1+ Math.max(
                height(root.left), height(root.right)) ;

            
               balance(root);

        return root;
    }

    private Node balance(Node root){
        if(isLeftHeavy(root)){
            if(balanceFactor(root.left) <= 0)
             root.left= leftRotate(root.left);
               return rightRotate(root);
    
    }else if(isRightHeavy(root)){
        if(balanceFactor(root.right)>0)
       root.right= rightRotate(root.right);
      return  rightRotate(root);        

    }
       return root; 
    }

    private boolean isLeftHeavy(Node node){
        return balanceFactor(node) >1; 
    }

    private boolean isRightHeavy(Node node){
        return balanceFactor(node) <-1; 

    }

    public int balanceFactor(Node node){
        return node==null ? 0 : height(node.left) - height(node.right);
    }

    private Node leftRotate(Node root){
    Node newRoot = root.right;
    root.right = newRoot.left;
    newRoot.left = root;

   setHeight(root);
    setHeight(newRoot);
    return newRoot;    
}

private Node rightRotate(Node root){
    Node newRoot = root.left;
    root.left = newRoot.right;
    newRoot.right = root;

    setHeight(root);
    setHeight(newRoot);

    return newRoot;    
}


private void setHeight(Node node){
    node.height = Math.max(
        height(node.left),
        height(node.right)) + 1;   
}

    public void traversePre() {
        traversePre(root);
    }

    private void traversePre(Node root) {

        if (root != null) {

            System.out.println(root.value);
            traversePre(root.left);
            traversePre(root.right);
        }
    }

    private int height(Node root) {

        return (root == null) ? -1 :  root.height;
    }

    private boolean isLeaf(Node node) {

        return node.left == null && node.right == null;
    }

    public int getHeight(int value) {
        return getHeight(root, value);
    }

    private int getHeight(Node root, int value) {
        if (root == null) {
            return 0;
        }

        var current = root;

        while (true) {

            if (current.value < value) {

                if (current.right == null)
                    return 0;
                current = current.right;

            } else if (current.value > value) {
                if (current.left == null)
                    return 0;
                current = current.left;
            } else
                return current.height;

        }

    }

    private Node minValueNode(Node node) {
        Node current = node;

        // loop down to find the leftmost leaf
        while (current.left != null)
            current = current.left;

        return current;
    }

    // Recursive function to delete a node with 
    // given value from subtree with given root. 
    // It returns root of the modified subtree.
    private Node deleteNode(Node root, int value) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the value to be deleted is smaller 
        // than the root's value, then it lies in 
        // left subtree
        if (value < root.value)
            root.left = deleteNode(root.left, value);

        // If the value to be deleted is greater 
        // than the root's value, then it lies in 
        // right subtree
        else if (value > root.value)
            root.right = deleteNode(root.right, value);

        // if value is same as root's value, then 
        // this is the node to be deleted
        else {
            // node with only one child or no child
            if ((root.left == null) || 
                (root.right == null)) {
                Node temp = root.left != null ? 
                            root.left : root.right;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                    root = temp; // Copy the contents of 
                                 // the non-empty child
            } else {
                // node with two children: Get the 
                // inorder successor (smallest in 
                // the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's 
                // data to this node
                root.value = temp.value;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.value);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left), 
                               height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS 
        // NODE (to check whether this node 
        // became unbalanced)
        int balance = balanceFactor(root);

        // If this node becomes unbalanced, then 
        // there are 4 cases

        // Left Left Case
        if (balance > 1 && balanceFactor(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && balanceFactor(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && balanceFactor(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && balanceFactor(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

}
