/**
 * Controls the functionality of the nodes in a BST
 */
public class BSTNode {

    //instance variables
    private Record item;
    private BSTNode left, right, parent;

    //constrcutor
    public BSTNode(Record item){
        this.item = item;
        parent = null;
        right = null;
        left = null;
        
    }

    //returns the record
    public Record getRecord(){
        return item;
    }

    //sets the recprd
    public void setRecord(Record d){
        this.item = d;
    }

    //returns the left child
    public BSTNode getLeftChild(){
        return left;
    }

    //returns the right child
    public BSTNode getRightChild(){
        return right;
    }

    //returns the parent 
    public BSTNode getParent(){
        return this.parent;
    }

    //sets the left child to = the given node
    public void setLeftChild(BSTNode u){
        left = u;
    }

    //sets the right child to the given node
    public void setRightChild(BSTNode u){
        right = u;
    }

    //sets the parent to the given node
    public void setParent(BSTNode u){
        parent = u;
    }
    
    //checks if the node is a leaf
    public boolean isLeaf(){
        if (left == null && right == null){
            return true;
        }
        else return false;
    }

}
