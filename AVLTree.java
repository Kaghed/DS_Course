public class AVLTree {

    private class Node {

        private int value;
        private Node left, right;
        private int Nheight = 0;
        
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

        
        setHeight(root);
            
            return balance(root);
 
    }

    private Node balance(Node root){
        if(isLeftHeavy(root)){
            if(balanceFactor(root.left) <= 0)
             root.left = leftRotate(root.left);
              return  rightRotate(root);
    
    }else if(isRightHeavy(root)){
        if(balanceFactor(root.right)>0)
           root.right = rightRotate(root.right);
          return  leftRotate(root);        

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
    node.Nheight = Math.max(
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

        return (root == null) ? -1 :  root.Nheight;
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
                return current.Nheight;

        }

    }
}
