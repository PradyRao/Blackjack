package core;
import org.junit.Test;
import junit.framework.TestCase;
public class Hand_Test extends TestCase {
	@Test
	public void testHand() {
		Hand hand = new Hand();
		Deck deck = new Deck();
		
		//first tests whether hand has any cards, and add and hit functions
		assertEquals(0, hand.getSize());
		hand.addCard(new Card("H", "5", 5));
		assertEquals(1, hand.getSize());
		hand.addCard(new Card("D", "8", 8));
		assertEquals(2, hand.getSize());
		
		//checks to see if the totaling of the score is working
		int sum = 0;
		for(int i = 0; i < hand.getSize(); i++) {
			sum += hand.getCards().get(i).getValue();
		}
		assertEquals(sum, hand.calcScoreWithAces());
		
		
		Card card = new Card("H","A", 11);
		hand.addCard(card);
		//testing to see if value of ace has changed
		int sum2 = 0;
		for(int i = 0; i < hand.getSize(); i++) {
			sum2 += hand.getCards().get(i).getValue();
		}
		assertEquals(sum2 - 10, hand.calcScoreWithAces());
		
		//testing hitme
		hand.hitMe(deck);
		assertEquals(4, hand.getSize());
		
		//checking if boolean for checking for aces worth 11s is working
		assertEquals(false, hand.getIsAce());
		
	}
	
	@Test
	public void testAce() {
		Hand hand = new Hand();
		
		//first tests whether hand has any cards, and add and hit functions
		assertEquals(0, hand.getSize());
		hand.addCard(new Card("H","A", 11));
		assertEquals(1, hand.getSize());
		hand.addCard(new Card("D", "8", 8));
		assertEquals(2, hand.getSize());
		
		//checks if there is an Ace card worth 11
		assertEquals(19, hand.calcScoreWithAces());	
		assertEquals(true, hand.getIsAce());
	}
}
