package APCSA.APCSA_Code_Your_Own;

public class MapItem extends Item{
    private Map map;

    public MapItem(Map map) {
        super("a map of the rooms. You'll fill it in as you go. ", new String[] { "view map" });
        this.map = map;
    }

    public boolean useAbility(String selectedAbility, Player player) {
        if (hasAbility(selectedAbility)) {
            printMap(player);
            return true;
        }
        return false;
    }

    private void printMap(Player player) {
        System.out.println("\n\n" + map.toPrettyString(player) + "\n");
    }
}
