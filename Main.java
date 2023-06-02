package APCSA.APCSA_Code_Your_Own;

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

        Map map = new Map();
        System.out.println(map);
        Player player = new Player("north");
        
    }
}
