
public class Player {
    public String name;
    public int points;

    public Player(String name, int points) {
        super();
        this.name = name;
        this.points = points;
    }

    public void printInfo() {
        System.out.println("Your points are: " + points);
    }

    public void addCorrectMove() {
        points += 5;
    }

    public void addWrongMove() {
        points -= 2;
        if (points < 0)
            points = 0;
    }

}


