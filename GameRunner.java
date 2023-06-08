package APCSA.APCSA_Code_Your_Own;

import java.util.*;

public class GameRunner {
    public static void runGame() {
        System.out.println(
                "\n\n\n~.~.~.  Welcome to Adventure Game by Will Barber! To quit the game, simply type \"quit\" from the main command screen. Follow the instructions on screen to play the game.  ~.~.~.\n\n");

        // TODO: add check for loading previously-saved game file here

        int mapSize = Utils.inputInt("Enter a map size from 4 to 8: ", 4, 8);
        System.out.println();
        Map map = new Map(mapSize);

        HashMap<String, Item> initialInventory = new HashMap<String, Item>();
        initialInventory.put("map", new MapItem(map));

        Player player = new Player("north", initialInventory);
        player.setRow(0);
        player.setColumn(0);

        player.addVisited(map.getRoom(player.getRow(), player.getColumn()));

        runGameLoop(player, map);
    }

    private static void runGameLoop(Player player, Map map) {
        String userOption = "none";
        while (!userOption.equals("quit")) {
            userOption = Utils.inputString("Enter your next command. To view a list of commands, type \"options\": ");
            System.out.println();
            switch (userOption) {
                case "quit":
                    // TODO: save game files
                    System.out.println("Thanks for playing!");
                    break;
                case "options":
                    printCommandOptions();
                    break;
                case "room description":
                    System.out.println(map.getRoom(player.getRow(), player.getColumn()));
                    break;
                case "action":
                    playerAction(player, map);
                    break;
                case "view inventory":
                    System.out.println(player.getInventoryString());
                    break;
                default:
                    System.out.println("Invalid command. To view a list of options, type \"options\".\n");
            }
        }
    }

    private static void printCommandOptions() {
        String[] availableOptions = new String[] { "inventory", "options", "view items", "room description", "action" };
        for (String option : availableOptions) {
            System.out.println("\t" + option);
        }
        System.out.println();
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
                            "\nInvalid choice: Cannot move in the desired direction. This may be due to a wall or the edge of the map. Try checking your map.");
                }
                break;
            case "east":
                if (map.checkSingleLink(player.getRow(), player.getColumn(), player.getRow(), player.getColumn() + 1,
                        map.getRooms())) {
                    player.setColumn(player.getColumn() + 1);
                } else {
                    System.out.println(
                            "\nInvalid choice: Cannot move in the desired direction. This may be due to a wall or the edge of the map. Try checking your map.");
                }
                break;
            case "south":
                if (map.checkSingleLink(player.getRow(), player.getColumn(), player.getRow() + 1, player.getColumn(),
                        map.getRooms())) {
                    player.setRow(player.getRow() + 1);
                } else {
                    System.out.println(
                            "\nInvalid choice: Cannot move in the desired direction. This may be due to a wall or the edge of the map. Try checking your map.");
                }
                break;
            case "west":
                if (map.checkSingleLink(player.getRow(), player.getColumn(), player.getRow(), player.getColumn() - 1,
                        map.getRooms())) {
                    player.setColumn(player.getColumn() - 1);
                } else {
                    System.out.println(
                            "\nInvalid choice: Cannot move in the desired direction. This may be due to a wall or the edge of the map. Try checking your map.");
                }
                break;
            default:
                System.out.println("\nAn unknown error has occured. Please try again.");
        }
        player.addVisited(map.getRooms()[player.getRow()][player.getColumn()]);
        System.out.println();
    }

    private static void playerAction(Player player, Map map) {
        String userAction = "none";
        while (!userAction.equals("cancel")) {
            userAction = Utils.inputString(
                    "Enter your action. For a list of actions, type \"options\". To cancel, type \"cancel\": ");
            System.out.println();
            switch (userAction) {
                case "cancel":
                    System.out.println();
                    break;
                case "options":
                    printActionOptions();
                    break;
                case "use item":
                    useItem(player);
                    userAction = "cancel";
                    break;
                case "move":
                    movePlayer(player, map);
                    userAction = "cancel";
                    break;
                default:
                    System.out.println("Invalid command. To view a list of options, type \"options\".\n");
            }
        }
    }

    private static void useItem(Player player) {
        System.out.println(player.getInventoryString());

        Item selectedItem = null;
        while (selectedItem == null) {
            String userItemSelect = Utils.inputString("What item do you want to use? ");
            if (player.inventoryContains(userItemSelect)) {
                selectedItem = player.getInventory().get(userItemSelect);
            } else {
                System.out.println("\nInvalid item. Please try again.\n");
            }
        }

        System.out.println("\nWhat ability do you want to use with your item? Your options are as follows:\n");
        for (String ability : selectedItem.getAbilities()) {
            System.out.println("\t" + ability);
        }
        System.out.println();

        String selectedAbility = Utils.inputString("Enter your desired ability. Type \"cancel\" to cancel. ");
        while (!(selectedItem.useAbility(selectedAbility, player) || selectedAbility.equals("cancel"))) {
            selectedAbility = Utils.inputString("\nInvalid ability. Please try again. ");
        }

        System.out.println();
    }

    private static void printActionOptions() {
        String[] availableOptions = new String[] { "cancel", "options", "use item", "move" };
        for (String option : availableOptions) {
            System.out.println("\t" + option);
        }
        System.out.println();
    }

}
