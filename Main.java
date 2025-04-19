import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        
        Tree tree = new Tree();
        tree.insert(7);
        tree.insert(4);
        tree.insert(1);
        tree.insert(6);
        tree.insert(9);
        tree.insert(8);
        tree.insert(10 );
        Tree tree2 = new Tree();
        tree2.insert(7);
        tree2.insert(4);
        tree2.insert(1);
        tree2.insert(6);
        tree2.insert(9);
        tree2.insert(10 );
        tree2.insert(8);
    
        
        System.out.println(tree.equals(tree2));
        


    }
}


