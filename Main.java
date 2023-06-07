package APCSA.APCSA_Code_Your_Own;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        boolean gameStarted = false;
        System.out.println("Starting MS-DOS...");
        System.out.println("MS-DOS Version 5.0");

        String input = "";
        System.out.println("Welcome to DOS! Type \"help\" for commands.");

        while (!gameStarted) {
            input = Utils.inputString("C:\\>", new String[] { "help", "dir", "start adventuregame.exe" },
                    "Invalid command. Type \"help\" for help.");

            switch (input) {
                case "help":
                    System.out.println(
                            "commands:\n\tdir\t\t\tview the current directory\n\thelp\t\t\tlist available commands\n\tcd {dir}\t\tnavigate to directory \'dir\'\n\tstart {app.exe}\t\tstart executable \'app.exe\'");
                    break;
                case "dir":
                    System.out.println("\t05/18/1986\t01:41 PM \t<DIR>\tadventuregame.exe");
                    break;
                case "start adventuregame.exe":
                    gameStarted = true;
                    break;
                default:
                    System.out.println("An unknown error has occured. Please try again.");
            }
        }

        ArrayList<Item> initialInventory = new ArrayList<Item>();
        initialInventory.add(new Item(false, "a map of the rooms. You'll fill it in as you go. ", "Map"));
        Player player = new Player("north", initialInventory);
        player.setRow(0);
        player.setColumn(0);
        Map map = new Map();
        player.addVisited(map.getRoom(player.getRow(), player.getColumn()));
        System.out.println(player);
        System.out.println(map.toPrettyString(player));
        

        // Map test code
        while (true){
            int newRow = Utils.inputInt("new row: ") - 1;
            int newColumn = Utils.inputInt("new column: ") - 1;
            player.setRow(newRow);
            player.setColumn(newColumn);
            player.addVisited(map.getRoom(player.getRow(), player.getColumn()));
            System.out.println(map.toPrettyString(player));
        }
    }
}
