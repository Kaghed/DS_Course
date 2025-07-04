import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphWthArray {
public class Node{

    char data;

    Node(char data){
        this.data = data;
    }
}
ArrayList<Node> nodes;
int [][] matrix;

GraphWthArray(int size){
    matrix= new int[size][size];  
    nodes = new ArrayList<>();
  
}

public void addNode(char ch){
     Node node = new Node(ch);
     nodes.add(node);
}
public void addEdge(int src , int dst){
    matrix[src][dst] = 1;
}

public boolean checkEdge(int src , int dst){
    return matrix[src][dst]==1 ? true:false;

}

public void print(){


   for(Node node : nodes)
   System.out.print(node.data + " ");

   System.out.println();

    for(int i=0;i<matrix.length;i++){
        System.out.println(nodes.get(i).data);
    for(int j=0;j<matrix[i].length;j++){
        System.out.print(matrix[i][j] + " ");
    }
System.out.println();
    
}
}

public void DFS(int src){
boolean[] visited = new boolean[matrix.length];
    dfsHelper(src , visited);
}

private void dfsHelper(int src , boolean[]visited){
    if(visited[src]){
        return;
    }
    visited[src] = true;
    System.out.println(nodes.get(src).data + " = visited");

    for(int i=0;i<matrix[src].length;i++)
    if(matrix[src][i] == 1 && !visited[i])
    dfsHelper(i, visited);


    return;
}

public void BFS(int src){
    Queue <Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[matrix.length];
    queue.offer(src);
    visited[src] = true;

    while(queue.size()!=0){
        src = queue.poll();
        System.out.println(nodes.get(src).data + " visited");
    

    for(int i=0;i<matrix[src].length;i++){
        if(matrix[src][i] == 1 && !visited[i]){
        queue.offer(i);
        visited[i] = true;
    }
         
    }
}
}

}
