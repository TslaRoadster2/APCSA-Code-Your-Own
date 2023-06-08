package APCSA.APCSA_Code_Your_Own;

public class Door {
    private String direction;

    public Door(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public String toString() {
        return "Door facing direction " + direction + ".";
    }
}
