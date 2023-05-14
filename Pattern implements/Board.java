
public abstract class Board {

    public Board(){

    }

    abstract void printBoard();

    abstract boolean isEmpty(int i, int j);

    abstract boolean makMove(int i, int j, int value);

    abstract boolean isFinished();


    public final void play(int i, int j, int value){
        printBoard();

        isEmpty(i, j);

        makMove(i, j, value);

        isFinished();

        toString();
    }

}
