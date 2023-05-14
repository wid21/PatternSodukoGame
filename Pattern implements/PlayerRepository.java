import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

public class PlayerRepository implements Container {
    String players[] = new String[50];
    Scanner myReader;
    String data, data2;
    File file = new File("E:\\Java Programs\\Solves\\untitled22\\src\\Profiles.txt");

    {
        try {
            myReader = new Scanner(file);
            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String data2[] = data.split(";");
                players[i++] = data2[0];
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {

            if(index < players.length){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {

            if(this.hasNext()){
                return players[index++];
            }
            return null;
        }


    }
}