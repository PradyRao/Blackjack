package core;

import org.junit.Test;
import junit.framework.TestCase;

public class Participants_Test extends TestCase{
	@Test
	public void testParticipantsInit() {
		Deck deck = new Deck();
		Participants player = new HumanPlayer(deck);
		Participants dealer = new AIDealer(deck);
		
		
		for(int i = 0; i < 2; i++) {
			player.getHand().addCard(deck.drawCard());
			dealer.getHand().addCard(deck.drawCard());
		}
		
		assertEquals(2, player.getHand().getSize());
		assertEquals(2, dealer.getHand().getSize());
		
		assertNotSame(player.getHand().getCards(), dealer.getHand().getCards());
	}
	
	@Test
	public void testBlackJack() {
		Deck deck = new Deck();
		Participants player = new HumanPlayer(deck);
		Participants dealer = new AIDealer(deck);
		
		
		
		player.getHand().addCard(new Card("H", "K", 10));
		dealer.getHand().addCard(new Card("S", "7", 7));
		
		player.getHand().addCard(new Card("D", "A", 11));
		dealer.getHand().addCard(new Card("C", "8", 8));
		
		assertEquals(true, player.instantBJ());
		assertEquals(false, dealer.instantBJ());
	}
	
	@Test
	public void testPlayer() {
		
	}
}
