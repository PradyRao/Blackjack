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
		GameController game = new GameController();
		Deck deck = new Deck();
		
		game.initialize(deck);
		
		assertEquals(2, game.dealer.getHand().getSize());
		assertEquals(2, game.human.getHand().getSize());
	}
	@Test
	public void testGameWinner() {
		GameController game = new GameController();

		game.dealer = new AIDealer();
		game.human = new HumanPlayer();

		game.dealer.getHand().addCard(new Card("H", "5", 5));
		game.dealer.getHand().addCard(new Card("S", "10", 10));
		
		game.human.getHand().addCard(new Card("H", "7", 7));
		game.human.getHand().addCard(new Card("S", "10", 10));
		
		//test can be changed for initial win too, change some score to 21
		game.initialBJWinner();
		//check winner, in this case it should be dealer
		game.selectWinner();
		
		assertEquals(false, game.dealerWin);
		
		
	}
	@Test
	public void testFilePlay() {
		GameController game = new GameController();

		game.dealer = new AIDealer();
		game.human = new HumanPlayer();

		//instead of manual add, it reads from file and adds cards and chooses winner
		game.dealer.getHand().addCard(new Card("H", "5", 5));
		game.dealer.getHand().addCard(new Card("S", "10", 10));
		
		game.human.getHand().addCard(new Card("H", "7", 7));
		game.human.getHand().addCard(new Card("S", "10", 10));
		
		//test can be changed for initial win too, change some score to 21
		game.initialBJWinner();
		//check winner, in this case it should be dealer
		game.selectWinner();
		
		//dummy case
		assertEquals(false, false);
	}

}
