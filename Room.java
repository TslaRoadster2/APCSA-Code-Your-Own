package APCSA.APCSA_Code_Your_Own;

import java.util.*;

public class Room {
    private ArrayList<Item> items = new ArrayList<Item>();
    private HashMap<String, Door> doors;
    private boolean connected;
    private int row;
    private int column;
    private ArrayList<NonPlayerCharacter> npcs = new ArrayList<NonPlayerCharacter>();

    public Room(int row, int column, HashMap<String, Door> doors) {
        this.row = row;
        this.column = column;
        this.doors = doors;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public HashMap<String, Door> getDoors() {
        return doors;
    }

    public ArrayList<NonPlayerCharacter> getNpcs() {
        return npcs;
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

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addNpc(NonPlayerCharacter npc) {
        this.npcs.add(npc);
    }

    public String toString() {
        String returnString = "";
        returnString += "The room contains the following doors:\n";
        for (Door door : doors.values()) {
            returnString += "\n\t" + door.toString();
        }
        returnString += "\n";

        returnString += "\nThe room contains the following items:\n";
        if (items.isEmpty()) {
            returnString += "\n\tThe room contains no items.";
        } else {
            for (Item item : items) {
                returnString += "\n\t" + item.toString();
            }
        }
        returnString += "\n";

        if (!npcs.isEmpty()) {
            returnString += "\nThe room contains the following enemies:\n";
            for (NonPlayerCharacter npc : npcs) {
                returnString += "\n\t" + npc.toString();
            }
            returnString += "\n";
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
