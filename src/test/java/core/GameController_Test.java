package core;
import org.junit.Test;
import junit.framework.TestCase;
public class GameController_Test extends TestCase {
	static UI view = new UI();
	
	@Test
	public void testInitialize() {
		GameController game = new GameController();
		Deck deck = new Deck();
		
		game.initialize(deck);
		
		assertEquals(2, game.dealer.getHand().getSize());
		assertEquals(2, game.human.getHand().getSize());
	}
	
	/*
	 * press H at least twice here before Standing
	 * then once stood, player's hand is displayed
	 */
	@Test
	public void testPlayerHit() {
		Deck deck = new Deck();
		GameController game = new GameController();
		game.dealer = new AIDealer(deck);
		game.human = new HumanPlayer();
		
		System.out.println("Player Hit Test starts here: please hit(H) at least twice");
		
		game.human.getHand().addCard(new Card("S", "3", 3));
		game.human.getHand().addCard(new Card("S", "4", 4));

		
		game.consolePlay();

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
		
		UI.displayHand("human", game.human.printHand());
		UI.displayHand("dealer", game.dealer.printHand());
		
		//test can be changed for initial win too, change some score to 21
		game.initialBJWinner();
		//check winner, in this case it should be human
		game.selectWinner();
		
		assertEquals(false, game.dealerWin);	
	}
	/*
	 * please choose "q" on the last option when testing this method, or else game will loop
	 * when running this test, press D in the first option to split (doesnt account for invalid inputs)
	 */
	@Test
	public void testSplittingPlayerGame() {
		GameController game = new GameController();
		System.out.println("Player Split Test starts here: Please Split(D) as your first command, then do as you like");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "K", 10));
		game.dealer.getHand().addCard(new Card("S", "7", 7));
		
		game.human.getHand().addCard(new Card("D", "K", 10));
		game.dealer.getHand().addCard(new Card("C", "5", 5));
		
		UI.displayHand("human", game.human.printHand());
		UI.displayHand("dealer", game.dealer.printHand());
		
		game.consolePlay();
	}
	
	/*
	 * please choose "q" on the last option when testing this method, or else game will loop
	 * when running this test, press S in the first option Stand (doesnt account for invalid inputs)
	 */
	@Test
	public void testSplittingDealerGame() {
		GameController game = new GameController();
		System.out.println("Dealer Split Test starts here: Please Stand(S) for faster results");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "3", 3));
		game.dealer.getHand().addCard(new Card("S", "5", 5));
		
		game.human.getHand().addCard(new Card("D", "5", 5));
		game.dealer.getHand().addCard(new Card("C", "5", 5));
		
		UI.displayHand("human", game.human.printHand());
		UI.displayHand("dealer", game.dealer.printHand());
		
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
	 * just Stand (S) when this runs
	 */
	@Test
	public void testPlayerScoring(){
		GameController game = new GameController();
		System.out.println("testing player scoring starts here: please Stand(S) for accurate results");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "10", 10));
		game.dealer.getHand().addCard(new Card("S", "9", 9));
		
		game.human.getHand().addCard(new Card("D", "10", 10));
		game.dealer.getHand().addCard(new Card("C", "10", 10));
		
		UI.displayHand("human", game.human.printHand());
		UI.displayHand("dealer", game.dealer.printHand());
		
		//testing if scoring is done right for player & human
		assertEquals(19, game.getBestScore(game.dealer));
		assertEquals(20, game.getBestScore(game.human));
		//player will properly win
		game.consolePlay();

	}
	
	/*
	 * please just Stand(S)
	 */
	@Test
	public void testDealerScoring(){
		GameController game = new GameController();
		System.out.println("testing dealer scoring starts here: Please Stand(S) for accurate results");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "5", 5));
		game.dealer.getHand().addCard(new Card("S", "9", 9));
		
		game.human.getHand().addCard(new Card("D", "10", 10));
		game.dealer.getHand().addCard(new Card("C", "10", 10));
		
		UI.displayHand("human", game.human.printHand());
		UI.displayHand("dealer", game.dealer.printHand());
		
		//testing if scoring is done right for player & human
		assertEquals(19, game.getBestScore(game.dealer));
		assertEquals(15, game.getBestScore(game.human));
		//dealer will properly win 
		game.consolePlay();

	}
	/*
	 * please choose "q" on the last option when testing this method, or else game will loop
	 * when running this test, press s in the first option to Stand (doesnt account for invalid inputs)
	 */
	@Test
	public void testDealerBust() {
		GameController game = new GameController();
		System.out.println("Dealer Bust Test starts here: please stand(S) or don't Bust for accurate results");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "3", 3));
		game.dealer.getHand().addCard(new Card("S", "10", 10));
		
		game.human.getHand().addCard(new Card("D", "5", 5));
		game.dealer.getHand().addCard(new Card("C", "5", 5));
		game.dealer.getHand().addCard(new Card("C", "9", 9));
		
		UI.displayHand("human", game.human.printHand());
		UI.displayHand("dealer", game.dealer.printHand());
		
		game.consolePlay();
	}
	
	/*
	 * please choose "q" on the last option when testing this method, or else game will loop
	 * automatically busts player, causing dealer to win
	 */
	@Test
	public void testPlayerbust() {
		GameController game = new GameController();
		System.out.println("Player Bust Test starts here: nothing to do here, player will automatically bust");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "10", 10));
		game.dealer.getHand().addCard(new Card("S", "10", 10));
		
		game.human.getHand().addCard(new Card("C", "9", 9));
		game.human.getHand().addCard(new Card("D", "5", 5));
		game.dealer.getHand().addCard(new Card("C", "9", 9));

		
		UI.displayHand("human", game.human.printHand());
		UI.displayHand("dealer", game.dealer.printHand());
		
		game.consolePlay();
	}

	@Test
	public void testInitialBJPlayer() {
		GameController game = new GameController();

		game.dealer = new AIDealer();
		game.human = new HumanPlayer();

		game.dealer.getHand().addCard(new Card("H", "5", 5));
		game.dealer.getHand().addCard(new Card("S", "10", 10));
		
		game.human.getHand().addCard(new Card("H", "A", 11));
		game.human.getHand().addCard(new Card("S", "10", 10));
		
		UI.displayHand("human", game.human.printHand());
		UI.displayHand("dealer", game.dealer.printHand());
		
		//player has BJ but not dealer
		game.initialBJWinner();
		//check winner, in this case it should be human
		game.selectWinner();
		
		assertEquals(false, game.dealerWin);	
	}
	@Test
	public void testInitialBJDealer() {
		GameController game = new GameController();

		game.dealer = new AIDealer();
		game.human = new HumanPlayer();

		game.dealer.getHand().addCard(new Card("H", "J", 10));
		game.dealer.getHand().addCard(new Card("S", "A", 11));
		
		game.human.getHand().addCard(new Card("H", "A", 11));
		game.human.getHand().addCard(new Card("S", "10", 10));
		
		UI.displayHand("human", game.human.printHand());
		UI.displayHand("dealer", game.dealer.printHand());
		
		//player and dealer both have BJs
		game.initialBJWinner();
		//check winner, in this case it should be dealer
		game.selectWinner();
		
		assertEquals(true, game.dealerWin);	
	}
}
