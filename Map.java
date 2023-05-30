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
        for (int row = 0; row < rooms.length - 1; row++){
            for (int column = 0; column < rooms[row].length - 1; column++){
                boolean connectedNorth = false;
                boolean connectedEast = false;
                boolean connectedSouth = false;
                boolean connectedWest = false;
                if (row == 0){//Top row
                    if (column == 0){//left-most column
                        connectedSouth = checkLink(row, column, row + 1, column, rooms);//Directly beneath
                        connectedEast = checkLink(row, column, row, column + 1, rooms);//Directly to the right
                    }
                    else if (column == rooms[row].length){//right-most column
                        connectedSouth = checkLink(row, column, row + 1, column, rooms);//Directly beneath
                        connectedWest = checkLink(row, column, row, column - 1, rooms);//Directly to the left
                    }
                    else{//intermediate column
                        connectedSouth = checkLink(row, column, row + 1, column, rooms);//Directly below
                        connectedEast = checkLink(row, column, row, column + 1, rooms);//Directly to the right
                        connectedWest = checkLink(row, column, row, column - 1, rooms);//Directly to the left
                    }
                }
                else if (row == rooms.length){//Bottom row
                    if (column == 0){//left-most column
                        connectedNorth = checkLink(row, column, row - 1, column, rooms);//Directly above
                        connectedEast = checkLink(row, column, row, column + 1, rooms);//Directly to the right
                    }
                    else if (column == rooms[row].length){//right-most column
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
                    else if (column == rooms[row].length){//right-most column
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

                int removeIndex = 0;

                if (!connectedNorth){
                    rooms[row][column].removeDoor(removeIndex);
                    removeIndex--;
                }

                if (!connectedEast){
                    rooms[row][column].removeDoor(removeIndex + 1);
                    removeIndex--;
                }

                if (!connectedSouth){
                    rooms[row][column].removeDoor(removeIndex + 2);
                    removeIndex--;
                }
                if (!connectedWest){
                    rooms[row][column].removeDoor(removeIndex + 3);
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
            if ((row2 > row1) && (column2 - column1 == 0)){//room2 is directly below room1
                return (room2n && room1s);
            }
            else if ((row1 < row2) && (column2 - column1 == 0)){//room1 is directly below room2
                return (room2s && room1n);
            }
            else if ((row2 - row1 == 0) && (column2 < column1)){//room2 is directly to the left of room1
                return (room2e && room1w);
            }
            else if ((row2 - row1 == 0) && (column2 > column1)){//room2 is directly to the right of room1
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
