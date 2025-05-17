import java.io.*;
import java.util.*;

    public class Main {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        AVLTree tree = new AVLTree();
        tree.insertLeaf(10);
        tree.insertLeaf(30);
        tree.insertLeaf(20);
    
        tree.traversePre();

    }
}


