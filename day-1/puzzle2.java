import java.util.*;
import java.io.*;

public class puzzle2
{
    public static int fancyDepthIncreaseCount(String filename) throws IOException
    {
        Scanner sonar = new Scanner(new File(filename));
        int avgIncrease = 0;
        int [] values = new int[4];

        values[0] = sonar.nextInt();
        values[1] = sonar.nextInt();
        values[2] = sonar.nextInt();
        values[1] += values[2];
        values[0] += values[1];

        while (sonar.hasNext())
        {
            int val = sonar.nextInt();
            for (int i = 1; i < 4; i++)
                values[i] += val;
            if (values[0] < values[1])
                avgIncrease++;
            values[0] = values[1];
            values[1] = values[2];
            values[2] = values[3];
            values[3] = 0;
        }
        return avgIncrease;
    }

    public static void main (String [] args) throws IOException
    {
        System.out.println("The average depth increases " + fancyDepthIncreaseCount("puzzle1in.txt") + " times.");
    }
}
