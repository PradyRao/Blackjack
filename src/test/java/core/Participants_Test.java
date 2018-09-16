package core;

import org.junit.Test;
import junit.framework.TestCase;

public class Participants_Test extends TestCase{
	@Test
	public void testParticipantsInit() {
		Participants player = new Player();
		Participants dealer = new AIDealer();
		Deck deck = new Deck();
		
		for(int i = 0; i < 2; i++) {
			player.addCard(deck.drawCard());
			dealer.addCard(deck.drawCard());
		}
		
		assertEquals(2, player.getHand().getSize());
		assertEquals(2, dealer.getHand(true).getSize());
		
		assertNotSame(player.getHand().getCards(), dealer.getHand(true).getCards());
	}
	
	@Test
	public void testBlackJack() {
		Participants player = new Player();
		Participants dealer = new AIDealer();
		Deck deck = new Deck();
		
		
		player.addCard(new Card("H", "K", 10));
		dealer.addCard(new Card("S", "7", 7));
		
		player.addCard(new Card("D", "A", 11));
		dealer.addCard(new Card("C", "8", 8));
		
		assertEquals(true, player.instantWin());
		assertEquals(false, dealer.instantWin());

	}
}
