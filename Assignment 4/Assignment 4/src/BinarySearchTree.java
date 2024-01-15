/**
 * Functionaility of the Binary Search Tree
 * @author Candice Williams
 */
public class BinarySearchTree {
    
    private BSTNode root;  //root node of the BST

    public BinarySearchTree(){
        root = new BSTNode(null);  //new instance of BSTNode is created
    }
    public BSTNode getRoot(){
        return this.root;  //gets the root of the BST
    }

    //returns the node storing the given Key k
    public BSTNode get(BSTNode r, Key k){ 
        if (r == null) return null;  //if the nose is null, return 'null'
        if (r.isLeaf()) return r;  //if the node is a leaf, return the node
        else{
            if (r.getRecord().getKey().compareTo(k) == 0){
                return r;  //if the node is found in the dictionary then return the node
            }
            else if (r.getRecord().getKey().compareTo(k) == -1){  //if the first key is smaller than the key we are looking for
                return get(r.getRightChild(), k);  //go to right branch
            }
            else{  //if the current node is smaller than the node we are looking for
                return get(r.getLeftChild(), k);   //go to left branch
            }
        }
    }

    
    //method to add a new record to the dictionary
    public void insert (BSTNode r, Record d) throws DictionaryException{
        BSTNode node = get(r, d.getKey());  //checks if the dictionary already has the given record
        if(node == null || !node.isLeaf()) throw new DictionaryException("Key already exists");
        else{
            node.setRecord(d); //makes current node store given record
            BSTNode left = new BSTNode(d); //create a new instance of left child
            BSTNode right = new BSTNode(d); //create a new instance of right child
            node.setLeftChild(left); //sets the left child
            node.setRightChild(right); //sets the right child
            left.setParent(node); //sets the parent of left to be the current node
            right.setParent(node);// sets the parent of right to be the current node
        }
    }

    
    //method to remove a record from the dictionary
    public void remove (BSTNode r, Key k) throws DictionaryException{
        BSTNode node = get(r,k); //returns the node we are trying to remove
        //if the node is a leaf or if it is a root, then it does not exist
        if (node.isLeaf() || node == null) throw  new DictionaryException("node does not exist");

        else{
            if (node.getLeftChild().isLeaf() || node.getRightChild().isLeaf()){  //if its left or right children are leaves
                if(node.getLeftChild().isLeaf()){ //if the left child is a leaf
                    BSTNode right = node.getRightChild(); 
                    BSTNode parent = node.getParent();

                    //if the node is the root, set a new root
                    if (parent == null){
                        this.root = right;
                        right.setParent(null); //root nodes have no parent
                    }
                    
                    else{
                        if (parent.getRightChild().getRecord().getKey().compareTo(node.getRecord().getKey()) == 0){  //if the parent of the right child equals the node
                            parent.setRightChild(right); //
                            right.setParent(parent);
                        }
                        else{
                            parent.setLeftChild(right);
                            right.setParent(parent);
                        }
                    }
                }

                if (node.getRightChild().isLeaf()){ //if the right child is a leaf
                    BSTNode left = node.getLeftChild();
                    BSTNode parent = node.getParent();

                    //if the node we are trying to remove is a root node
                    if (parent == null){
                        this.root = left;
                        left.setParent(null);
                    }
                    else{
                        //if the right child is equals to the node 
                        if (parent.getRightChild().getRecord().getKey().compareTo(node.getRecord().getKey()) == 0){
                            parent.setRightChild(left);
                            left.setParent(parent);
                        }
                        //if the left child is equals to the node 
                        else{
                            parent.setLeftChild(left);
                            left.setParent(parent);
                        }
                    }
                }
            }

            //find the smallest internal node within the tree to replace removed node
            else{

            BSTNode p = smallest(node.getRightChild()); //smallest node
            node.setRecord(p.getRecord());  //node = smallest node
            BSTNode parentPrime = p.getParent();  //gets the parents of the smallest node

            if (parentPrime.getLeftChild().equals(p)){  //if the left child = smallest
                parentPrime.setLeftChild(p.getRightChild()); 
            }
            else{
                parentPrime.setRightChild(p.getRightChild()); //else
            }
            p.getRightChild().setParent(parentPrime);  //set new parent
        }
    }
    }

    //method to determine the successor of a node
    public BSTNode successor(BSTNode r, Key k){
        if (r.isLeaf()) return null; //no possible successors exist

        else{
            BSTNode node = get(r,k);
            if(!node.isLeaf() && !node.getRightChild().isLeaf()){  //if the node isn't a leaf and its child isn't a leaf
                return smallest(node.getRightChild()); //return the smallest child to its right
            }
            else{
                node = node.getParent();  //get the parent node
                while(node != null && node.getRecord().getKey().compareTo(k) == -1){ //if the nose isn't null and it is smaller than the key k
                    node = node.getParent();
                }
                return node;
            }
        }
        
    }

    //method to determine the predecessor of a node
    public BSTNode predecessor(BSTNode r, Key k){
        if (r.isLeaf()) return null;  //node has no predecessors, it is a root

        else{
            BSTNode node = get(r,k);
            if (!node.isLeaf() && !node.getLeftChild().isLeaf()){  //if the node is not a leaf and it's left child is not a leaf
                return largest(node.getLeftChild());  
            }
            else{
                node = node.getParent();
                while(node != null && node.getRecord().getKey().compareTo(k) == 1){  // if the node is not null and it is larger than key k
                    node = node.getParent();
                }
                return node;
            }
        }
    }

    //method to return the smallest node in the BST
    public BSTNode smallest(BSTNode r){ 
        if (r == null) return null; // if the node is empty, then return null
        else{
            BSTNode temp = r;
            while (!temp.isLeaf()){  //if the temp BSTNode is not a leaf
                temp = temp.getLeftChild(); //set temp to = it's left child
            }
            return temp.getParent();
        }
        
    }

    
    //method to return the largest node in the BST
    public BSTNode largest(BSTNode r){
        if (r.isLeaf()) return r; //if the node is a leaf then return the node
        else{
            BSTNode temp1 = largest(r.getLeftChild()); //compare the left node
            BSTNode temp2 = largest(r.getRightChild()); //compare the right node
            if (temp1.getRecord().getKey().compareTo(temp2.getRecord().getKey()) == 1) return temp1; //if the left is bigger then return the left
            else return temp2; //other wise return the right node
        }
    }
}