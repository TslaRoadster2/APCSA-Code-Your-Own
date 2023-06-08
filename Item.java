package APCSA.APCSA_Code_Your_Own;

public class Item {
    private boolean hidden;
    private String description;
    private String[] abilities;

    public Item(boolean hidden, String description) {
        this(hidden, description, new String[] { "none" });
    }

    public Item(boolean hidden, String description, String[] abilities) {
        this.hidden = hidden;
        this.description = description;
        this.abilities = abilities;

    }

    public boolean isHidden() {
        return hidden;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public boolean hasAbility(String selectedAbility){
        for (String ability : abilities){
            if (selectedAbility.equals(ability)){
                return true;
            }
        }
        return false;
    }

    public boolean useAbility(String selectedAbility, Player player){
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
