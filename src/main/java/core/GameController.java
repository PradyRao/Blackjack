package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class GameController {
	Deck gameDeck = new Deck();
	UI view = new UI();
	
	Participants human;
	Participants dealer;
	
	String input = "";
	String dealerName = "Dealer";
	String playerName = "Player(you)";
	
	String[] validType = {"H", "D", "S", "C"};
	String[] validRank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	
	Scanner reader;
	
	boolean pInstWin = false;
	boolean dInstWin = false;
	
	boolean dealerWin = false; //testing purposes
	
	public void launch(){
		selectState();
	}
	
	public void selectState(){
		UI.initalizeScanner();
		UI.outputGamePrompt();
		input = UI.input();
		
		if(input.equals("c")){
			initialize(gameDeck);
			consolePlay();
		}
		else if(input.equals("f")){
			try {
				filePlay("src/test/resources/testFile4.txt", gameDeck);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //not implemented yet
		}
		else if(input.equals("q")){
			UI.println("thanks for playing!");
		}
		else {
			UI.invalidPrompt();
		}
	}

	public void consolePlay(){
		UI.initalizeScanner();
		initialBJWinner();
		if(dInstWin) {
			UI.initialBJOutput(dealerName);
			UI.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}	
		else if(pInstWin) {
			UI.initialBJOutput(playerName);
			UI.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}
		else {
			newHumanConsolePlay(/*UI.input()*/);
			if(getBestScore(human) > 21) {
				UI.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
				selectWinner();
			}
			else {
				dealerPlay();
				selectWinner();
			}
		}	
		//goes back to input select to choose whether to play again
		selectState();
	}
	
	/*
	 * file play:
	 * reads a file from a directory and puts Card and Commands into its own arrays
	 * then plays the game
	 */
	public void filePlay(String path, Deck deck) throws FileNotFoundException {
		File file = new File(path);
		List<String> fileText = null;
		List<String> commands = new ArrayList<String>();
		if(file.exists()) {
			try {
				reader = new Scanner(new FileReader(path));

				fileText = Arrays.asList(reader.useDelimiter("\\Z").next().split("\\s+"));
				
				
				deck.cardList = new ArrayList<Card>();
				
				for(String element: fileText) {
					if(checkValidCard(element)) {
						deck.cardList.add(new Card(element.substring(0,1).toUpperCase(),
								element.substring(1).toUpperCase(), 
								deck.values.get(element.substring(1).toUpperCase())));
					}
					else {
						commands.add(element);
					}
							
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			System.out.println(fileText);
			System.out.println(commands);
			
			fileInit(deck);
			if(!commands.isEmpty()) {
				newHumanFilePlay(commands);
			}
			if(getBestScore(human) > 21) {
				UI.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
				selectWinner();
			}
			else {
				dealerPlay();
				selectWinner();
			}
		}
	}
	
	/*
	 * dealer's main interactions are done in Dealer.turnHandler(Deck)
	 * this function decides displays all of dealer's cards and its score
	 * decides based on returned booleans if dealer is bust or standing 
	 */
	public void dealerPlay() {
		// TODO Auto-generated method stub
		if(dealer.checkCanSplit()) {
			setAndDisplaySplit(dealerName, dealer);
			for(Hand h: dealer.split) {
				dealer.setCurrHand(h);
				dealer.turnHandler(gameDeck);
				dealerView();
			}		
		}
		else if(getScore(dealer) > 21) {
			UI.bustedOutput(dealerName, getScore(dealer));
			UI.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}	
		else {
			dealer.setCurrHand(dealer.hand);
			dealer.turnHandler(gameDeck);
			dealerView();
		}
		UI.emptyLine();
	}
	
	/*
	 * function for human player turn
	 * goes through the game rules and prompts player to hit or stand
	 * ends if player chooses to stand, or bursts
	 */
	public void newHumanConsolePlay(/*String inputType*/) {
		if(getScore(human) <= 21) {
			UI.displayScore(playerName, getScore(human));
			UI.outputResponsePrompt();
			input = UI.input();//inputType;
		}
		if(input.equals("D")) {
			if(human.checkCanSplit()) {
				human.splitHand(gameDeck);
				System.out.println("your deck has been split");
				System.out.println("new hand 1: ");
				UI.displayHand(playerName, human.split[0].displayHand());
				UI.displayScore(playerName, human.split[0].calcScoreWithAces());
				System.out.println("new hand 2: ");
				UI.displayHand(playerName, human.split[1].displayHand());
				UI.displayScore(playerName, human.split[1].calcScoreWithAces());
				
				
				for(Hand h: human.split) {
					UI.outputResponsePrompt();
					input = UI.input();//inputType;
					doTheHumanConsoleGame(h/*, inputType*/);
				}

			}
			else {
				UI.print("you cannot split your hand");
				UI.emptyLine();
				UI.outputResponsePrompt();
				input = UI.input();//inputType;
				doTheHumanConsoleGame(human.hand/*, inputType*/);
			}
		}
		else if(getScore(human) > 21) {
			UI.bustedOutput(playerName, getScore(human));
		}
		else {
			doTheHumanConsoleGame(human.hand/*, inputType*/);	
		}
	}
	
	private void doTheHumanConsoleGame(Hand hand /*, String inputType*/) {
		// TODO Auto-generated method stub
		human.setCurrHand(hand);
		while(!input.equals("S") && getScore(human) < 21) {
			human.getHand().hitMe(gameDeck);
			UI.displayHand(playerName, human.printHand());
			UI.displayScore(playerName, getScore(human));
			
			if(getScore(human) > 21) {
				break;
			}
			UI.outputResponsePrompt();
			input = UI.input();//inputType;
		}
		if(input.equals("S")) {
			UI.standOutput(playerName, getScore(human));
		}
		else {
			UI.bustedOutput(playerName, getScore(human));
		}
		UI.displayHand(playerName, human.printHand());
		UI.emptyLine();
		
	}
	
	
	/*
	 * copy functions for file play, same as human play
	 * really inefficient due to my code just not working with extra input parameters
	 */
	public void newHumanFilePlay(List<String> commands) {
		int i = 0;
		if(getScore(human) <= 21) {
			UI.displayScore(playerName, getScore(human));
			UI.outputResponsePrompt();
			input = commands.get(i++);//inputType;
		}
		if(input.equals("D")) {
			if(human.checkCanSplit()) {
				human.splitHand(gameDeck);
				System.out.println("your deck has been split");
				System.out.println("new hand 1: ");
				UI.displayHand(playerName, human.split[0].displayHand());
				UI.displayScore(playerName, human.split[0].calcScoreWithAces());
				System.out.println("new hand 2: ");
				UI.displayHand(playerName, human.split[1].displayHand());
				UI.displayScore(playerName, human.split[1].calcScoreWithAces());
				
				
				for(Hand h: human.split) {
					UI.outputResponsePrompt();
					input = commands.get(i++);
					doTheHumanFileGame(h, i, commands);
				}

			}
			else {
				UI.print("you cannot split your hand");
				UI.emptyLine();
				UI.outputResponsePrompt();
				input = commands.get(i++);
				doTheHumanFileGame(human.hand, i, commands);
			}
		}
		else if(getScore(human) > 21) {
			UI.bustedOutput(playerName, getScore(human));
		}
		else {
			doTheHumanFileGame(human.hand, i, commands);	
		}
	}
	
	private void doTheHumanFileGame(Hand hand, int i, List<String> commands) {
		// TODO Auto-generated method stub
		human.setCurrHand(hand);
		while(!input.equals("S") && getScore(human) < 21) {
			human.getHand().hitMe(gameDeck);
			UI.displayHand(playerName, human.printHand());
			UI.displayScore(playerName, getScore(human));
			
			if(getScore(human) > 21) {
				break;
			}
			UI.outputResponsePrompt();
			input = commands.get(i++);//inputType;
		}
		if(input.equals("S")) {
			UI.standOutput(playerName, getScore(human));
		}
		else {
			UI.bustedOutput(playerName, getScore(human));
		}
		UI.displayHand(playerName, human.printHand());
		UI.emptyLine();
		
	}
	
	
	
	/*
	 * below are helper functions to make play work
	 * 
	 * 
	 * 
	 */
	public void dealerView() {
		if(getScore(dealer) > 21) {
			UI.bustedOutput(dealerName, getScore(dealer));
			UI.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}
		else {
			UI.standOutput(dealerName, getScore(dealer));
			UI.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}
	}
	
	public void fileInit(Deck deck){
		human = new HumanPlayer();
		dealer = new AIDealer();
		for(int i = 0; i < 2; i++) {
			human.getHand().hitMe(deck);
		}
		for(int i = 0; i < 2; i++) {
			dealer.getHand().hitMe(deck);
		}
	}
	
	public void initialize(Deck deck) {
		human = new HumanPlayer(deck);
	    dealer = new AIDealer(deck);
	
	    UI.displayHand(playerName, human.printHand());
	    UI.displayHand(dealerName, ((AIDealer)dealer).printHand(false));
	    UI.emptyLine();
	}
	
	
	public int getScore(Participants type) {
		return type.getHand().calcScoreWithAces();
	}

	public void initialBJWinner() {
		if(human.instantBJ() && dealer.instantBJ()) {
			//dealer wins here, not implemented
			dInstWin = true;
			dealerWin = true;
			pInstWin = false;
		}
		else if(human.instantBJ() && !dealer.instantBJ()) {
			//player wins here, not implemented
			pInstWin = true;
			dInstWin = false;
		}
		else if(!human.instantBJ() && dealer.instantBJ()) {
			//dealer wins here, not implemented
			dInstWin = true;
			dealerWin = true;
			pInstWin = false;
		}
		else {
			dInstWin = false;
			pInstWin = false;
		}
	}
	
	
	public int getBestScore(Participants player) {
		if(player.hasSplit) {
			int a = player.split[0].calcScoreWithAces();
			int b = player.split[1].calcScoreWithAces();
			if(a == b && a <= 21) {
				return a;
			}
			else if(a > b && a <= 21) {
				return a;
			}
			else if(a < b && b <= 21) {
				return b;
			}
			else if(a >= 21 && b <= 21) {
				return b;
			}
			else if(a <= 21 && b >= 21) {
				return a;
			}
			return Math.max(a, b);	
		}
		return getScore(player);	
	}
	
	public void selectWinner() {
		int a = getBestScore(dealer);
		int b = getBestScore(human);
		if(b > 21) {
			UI.outputDealerWin();
			dealerWin = true;
		}
		else if(a > 21 && b <= 21) {
			UI.outputPlayerWin();
		}
		else if(a <= 21 && b > 21) {
			dealerWin = true;
			UI.outputDealerWin();
		}
		else if(a >= b && a <= 21) {
			dealerWin = true;
			UI.outputDealerWin();
		}
		else if(b > a && b <= 21) {
			UI.outputPlayerWin();
		}
		UI.emptyLine();
	}
	

	
	public void setAndDisplaySplit(String name, Participants player) {
		player.splitHand(gameDeck);
		
		UI.print(name + " deck has been split");
		System.out.println("new hand 1: ");
		UI.displayHand(name, player.split[0].displayHand());
		UI.displayScore(name, player.split[0].calcScoreWithAces());
		UI.print("new hand 2: ");
		UI.displayHand(name, player.split[1].displayHand());
		UI.displayScore(name, player.split[1].calcScoreWithAces());
	}
	
	public boolean checkValidCard(String element) {
		for(String t: validType) {
			for(String r: validRank) {
				if(element.equals(t + r)) {
					return true;
				}
			}
		}
		return false;
	}

}
		
	    