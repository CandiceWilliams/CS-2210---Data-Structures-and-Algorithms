import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * An interface so that the user can manipulate the elements of an ordered dictionary 
 * @author Candice Williams
 */
public class Interface {
    
    public static void main(String[] args) throws IOException, DictionaryException, MultimediaException{

        Record record;
        Key key;
        BSTDictionary dictionary;  //ordered dictionary
        String rec ="";
        String[] delRec = new String [3];
        String[] addRec = new String [4];
        SoundPlayer player = new SoundPlayer();
	    PictureViewer viewer = new PictureViewer();	    
	    ShowHTML browser = new ShowHTML();
        String filename = args[0];  //file the user wants dictionary of
        

        dictionary = new BSTDictionary();

        try{
            dictionary = inputFile(filename, dictionary);
        }catch (Exception e){
            System.out.println("There seems to be an error with your file name. Dictionary not loaded");
        }
        
        StringReader  line = new StringReader();
        String userCommand = "";

        //while user doesn't type "exit"
        while(!userCommand.equalsIgnoreCase("exit")){
            userCommand = line.read("Please enter your next command:     "); 

            
            if (userCommand.startsWith("define ")){
                rec = userCommand.substring(7);  //start of the label 
                key = new Key(rec,1);
                record = dictionary.get(key); //gets the record of  the label the user is searching for
                if (record != null){  //if the record exists, its data item is printed out
                    System.out.println(record.getDataItem());  
                }
                else{
                    System.out.println("The word " + rec + " is not in the ordered dictionary");
                }
            }

            else if (userCommand.startsWith("translate ")){  
                rec = userCommand.substring(11);  //start of the user label
                key = new Key(rec, 2);  //creates new key
                record = dictionary.get(key);  //gets the key from the dictionary
                if (record != null){  // if the key exists, print the french translation
                    System.out.println(record.getDataItem());
                }
                else{
                    System.out.println("There is no definition for the word " + rec);  
                } 
            }

            else if (userCommand.startsWith("sound ")){
                try{
                    rec = userCommand.substring(7);  
                    key = new Key(rec, 3);  //new key cretaed
                    record = dictionary.get(key);  //checks dictionary if key exists
                    if (record != null){ //if key does exist, then sound file is played
                        player.play(record.getDataItem());
                    }
                    else{
                        System.out.println("There is no sound file for " + rec);
                    }
                } catch (MultimediaException e){  //if the file is corrupted then this is displayed instead
                    System.out.println("The sound file " + rec + " cannot be processed at this time");
                }
                 
            }

            else if (userCommand.startsWith("play ")){
                try{
                    rec = userCommand.substring(5);
                    key = new Key(rec, 4);  //new key
                    record = dictionary.get(key);  //checks dictionary for key
                    if (record != null){
                        player.play(record.getDataItem());  // if the key is in the dictionary, the music file is played
                    }
                    else{
                        System.out.println("There is no music file for " + rec);
                    }  
                } catch (MultimediaException e){
                    System.out.println("The music file " + rec + " cannot be processed at this time");  // if there is a problem with the file, this is shown instead
                }
                 
            }

            else if (userCommand.startsWith("say ")){

                try{
                    rec = userCommand.substring(4); //copies the label name from index 4 for user command
                    key = new Key(rec, 5);  //creates new key with appropriate type 
                    record = dictionary.get(key);  //checks if the voice file is in the dictionary
                    if (record != null){
                        player.play(record.getDataItem());  //if it is in the dictionary, the voice file is played
                    }
                    else{
                        System.out.println("There is no voice file for " + rec); 
                    }
                } catch (MultimediaException e){
                    System.out.println("The voice file " + rec + " cannot be processed at this time");  //if the file is corrupted, this message is displayed instead
                }
                 
            }

            else if (userCommand.startsWith("show ")){
                try{
                    rec = userCommand.substring(5);  //copies the label name from index 5 for user command
                    key = new Key(rec, 6);  //creates new key with appropriate type
                    record = dictionary.get(key);  //checks if the image file is in the dictionary
                    if (record != null){
                        viewer.show(record.getDataItem());  //if it is in the dictionary, the imaged file is displayed
                    }
                    else{
                        System.out.println("There is no image file for " + rec);
                    } 
                } catch (MultimediaException e){
                    System.out.println("The image file " + rec + " cannot be processed at this time"); //if the file is corrupted, this message is displayed instead
                }
                 
            }

            else if (userCommand.startsWith("animate ")){
                try{
                    rec = userCommand.substring(7);  //copies the label name from index 7 from the user command
                    key = new Key(rec, 7);  //creates new key with appropriate type
                    record = dictionary.get(key);  //checks if the .gif file is in the dictionary
                    if (record != null){
                        viewer.show(record.getDataItem());  
                    }
                    else{
                        System.out.println("There is no animated image file for " + rec);
                    } 
                } catch (MultimediaException e){
                    System.out.println("The animated image file " + rec + " cannot be processed at this time"); //if the animated image/.gif is corrupted, this message is displayed instead
                }
                 
            }

            else if (userCommand.startsWith("browse ")){
                rec = userCommand.substring(7);  //copies the label name from index 7 from the user command
                key = new Key(rec, 8);   //creates new key with appropriate type
                record = dictionary.get(key);  //checks if the .html file is in the dictionary
                if (record != null){
                    browser.show(record.getDataItem());  //opens the .html webpage
                }
                else{
                    System.out.println("There is no webpage called " + rec); //if the .html is corrupted, this message is displayed instead
                } 
            }

            else if (userCommand.startsWith("delete ")){ 
                delRec = userCommand.split(" ", 3);  //stores the label and type given by the user into an array
                key = new Key(delRec[1], Integer.parseInt(delRec[2]));  //creates a new key from label and type given by user
                record = dictionary.get(key);  //checks if the dictionary has the desired key
                if (record != null){
                    dictionary.remove(record.getKey()); //if the key exists, then remove it
                } 
                else{
                    System.out.println("No record in ordered dictionary has key (" + delRec[1] + delRec[2] + ")");  //If the key does not exist then
                } 
            }

            else if (userCommand.startsWith("add ")){
                addRec = userCommand.split(" ", 4);  //stores the label, type and, data item into an array
                key = new Key(addRec[1], Integer.parseInt(addRec[2]));  //creates a new key from label and type
                record = new Record (key, addRec[3]);  //creates a new record from abaove key and an array
                if (dictionary.get(key) != null){  //checks dictionary to see if key exists
                    dictionary.put(record);  //add to dictionary if key not in dictionary
                }
                //else display this message
                else{
                    System.out.println("A record with the given key (" + addRec[1] + addRec[2] + ") is already in the ordered dictionary");
                } 
            }

            else if (userCommand.startsWith("list ")){
                String prefix = userCommand.substring(5);  //store user prefix in string
                ArrayList<String> words = new ArrayList<>();  //create an array list to hold all the user words
                
                //check type by type to see if any key matches
                for (int i = 1; i <= 8; i++){
                    key = new Key(prefix, i);   
                    if (dictionary.get(key).getKey().compareTo(key) == 0){ //compare the new key with its equivalent in the dictionary. 
                        words.add(key.getLabel());  //if the new key == the dictionary key, it's label is added to the list
                    }

                    //if the successor of the new key starts with the prefix
                    if (dictionary.successor(key).getKey().getLabel().startsWith(prefix)){ 
                        Key succKey = dictionary.successor(key).getKey();  //store the successor key in a Key variable
                        while (succKey.getLabel().startsWith(prefix)){  //while the successor variable starts with the prefix then
                            words.add(succKey.getLabel()); // add the key label to the array list 
                            succKey = dictionary.successor(succKey).getKey();  //check the succesor of the successor key
                        }
                        break;  //break beacause all prefixes have been found. continuing would give duplicates
                    }
                    
                }

                //displays the labels matching the prefixes, if any were found
                if (words.size() > 0){  
                    for (String i: words){
                        System.out.println(i);
                    }  
                }
                //displays message if no matching labels were found
                else{
                    System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
                }
            }

            else if (userCommand.equals("first")){
                ArrayList<String> words = new ArrayList<>();  //arraylist to store the smallest keys
                record = dictionary.smallest(); //gets the smallest key
                words.add(record.getKey().getLabel() + ", " + record.getKey().getType() + ", " + record.getDataItem());  //adds smallest key to arraylist

                
                //while the smallest key's value is equal to it's successor add it to the arraylist
                while (record.getKey().compareTo(dictionary.successor(record.getKey()).getKey()) == 0){ 
                    record = dictionary.successor(record.getKey());
                    words.add(record.getKey().getLabel() + ", " + record.getKey().getType() + ", " + record.getDataItem());
                }

                //print all smallest keys
                for (String i: words){
                    System.out.println(i);
                }
            }

            else if (userCommand.equals("last")){
                ArrayList<String> words = new ArrayList<>();  //arraylist to store largest keys
                record = dictionary.largest(); //gets the largest key
                words.add(record.getKey().getLabel() + ", " + record.getKey().getType() + ", " + record.getDataItem());  //adds largest key to arraylist

                //while the largest key's value is equal to it's successor, add it to the arraylist
                while (record.getKey().compareTo(dictionary.predecessor(record.getKey()).getKey()) == 0){
                    record = dictionary.predecessor(record.getKey());
                    words.add(record.getKey().getLabel() + ", " + record.getKey().getType() + ", " + record.getDataItem());
                }

                //print all largest keys
                for (String i: words){
                    System.out.println(i);
                }
            
            }

            //if the user command is not to exit and none of the options they chose were valid, this is displayed
            else{
                if (!userCommand.equals("exit")){
                    System.out.println("Invalid command");
                }
                
            }
            
        }
    }

    
    
