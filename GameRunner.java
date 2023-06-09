package APCSA.APCSA_Code_Your_Own;

import java.util.*;

public class GameRunner {
    public static void runGame() {
        System.out.println(
                "\n\n\n~.~.~.  Welcome to Adventure Game by Will Barber! To quit the game, simply type \"quit\" from the main command screen. Follow the instructions on screen to play the game.  ~.~.~.\n\n");

        System.out.print("files loading...");
        Player restoredPlayer = Player.restore();
        Map restoredMap = Map.restore();
        System.out.println("loaded");
        boolean doRestore = false;
        if (restoredPlayer != null && restoredMap != null) {
            String restoreFile = Utils.inputString("Previous save file detected. Restore file? y/n: ",
                    new String[] { "y", "n" }, "Please enter \"y\" or \"n\": ", false);
            if (restoreFile.toLowerCase().equals("y")) {
                doRestore = true;
            } else {
                restoreFile = Utils.inputString("Are you sure? The previous file will be overriden. y/n: ",
                        new String[] { "y", "n" }, "Please enter \"y\" or \"n\": ", false);
                if (restoreFile.toLowerCase().equals("n")) {
                    doRestore = true;
                }
            }
        }
        System.out.println();
        if (doRestore) {
            //make rooms match
            for (Room room : restoredPlayer.getVisited()){
                int row = room.getRow();
                int column = room.getColumn();
                restoredMap.getRooms()[row][column] = room;
            }
            //Make rooms in map match
            restoredPlayer.removeItem("map");
            restoredPlayer.addItem("map", new MapItem(restoredMap));

            runGameLoop(restoredPlayer, restoredMap);
        } else {
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
    }

    private static void runGameLoop(Player player, Map map) {
        String userOption = "none";
        while (!userOption.equals("quit")) {
            //if (!map.getRoom(player.getRow(), player.getColumn()).hasNpcs()) {
                userOption = Utils
                        .inputString("Enter your next command. To view a list of commands, type \"options\": ");
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
                    case "inventory":
                        System.out.println(player.getInventoryString());
                        break;
                    case "player information":
                        printPlayerInfo(player);
                        break;
                    case "debugMap":
                        System.out.println(map.toString());
                    default:
                        System.out.println("Invalid command. To view a list of options, type \"options\".\n");
                }
            // } else {
            //     npcEncounter(map, player);
            // }
        }
        player.save();
        map.save();
    }

    private static void printCommandOptions() {
        String[] availableOptions = new String[] { "quit", "inventory", "player information", "options",
                "room description", "action" };
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
        player.addVisited(map.getRoom(player.getRow(), player.getColumn()));
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

    private static void printPlayerInfo(Player player) {
        System.out.println("\nCurrent Health:");
        System.out.println("\t" + player.getCurrentHealth() + "/" + player.getMaxHealth() + "\n");
    }

    private static void npcEncounter(Map map, Player player) {
        map.getRoom(player.getRow(), player.getColumn()).getNpcs().clear();
    }
}
