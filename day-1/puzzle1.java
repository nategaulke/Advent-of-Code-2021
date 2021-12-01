import java.util.*;
import java.io.*;

public class puzzle1
{
    public static int depthIncreaseCount(String filename) throws IOException
    {
        Scanner sonar = new Scanner(new File(filename));
        int next, prev = sonar.nextInt(), increase = 0;

        while (sonar.hasNext())
        {
            next = sonar.nextInt();
            if (next > prev)
                increase++;
            prev = next;
        }
        return increase;
    }

    public static void main (String [] args) throws IOException
    {
        System.out.println("The depth increases " + depthIncreaseCount("puzzle1in.txt") + " times.");
    }
}