    //helper method to initalise dictionary
    private static BSTDictionary inputFile(String file, BSTDictionary dictionary) throws IOException, DictionaryException{
        FileReader fr = new FileReader(file);  //.txt that the dictionary is based on
        BufferedReader br = new BufferedReader(fr);
        Record record;
        Key key;
        String label, data;
        int type;

        //in while loop, the file is sorted and categorised accurately into it's proper sections
        while((label = br.readLine()) != null){ //stores the first line
            data = br.readLine(); //stores second line

            if (data.charAt(0) == '-'){  //sound file
                type = 3;
                key = new Key(label,type);
                record = new Record(key, data);
                dictionary.put(record);
            }

            else if (data.charAt(0) == '+'){  //music file
                type = 4;
                key = new Key(label,type);
                record = new Record(key, data);
                dictionary.put(record);
            }

            else if (data.charAt(0) == '*'){  //voice file
                type = 5;
                key = new Key(label,type);
                record = new Record(key, data);
                dictionary.put(record);
            }

            else if (data.charAt(0) == '/'){  //translation to french oui oui
                type = 2;
                key = new Key(label,type);
                record = new Record(key, data);
                dictionary.put(record);
            }

            else if (data.endsWith(".gif")){  //animated .gif
                type = 7;
                key = new Key(label,type);
                record = new Record(key, data);
                dictionary.put(record); 
            }

            else if (data.endsWith(".jpg")){  //.jpg picture
                type = 6;
                key = new Key(label,type);
                record = new Record(key, data);
                dictionary.put(record); 
            }

            else if (data.endsWith(".html")){  //.html webiste
                type = 8;
                key = new Key(label,type);
                record = new Record(key, data);
                dictionary.put(record); 
            }

            else{
                type = 1;  //regular definition
                key = new Key(label,type); 
                record = new Record(key, data);
                dictionary.put(record);
            }


        }
        br.close();  //closes buffered reader 
        return dictionary;  //return after store all keys back to main
    }

     
}