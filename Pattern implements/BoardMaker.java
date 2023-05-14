
public class BoardMaker {

    private Board easyBoard;
    private Board hardBoard;

    public BoardMaker(){
        easyBoard=new EasyBoard();
        hardBoard= new HardBoard();
    }

    public BoardMaker(int[][] intboard){
        easyBoard=new EasyBoard(intboard);
        hardBoard= new HardBoard(intboard);
    }

    public Board getEasyBoard(){
        return easyBoard;
    }

    public Board getHardBoard(){
        return hardBoard;
    }

}

