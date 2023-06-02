package APCSA.APCSA_Code_Your_Own;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private Room[][] mapRooms = new Room[8][8];

    public Map(){
        mapRooms = generateNewMap();
    }

    public Room[][] generateNewMap(){
        Room[][] rooms = new Room[8][8];
        for (int i = 0; i < rooms.length; i++){
            for (int j = 0; j < rooms[i].length; j++){
                HashMap<String, Door> tempDoors = new HashMap<String, Door>();
                if ((Utils.randInt(0, 3) != 0)){
                    tempDoors.put("north", new Door("north", false));
                }
                if ((Utils.randInt(0, 3) != 0)){
                    tempDoors.put("east", new Door("east", false));
                }
                if ((Utils.randInt(0, 3) != 0)){
                    tempDoors.put("south", new Door("south", false));
                }
                if ((Utils.randInt(0, 3) != 0)){
                    tempDoors.put("west", new Door("west", false));
                }
                rooms[i][j] = new Room(tempDoors);
            }
        }
        for (int row = 0; row < rooms.length; row++){//Check Links
            for (int column = 0; column < rooms[row].length; column++){
                boolean connectedNorth = false;
                boolean connectedEast = false;
                boolean connectedSouth = false;
                boolean connectedWest = false;
                if (row == 0){//Top row
                    if (column == 0){//left-most column
                        connectedSouth = checkLink(row, column, row + 1, column, rooms);//Directly beneath
                        // System.out.println("room1: " + rooms[row][column].getDoors() + " room2: " + rooms[row + 1][column].getDoors());
                        // System.out.println("row: " + row + " column " + column + " checkLink below: " + checkLink(row, column, row + 1, column, rooms));
                        connectedEast = checkLink(row, column, row, column + 1, rooms);//Directly to the right
                        // System.out.println("row: " + row + " column " + column + " checkLink right: " + checkLink(row, column, row, column + 1, rooms));
                    }
                    else if (column == rooms[row].length - 1){//right-most column
                        connectedSouth = checkLink(row, column, row + 1, column, rooms);//Directly beneath
                        connectedWest = checkLink(row, column, row, column - 1, rooms);//Directly to the left
                    }
                    else{//intermediate column
                        connectedSouth = checkLink(row, column, row + 1, column, rooms);//Directly below
                        connectedEast = checkLink(row, column, row, column + 1, rooms);//Directly to the right
                        connectedWest = checkLink(row, column, row, column - 1, rooms);//Directly to the left
                    }
                }
                else if (row == rooms.length - 1){//Bottom row
                    if (column == 0){//left-most column
                        connectedNorth = checkLink(row, column, row - 1, column, rooms);//Directly above
                        connectedEast = checkLink(row, column, row, column + 1, rooms);//Directly to the right
                    }
                    else if (column == rooms[row].length - 1){//right-most column
                        connectedNorth = checkLink(row, column, row - 1, column, rooms);//Directly above
                        connectedWest = checkLink(row, column, row, column - 1, rooms);//Directly to the left
                    }
                    else{//intermediate column
                        connectedNorth = checkLink(row, column, row - 1, column, rooms);//Directly above
                        connectedEast = checkLink(row, column, row, column + 1, rooms);//Directly to the right
                        connectedWest = checkLink(row, column, row, column - 1, rooms);//Directly to the left
                    }
                }
                else{//Intermediate row
                    if (column == 0){//left-most column
                        connectedSouth = checkLink(row, column, row + 1, column, rooms);//Directly below
                        connectedEast = checkLink(row, column, row, column + 1, rooms);//Directly to the right
                        connectedNorth = checkLink(row, column, row - 1, column, rooms);//Directly above
                    }
                    else if (column == rooms[row].length - 1){//right-most column
                        connectedSouth = checkLink(row, column, row + 1, column, rooms);//Directly below
                        connectedWest = checkLink(row, column, row, column - 1, rooms);//Directly to the left
                        connectedNorth = checkLink(row, column, row - 1, column, rooms);//Directly above
                    }
                    else{//intermediate column
                        connectedSouth = checkLink(row, column, row + 1, column, rooms);//Directly below
                        connectedEast = checkLink(row, column, row, column + 1, rooms);//Directly to the right
                        connectedWest = checkLink(row, column, row, column - 1, rooms);//Directly to the left
                        connectedNorth = checkLink(row, column, row - 1, column, rooms);//Directly above
                    }
                }

                // System.out.println("row: " + row + " column: " + column + " north: " + connectedNorth + " south: " + connectedSouth + " east: " + connectedEast + " west: " + connectedWest);

                if (!connectedNorth){
                    rooms[row][column].removeDoor("north");
                }

                if (!connectedEast){
                    rooms[row][column].removeDoor("east");
                }

                if (!connectedSouth){
                    rooms[row][column].removeDoor("south");
                }
                if (!connectedWest){
                    rooms[row][column].removeDoor("west");
                }
            }
        }

        for (int i = 0; i < rooms.length; i++){
            for (int j = 0; j < rooms.length; j++){
                if (rooms[i][j].getDoors().size() == 0){
                    rooms[i][j].setConnection(false);
                }
                else{
                    rooms[i][j].setConnection(true);
                }
            }
        }


        return rooms;
    }

    public boolean checkLink(int row1, int column1, int row2, int column2, Room[][] roomList){
        Room room1 = roomList[row1][column1];
        Room room2 = roomList[row2][column2];

        boolean room1n = false;
        boolean room1e = false;
        boolean room1s = false;
        boolean room1w = false;

        boolean room2n = false;
        boolean room2e = false;
        boolean room2s = false;
        boolean room2w = false;
        

        for (String direction : room1.getDoors().keySet()){
            if (direction.equals("north")){
                room1n = true;
            }
            if (direction.equals("east")){
                room1e = true;
            }
            if (direction.equals("south")){
                room1s = true;
            }
            if (direction.equals("west")){
                room1w = true;
            }
        }

        for (String direction : room2.getDoors().keySet()){
            if (direction.equals("north")){
                room2n = true;
            }
            if (direction.equals("east")){
                room2e = true;
            }
            if (direction.equals("south")){
                room2s = true;
            }
            if (direction.equals("west")){
                room2w = true;
            }
        }

        if ((Math.abs(row1-row2) == 1 || Math.abs(column1-column2) == 1)){
            if ((row2 > row1) && (column2 == column1)){//room2 is directly below room1
                return (room2n && room1s);
            }
            else if ((row2 < row1) && (column2 == column1)){//room1 is directly below room2
                return (room2s && room1n);
            }
            else if ((row2 == row1) && (column2 < column1)){//room2 is directly to the left of room1
                return (room2e && room1w);
            }
            else if ((row2 == row1) && (column2 > column1)){//room2 is directly to the right of room1
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
                returnString += "Room at location " + i + "," + j + ": ";
                returnString += mapRooms[i][j] + "\n\n";
            }
        }
        return returnString;
    }
}
