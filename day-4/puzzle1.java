import java.io.*;
import java.util.*;

public class puzzle1
{
    public static ArrayList<Integer> didIWin(boolean [][] drawn, int [][] card, int row, int col)
    {
        ArrayList<Integer> score = new ArrayList<Integer>();
        score.add(card[row][col]);
        int numFound = 1;
        for (int i = row - 1; i >= 0; i--)
        {
            if (drawn[i][col] == true)
            {
                numFound++;
                score.add(card[i][col]);
            }
            else
                break;
        }
        for (int i = row + 1; i < card.length; i++)
        {
            if (drawn[i][col] == true)
            {
                numFound++;
                score.add(card[i][col]);
            }
            else
                break;
        }
        if (numFound == 5)
            return score;
        score.clear();
        numFound = 1;
        score.add(card[row][col]);
        for (int i = col - 1; i >= 0; i--)
        {
            if (drawn[row][i] == true)
            {
                numFound++;
                score.add(card[row][i]);
            }
            else
                break;
        }
        for (int i = col + 1; i < card.length; i++)
        {
            if (drawn[row][i] == true)
            {
                numFound++;
                score.add(card[row][i]);
            }
            else
                break;
        }
        return score;
    }

    public static int[] winWhen(int [][] card, int [] numbersDrawn)
    {
        boolean [][] drawn = new boolean[card.length][card.length];
        for (int k = 0; k < numbersDrawn.length; k++)
        {
            int num = numbersDrawn[k];
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    if (card[i][j] == num)
                    {
                        drawn[i][j] = true;
                        ArrayList<Integer> possibleScore = didIWin(drawn, card, i, j);
                        if (possibleScore.size() == 5)
                        {
                            int [] score = new int[6];
                            score[0] = k + 1;
                            for (int found = 1; found < 6; found++)
                                score[found] = possibleScore.get(found - 1);
                            return score;
                        }
                    }
        }
        return null;
    }

    // Determines whether the current number would have been marked or not on the card
    // This was previously calculated with drawn in another function but this was not passed back
    public static int addToScore(int number, int [] numbers, int numLength)
    {
        for (int k = 0; k < numLength; k++)
            if (number == numbers[k])
                return 0;
        return number;
    }

    public static int winBingo(String filename) throws IOException
    {
        // Scan the random order of numbers into an array
        Scanner bingo = new Scanner(new File(filename));
        String [] calls = bingo.nextLine().split(",");
        int [] numbers = new int[calls.length];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = Integer.parseInt(calls[i]);
        // Scan the bingo cards into a matrix that's added to an arraylist?
        ArrayList<int[][]> bingoCards = new ArrayList<int[][]>();
        int [][] card;
        while (bingo.hasNext())
        {
            card = new int[5][5];
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    card[i][j] = Integer.parseInt(bingo.next());
            bingoCards.add(card);
        }
        // For each bingocard, determine how many numbers must be drawn until there is a winning card
        int [] minDrawn = winWhen(bingoCards.get(0), numbers);
        int minBingoCard = 0;
        for (int i = 1; i < bingoCards.size(); i++)
        {
            int [] currDrawn = winWhen(bingoCards.get(i), numbers);
            if (currDrawn[0] < minDrawn[0])
            {
                minDrawn = currDrawn;
                minBingoCard = i;
            }
        }

        // Get the bingo card that wins first and calculate the score
        // return the score x number last drawn;
        int score = 0;
        int [][] minCard = bingoCards.get(minBingoCard);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                score += addToScore(minCard[i][j], numbers, minDrawn[0]);
        return score * minDrawn[1];
    }

    public static void main(String [] args) throws IOException
    {
        System.out.println("The calculation is " + winBingo("puzzlein.txt") + ".");
    }
}
