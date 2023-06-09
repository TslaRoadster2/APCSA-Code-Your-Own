package APCSA.APCSA_Code_Your_Own;

import java.io.Serializable;

public class Door implements Serializable{
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
