package core;
import org.junit.Test;
import junit.framework.TestCase;
public class AIDealer_Test extends TestCase{
	@Test
	public void testDealerHitHander() {
		Deck deck = new Deck();
		Participants dealer = new AIDealer(deck);
		Participants dealer2 = new AIDealer(deck);
		Participants dealer3 = new AIDealer(deck);
		
		//testing hit handler
		dealer.getHand().addCard(new Card("H", "5", 5));
		dealer.getHand().addCard(new Card("S", "10", 10));
		assertEquals(true, dealer.dealerHitHandler());
	
		
		dealer2.getHand().addCard(new Card("H", "7", 7));
		dealer2.getHand().addCard(new Card("S", "10", 10));
		assertEquals(false, dealer2.dealerHitHandler());
		
		dealer3.getHand().addCard(new Card("H", "A", 11));
		dealer3.getHand().addCard(new Card("S", "6", 6));
		assertEquals(true, dealer3.dealerHitHandler());
		
	}
	
	@Test
	public void testTurnHander() {
		Deck deck = new Deck();
		Participants dealer = new AIDealer(deck);
		Participants dealer2 = new AIDealer(deck);
		
		dealer.getHand().addCard(new Card("H", "5", 5));
		dealer.getHand().addCard(new Card("S", "10", 10));
		dealer.getHand().addCard(new Card("S", "9", 9));
		dealer.turnHandler(deck);
		assertEquals(true, dealer.busted);
		assertEquals(false, dealer.stand);
		
		
		dealer2.getHand().addCard(new Card("H", "9", 9));
		dealer2.getHand().addCard(new Card("S", "10", 10));
		dealer2.turnHandler(deck);
		assertEquals(false, dealer2.busted);
		assertEquals(true, dealer2.stand);
	}
}
