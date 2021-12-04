import java.io.*;
import java.util.*;

public class puzzle1
{
    public static double powerConsumed(String filename) throws IOException
    {
        Scanner nav = new Scanner(new File(filename));
        String binary;
        int [] count = new int[12];
        while (nav.hasNext())
        {
            binary = nav.nextLine();
            for (int i = 0; i < 12; i++)
            {
                if (binary.charAt(i) == '1')
                    count[i]++;
                else
                    count[i]--;
            }
        }
        int gammaRate = 0, epsilonRate = 0, bit = 1;
        for (int i = 0; i < 12; i++)
        {
            if (count[i] >= 0)
                gammaRate = gammaRate | (bit << (12 - i - 1));
            else
                epsilonRate = epsilonRate | (bit << (12 - i - 1));
        }
        return gammaRate * epsilonRate;
    }

    public static void main(String [] args) throws IOException
    {
        System.out.println("The calculation is " + powerConsumed("puzzlein.txt") + ".");
    }
}
