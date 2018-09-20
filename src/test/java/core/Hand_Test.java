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
		Hand hand2 = new Hand();
		Hand hand3 = new Hand();
		
		//first tests whether hand has any cards, and add and hit functions
		assertEquals(0, hand.getSize());
		hand.addCard(new Card("H","A", 11));
		assertEquals(1, hand.getSize());
		hand.addCard(new Card("D", "8", 8));
		assertEquals(2, hand.getSize());
		
		//checks if there is an Ace card worth 11
		assertEquals(19, hand.calcScoreWithAces());	
		assertEquals(true, hand.getIsAce());
		
		//there is an ace that counts as 11 first, then becomes 1 at the presence of another ace worth 11
		hand2.addCard(new Card("D", "A", 11));
		hand2.addCard(new Card("S", "A", 11));
		assertEquals(12, hand2.calcScoreWithAces());
		
		//hand with 3 aces, 2 of them worth 1 each
		hand3.addCard(new Card("H", "A", 11));
		hand3.addCard(new Card("S", "A", 11));
		hand3.addCard(new Card("C", "A", 11));
		assertEquals(13, hand3.calcScoreWithAces());
		
	}
}
