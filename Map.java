package APCSA.APCSA_Code_Your_Own;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private Room[][] mapRooms;

    public Map() {
        mapRooms = generateNewMap();
    }

    public Room[][] generateNewMap() {
        Room[][] rooms = new Room[6][6];
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                HashMap<String, Door> tempDoors = new HashMap<String, Door>();
                if ((Utils.randInt(0, 3) != 0)) {
                    tempDoors.put("north", new Door("north", false));
                }
                if ((Utils.randInt(0, 3) != 0)) {
                    tempDoors.put("east", new Door("east", false));
                }
                if ((Utils.randInt(0, 3) != 0)) {
                    tempDoors.put("south", new Door("south", false));
                }
                if ((Utils.randInt(0, 3) != 0)) {
                    tempDoors.put("west", new Door("west", false));
                }
                rooms[i][j] = new Room(i, j, tempDoors);
            }
        }

        if (!checkContinuity(rooms)) {
            rooms = generateNewMap();
        }
        return rooms;
    }

    public Room[][] repairMapLinks(Room[][] rooms) {
        for (int row = 0; row < rooms.length; row++) {// Check Links
            for (int column = 0; column < rooms[row].length; column++) {
                boolean connectedNorth = false;
                boolean connectedEast = false;
                boolean connectedSouth = false;
                boolean connectedWest = false;
                if (row == 0) {// Top row
                    if (column == 0) {// left-most column
                        connectedSouth = checkSingleLink(row, column, row + 1, column, rooms);// Directly beneath
                        connectedEast = checkSingleLink(row, column, row, column + 1, rooms);// Directly to the right
                    } else if (column == rooms[row].length - 1) {// right-most column
                        connectedSouth = checkSingleLink(row, column, row + 1, column, rooms);// Directly beneath
                        connectedWest = checkSingleLink(row, column, row, column - 1, rooms);// Directly to the left
                    } else {// intermediate column
                        connectedSouth = checkSingleLink(row, column, row + 1, column, rooms);// Directly below
                        connectedEast = checkSingleLink(row, column, row, column + 1, rooms);// Directly to the right
                        connectedWest = checkSingleLink(row, column, row, column - 1, rooms);// Directly to the left
                    }
                } else if (row == rooms.length - 1) {// Bottom row
                    if (column == 0) {// left-most column
                        connectedNorth = checkSingleLink(row, column, row - 1, column, rooms);// Directly above
                        connectedEast = checkSingleLink(row, column, row, column + 1, rooms);// Directly to the right
                    } else if (column == rooms[row].length - 1) {// right-most column
                        connectedNorth = checkSingleLink(row, column, row - 1, column, rooms);// Directly above
                        connectedWest = checkSingleLink(row, column, row, column - 1, rooms);// Directly to the left
                    } else {// intermediate column
                        connectedNorth = checkSingleLink(row, column, row - 1, column, rooms);// Directly above
                        connectedEast = checkSingleLink(row, column, row, column + 1, rooms);// Directly to the right
                        connectedWest = checkSingleLink(row, column, row, column - 1, rooms);// Directly to the left
                    }
                } else {// Intermediate row
                    if (column == 0) {// left-most column
                        connectedSouth = checkSingleLink(row, column, row + 1, column, rooms);// Directly below
                        connectedEast = checkSingleLink(row, column, row, column + 1, rooms);// Directly to the right
                        connectedNorth = checkSingleLink(row, column, row - 1, column, rooms);// Directly above
                    } else if (column == rooms[row].length - 1) {// right-most column
                        connectedSouth = checkSingleLink(row, column, row + 1, column, rooms);// Directly below
                        connectedWest = checkSingleLink(row, column, row, column - 1, rooms);// Directly to the left
                        connectedNorth = checkSingleLink(row, column, row - 1, column, rooms);// Directly above
                    } else {// intermediate column
                        connectedSouth = checkSingleLink(row, column, row + 1, column, rooms);// Directly below
                        connectedEast = checkSingleLink(row, column, row, column + 1, rooms);// Directly to the right
                        connectedWest = checkSingleLink(row, column, row, column - 1, rooms);// Directly to the left
                        connectedNorth = checkSingleLink(row, column, row - 1, column, rooms);// Directly above
                    }
                }

                if (!connectedNorth) {
                    rooms[row][column].removeDoor("north");
                }

                if (!connectedEast) {
                    rooms[row][column].removeDoor("east");
                }

                if (!connectedSouth) {
                    rooms[row][column].removeDoor("south");
                }
                if (!connectedWest) {
                    rooms[row][column].removeDoor("west");
                }
            }
        }

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms.length; j++) {
                if (rooms[i][j].getDoors().size() == 0) {
                    rooms[i][j].setConnection(false);
                } else {
                    rooms[i][j].setConnection(true);
                }
            }
        }
        return rooms;
    }

    public boolean checkSingleLink(int row1, int column1, int row2, int column2, Room[][] roomList) {
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

        if (room1.hasNorthDoor()) {
            room1n = true;
        }
        if (room1.hasEastDoor()) {
            room1e = true;
        }
        if (room1.hasSouthDoor()) {
            room1s = true;
        }
        if (room1.hasWestDoor()) {
            room1w = true;
        }

        if (room2.hasNorthDoor()) {
            room2n = true;
        }
        if (room2.hasEastDoor()) {
            room2e = true;
        }
        if (room2.hasSouthDoor()) {
            room2s = true;
        }
        if (room2.hasWestDoor()) {
            room2w = true;
        }

        if ((Math.abs(row1 - row2) == 1 || Math.abs(column1 - column2) == 1)) {
            if ((row2 > row1) && (column2 == column1)) {// room2 is directly below room1
                return (room2n && room1s);
            } else if ((row2 < row1) && (column2 == column1)) {// room1 is directly below room2
                return (room2s && room1n);
            } else if ((row2 == row1) && (column2 < column1)) {// room2 is directly to the left of room1
                return (room2e && room1w);
            } else if ((row2 == row1) && (column2 > column1)) {// room2 is directly to the right of room1
                return (room2w && room1e);
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean checkContinuity(Room[][] map) {
        map = repairMapLinks(map);

        ArrayList<Room> roomList = new ArrayList<Room>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                roomList.add(map[i][j]);
            }
        }

        ArrayList<Room> visited = new ArrayList<Room>();
        ArrayList<Room> queued = new ArrayList<Room>();
        queued.add(map[0][0]);
        while (!queued.isEmpty()) {
            Room currentRoom = queued.remove(0);
            visited.add(currentRoom);
            int currentRow = currentRoom.getRow();
            int currentColumn = currentRoom.getColumn();
            if (currentRoom.hasNorthDoor() && !(visited.contains(map[currentRow - 1][currentColumn])
                    || queued.contains(map[currentRow - 1][currentColumn]))) {
                queued.add(0, map[currentRow - 1][currentColumn]);
            }
            if (currentRoom.hasEastDoor() && !(visited.contains(map[currentRow][currentColumn + 1])
                    || queued.contains(map[currentRow][currentColumn + 1]))) {
                queued.add(0, map[currentRow][currentColumn + 1]);
            }
            if (currentRoom.hasSouthDoor() && !(visited.contains(map[currentRow + 1][currentColumn])
                    || queued.contains(map[currentRow + 1][currentColumn]))) {
                queued.add(0, map[currentRow + 1][currentColumn]);
            }
            if (currentRoom.hasWestDoor() && !(visited.contains(map[currentRow][currentColumn - 1])
                    || queued.contains(map[currentRow][currentColumn - 1]))) {
                queued.add(0, map[currentRow][currentColumn - 1]);
            }
        }
        boolean continuous = visited.size() == roomList.size();
        return continuous;
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < mapRooms.length; i++) {
            for (int j = 0; j < mapRooms.length; j++) {
                returnString += "Room at location " + i + "," + j + ": ";
                returnString += mapRooms[i][j].toString() + "\n\n";
            }
        }
        return returnString;
    }

    public String toPrettyString() {
        String result = "";
        for (int i = 0; i < mapRooms.length; i++) {
            for (int j = 0; j < mapRooms[i].length; j++) {
                if (mapRooms[i][j].hasNorthDoor()) {
                    result += " -+-    ";
                } else {
                    result += " ---    ";
                }
            }
            result += "\n";
            for (int j = 0; j < mapRooms[i].length; j++) {
                if (mapRooms[i][j].hasWestDoor()) {
                    result += "+   ";
                } else {
                    result += "|   ";
                }
                if (mapRooms[i][j].hasEastDoor()) {
                    result += "+   ";
                } else {
                    result += "|   ";
                }
            }
            result += "\n";
            for (int j = 0; j < mapRooms[i].length; j++) {
                if (mapRooms[i][j].hasSouthDoor()) {
                    result += " -+-    ";
                } else {
                    result += " ---    ";
                }
            }
            result += "\n";
            result += "\n";
        }
        return result;
    }
}
