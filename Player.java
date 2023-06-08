package APCSA.APCSA_Code_Your_Own;

import java.util.*;

public class Player {
    private String direction;
    private HashMap<String, Item> inventory;
    private int currentRow = 0;
    private int currentColumn = 0;
    private ArrayList<Room> visited;

    public Player(String direction, HashMap<String, Item> initialInventory) {
        this.direction = direction;
        this.inventory = initialInventory;
        this.visited = new ArrayList<Room>();
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setRow(int row) {
        this.currentRow = row;
    }

    public void setColumn(int column) {
        this.currentColumn = column;
    }

    public void addVisited(Room room) {
        if (!visited.contains(room)) {
            visited.add(room);
        }

    }

    public ArrayList<Room> getVisited() {
        return visited;
    }

    public String getDirection() {
        return direction;
    }

    public void addItem(String name, Item item) {
        inventory.put(name, item);
    }

    public Item getItem(int index) {
        return inventory.get(index);
    }

    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    public void removeItem(int index) {
        inventory.remove(index);
    }

    public int getRow() {
        return currentRow;
    }

    public int getColumn() {
        return currentColumn;
    }

    public String getLocation() {
        return "You are facing direction " + direction + ", and are in the room at coordinates (" + currentRow + ", "
                + currentColumn + "). ";
    }

    public boolean inventoryContains(String name){
        for (String itemName : inventory.keySet()){
            if (itemName.equals(name)){
                return true;
            }
        }
        return false;
    }

    public String getInventoryString() {
        String returnString = "Your inventory contains:\n";
        for (String name : inventory.keySet()) {
            returnString += "\t" + name + "  -->  " + inventory.get(name) + "\n";
        }
        return returnString;
    }

    public String toString() {
        return getLocation() + getInventoryString();
    }

}
