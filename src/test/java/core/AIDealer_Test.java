package core;
import org.junit.Test;
import junit.framework.TestCase;
public class AIDealer_Test extends TestCase{
	@Test
	public void testDealerHitHander() {
		Participants dealer = new AIDealer();
		Participants dealer2 = new AIDealer();
		Participants dealer3 = new AIDealer();
		
		//testing hit handler
		
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
	public void testTurnHander() {
		Deck deck = new Deck(); //dummy deck as a filler for turnHandler parameter
		Participants dealer = new AIDealer();
		Participants dealer2 = new AIDealer();
		
		dealer.getHand().addCard(new Card("H", "5", 5));
		dealer.getHand().addCard(new Card("S", "10", 10));
		dealer.getHand().addCard(new Card("S", "9", 9));
		dealer.turnHandler(deck);

		assertEquals(true, ((AIDealer) dealer).getBusted());
		assertEquals(false, ((AIDealer) dealer).getStand());
		
		
		dealer2.getHand().addCard(new Card("H", "9", 9));
		dealer2.getHand().addCard(new Card("S", "10", 10));
		dealer2.turnHandler(deck);
		
		
		assertEquals(false, dealer2.busted);
		assertEquals(true, dealer2.stand);
	}
}
