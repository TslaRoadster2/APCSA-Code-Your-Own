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
                rooms[i][j] = new Room(new String[] {"north", "east", "south", "west"});
            }
        }
        //TODO: remove doors that don't connect using checkLink
        /*for (int i = 0; i < rooms.length - 1; i++){
            for (int j = 0; j < rooms.length - 1; j++){
                
            }
        }*/
        return rooms;
    }

    public boolean checkLink(int row1, int column1, int row2, int column2){
        Room room1 = mapRooms[row1][column1];
        Room room2 = mapRooms[row2][column2];

        boolean room1n = false;
        boolean room1e = false;
        boolean room1s = false;
        boolean room1w = false;

        boolean room2n = false;
        boolean room2e = false;
        boolean room2s = false;
        boolean room2w = false;
        

        for (int i = 0; i < room1.getDoors().length; i++){
            if (room1.getDoors()[i].equals("north")){
                room1n = true;
            }
            if (room1.getDoors()[i].equals("east")){
                room1e = true;
            }
            if (room1.getDoors()[i].equals("south")){
                room1s = true;
            }
            if (room1.getDoors()[i].equals("west")){
                room1w = true;
            }
        }

        for (int i = 0; i < room2.getDoors().length; i++){
            if (room2.getDoors()[i].equals("north")){
                room2n = true;
            }
            if (room2.getDoors()[i].equals("east")){
                room2e = true;
            }
            if (room2.getDoors()[i].equals("south")){
                room2s = true;
            }
            if (room2.getDoors()[i].equals("west")){
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
