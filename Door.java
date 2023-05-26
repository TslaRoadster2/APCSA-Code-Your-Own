package APCSA.APCSA_Code_Your_Own;

public class Door {
    private String direction;
    private boolean locked;

    public Door(String direction, boolean locked){
        this.direction = direction;
        this.locked = locked;
    }

    public String getDirection(){
        return direction;
    }

    public boolean locked(){
        return locked;
    }
}
