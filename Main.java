import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        
        Tree tree = new Tree();
        tree.insertLeaf(7);
        tree.insertLeaf(4);
        tree.insertLeaf(1);
        tree.insertLeaf(6);
        tree.insertLeaf(9);
        tree.insertLeaf(8);
        tree.insertLeaf(10);
        System.out.println(tree.find(6));
       tree.deleteLeaf(7);
       System.out.println(tree.find(6));;        


    }
}


