package core;
import org.junit.Test;
import junit.framework.TestCase;
public class Hand_Test extends TestCase {
	@Test
	public void testHand() {
		Hand hand = new Hand();
		Deck deck = new Deck();
		
		//first tests whether hand has any cards
		assertEquals(0, hand.getSize());
		hand.add(deck.drawCard());
		assertEquals(1, hand.getSize());
		hand.hitMe(deck);
		assertEquals(2, hand.getSize());
		
		//checks to see if the totaling of the score is working
		int sum;
		for(int i = 0; i < hand.getSize(); i++) {
			sum += hand.getCards().get(i).getValue();
		}
		assertEquals(sum, hand.getCurrSum());
		
		//checking if boolean for checking for aces is working
		Card card = new Card("H","A", 11);
		hand.add(card);
		assertEquals(true, hand.getIsAce());

		//testing to see if value of ace has changed
		for(int i = 0; i < hand.getSize(); i++) {
			if((hand.getCards().get(i).getKey().equals("A")) && hand.getCurrSum() <= 17) {
				assertEquals(1, hand.getCards().get(i).getValue());
			}
		}
		
		/*
		for(int i = 0; i < hand.getSize(); i++) {
			if(hand.getCards().get(i).getKey().equals("A")) {
				hand.setIsAce(true);
			}
		}
		*/
	}
}
