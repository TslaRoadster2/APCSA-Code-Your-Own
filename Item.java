package APCSA.APCSA_Code_Your_Own;

public class Item {
    private boolean hidden;
    private String description;
    private String ability;
    private String name;

    public Item(boolean hidden, String description, String name) {
        this(hidden, description, "none", name);
    }

    public Item(boolean hidden, String description, String ability, String name) {
        this.hidden = hidden;
        this.description = description;
        this.ability = ability;
        this.name = name;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String toString() {
        return name + ". It is " + description + "Its ability is as follows: " + ability + ".";
    }
}
