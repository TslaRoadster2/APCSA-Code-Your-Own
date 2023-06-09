package APCSA.APCSA_Code_Your_Own;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    private String direction;
    private HashMap<String, Item> inventory;
    private int currentRow = 0;
    private int currentColumn = 0;
    private ArrayList<Room> visited;
    private int currentHealth = 50;
    private int maxHealth = 50;
    private boolean isDead = false;

    private static final String fileName = "PlayerState.ser";

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

    public Item getItem(String name) {
        return inventory.get(name);
    }

    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    public void removeItem(String name) {
        inventory.remove(name);
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

    public boolean inventoryContains(String name) {
        for (String itemName : inventory.keySet()) {
            if (itemName.equals(name)) {
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

    public void setMaxHealth() {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth > 0) {
            isDead = true;
        }
        return isDead;
    }

    public void renewHealth() {
        currentHealth = maxHealth;
    }

    public void renewHealth(int health) {
        currentHealth += health;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public String toString() {
        return getLocation() + getInventoryString();
    }

    public boolean save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
            return true;
        } catch (IOException exception) {
            System.err.println(exception);
            return false;
        }
    }

    public static Player restore() {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Player player = (Player) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return player;
        } catch (Exception exception) { // IOException, ClassNotFoundException
            return null;
        }
    }

}
