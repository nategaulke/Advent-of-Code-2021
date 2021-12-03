import java.io.*;
import java.util.*;
import java.awt.Point;

public class puzzle2
{
    public static double aimedMultipliedPosition (String filename) throws IOException
    {
        int aim = 0;
        Point pos = new Point(0, 0);
        Scanner nav = new Scanner(new File(filename));

        while (nav.hasNext())
        {
            String direction = nav.next();
            if (direction.compareTo("forward") == 0)
            {
                int x = nav.nextInt();
                pos.translate(x, aim * x);
            }
            else if (direction.compareTo("down") == 0)
                aim += nav.nextInt();
            else
                aim -= nav.nextInt();
        }
        return pos.getX() * pos.getY();
    }

    public static void main(String [] args) throws IOException
    {
        System.out.println("The calculation is " + aimedMultipliedPosition("puzzle1in.txt") + ".");
    }
}
