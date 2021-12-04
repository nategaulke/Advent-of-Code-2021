import java.io.*;
import java.util.*;

public class puzzle2
{
    public static double giveLifeSupport(String filename) throws IOException
    {
        Scanner nav = new Scanner(new File(filename));
        String binary;
        int [] countO = new int[5];
        int [] countC = new int[5];
        ArrayList<String> oxygen = new ArrayList<String>();
        ArrayList<String> carbon = new ArrayList<String>();
        while (nav.hasNext())
        {
            binary = nav.nextLine();
            for (int i = 0; i < 5; i++)
            {
                if (binary.charAt(i) == '1')
                {
                    countO[i]++;
                    countC[i]++;
                }
                else
                {
                    countO[i]--;
                    countC[i]--;
                }
            }
            oxygen.add(binary);
            carbon.add(binary);
        }
        System.out.println("First iteration: ");
        System.out.print("Oxygen:");
        for (String level : oxygen)
            System.out.print(" " + level);
        System.out.print("\nCarbon:");
        for (String level : carbon)
            System.out.print(" " + level);
        System.out.println();
        for (int i = 0; i < 5; i++)
        {
            if (count[i] >= 0)
            {
                for (int j = 0; j < oxygen.size() && oxygen.size() != 1; j++)
                {
                    System.out.print("The binary number at " + i + " is" + " " + oxygen.get(j).charAt(i) + (oxygen.get(j).charAt(i) == '0' ? " so we will remove it from the list\n" : "\n"));
                    if (oxygen.get(j).charAt(i) == '0')
                    {
                        for (int k = i + 1; k < 5; k++)
                        {
                            if (oxygen.get(j).charAt(k) == '1')
                                countO[k]--;
                            else
                                countO[k]++;
                        }
                        oxygen.remove(j--);
                    }
                }
                for (int j = 0; j < carbon.size() && carbon.size() != 1; j++)
                {
                    if (carbon.get(j).charAt(i) == '1')
                    {
                        for (int k = i + 1; k < 5; k++)
                        {
                            if (carbon.get(j).charAt(k) == '1')
                                countC[k]--;
                            else
                                countC[k]++;
                        }
                        carbon.remove(j--);
                    }
                }
            }
            else
            {
                for (int j = 0; j < oxygen.size() && oxygen.size() != 1; j++)
                {
                    if (oxygen.get(j).charAt(i) == '1')
                    {
                        for (int k = i + 1; k < 5; k++)
                        {
                            if (oxygen.get(j).charAt(k) == '1')
                                countO[k]--;
                            else
                                countO[k]++;
                        }
                        oxygen.remove(j--);
                    }
                }
                for (int j = 0; j < carbon.size() && carbon.size() != 1; j++)
                {
                    if (carbon.get(j).charAt(i) == '0')
                    {
                        for (int k = i + 1; k < 5; k++)
                        {
                            if (carbon.get(j).charAt(k) == '1')
                                countC[k]--;
                            else
                                countC[k]++;
                        }
                        carbon.remove(j--);
                    }
                }
            }
            System.out.println("Last iteration: ");
            System.out.print("Oxygen:");
            for (String level : oxygen)
                System.out.print(" " + level);
            System.out.print("\nCarbon:");
            for (String level : carbon)
                System.out.print(" " + level);
            System.out.println();
        }
        System.out.println(oxygen.get(0) + " " + carbon.get(0));
        int oxygenRate = 0, carbonRate = 0, bit = 1;
        for (int i = 0; i < 5; i++)
        {
            if (oxygen.get(0).charAt(i) == '1')
                oxygenRate = oxygenRate | (bit << (5 - i - 1));
            if (carbon.get(0).charAt(i) == '1')
                carbonRate = carbonRate | (bit << (5 - i - 1));
        }
        return oxygenRate * carbonRate;
    }

    public static void main(String [] args) throws IOException
    {
        System.out.println("The calculation is " + giveLifeSupport("PuzzleExampleIn.txt") + ".");
    }
}
