/**
 * creates a record based on a Key and it's assocaited Data item
 * @author Candice Williams
 */

public class Record {
    
    private Key theKey;
    private String data;

    //constructor
    public Record(Key k, String theData){  
        this.theKey = k;
        this.data = theData;
    }

    public Key getKey(){
        return theKey;  //returns the Key of the record obj
    }

    public String getDataItem(){
        return data;  //returns the associated data of the Key of the Record
    }
}
