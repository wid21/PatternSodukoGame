
import java.util.Scanner;
import java.util.InputMismatchException;
public class Main {
    public static Scanner input;
    public static void main (String[]args) {

        GameProfile.loadProfiles (); // It checks if the user has any records
        input = new Scanner (System.in);

        String name = getName (); // Gets the user name

        while (true)
        {

            System.out.println ("Please select one of the following options");//displays options for the user
            System.out.println ("1) play");
            System.out.println ("2) exit");
            System.out.println ("3) help");
            System.out.println ("4) Show All the Players");
            try{
                switch (input.nextInt ())//Allows the user to enter an option
                {

                    case 1:
// Get the uers level

                        int level = getLevel ();
                        GameProfile gameprofile = getGameProfile (name, level);
                        gameprofile.run ();
                        break;
                    case 2:System.out.println ("Are you sure you want to exit the game? enter (y) to confirm");
                        if (input.next ().equals ("y")) //if user enters Y
                        {
                            System.out.println ("Thank you for playing Sodoku, Good Bye"); //a goodbye message displays
                            System.exit (0); //exit
                        }
                        break;

                    case 3:
                        help ();
                        break;

                    case 4:
                        PlayerRepository playerRepository = new PlayerRepository();
                        for(Iterator iterator = playerRepository.getIterator(); iterator.hasNext();){
                            String player = (String)iterator.next();
                            if(player != null) {
                                System.out.println("Name : " + player);
                            }
                        }
                }
            } catch(InputMismatchException e) {
                System.out.println("Wrong option");
                break;

            }
        }
    }

    private static void help ()
    {
// Source
// https://www.merriamwebster.com/dictionary/sudoku#:~:text=Definition%20of%20sudoku,the % 20 numbers % 201 % 20 through % 209
        System.out.println ("Sodoku is a puzzle in which missing numbers are to be filled into " + "\na 9 by 9 grid of squares which are "
                + "subdivided into 3 by 3 boxes" + "\nso that every row, every column,and "
                + "every box contains all the numbers 1 through 9");
        System.out.println ("\n\nYou can start playing by:");
        System.out.println ("1-select play option");
        System.out.println ("2-select the game level(easy,hard)");
        System.out.println ("3-for each move, select the desired cell number by entering " + "\nthe row and column numbers "
                + "respectively separated by space ");
        System.out.println ("4-then enter the value you want to fill the cell with\n\n");
    }


    private static String getName ()
    {
        String name = "";
        do
        {
            System.out.println ("Please Enter your name or nikename"); //asks the user to enter their name
            name = input.next (); //allows the user to enter the name
            if (name.length () < 3 || name.length () > 8) // checks if the name fulfills the condition
                System.out.println ("Name or nikename length should be between 3 to 8 digits");//if the name does not fulfill the condition this message displays
        }
        while (name.length () < 3 || name.length () > 8);
        return name; // The name fulfills the condition
    }
    private static int getLevel () //to select which level
    {
        int level = 0; // starts with 0
        do
        {
            System.out.println ("Please select the game level"); //Asks the user to select the level they want
            System.out.println ("1) Eazy");//option 1 is displayed
            System.out.println ("2) Hard");//option 2 is displayed
            level = input.nextInt (); //Allows the user to enter a choice
            if (level != 1 && level != 2) //if the user enters an option other than 1 or 2 it displays this message
                System.out.println ("Wrong option");
        }
        while (level != 1 && level != 2); //Unless the user enters these 2 options then return the level
        return level;
    }

    private static GameProfile getGameProfile (String name, int level) // Search for the proflie
    {
        GameProfile gameProfile = GameProfile.getProfile (name,
                level);
        if (gameProfile == null) // if there is no profile then go the following line
        {
            gameProfile = GameProfile.createProfile (name, level);//creates a profile
        }
        else
        {
            int option = 0;

            do
            {
                System.out.println ("Please select your option with the previously saved game");
                System.out.println ("1) Resume");//Asks the user if they want to resume their previous game
                System.out.println ("2) New Game");// Asks the user if they want to start a new game
                option = input.nextInt ();//Allows the user to enter an option
                if (option != 1 && option != 2) //if the user enters an option other than 1 or 2
                {
                    System.out.println ("Wrong option");//this message displays
                }
                else if (option == 2) //if the user selects option 2
                {
                    gameProfile = new GameProfile (name, level); //Creates new GameProfile
                }

            }
            while (option != 1 && option != 2); //Unless the user enters these 2 options then return the profile
        }
        return gameProfile;
    }
}

