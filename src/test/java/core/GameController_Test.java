package core;
import org.junit.Test;
import junit.framework.TestCase;
public class GameController_Test extends TestCase {
	/*
	 * most of these tests should've already been tested on their respective model classes
	 */
	@Test
	public void testInputTypes() {
		GameController game = new GameController();
		
		game.setInputType("c");
		assertNotSame("idle", game.getInputType());
	}
	@Test
	public void testInitialize() {
		
	}
	@Test
	public void testChooseWinner() {
		Deck deck = new Deck();
		Participants human = new HumanPlayer();
	    Participants dealer = new AIDealer();
	    
	    dealer.getHand().addCard(new Card("H", "5", 5));
		dealer.getHand().addCard(new Card("S", "10", 10));
		assertEquals(true, true);
	
		
		human.getHand().addCard(new Card("H", "7", 7));
		human.getHand().addCard(new Card("S", "10", 10));
		assertEquals(false, false);
		
	}
	@Test
	public void testInstantWinBJ() {
		
	}
	@Test
	public void testConsolePlay() {
		
	}
	@Test
	public void testFilePlay() {
		
	}

}
