package APCSA.APCSA_Code_Your_Own;

import java.util.*;

public class GameRunner {
    public static void runGame(){
        System.out.println(
                "\n\n\n~.~.~.  Welcome to Adventure Game by Will Barber! To quit at any time, simply type \"quit\". Follow the instructions on screen to play the game.  ~.~.~.\n\n");

        ArrayList<Item> initialInventory = new ArrayList<Item>();
        initialInventory.add(new Item(false, "a map of the rooms. You'll fill it in as you go. ", "Map"));
        Player player = new Player("north", initialInventory);
        player.setRow(0);
        player.setColumn(0);
        int mapSize = Utils.inputInt("Enter a map size from 4 to 8: ", 4, 8);
        System.out.println();
        Map map = new Map(mapSize);
        player.addVisited(map.getRoom(player.getRow(), player.getColumn()));

        String userOption = "none";
        String[] availableOptions = new String[] { "quit", "options", "move", "description", "action" };
        while (!userOption.equals("quit")) {
            userOption = Utils.inputString("Enter your next command. To view a list of options, type \"options\": ",
                    availableOptions, "\nInvalid command. To view a list of options, type \"options\".\n", false);
            System.out.println();
            switch (userOption) {
                case "quit":
                    System.out.println("Thanks for playing!");
                    break;
                case "options":
                    for (String option : availableOptions) {
                        System.out.println("\t" + option);
                    }
                    System.out.println();
                    break;
                case "move":
                    movePlayer(player, map);
                    player.addVisited(map.getRooms()[player.getRow()][player.getColumn()]);
                    System.out.println();
                    break;
                case "description":
                    break;
                case "action":
                    playerAction(player, map);
                    break;
                default:
                    System.out.println("An unknown error has occured. Please try again.");
            }
        }
    }

    private static void movePlayer(Player player, Map map) {
        String userDirection = Utils.inputString("Enter a direction: North, East, South, or West: ",
                new String[] { "north", "east", "south", "west" },
                "\nInvalid option. Please enter one of the options listed.\n", false);
        switch (userDirection) {
            case "north":
                if (map.checkSingleLink(player.getRow(), player.getColumn(), player.getRow() - 1, player.getColumn(),
                        map.getRooms())) {
                    player.setRow(player.getRow() - 1);
                } else {
                    System.out.println(
                            "\nInvalid choice: cannot move in the desired direction. This may be due to a wall or the edge of the map.");
                }
                break;
            case "east":
                if (map.checkSingleLink(player.getRow(), player.getColumn(), player.getRow(), player.getColumn() + 1,
                        map.getRooms())) {
                    player.setColumn(player.getColumn() + 1);
                } else {
                    System.out.println(
                            "\nInvalid choice: cannot move in the desired direction. This may be due to a wall or the edge of the map.");
                }
                break;
            case "south":
                if (map.checkSingleLink(player.getRow(), player.getColumn(), player.getRow() + 1, player.getColumn(),
                        map.getRooms())) {
                    player.setRow(player.getRow() + 1);
                } else {
                    System.out.println(
                            "\nInvalid choice: cannot move in the desired direction. This may be due to a wall or the edge of the map.");
                }
                break;
            case "west":
                if (map.checkSingleLink(player.getRow(), player.getColumn(), player.getRow(), player.getColumn() - 1,
                        map.getRooms())) {
                    player.setColumn(player.getColumn() - 1);
                } else {
                    System.out.println(
                            "\nInvalid choice: cannot move in the desired direction. This may be due to a wall or the edge of the map.");
                }
                break;
            default:
                System.out.println("\nAn unknown error has occured. Please try again.");
        }
    }

    private static void playerAction(Player player, Map map) {
        System.out.println(map.toPrettyString(player) + "\n");// TODO: actual action functionality
    }

}
