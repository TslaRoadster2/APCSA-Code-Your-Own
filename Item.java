package APCSA.APCSA_Code_Your_Own;

import java.io.Serializable;

public class Item implements Serializable{
    private String description;
    private String[] abilities;

    public Item(String description) {
        this(description, new String[] { "none" });
    }

    public Item(String description, String[] abilities) {
        this.description = description;
        this.abilities = abilities;

    }

    public String[] getAbilities() {
        return abilities;
    }

    public boolean hasAbility(String selectedAbility) {
        for (String ability : abilities) {
            if (selectedAbility.equals(ability)) {
                return true;
            }
        }
        return false;
    }

    public boolean useAbility(String selectedAbility, Player player) {
        return hasAbility(selectedAbility);
    }

    public String toString() {
        String returnString = "It is " + description + "Its abilities are as follows:";
        for (int i = 0; i < abilities.length; i++) {
            {
                returnString += " " + abilities[i];
                if (i == abilities.length - 1) {
                    returnString += ".";
                } else {
                    returnString += ";";
                }
            }
        }
        return returnString;
    }
}
