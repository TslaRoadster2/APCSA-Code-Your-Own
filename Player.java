package APCSA.APCSA_Code_Your_Own;

import java.util.ArrayList;;

public class Player {
    private String direction;
    private ArrayList<Item> inventory;

    public Player(String direction) {
        this.direction = direction;
        this.inventory = new ArrayList<Item>();
    }

    public Player(String direction, ArrayList<Item> initialInventory) {
        this.direction = direction;
        this.inventory = initialInventory;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public Item getItem(int index) {
        return inventory.get(index);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void removeItem(int index) {
        inventory.remove(index);
    }

}
