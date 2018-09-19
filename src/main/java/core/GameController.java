package core;

import java.util.Scanner;

public class GameController {
	Participants human;
	Participants dealer;
	Deck gameDeck = new Deck();
	String input = "";
	String dealerName = "Dealer";
	String playerName = "Player(you)";
	UI view = new UI();
	Scanner sc = new Scanner(System.in);
	
	boolean pInstWin = false;
	boolean dInstWin = false;
	
	boolean dealerWin = false; //testing purposes
	
	public void launch(){
		selectState();
	}
	
	public void selectState(){
		view.outputGamePrompt();
		input = sc.nextLine();
		
		if(input.equals("c")){
			initialize(gameDeck);
			consolePlay();
		}
		else if(input.equals("f")){
			//initialize(gameDeck);
			filePlay(); //not implemented yet
		}
		else if(input.equals("q")){
			sc.close();
		}
		else {
			view.invalidPrompt();
		}
	}
	
	public void initialize(Deck deck) {
		human = new HumanPlayer(deck);
	    dealer = new AIDealer(deck);
	
	    view.displayHand(playerName, human.printHand());
	    view.displayHand(dealerName, ((AIDealer)dealer).printHand(false));
	    view.emptyLine();
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
	
	//SO FAR UP TO HERE IS GOOD
	
	public void selectWinner() {
		int a = getBestScore(dealer);
		int b = getBestScore(human);
		if(b > 21) {
			view.outputDealerWin();
			dealerWin = true;
		}
		else if(a > 21 && b <= 21) {
			view.outputPlayerWin();
		}
		else if(a <= 21 && b > 21) {
			dealerWin = true;
			view.outputDealerWin();
		}
		else if(a >= b && a <= 21) {
			dealerWin = true;
			view.outputDealerWin();
		}
		else if(b > a && b <= 21) {
			view.outputPlayerWin();
		}
		view.emptyLine();
	}

	public void consolePlay(){
		initialBJWinner();
		if(dInstWin) {
			view.initialBJOutput(dealerName);
			view.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}	
		else if(pInstWin) {
			view.initialBJOutput(playerName);
			view.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}
		else {
			newHumanPlay();
			if(getBestScore(human) > 21) {
				view.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
				selectWinner();
			}
			else {
				dealerPlay();
				selectWinner();
			}
		}	
		//goes back to input select to choose whether to play again
		System.out.println("do you want to go back to main menu? Yes(y) No(n): ");
		input = sc.nextLine();
		if(input.equals("y")) {
			selectState();
		}
		else {
			System.out.println("thanks for playing!");
		}
	}

	public void filePlay() {
		//not yet implemented
	}
	
	public void setAndDisplaySplit(String name, Participants player) {
		player.splitHand(gameDeck);
		
		System.out.println(name + " deck has been split");
		System.out.println("new hand 1: ");
		view.displayHand(name, player.split[0].displayHand());
		view.displayScore(name, player.split[0].calcScoreWithAces());
		System.out.println("new hand 2: ");
		view.displayHand(name, player.split[1].displayHand());
		view.displayScore(name, player.split[1].calcScoreWithAces());
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
			view.bustedOutput(dealerName, getScore(dealer));
			view.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}	
		else {
			dealer.setCurrHand(dealer.hand);
			dealer.turnHandler(gameDeck);
			dealerView();
		}
		view.emptyLine();
	}
	
	public void dealerView() {
		if(getScore(dealer) > 21) {
			view.bustedOutput(dealerName, getScore(dealer));
			view.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}
		else {
			view.standOutput(dealerName, getScore(dealer));
			view.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}
	}

	/*
	 * function for human player turn
	 * goes through the game rules and prompts player to hit or stand
	 * ends if player chooses to stand, or bursts
	 */
	public void newHumanPlay() {
		if(getScore(human) <= 21) {
			view.displayScore(playerName, getScore(human));
			view.outputResponsePrompt();
			input = sc.nextLine();
		}
		if(input.equals("D")) {
			if(human.checkCanSplit()) {
				human.splitHand(gameDeck);
				System.out.println("your deck has been split");
				System.out.println("new hand 1: ");
				view.displayHand(playerName, human.split[0].displayHand());
				view.displayScore(playerName, human.split[0].calcScoreWithAces());
				System.out.println("new hand 2: ");
				view.displayHand(playerName, human.split[1].displayHand());
				view.displayScore(playerName, human.split[1].calcScoreWithAces());
				
				
				for(Hand h: human.split) {
					view.outputResponsePrompt();
					input = sc.nextLine();
					doTheHumanGame(h);
				}

			}
			else {
				System.out.println("you cannot split your hand");
				view.outputResponsePrompt();
				input = sc.nextLine();
				doTheHumanGame(human.hand);
			}
		}
		else if(getScore(human) > 21) {
			view.bustedOutput(playerName, getScore(human));
		}
		else {
			doTheHumanGame(human.hand);	
		}
	}
	
	private void doTheHumanGame(Hand hand) {
		// TODO Auto-generated method stub
		human.setCurrHand(hand);
		while(!input.equals("S") && getScore(human) < 21) {
			human.getHand().hitMe(gameDeck);
			view.displayHand(playerName, human.printHand());
			view.displayScore(playerName, getScore(human));
			
			if(getScore(human) > 21) {
				break;
			}
			view.outputResponsePrompt();
			input = sc.nextLine();
		}
		if(input.equals("S")) {
			view.standOutput(playerName, getScore(human));
		}
		else {
			view.bustedOutput(playerName, getScore(human));
		}
		view.displayHand(playerName, human.printHand());
		view.emptyLine();
		
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

	/* 
	 * methods below are purely for testing purposes
	 */
	public void setInputType(String string) {
		// TODO Auto-generated method stub
		input = string;
	}

	public Object getInputType() {
		// TODO Auto-generated method stub
		return input;
	}
}
		
	    