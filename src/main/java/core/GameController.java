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
			consolePlay();
		}
		else if(input.equals("f")){
			filePlay(); //not implemented yet
		}
		else if(input.equals("q")){
			//exit the game
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
	
	public void selectWinner() {
		int a = getScore(dealer);
		int b = getScore(human);
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
		initialize(gameDeck);
		
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
			humanPlay();
			if(human.getHand().calcScoreWithAces() > 21) {
				view.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
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

	public void filePlay() {
		//not yet implemented
	}

	/*
	 * dealer's main interactions are done in Dealer.turnHandler(Deck)
	 * this function decides displays all of dealer's cards and its score
	 * decides based on returned booleans if dealer is bust or standing 
	 */
	public void dealerPlay() {
		// TODO Auto-generated method stub
		dealer.turnHandler(gameDeck);  
		if(((AIDealer) dealer).getBusted()) {
			view.bustedOutput(dealerName, getScore(dealer));
			view.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}
		else if(((AIDealer) dealer).getStand() && !((AIDealer) dealer).getBusted()) {
			view.standOutput(dealerName, getScore(dealer));
			view.displayHand(dealerName, ((AIDealer)dealer).printHand(true));
		}
		view.emptyLine();
	}

	/*
	 * function for human player turn
	 * goes through the game rules and prompts player to hit or stand
	 * ends if player chooses to stand, or bursts
	 */
	public void humanPlay() {
		// TODO Auto-generated method stub
		if(getScore(human) <= 21) {
			view.displayScore(playerName, getScore(human));
			view.outputResponsePrompt();
			input = sc.nextLine();
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
		
	    