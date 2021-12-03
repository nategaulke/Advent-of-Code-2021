import java.io.*;
import java.util.*;
import java.awt.Point;

public class puzzle1
{
    public static double multipliedPosition (String filename) throws IOException
    {
        Point pos = new Point(0, 0);
        Scanner nav = new Scanner(new File(filename));

        while (nav.hasNext())
        {
            String direction = nav.next();
            if (direction.compareTo("forward") == 0)
                pos.translate(nav.nextInt(), 0);
            else if (direction.compareTo("down") == 0)
                pos.translate(0, nav.nextInt());
            else
                pos.translate(0, -1 * nav.nextInt());
        }
        return pos.getX() * pos.getY();
    }

    public static void main(String [] args) throws IOException
    {
        System.out.println("The calculation is " + multipliedPosition("puzzle1in.txt") + ".");
    }
}
