/**
 * Java Program that creates keys that are meant to be stored in a dictionary
 * @author: Candice Williams
 */

public class Key {
    
    private String label;
    private int type;

    //constructor with the name and type of the key
    public Key(String theLabel, int theType){
        label = theLabel.toLowerCase();
        type = theType;
    }

    public String getLabel(){ //returns the label/name of the key
        return label;
    }

    public int getType(){  //returns the type of data stored associated with the key
        return type;
    }

    public int compareTo(Key k){
        if (this.label.equals(k.label) && this.type == k.type){  //if two keys are equal and there types are equals, return 0. Same key
            return 0;
        }

        //if the first key is lexigraphically smaller  than the other, return -1
        else if (this.label.compareTo(k.label) < 0){
            return -1;
        }

        //if the first key is lexigraphically equivalent to the second but the first type is smaller
        else if (this.label.equals(k.label) && this.type < k.type){ 
            return -1;
        }

        else return 1; //return 1 by default
    }
    
}
