package APCSA.APCSA_Code_Your_Own;

public class MapItem extends Item{
    private Map map;

    public MapItem(Map map){
        super(false, "a map of the rooms. You'll fill it in as you go. ", new String[] {"view map"});
        this.map = map;
    }
    
    public void printMap(Player player){
        System.out.println(map.toPrettyString(player) + "\n");
    }
}
