package core;
import java.util.Scanner;
import org.junit.Test;
import junit.framework.TestCase;
public class GameController_Test extends TestCase {
	UI View = new UI();
	Scanner sc;
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
		
		View.displayHand("human", game.human.printHand());
		View.displayHand("dealer", game.dealer.printHand());
		
		//test can be changed for initial win too, change some score to 21
		game.initialBJWinner();
		//check winner, in this case it should be dealer
		game.selectWinner();
		
		assertEquals(false, game.dealerWin); //this does work, but needs to be changed to account for bestScore for player
		
		
	}
	@Test
	public void testFilePlay() {
		
	}
	
	/*
	 * please choose "n" on the last option when testing this method, or else scanner closes and fails the next method
	 * when running this test, press D in the first option to split (doesnt account for invalid inputs)
	 */
	@Test
	public void testSplittingPlayerGame() {
		sc = new Scanner(System.in);
		GameController game = new GameController();
		System.out.println("Player Split Test starts here");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "K", 10));
		game.dealer.getHand().addCard(new Card("S", "7", 7));
		
		game.human.getHand().addCard(new Card("D", "K", 10));
		game.dealer.getHand().addCard(new Card("C", "5", 5));
		
		View.displayHand("human", game.human.printHand());
		View.displayHand("dealer", game.dealer.printHand());
		
		game.consolePlay();
	}
	
	/*
	 * please choose "n" on the last option when testing this method, or else scanner closes and fails the next method
	 * when running this test, press S in the first option Stand (doesnt account for invalid inputs)
	 */
	@Test
	public void testSplittingDealerGame() {
		sc = new Scanner(System.in);
		GameController game = new GameController();
		System.out.println("Dealer Split Test starts here");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "3", 3));
		game.dealer.getHand().addCard(new Card("S", "5", 5));
		
		game.human.getHand().addCard(new Card("D", "5", 5));
		game.dealer.getHand().addCard(new Card("C", "5", 5));
		
		View.displayHand("human", game.human.printHand());
		View.displayHand("dealer", game.dealer.printHand());
		
		game.consolePlay();
	}
	
	@Test
	public void testBestScore() {
		GameController game = new GameController();
		
		game.human = new HumanPlayer();
		game.human.hasSplit = true;
		//game.human2 = new HumanPlayer();
		game.human.split[0] = new Hand();
		game.human.split[1] = new Hand();
		
		
		game.human.split[0].addCard(new Card("H", "K", 10));
		game.human.split[0].addCard(new Card("S", "7", 7));
		
		game.human.split[1].addCard(new Card("D", "K", 10));
		game.human.split[1].addCard(new Card("C", "5", 5));

		
		
		System.out.println(game.getBestScore(game.human));
		
		assertEquals(17, game.getBestScore(game.human));
		
	}
	
	/*
	 * please choose "n" on the last option when testing this method, or else scanner closes and fails the next method
	 * when running this test, press s in the first option to Stand (doesnt account for invalid inputs)
	 */
	@Test
	public void testDealerBust() {
		sc = new Scanner(System.in);
		GameController game = new GameController();
		System.out.println("Dealer Bust Test starts here");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "3", 3));
		game.dealer.getHand().addCard(new Card("S", "10", 10));
		
		game.human.getHand().addCard(new Card("D", "5", 5));
		game.dealer.getHand().addCard(new Card("C", "5", 5));
		game.dealer.getHand().addCard(new Card("C", "9", 9));
		
		View.displayHand("human", game.human.printHand());
		View.displayHand("dealer", game.dealer.printHand());
		
		game.consolePlay();
	}
	
	/*
	 * please choose "n" on the last option when testing this method, or else scanner closes and fails the next method
	 * automatically busts player, causing dealer to win
	 */
	@Test
	public void testPlayerbust() {
		sc = new Scanner(System.in);
		GameController game = new GameController();
		System.out.println("Player Bust Test starts here");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "10", 10));
		game.dealer.getHand().addCard(new Card("S", "10", 10));
		
		game.human.getHand().addCard(new Card("C", "9", 9));
		game.human.getHand().addCard(new Card("D", "5", 5));
		game.dealer.getHand().addCard(new Card("C", "9", 9));

		
		View.displayHand("human", game.human.printHand());
		View.displayHand("dealer", game.dealer.printHand());
		
		game.consolePlay();
	}

}
