/**
 * This class defines basic information about the nodes in the graph
 * @author Candice Williams
 */
public class GraphNode {
    
    // Instance variables to store information about the node 
    private int name;
    private boolean mark;

    /**
     * Constructor to create a new graph node with a given name
     * @param name of the graph node
     */
    public GraphNode(int name){
        this.name = name;
        this.mark = false;

    }

    /**
     * Method to mark or unmark the node
     * @param mark staus of node
     */
    public void mark(boolean mark){
        this.mark = mark;
    }

    /**
     * Method to check if the node is marked
     * @return true if the node in question is marked; false otherwise
     */
    public boolean isMarked(){
        return mark;
    }

    /**
     * Method to get the name of the node
     * @return the name of the node
     */
    public int getName(){
        return name;
    }
}
