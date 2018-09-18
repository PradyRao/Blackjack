package core;

import java.util.Scanner;

public class GameController {
	Scanner sc = new Scanner(System.in);
	Participants human;
	Participants dealer;
	Deck gameDeck = new Deck();
	String input = "idle";
	
	boolean pInstWin = false;
	boolean dInstWin = false;
	
	boolean dealerWin = false; //testing purposes
	
	public void launch(){
		selectState();
	}
	
	public void selectState(){
		while(input.equals("idle")){
			System.out.println("choose play type - c for console and f for file. q to quit: ");
			input = sc.nextLine();
		}
		if(input.equals("c")){
			consolePlay();
		}
		else if(input.equals("f")){
			filePlay(); //not implemented yet
		}
		else if(input.equals("q")){
			//exit the game
		}
		else {
			//invalid input
		}
	
	}
	
	public void initialize(Deck deck) {
		human = new HumanPlayer(deck);
	    dealer = new AIDealer(deck);
	
	    System.out.println("Player Hand: " + ((HumanPlayer) human).printHand());
	    System.out.println("Dealer Hand: " + ((AIDealer)dealer).printHand(false));
	    System.out.println();
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
		int a = dealer.getHand().calcScoreWithAces();
		int b = human.getHand().calcScoreWithAces();
		if(b > 21) {
			System.out.println("Better luck next time! The dealer has won the game");
			dealerWin = true;
		}
		else if(a > 21 && b <= 21) {
			System.out.println("Congrats! You have won the game!");
		}
		else if(a <= 21 && b > 21) {
			dealerWin = true;
			System.out.println("Better luck next time! The dealer has won the game");
		}
		else if(a >= b && a <= 21) {
			dealerWin = true;
			System.out.println("Better luck next time! The dealer has won the game");
		}
		else if(b > a && b <= 21) {
			System.out.println("Congrats! You have won the game!");
		}
		System.out.println();
	}

	public void consolePlay(){
		initialize(gameDeck);
		
		initialBJWinner();
		if(dInstWin) {
			System.out.println("result of checking initial blackjack winner: Dealer");
			System.out.println("Dealer's cards: " + ((AIDealer)dealer).printHand(true));
		}	
		else if(pInstWin) {
			System.out.println("result of checking initial blackjack winner: Player(you)");
			System.out.println("Dealer's cards: " + ((AIDealer)dealer).printHand(true));
		}
		else {
			humanPlay();
			if(human.getHand().calcScoreWithAces() > 21) {
				System.out.println("Dealer's cards: " + ((AIDealer)dealer).printHand(true));
				selectWinner();
			}
			else {
				dealerPlay();
				selectWinner();
			}
		}	
		//sets input to idle again so user can choose what to do next
		input = "idle";
		//goes back to input select to choose what to do next
		selectState();
	}

	public void filePlay() {
		
	}

	/*
	 * dealer's main interactions are done in Dealer.turnHandler(Deck)
	 * this function decides displays all of dealer's cards and its score
	 * decides based on returned booleans if dealer is bust or standing 
	 */
	public void dealerPlay() {
		// TODO Auto-generated method stub
		int a = dealer.getHand().calcScoreWithAces();
		dealer.turnHandler(gameDeck);  
		if(((AIDealer) dealer).getBusted()) {
			a = dealer.getHand().calcScoreWithAces();
			System.out.println("Dealer has busted, score: " + a);
			System.out.println("dealer's cards: " + ((AIDealer)dealer).printHand(true));
		}
		else if(((AIDealer) dealer).getStand() && !((AIDealer) dealer).getBusted()) {
			a = dealer.getHand().calcScoreWithAces();
			System.out.println("Dealer has chosen to stand, dealer's cards: " + ((AIDealer)dealer).printHand(true));
			System.out.println("Dealer score: " + a);
		}
		System.out.println();
	}

	/*
	 * function for human player turn
	 * goes through the game rules and prompts player to hit or stand
	 * ends if player chooses to stand, or bursts
	 */
	public void humanPlay() {
		// TODO Auto-generated method stub
		int a = human.getHand().calcScoreWithAces();
		//getting the most recent card drawn from player hand and making it a string 
		String hitCard = "";
		if(a <= 21) {
			System.out.println("your score is: " + a);
			System.out.println("would you like to hit (H) or stand (S): ");
			input = sc.nextLine();
			while(!input.equals("S") && a < 21) {
				human.getHand().hitMe(gameDeck);
				a = human.getHand().calcScoreWithAces();
				hitCard = human.printHand();
				System.out.println("your cards are: " + hitCard);
				System.out.println("your score is: " + a);
				if(a > 21) {
					break;
				}
				System.out.println("would you like to hit (H) or stand (S): ");
				input = sc.nextLine();
			}
		}
		if(input.equals("S")) {
			System.out.println("you have chosen to stand with score: " + a);
		}
		else {
			System.out.println("you have busted");
		}
		System.out.println();	
	}
	
	/* 
	 * methods below are purely for testing purposes
	 */
	public void setInputType(String string) {
		// TODO Auto-generated method stub
		
	}

	public Object getInputType() {
		// TODO Auto-generated method stub
		return null;
	}
}
		
	    