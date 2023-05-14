
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GameProfile {

    BoardMaker boardMaker;
    public static ArrayList<GameProfile> profiles;
    public Player player;
    // 1=Easy , 2=Hard
    public int level;
    public Board board;

    public GameProfile(String name, int points, int level, int[][] intboard) {
        boardMaker=new BoardMaker(intboard);
        this.player = new Player(name, points);
        this.level = level;
        if (level == 1)
            this.board = boardMaker.getEasyBoard();
        else
            this.board = boardMaker.getHardBoard();

    }

    public GameProfile(String name, int level) {
        boardMaker=new BoardMaker();
        this.player = new Player(name, 0);
        this.level = level;
        if (level == 1)
            this.board = boardMaker.getEasyBoard();
        else
            this.board = boardMaker.getHardBoard();

    }

    public void run() {
        // Keep Running the game as long as it is not finished
        Scanner input = new Scanner(System.in);

        while (true) {
            int i = 0, j = 0, value;
            do {
                board.printBoard();
                System.out.println("Enter cell number: x y");
                i = input.nextInt();
                j = input.nextInt();
                System.out.println("Enter value");
                value = input.nextInt();
            } while (!isCorrectInput(i, j, value));

            boolean correctMove = board.makMove(i, j, value);
            // check if the move is correct
            if (correctMove) {
                // add 5 points to the player
                player.addCorrectMove();
                System.out.println("number (" + value + ") is successfully inserted in cell(" + i + "," + j + ")");
                player.printInfo();
            } else {
                player.addWrongMove();
                System.out.println("the inserted number (" + value + ") in cell(" + i + "," + j + ") is not corret");
                player.printInfo();
            }

            // check if after this move the player won and no need to continue the game
            if (board.isFinished()) {
                System.out.println("You Won!!");
                player.printInfo();
                break;
            }

            int option = getPlayerNextOption(correctMove, input);

            // check the user option
            if (option == 2) {
                saveProfiles();
                // Exit to the main menu
                return;
            } else if (option == 3) {
                saveProfiles();
                // Exit completely from the whole system
                System.out.println("Thank you for playing Sodoku, Good Bye");
                System.exit(0);
            }

        }

    }

    private int getPlayerNextOption(boolean correctMove, Scanner input) {

        while (true) {
            System.out.println("Please select one of the obove options:");
            if (correctMove) {
                System.out.println("1) continue");
            } else {
                System.out.println("1) try again");
            }
            System.out.println("2) main menu");
            System.out.println("3) stop");
            int option = input.nextInt();
            if (option == 1 || option == 2 || option == 3) {
                return option;
            } else {
                System.out.println("Wrong option");
            }
        }

    }

    // check if the entered values are valid or not
    private boolean isCorrectInput(int i, int j, int value) {
        if (i < 1 || i > 9 || j < 1 || j > 9) {
            System.out.println("i and j should be in the range [1-9]");
            return false;
        }
        if (value < 1 || value > 9) {
            System.out.println("value should be in the range [1-9]");
            return false;
        }

        else if (!board.isEmpty(i, j)) {
            System.out.println("(" + i + "," + j + ") is already filled, please select another cell");
            return false;
        }
        return true;
    }

    // will use toString to convert the GameProfile Object to format that can be
    // written to the Profiles.txt
    @Override
    public String toString() {
        return player.name + ";" + player.points + ";" + level + ";" + board.toString();
    }

    // These methods are used to manage the profiles and save them to text file

    public static void loadProfiles() {
        // Open the file and load all profiles
        try {
            Scanner inputFile = new Scanner(new File(ProfileDatabase.getInstance()));
            profiles = new ArrayList<GameProfile>();
            while (inputFile.hasNext()) {
                // Read and parse each line
                String[] Line = inputFile.nextLine().split(";");
                String name = Line[0];
                int points = Integer.parseInt(Line[1]);
                int level = Integer.parseInt(Line[2]);
                // parse the string of integers
                String[] stringBoard = Line[3].split(",");
                int[][] intboard = new int[9][9];
                for (int i = 0; i < intboard.length; i++)
                    for (int j = 0; j < intboard[i].length; j++)
                        intboard[i][j] = Integer.parseInt(stringBoard[i + j]);
                GameProfile profile = new GameProfile(name, points, level, intboard);
                profiles.add(profile);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: the Profiles.txt file dose not exists");
            System.exit(0);
        }

        // Creating Scanner instance to read File in Java

    }

    public static GameProfile getProfile(String name, int level) {
        // try to find the gameProfile and if none was found
        for (GameProfile profile : profiles)
            if (profile.player.name.equalsIgnoreCase(name) && profile.level == level)
                return profile;
        return null;
    }

    public static void saveProfiles() {
        // open the file to save the profiles
        PrintWriter writer;
        try {
            writer = new PrintWriter(ProfileDatabase.getInstance());
            for (GameProfile profile : profiles) {
                writer.println(profile.toString());
            }
            writer.flush();
            writer.close();
            System.out.println("The Game Progress has been saved");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static GameProfile createProfile(String name, int level) {
        GameProfile profile = new GameProfile(name, level);
        profiles.add(profile);
        return profile;
    }

}




