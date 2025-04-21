
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

    public int bstmin(){
        if (root == null)
        throw new IllegalStateException();
        
        var current = root;
        var last = current;

        while(current!= null){
            last = current;
            current=current.left;
        }
        return last.value;
    }


    public boolean equals(Tree tree){

        if(tree == null)
        return false;
        
        return equals(root , tree.root);
    }

    private boolean equals(Node node1 , Node node2){

        if(node1 == null && node2 == null)
        return true;
       
        if(node1 != null && node2 !=null){
            return (node1.value == node2.value)
            && equals(node1.left ,node2.left)
            && equals(node1.right , node2.right);
        }


       // if( (node1 == null && node2 != null) || (node1 != null && node2 == null) )
        return false;

    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
     }
  private boolean isBinarySearchTree(Node root , int min , int max){
     if(root == null)
     return true;
 
     if(root.value <= min || root.value >= max){
     return false;
     }
     
     return isBinarySearchTree(root.left, min,root.value-1)
    && isBinarySearchTree(root.right, root.value + 1, max);
    
 
 
 }
 public void swapRoot(){
     var temp = root.left;
     root.left = root.right;
     root.right = temp;
 }
 



}
