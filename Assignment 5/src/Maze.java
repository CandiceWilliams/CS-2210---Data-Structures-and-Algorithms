/**
 * This class creates the maze based on the given input file and then solves it
 * @author Candice Williams
 * @
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.Iterator;


public class Maze {

    private Graph graph;
    private int entrance, exit, width, length, coins;
    private Stack<GraphNode> path;

    /**
     * Constructor for Maze class. Creates the maze based on the given inputfile
     * @param inputFile a text file that dictates the layout of the maze
     * @throws MazeException
     */
    public Maze(String inputFile) throws MazeException{

        //Strings to help keep track of the current row and column when making the maze
        String horizontalLine;
        String verticalLine;

        try {
            //read the specified maze file
            BufferedReader maze = new BufferedReader(new FileReader(inputFile));
            //skip the scale factor line
            maze.readLine();

            width = Integer.parseInt(maze.readLine()); //number of rooms in each row
            length = Integer.parseInt(maze.readLine()); //number of rooms in each column
            coins = Integer.parseInt(maze.readLine());  //total number of coins available

            // Initialize a new graph object with the size being the area of the graph
            graph = new Graph(width*length);  

            //get the first row of the maze
            horizontalLine = maze.readLine();
            int hNodeCounter = 0; //counter to keep track of all horizontal nodes
            int vNodeCounter = 0;  //counter to keep track of all vertical nodes

            while(horizontalLine != null){
                // Process horizontal line  
                for(int i = 0; i < horizontalLine.length(); i+=2){
                    // Identify entrance node
                    if (horizontalLine.charAt(i) == 's'){
                        entrance = hNodeCounter;
                    }
                    // Identify exit node
                    if (horizontalLine.charAt(i) == 'x'){
                        exit = hNodeCounter;
                    }
                    // Check for walls and create edges accordingly
                    if(i+2 < horizontalLine.length()){
                        if (horizontalLine.charAt(i + 1) != 'w'){
                            //if the next node is a corridor, mark it as such. edge type is set to 0 by default.
                            if (horizontalLine.charAt(i + 1) == 'c'){
                                graph.insertEdge(graph.getNode(hNodeCounter), graph.getNode(hNodeCounter + 1), 0, "corridor");
                            }
                            //if the next node is a number, label it as a door and set the edge type to that number
                            else if(Character.isDigit(horizontalLine.charAt(i + 1))){
                                graph.insertEdge(graph.getNode(hNodeCounter), graph.getNode(hNodeCounter + 1), Character.getNumericValue(horizontalLine.charAt(i)), "door");
                            }
                            
                        }
                    }
                hNodeCounter++; //increment horizontal node counter
                }

                // Process vertical line
                verticalLine = maze.readLine();
                //if vertical line == null, break out of the for loop since that means we are finsihed processing all lines
                if(verticalLine == null) break;

                // Check for walls and create edges accordingly
                for(int j = 0; j < verticalLine.length(); j+=2){
                    if(verticalLine.charAt(j) !='w'){
                        //if the current node is a corridor, label it as such. attach this node to the node above it
                        if(verticalLine.charAt(j) == 'c'){
                            graph.insertEdge(graph.getNode(vNodeCounter), graph.getNode(vNodeCounter + width), 0, "corridor");
                        }
                        // if the current node is a number, make the edge type equal that number. attach this node to the node above it. 
                        // set its label type to door
                        else if(Character.isDigit(verticalLine.charAt(j))){
                            graph.insertEdge(graph.getNode(vNodeCounter), graph.getNode(vNodeCounter + width), Character.getNumericValue(verticalLine.charAt(j)), "door");
                        }
                    }
                    vNodeCounter++;  //increment vertical node counter
                }

                //read next horizontal line of maze
                horizontalLine = maze.readLine();
            }

            
            //close the maze file
            maze.close();
        } catch (Exception e) {
            // Throw a MazeException if an error occurs while creating the maze
            throw new MazeException("Error when creating maze");
        }

        
        
    }

   /**
    * Getter method for the graph
    * @return the graph. If the graph is null, an exception is thrown
    * @throws MazeException
    */
    public Graph getGraph()throws MazeException{
        if (graph == null){
            throw new MazeException("Graph not yet defined");
        }
        else{
            return graph;
        }
    }

    /**
     * Method to find and return the solution path as an iterator
     * @return null if the graph has no solution. Otherwise, it returns the correct path leading to the exit
     * @throws GraphException
     */
    public Iterator solve() throws GraphException{
        //initialise a begin and end node based on the entrance and exit values gathered from the maze
        GraphNode begin = graph.getNode(entrance);
        GraphNode end = graph.getNode(exit);

        //initialise new stack to store the path node
        path = new Stack<GraphNode>();

        //call the search method to find the solution 
        if (search(begin, end, coins) == false) return null;

        return path.iterator();
    }

    /**
     * Helper method for the solve method
     * @param begin the beginning of the maze
     * @param dest  the end of the maze
     * @param coins total amount of coins user has
     * @return true is a path exists; false otherwise
     * @throws GraphException
     */
    private boolean search(GraphNode begin, GraphNode dest, int coins ) throws GraphException{

        //mark the starting node and push it onto the stack
        begin.mark(true);
        path.push(begin);

        //if the entrance and exit are the same node, return the path
        if (begin.getName() == dest.getName()){
            return true;
        }

        //iterate through each edge 
        Iterator<GraphEdge> edgesIter = graph.incidentEdges(begin);
        while (edgesIter.hasNext()){
            int coinsLeft = coins;
            GraphEdge currPath = edgesIter.next();
            GraphNode nextPath = currPath.secondEndpoint();

            // Update nextPath if it points back to the current node
            if(nextPath.getName() == begin.getName()){
                nextPath = currPath.firstEndpoint(); 
            }

            // Case if nextPath leads to an unmarked corridor
            if (!nextPath.isMarked() && currPath.getLabel().equals("corridor")){
                if (search(nextPath, dest, coinsLeft) == true) return true;
            }

            // Case if nextPath leads to an unmarked door
            else if (!nextPath.isMarked() && currPath.getLabel().equals("door") && (coinsLeft - currPath.getType()) >= 0){
                coinsLeft -= currPath.getType();
                if (search(nextPath, dest, coinsLeft) == true) return true;
            }
        }
        
        //backtrack if no path can be found
        path.pop();
        begin.mark(false);

        //if no path can be found
        return false;
    }
}
