
public class Tree {

    private class Node {

        private int value;
        private Node left, right;

        public Node(int value) {
            this.value = value;
        }

    }

    private Node root;

    public void insert(int value) {

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


    public void traversePreOrder(){
        traversePreOrder(root);
    } 
    private void traversePreOrder(Node root){

        
        if(root != null){

            System.out.println(root.value);
            traversePreOrder(root.left);
            traversePreOrder(root.right);
        }

    }

    
    public void traverseInOrder(){
        traverseInOrder(root);
    } 
    private void traverseInOrder(Node root){

        
        if(root != null){

            traverseInOrder(root.left);
            System.out.println(root.value);
            traverseInOrder(root.right);
        }

    }

    
    public void traversePostOrder(){
        traversePostOrder(root);
    } 

    private void traversePostOrder(Node root){

        
        if(root != null){

            traversePostOrder(root.left);
            traversePostOrder(root.right);
            System.out.println(root.value);
            
        }

    }

    private boolean isLeaf(Node node){
     
        return node.left == null && node.right == null; 
    }

    public int height(){
        return height(root);
    }

    private int height(Node root){
       
        if(isLeaf(root))
        return 0;

        return 1+ Math.max(
            height(root.left), 
            height(root.right));
    }

    public int min(){
        return min(root);
    }
    private int min(Node root){

        if(isLeaf(root)) return root.value;
    
        var left = min(root.left);
        var right = min(root.right);

        return Math.min(root.value, Math.min(left, 
        right));
    }

    

 


}
