public class BSTDictionary implements BSTDictionaryADT {
    
    private BinarySearchTree dict; //the BST that stores the records

    //constructor
    public BSTDictionary(){
        dict = new BinarySearchTree(); //creates a new binary search tree instance
    }

    public Record get(Key k){
        return dict.get(dict.getRoot(),k).getRecord();  //returns the record associated with the desired key
    }

    public void put (Record d) throws DictionaryException{ //inserts a new record into the BST
        dict.insert(dict.getRoot(), d);
    }

    public void remove (Key k) throws DictionaryException{ //removes a specified record from tree based on given key
        dict.remove(dict.getRoot(), k);
    }

    public Record successor (Key k){  //returns the successor of the specified key
        return dict.successor(dict.getRoot(), k).getRecord();
    }

    public Record predecessor (Key k){  //returns the predecssor of the specified key
        return dict.predecessor(dict.getRoot(), k).getRecord();
    }
 
    public Record smallest (){  //returns the smallest record in the BST
        return dict.smallest(dict.getRoot()).getRecord();
    }

    public Record largest(){  //returns the largest record in the BST
        return dict.largest(dict.getRoot()).getRecord();
    }
}
