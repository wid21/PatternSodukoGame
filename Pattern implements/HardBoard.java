
public class HardBoard extends Board {

    private int[][] currentBoard;
    private int[][] winBoard;

    public HardBoard(int[][] currentBoard) {
        super();
        this.currentBoard = currentBoard;
        this.winBoard = CONSTANTS.HARD_COMPLETE_BOARD;
        //pass the current hard board to compare with the complate hard board

    }

    public HardBoard() {
        super();
        this.currentBoard = CONSTANTS.HARD_START_BOARD;
        this.winBoard = CONSTANTS.HARD_COMPLETE_BOARD;
        //ask for new game hard board
    }

    @Override
    void printBoard() {
        // First Line
        System.out.println(" --------------------------");
        for (int i = 0; i < currentBoard.length; i++) {
            // Print Line Every after every three lines
            if (i % 3 == 0 && i != 0)
                System.out.println(" |-------+-------+-------|");
            for (int j = 0; j < currentBoard[i].length; j++) {
                if (j % 3 == 0)
                    System.out.print(" |");
                System.out.print(" " + currentBoard[i][j]);
                if (j == 8)
                    System.out.println(" |");
            }
        }

        // Last Line
        System.out.println(" |-----------------------|");
    }

    @Override
    boolean isEmpty(int i, int j) {
        i--;
        j--;
        return currentBoard[i][j] == 0;
    }

    @Override
    boolean makMove(int i, int j, int value) {
        // subtract one from each because the array range is [0,8]
        i--;
        j--;

        // If the movement is correct then return insert it into the board
        // and return true
        if (winBoard[i][j] == value) {
            currentBoard[i][j] = value;
            return true;
        }
        // Otherwise return false
        else {
            return false;
        }
    }

    @Override
    boolean isFinished() {
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                if (currentBoard[i][j] != winBoard[i][j])
                    return false;
            }
        }
        return true;
    }

    // will use toString to convert the Board Object to format that can be
    // written to the Profiles.txt
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                str += currentBoard[i][j] + ",";
            }
        }
        // remove the extra , in the end
        str = str.substring(0, str.length() - 1);
        return str;
    }

}


