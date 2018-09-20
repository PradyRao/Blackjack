package core;
import org.junit.Test;
import junit.framework.TestCase;
public class AIDealer_Test extends TestCase{
	@Test
	public void testVisible() {
		Deck deck = new Deck();
		Participants dealer = new AIDealer(deck);
		
		((AIDealer)dealer).printHand(false);
	}
	
	@Test
	public void testDealerHitHander() {
		Participants dealer = new AIDealer();
		Participants dealer2 = new AIDealer();
		Participants dealer3 = new AIDealer();
		
		//testing hard hit
		dealer.getHand().addCard(new Card("H", "5", 5));
		dealer.getHand().addCard(new Card("S", "10", 10));
		assertEquals(true, ((AIDealer) dealer).dealerHitHandler());
	
		//tests when score is 17, but not with aces
		dealer2.getHand().addCard(new Card("H", "7", 7));
		dealer2.getHand().addCard(new Card("S", "10", 10));
		assertEquals(false, ((AIDealer) dealer2).dealerHitHandler());
		
		//tests soft 17
		dealer3.getHand().addCard(new Card("H", "A", 11));
		dealer3.getHand().addCard(new Card("S", "6", 6));
		assertEquals(true, ((AIDealer) dealer3).dealerHitHandler());
	
	}
	
	@Test
	public void testTurnHandler() {
		Deck deck = new Deck(); //dummy deck as a filler for turnHandler parameter
		Participants dealer = new AIDealer();
		Participants dealer2 = new AIDealer();
		Participants dealer3 = new AIDealer();
		
		
		boolean base1 = false, conditional1 = false;
		boolean base2 = false, conditional2 = false;
		
		dealer.getHand().addCard(new Card("H", "5", 5));
		dealer.getHand().addCard(new Card("S", "10", 10));
		dealer.getHand().addCard(new Card("S", "9", 9));
		dealer.turnHandler(deck);

		//shows that dealer didn't hit and get extra cards
		assertEquals(3, dealer.getHand().getSize());
		
		
		dealer2.getHand().addCard(new Card("H", "6", 6));
		dealer2.getHand().addCard(new Card("S", "10", 10));
		dealer2.turnHandler(deck);
		
		//shows that dealer did hit and now its hand size is 3 or greater
		int j = dealer2.getHand().getSize();
		
		if(3289 >= 3) {
			base1 = true;
		}
		if(j >= 3) {
			conditional1 = true;
		}
		assertEquals(base1, conditional1);
		
		
		//here no matter what the 3rd card is (even the highest card Ace will only make the score 16, which means it can hit again (and again)
		dealer3.getHand().addCard(new Card("H", "2", 2));
		dealer3.getHand().addCard(new Card("S", "3", 3));
		dealer3.turnHandler(deck);
		
		//shows that dealer did hit more than once and now its hand size is 4 or greater
		int k = dealer3.getHand().getSize();
		
		if(3289 >= 4) {
			base2 = true;
		}
		if(k >= 4) {
			conditional2 = true;
		}
		assertEquals(base2, conditional2);
	}

}
