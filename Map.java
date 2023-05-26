package APCSA.APCSA_Code_Your_Own;

public class Map {
    private Room[][] mapRooms = new Room[8][8];

    public Map(){
        mapRooms = generateNewMap();
    }

    public Room[][] generateNewMap(){
        Room[][] rooms = new Room[8][8];
        for (int i = 0; i < rooms.length; i++){
            for (int j = 0; j < rooms[i].length; j++){
                rooms[i][j] = new Room(new Door[] {new Door("north", false), new Door("east", false), new Door("south", false), new Door("west", false)});//TODO: remove hardcoding
            }
        }
        //TODO: remove doors that don't connect using checkLink
        for (int i = 0; i < rooms.length - 1; i++){
            for (int j = 0; j < rooms[i].length - 1; j++){
                if (i == 0){
                    if (j == 0){
                        checkLink(i, j, i + 1, j, rooms);
                        checkLink(i, j, i, j + 1, rooms);
                    }
                    else if (j == rooms[i].length){
                        checkLink(i, j, i + 1, j, rooms);
                        checkLink(i, j, i, j - 1, rooms);
                    }
                    else{
                        checkLink(i, j, i + 1, j, rooms);
                        checkLink(i, j, i, j + 1, rooms);
                        checkLink(i, j, i, j - 1, rooms);
                    }
                }
                else if (i == rooms.length){
                    if (j == 0){
                        checkLink(i, j, i - 1, j, rooms);
                        checkLink(i, j, i, j + 1, rooms);
                    }
                    else if (j == rooms[i].length){
                        checkLink(i, j, i - 1, j, rooms);
                        checkLink(i, j, i, j + 1, rooms);
                    }
                    else{
                        checkLink(i, j, i - 1, j, rooms);
                        checkLink(i, j, i, j + 1, rooms);
                        checkLink(i, j, i, j - 1, rooms);
                    }
                }
                else{
                    if (j == 0){
                        checkLink(i, j, i + 1, j, rooms);
                        checkLink(i, j, i, j + 1, rooms);
                        checkLink(i, j, i - 1, j, rooms);
                    }
                    else if (j == rooms[i].length){
                        checkLink(i, j, i - 1, j, rooms);
                        checkLink(i, j, i, j + 1, rooms);
                        checkLink(i, j, i - 1, j, rooms);
                    }
                    else{
                        checkLink(i, j, i + 1, j, rooms);
                        checkLink(i, j, i, j + 1, rooms);
                        checkLink(i, j, i, j - 1, rooms);
                        checkLink(i, j, i - 1, j, rooms);
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
        

        for (int i = 0; i < room1.getDoors().length; i++){
            if (room1.getDoors()[i].getDirection().equals("north")){
                room1n = true;
            }
            if (room1.getDoors()[i].getDirection().equals("east")){
                room1e = true;
            }
            if (room1.getDoors()[i].getDirection().equals("south")){
                room1s = true;
            }
            if (room1.getDoors()[i].getDirection().equals("west")){
                room1w = true;
            }
        }

        for (int i = 0; i < room2.getDoors().length; i++){
            if (room2.getDoors()[i].getDirection().equals("north")){
                room2n = true;
            }
            if (room2.getDoors()[i].getDirection().equals("east")){
                room2e = true;
            }
            if (room2.getDoors()[i].getDirection().equals("south")){
                room2s = true;
            }
            if (room2.getDoors()[i].getDirection().equals("west")){
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
}
