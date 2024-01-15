/**
 * This class dictates how the edges of the graph will behave and initialises those edges
 * @author Candice Williams
 */

public class GraphEdge {

    // Instance variables to store information about the edg
    private GraphNode endpoint1, endpoint2;
    private int type;
    private String label;
    
    /**
     * Constructor to create a new edge between two nodes
     * @param u First node
     * @param v Second node
     * @param type the type of the node
     * @param label the label for the node
     */
    public GraphEdge(GraphNode u, GraphNode v, int type, String label){
        endpoint1 = u;
        endpoint2 = v;
        this.type = type;
        this.label = label;
    }

    /**
     * Method to get the first endpoint of the edge
     * @return the first endpoint
     */
    public GraphNode firstEndpoint(){
        return endpoint1;
    }

    /**
     * Method to get the second endpoint of the edge
     * @return the second endpoint
     */
    public GraphNode secondEndpoint(){
        return endpoint2;
    }

    /**
     * Method to get the type of the edge
     * @return the type
     */
    public int getType(){
        return type;
    }

    /**
     *  Method to set the type of the edge
     * @param newType the new type
     */
    public void setType(int newType){
        type = newType;
    }

    /**
     * Method to get the label of the edge
     * @return label of the edge
     */
    public String getLabel(){
        return label;
    }

    /**
     * Method to set the label of the edge
     * @param newLabel new label for edge
     */
    public void setLabel( String newLabel){
        label = newLabel;
    }
}
