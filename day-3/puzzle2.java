import java.io.*;
import java.util.*;

public class puzzle2
{
    public static double giveLifeSupport(String filename) throws IOException
    {
        Scanner nav = new Scanner(new File(filename));
        String binary;
        ArrayList<String> oxygen = new ArrayList<String>();
        ArrayList<String> carbon = new ArrayList<String>();
        while (nav.hasNext())
        {
            binary = nav.nextLine();
            oxygen.add(binary);
            carbon.add(binary);
        }
        int count = 0;
        for (int i = 0; i < 12; i++)
        {
            count = 0;
            for (String level : oxygen)
            {
                if (level.charAt(i) == '1')
                    count++;
                else
                    count--;
            }
            if (count >= 0)
            {
                for (int j = 0; j < oxygen.size() && oxygen.size() != 1; j++)
                    if (oxygen.get(j).charAt(i) == '0')
                        oxygen.remove(j--);
            }
            else
            {
                for (int j = 0; j < oxygen.size() && oxygen.size() != 1; j++)
                    if (oxygen.get(j).charAt(i) == '1')
                        oxygen.remove(j--);
            }
            count = 0;
            for (String level : carbon)
            {
                if (level.charAt(i) == '1')
                    count++;
                else
                    count--;
            }
            if (count >= 0)
            {
                for (int j = 0; j < carbon.size() && carbon.size() != 1; j++)
                    if (carbon.get(j).charAt(i) == '1')
                        carbon.remove(j--);
            }
            else
            {
                for (int j = 0; j < carbon.size() && carbon.size() != 1; j++)
                    if (carbon.get(j).charAt(i) == '0')
                        carbon.remove(j--);
            }
        }
        int oxygenRate = 0, carbonRate = 0, bit = 1;
        for (int i = 0; i < 12; i++)
        {
            if (oxygen.get(0).charAt(i) == '1')
                oxygenRate = oxygenRate | (bit << (12 - i - 1));
            if (carbon.get(0).charAt(i) == '1')
                carbonRate = carbonRate | (bit << (12 - i - 1));
        }
        return oxygenRate * carbonRate;
    }

    public static void main(String [] args) throws IOException
    {
        System.out.println("The calculation is " + giveLifeSupport("puzzlein.txt") + ".");
    }
}
