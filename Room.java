package APCSA.APCSA_Code_Your_Own;
import java.util.HashMap;
import java.util.ArrayList;

public class Room {
    private HashMap<Item, String> items;
    private String description;
    private String descriptionAddendum;
    private ArrayList<Door> doors;

    public Room(ArrayList<Door> doors){
        this.doors = doors;
    }

    public Room(ArrayList<Door> doors, HashMap<Item, String> items){
        this.doors = doors;
        this.items = items;

    }

    public Room(ArrayList<Door> doors, String descriptionAddendum){
        this.doors = doors;
        this.descriptionAddendum = descriptionAddendum;
    }

    public Room(ArrayList<Door> doors, HashMap<Item, String> items, String descriptionAddendum){
        this.doors = doors;
        this.items = items;
        this.descriptionAddendum = descriptionAddendum;
    }

    public String generateDescription(String playerDirection){
        return "not finished";
    }

    public ArrayList<Door> getDoors(){
        return doors;
    }

    public void removeDoor(int index){
        doors.remove(index);
    }

    public void setDoors(ArrayList<Door> doors){
        this.doors = doors;
    }

    public String generateDescription(){
        /*if (doors.size() == 1){
            description += "You are in a dark room. You can see no doors other than the one that you entered via.";
        }
        else if (doors.size() == 2){
            description += "You are in a dark room. Apart from the door that you entered via, you can see another door in the " + doors.get(1) + " wall";
        }
        else if (doors.size() != 0){
            description += "You are in a dark room. Apart from the door that you entered via, you can see doors in the ";
            for (int i = 0; i < doors.size() - 1; i++){
                description += doors.get(i) + ", ";
            }
            description += " and " + doors.get(doors.size() - 1) + " walls.";

        }
        return description;*/
        return "not done";
    }

    public String toString(){
        String returnString = "";
        for (int i = 0; i < doors.size(); i++){
            returnString += doors.get(i) + " ";
        }
        return returnString;
        
        //return "doors: " + doors.toString() + "\nitems: " /*+ items.toString()*/ + "\ndescription: " + description + "\ndescription addendum: " + descriptionAddendum;

    }



}
