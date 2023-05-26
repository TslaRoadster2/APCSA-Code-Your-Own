package APCSA.APCSA_Code_Your_Own;
import java.util.ArrayList;

public class Map {
    private Room[][] mapRooms = new Room[8][8];

    public Map(){
        mapRooms = generateNewMap();
    }

    public Room[][] generateNewMap(){
        Room[][] rooms = new Room[8][8];
        for (int i = 0; i < rooms.length; i++){
            for (int j = 0; j < rooms[i].length; j++){
                ArrayList<Door> tempDoors = new ArrayList<Door>();
                tempDoors.add(new Door("north", false));
                tempDoors.add(new Door("east", false));
                tempDoors.add(new Door("south", false));
                tempDoors.add(new Door("west", false));
                rooms[i][j] = new Room(tempDoors);//TODO: remove hardcoding
            }
        }
        for (int i = 0; i < rooms.length - 1; i++){
            for (int j = 0; j < rooms[i].length - 1; j++){
                boolean connectedNorth = false;
                boolean connectedEast = false;
                boolean connectedSouth = false;
                boolean connectedWest = false;
                if (i == 0){
                    if (j == 0){
                        connectedSouth = checkLink(i, j, i + 1, j, rooms);
                        connectedEast = checkLink(i, j, i, j + 1, rooms);
                    }
                    else if (j == rooms[i].length){
                        connectedSouth = checkLink(i, j, i + 1, j, rooms);
                        connectedWest = checkLink(i, j, i, j - 1, rooms);
                    }
                    else{
                        connectedSouth = checkLink(i, j, i + 1, j, rooms);
                        connectedEast = checkLink(i, j, i, j + 1, rooms);
                        connectedWest = checkLink(i, j, i, j - 1, rooms);
                    }
                }
                else if (i == rooms.length){
                    if (j == 0){
                        connectedNorth = checkLink(i, j, i - 1, j, rooms);
                        connectedEast = checkLink(i, j, i, j + 1, rooms);
                    }
                    else if (j == rooms[i].length){
                        connectedNorth = checkLink(i, j, i - 1, j, rooms);
                        connectedWest = checkLink(i, j, i, j - 1, rooms);
                    }
                    else{
                        connectedNorth = checkLink(i, j, i - 1, j, rooms);
                        connectedEast = checkLink(i, j, i, j + 1, rooms);
                        connectedWest = checkLink(i, j, i, j - 1, rooms);
                    }
                }
                else{
                    if (j == 0){
                        connectedSouth = checkLink(i, j, i + 1, j, rooms);
                        connectedEast = checkLink(i, j, i, j + 1, rooms);
                        connectedNorth = checkLink(i, j, i - 1, j, rooms);
                    }
                    else if (j == rooms[i].length){
                        connectedSouth = checkLink(i, j, i + 1, j, rooms);
                        connectedWest = checkLink(i, j, i, j - 1, rooms);
                        connectedNorth = checkLink(i, j, i - 1, j, rooms);
                    }
                    else{
                        connectedSouth = checkLink(i, j, i + 1, j, rooms);
                        connectedEast = checkLink(i, j, i, j + 1, rooms);
                        connectedWest = checkLink(i, j, i, j - 1, rooms);
                        connectedNorth = checkLink(i, j, i - 1, j, rooms);
                    }
                }

                if (!connectedNorth){//TODO: Fix incorrect indexes if previous connection removed
                    rooms[i][j].getDoors().remove(0);
                }

                if (!connectedEast){
                    rooms[i][j].getDoors().remove(1);
                }

                if (!connectedSouth){
                    rooms[i][j].getDoors().remove(2);
                }
                if (!connectedWest){
                    rooms[i][j].getDoors().remove(3);
                }
            }
        }
        return rooms;
    }

    public boolean checkLink(int row1, int column1, int row2, int column2, Room[][] rooms){
        Room room1 = rooms[row1][column1];
        Room room2 = rooms[row2][column2];

        boolean room1n = false;
        boolean room1e = false;
        boolean room1s = false;
        boolean room1w = false;

        boolean room2n = false;
        boolean room2e = false;
        boolean room2s = false;
        boolean room2w = false;
        

        for (int i = 0; i < room1.getDoors().size(); i++){
            if (room1.getDoors().get(i).getDirection().equals("north")){
                room1n = true;
            }
            if (room1.getDoors().get(i).getDirection().equals("east")){
                room1e = true;
            }
            if (room1.getDoors().get(i).getDirection().equals("south")){
                room1s = true;
            }
            if (room1.getDoors().get(i).getDirection().equals("west")){
                room1w = true;
            }
        }

        for (int i = 0; i < room2.getDoors().size(); i++){
            if (room2.getDoors().get(i).getDirection().equals("north")){
                room2n = true;
            }
            if (room2.getDoors().get(i).getDirection().equals("east")){
                room2e = true;
            }
            if (room2.getDoors().get(i).getDirection().equals("south")){
                room2s = true;
            }
            if (room2.getDoors().get(i).getDirection().equals("west")){
                room2w = true;
            }
        }

        if (!(Math.abs(row1-row2) == 1 || Math.abs(column1-column2) == 1)){
            if (row2 > row1 && column2 - column1 == 0){//room2 is directly below room1
                return (room2n && room1s);
            }
            if (row1 < row2 && column2 - column1 == 0){//room1 is directly below room2
                return (room2s && room1n);
            }
            if (row2 - row1 == 0 && column2 < column1){//room2 is directly to the left of room1
                return (room2e && room1w);
            }
            if (row2 - row1 == 0 && column2 > column1){//room2 is directly to the right of room1
                return (room2w && room1e);
            }
            return false;
        }
        else{
            return false;
        }
    }

    public String toString(){
        String returnString = "";
        for (int i = 0; i < mapRooms.length; i++){
            for (int j = 0; j < mapRooms.length; j++){
                returnString += mapRooms[i][j] + " ";
            }
        }
        return returnString;
    }
}
