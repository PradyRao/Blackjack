package core;

import org.junit.Test;
import junit.framework.TestCase;

public class Participants_Test extends TestCase{
	@Test
	public void testParticipantsInit() {
		Deck deck = new Deck();
		Participants player = new HumanPlayer(deck);
		Participants dealer = new AIDealer(deck);
		
		assertEquals(2, player.getHand().getSize());
		assertEquals(2, dealer.getHand().getSize());
		
		assertNotSame(player.getHand().getCards(), dealer.getHand().getCards());
	}
	
	@Test
	public void testBlackJack() {
		Participants player = new HumanPlayer();
		Participants dealer = new AIDealer();
		
		
		
		player.getHand().addCard(new Card("H", "K", 10));
		dealer.getHand().addCard(new Card("S", "7", 7));
		
		player.getHand().addCard(new Card("D", "A", 11));
		dealer.getHand().addCard(new Card("C", "8", 8));
		
		assertEquals(true, player.instantBJ());
		assertEquals(false, dealer.instantBJ());
	}
	@Test
	public void testSplitting() {
		Deck deck = new Deck();
		Participants player = new HumanPlayer();
		Participants dealer = new AIDealer();
		
		player.getHand().addCard(new Card("H", "K", 10));
		dealer.getHand().addCard(new Card("S", "5", 5));
		
		player.getHand().addCard(new Card("D", "K", 10));
		dealer.getHand().addCard(new Card("C", "5", 5));
		
		assertEquals(true, player.checkCanSplit());
		assertEquals(true, dealer.checkCanSplit());
		
		player.splitHand(deck);
		dealer.splitHand(deck);
		
		assertNotSame(player.getHand(), player.split[0]);
		assertNotSame(player.getHand(), player.split[1]);
		
		assertNotSame(dealer.getHand(), dealer.split[0]);
		assertNotSame(dealer.getHand(), dealer.split[1]);
		
	}
}
