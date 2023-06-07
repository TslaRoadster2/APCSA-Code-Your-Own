package APCSA.APCSA_Code_Your_Own;

import java.util.HashMap;
import java.util.ArrayList;

public class Room {
    private ArrayList<Item> items;
    private String description;
    private HashMap<String, Door> doors;
    private boolean connected;
    private int row;
    private int column;

    public Room(int row, int column, HashMap<String, Door> doors) {
        this.row = row;
        this.column = column;
        this.doors = doors;
    }

    public Room(HashMap<String, Door> doors, ArrayList<Item> items) {
        this.doors = doors;
        this.items = items;

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

    public String toString() {
        String returnString = "";
        returnString += "The room contains the following doors:\n";
        for (Door door: doors.values()){
            returnString += door.toString() + "\n";
        }
        returnString += "\nThe room contains the following items:\n";
        for (Item item: items){
            if (!item.isHidden()){
                returnString += item.toString() + "\n";
            }
        }
        return returnString;
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
