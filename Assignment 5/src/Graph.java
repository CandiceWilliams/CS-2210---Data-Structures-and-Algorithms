/**
 * This class implements the GraphADT to dictates how the graph, its edges, and its node will be stored.
 * The program uses an adjaceny matrix to store node values in their respected slots
 * @author Candice Williams
 */
import java.util.*;

public class Graph implements GraphADT{
    
    //instance variables
    private GraphNode[] node;
    private GraphEdge[][] matrix;

    /**
     * Constructor to initialise the graph with 'n' nodes
     * @param n size of graph
     */
    public Graph(int n){
        node = new GraphNode[n];
        matrix = new GraphEdge[n][n];

        // Initialize each node in the graph
        for (int i = 0; i < n; i++){
            node[i] = new GraphNode(i);
        }
    }

    /** 
     * Method to insert an undirected edge between two nodes in the graph
     * @param u first endpoint of edge
     * @param v second endpoint of edge
     * @param edgeType the type of edge. 
     * @param label the label of the edge. 
     * @throws GraphException if the edge already exists within the graph or if the specified nodes do not exist
    */
    public void insertEdge(GraphNode u, GraphNode v, int edgeType, String label)throws GraphException{
        //checks if the nodes exist in the graph
        if (isInGraph(u, v)){
            //checks if the edges already exists
            if(matrix[u.getName()][v.getName()] == null && matrix[v.getName()][u.getName()] == null){
                //create a new edge and update adjaceny matrix
                matrix[u.getName()][v.getName()] = new GraphEdge(u, v, edgeType, label);
                matrix[v.getName()][u.getName()] = new GraphEdge(u, v, edgeType, label);
            }

            else{
                throw new GraphException("Edge already exists");
            }
        }
        else{
            throw new GraphException("Nodes do not exist");
        }
    }

    
    /**
     * Method to get the edge between two node
     * @param u First endpoint
     * @param v Second endpoint
     * @throws GraphException if the edge is not in the graph or if the nodes do not exist within the graph
     * @return the edge of the matrix you're searching for
     */
    public GraphEdge getEdge(GraphNode u, GraphNode v)throws GraphException{
        if (isInGraph(u, v)){
            //checks if edge exists
            if (matrix[u.getName()][v.getName()] == null && matrix[v.getName()][u.getName()] == null){
                throw new GraphException("Edge not in Graph");
            }
            else return matrix[u.getName()][v.getName()];
        }
        else {
            throw new GraphException("Nodes not found");
        }
    }

    /**
     * Method to get a specific node in the graph
     * @param name the number the node is associated with in the graph
     * @throws GraphException if node is not in the graph
     * @return the node with the specified name
     */
    public GraphNode getNode(int name) throws GraphException{
        // Check if the node exists in the graph.
        if(name >= node.length || name < 0 ){
            throw new GraphException("Node not found");
        }
        return node[name];
    }

    /**
     * Method to get an iterator over edges incident to a node
     * @param u the node you are trying to find incident edges for
     * @throws GraphException if node u is not in the graph
     * @return the edges incident to u
     */
    public Iterator incidentEdges(GraphNode u) throws GraphException{
        if (isInGraph(u)){
            Stack incident = new Stack();
            // Iterate through the adjacency matrix to find incident edges
            for (int i = 0; i < matrix[u.getName()].length; i++){
                if (matrix[u.getName()][i] != null){
                    incident.push(matrix[u.getName()][i]);
                }
            }
            return incident.iterator();
        }

        else throw new GraphException("Node does not exist within graph");
    }

    /**
     * Method to check if two nodes are adjacent
     * @param u first node to be checked
     * @param v second node to be checked
     * @throws GraphException if nodes do not exist within the graph
     * @return true if the nodes are adajacent; Otherwise, false is returned
     */
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException{
        if (isInGraph(u, v)){
            // Check if there is an edge between the nodes
            if (matrix[u.getName()][v.getName()] == null){
                return false;
            }
            else return true;
        }

        else throw new GraphException("Nodes do not exist");
    }

    /**
     * Helper method to check if two nodes are in the graph
     * @param u first node to be checked
     * @param v second node to be checked
     * @return true if the nodes are in the graph; false otherwise
     */
    private boolean isInGraph(GraphNode u, GraphNode v){
        try{
            getNode(u.getName());
            getNode(v.getName());
            return true;
        }catch(GraphException e){
            return false;
        }
    }

    /**
     * Helper method to check if a single node is in the graph
     * @param u first node to be checked
     * @return true if the node exists within the graph; false otherwise
     */
    private boolean isInGraph(GraphNode u){
        try{
            getNode(u.getName());
            return true;
        }catch(GraphException e){
            return false;
        }
    }
}
