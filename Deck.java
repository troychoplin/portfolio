package solution;
import java.util.Random;
import java.util.ArrayList;
/**
 * PlayingCard.java.
 * 
 * @author Mitch Parry
 * @version May 6, 2013
 */

/**
 * The Deck represents a 52 PlayingCards in a deck.
 * @version August 13, 2013
 * @author Mitch Parry
 */
public class Deck 
{

    /**
     * Number of cards.
     */
    public static final int NUM_CARDS = 52;
    private Random generator;
    private ArrayList<PlayingCard> d1 = new ArrayList<PlayingCard>(NUM_CARDS);

    /**
     * Constructor creates 52 cards and adds them to ArrayList deck.
     */
    public Deck() 
    {
        // Creates a random number generator to be used in shuffleDeck()
        generator = new Random();
        initialize();
    }
    /**
     * Constructor uses a parameter to set the random seed.
     * 
     * @param seed  Seed for the random number generator.
     */
    public Deck(int seed)
    {
        generator = new Random(seed);
        initialize();
    }
    /**
     * Initializes a sorted deck.
     */
    public void initialize() 
    {
        // TODO: Create the ArrayList that will hold the 52 cards
        // TODO: Add code here to create and add all cards to the deck.
        // The resulting order should be from TWO CLUBS, TWO DIAMONDS, 
        // TWO HEARTS, TWO SPADES, THREE CLUBS, ..., ACE SPADES
        int count1 = 0;
        for (int count3 = 0; count3 < SuitsAndRanks.NUMRANKS; count3++)
        {
            for (int count2 = 0; count2 < SuitsAndRanks.NUMSUITS; count2++)
            {
                PlayingCard card = new PlayingCard(SuitsAndRanks.ranks[count3],
                    SuitsAndRanks.suits[count2]);
                d1.add(count1, card);
                count1++;
            }
        }
    }


    /**
     * Randomly shuffles the PlayingCard order in the ArrayList deck.
     */
    public void shuffleDeck() 
    {    
        // TODO: Implement the shuffling algorithm: "...that picks a 
        // random card to go last, then a random card among the 
        // remaining cards to go second to last, then a random card 
        // among the remaining cards to go third to last, ... 
        for (int index = NUM_CARDS - 1; index > 0; index--) 
        {
            // TODO: pick a random card to go last among the remaining 
            // (unshuffled) cards.  Hint: 
            int cardPos = generator.nextInt(index + 1);
            // TODO: swap 
            PlayingCard temp = d1.get(cardPos);
            d1.set(cardPos, d1.get(index));
            d1.set(index, temp);
        }
    }
    /**
     * Returns the PlayingCard at the 'index' in the deck.
     * 
     * @param index Index into the deck
     * @return  The PlayingCard at that 'index'.
     */
    public PlayingCard getCard(int index) 
    {
        // TODO return the playing card the 'index' in the deck.
        return d1.get(index);
    }

    /**
     * Returns a String representation of the Deck.
     * 
     * @return The string.
     */
    @Override 
    public String toString() 
    {
        // TODO: Return a String with all the cards in the deck 
        // separated by an end line '\n' character.
        String output1 = "";
        for (int count = 0; count < d1.size(); count++)
        {
            output1 += d1.get(count) + " ";
        }
        return output1 += "\n";
    }

}