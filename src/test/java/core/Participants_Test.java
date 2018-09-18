package core;

import org.junit.Test;
import junit.framework.TestCase;

public class Participants_Test extends TestCase{
	@Test
	public void testParticipantsInit() {
		Deck deck = new Deck();
		Participants player = new HumanPlayer(deck);
		Participants dealer = new AIDealer(deck);
		
		
		/*for(int i = 0; i < 2; i++) {
			player.getHand().addCard(deck.drawCard());
			dealer.getHand().addCard(deck.drawCard());
		}*/
		
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
		
		player.getHand().addCard(new Card("D", "10", 10));
		dealer.getHand().addCard(new Card("C", "5", 5));
		
		assertEquals(true, player.checkSplit());
		assertEquals(true, dealer.checkSplit());
		
		player.splitHand(deck);
		dealer.splitHand(deck);
		
		assertNotSame(player.getHand(), player.split1);
		assertEquals(player.getHand(), player.split2);
		
		assertNotSame(dealer.getHand(), dealer.split1);
		assertNotSame(dealer.getHand(), dealer.split2);
		
	}
}
