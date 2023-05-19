package APCSA.APCSA_Code_Your_Own;
import java.util.HashMap;

public class Room {
    private HashMap<Item, String> items;
    private String description;
    private String descriptionAddendum;
    private String[] doors;

    public Room(String[] doors){
        this.doors = doors;
    }

    public Room(String[] doors, HashMap<Item, String> items){
        this.doors = doors;
        this.items = items;

    }

    public Room(String[] doors, String descriptionAddendum){
        this.doors = doors;
        this.descriptionAddendum = descriptionAddendum;
    }

    public Room(String[] doors, HashMap<Item, String> items, String descriptionAddendum){
        this.doors = doors;
        this.items = items;
        this.descriptionAddendum = descriptionAddendum;
    }

    public String generateDescription(String direction){
        return "not finished";
    }

    public String[] getDoors(){
        return doors;
    }

    public void setDoors(String[] doors){
        this.doors = doors;
    }

    public String generateDescription(){
        if (doors.length == 1){
            description += "You are in a dark room. You can see no doors other than the one that you entered via.";
        }
        else if (doors.length == 2){
            description += "You are in a dark room. Apart from the door that you entered via, you can see another door in the " + doors[1] + " wall";
        }
        else if (doors.length != 0){
            description += "You are in a dark room. Apart from the door that you entered via, you can see doors in the ";
            for (int i = 0; i < doors.length - 1; i++){
                description += doors[i] + ", ";
            }
            description += " and " + doors[doors.length - 1] + " walls.";

        }
        return description;
    }

    public String toString(){
        return "doors: " + doors.toString() + "\nitems: " + items.toString() + "\ndescription: " + description + "\ndescription addendum: " + descriptionAddendum;

    }



}
