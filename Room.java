package APCSA.APCSA_Code_Your_Own;

import java.util.HashMap;

public class Room {
    private HashMap<Item, String> items;
    private String description;
    private String descriptionAddendum;
    private HashMap<String, Door> doors;
    private boolean connected;
    private int row;
    private int column;

    public Room(int row, int column, HashMap<String, Door> doors) {
        this.row = row;
        this.column = column;
        this.doors = doors;
    }

    public Room(HashMap<String, Door> doors, HashMap<Item, String> items) {
        this.doors = doors;
        this.items = items;

    }

    public Room(HashMap<String, Door> doors, String descriptionAddendum) {
        this.doors = doors;
        this.descriptionAddendum = descriptionAddendum;
    }

    public Room(HashMap<String, Door> doors, HashMap<Item, String> items, String descriptionAddendum) {
        this.doors = doors;
        this.items = items;
        this.descriptionAddendum = descriptionAddendum;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String generateDescription(String playerDirection) {
        return "not finished";
    }

    public HashMap<String, Door> getDoors() {
        return doors;
    }

    public boolean isConnected() {
        return connected;
    }

    public void removeDoor(String direction) {
        doors.remove(direction);
    }

    public void setDoors(HashMap<String, Door> doors) {
        this.doors = doors;
    }

    public void addDoor(Door door) {
        if (!doors.containsKey(door.getDirection())) {
            doors.put(door.getDirection(), new Door(door.getDirection()));
        }
    }

    public void setConnection(boolean connected) {
        this.connected = connected;
    }

    public String generateDescription() {
        /*
         * if (doors.size() == 1){
         * description +=
         * "You are in a dark room. You can see no doors other than the one that you entered via."
         * ;
         * }
         * else if (doors.size() == 2){
         * description +=
         * "You are in a dark room. Apart from the door that you entered via, you can see another door in the "
         * + doors.get(1) + " wall";
         * }
         * else if (doors.size() != 0){
         * description +=
         * "You are in a dark room. Apart from the door that you entered via, you can see doors in the "
         * ;
         * for (int i = 0; i < doors.size() - 1; i++){
         * description += doors.get(i) + ", ";
         * }
         * description += " and " + doors.get(doors.size() - 1) + " walls.";
         * 
         * }
         * return description;
         */
        return "not done";
    }

    public String toString() {
        String returnString = "";
        for (String direction : doors.keySet()) {
            returnString += doors.get(direction) + " ";
        }
        returnString += "The room connection status is " + connected;
        return returnString;

        // return "doors: " + doors.toString() + "\nitems: " /*+ items.toString()*/ +
        // "\ndescription: " + description + "\ndescription addendum: " +
        // descriptionAddendum;

    }

    public boolean hasNorthDoor() {
        return doors.containsKey("north");
    }

    public boolean hasEastDoor() {
        return doors.containsKey("east");
    }

    public boolean hasSouthDoor() {
        return doors.containsKey("south");
    }

    public boolean hasWestDoor() {
        return doors.containsKey("west");
    }
}
