package core;

import java.util.Scanner;

public class GameController {
	Scanner sc = new Scanner(System.in);
	Participants human;
	Participants dealer;
	Deck gameDeck = new Deck();
	InputType input = new InputType("idle");
	
	public void launch(){
		selectState();
	}
	
	public void selectState(){
		while(input.getType().equals("idle")){
			System.out.println("choose play type - c for console and f for file. q to quit: ");
			input.setType(sc.nextLine());
		}
		if(input.getType().equals("c")){
			consolePlay();
		}
		else if(input.getType().equals("f")){
			filePlay(); //not implemented yet
		}
		else if(input.getType().equals("q")){
			//exit the game
		}
		else {
			//invalid input
		}
	
	}

	
	private boolean instantWinBJ() {
		if(human.instantBJ() && dealer.instantBJ()) {
			//dealer wins here, not implemented
			return true;
		}
		if(human.instantBJ() && !dealer.instantBJ()) {
			//player wins here, not implemented
			return true;
		}
		if(!human.instantBJ() && dealer.instantBJ()) {
			//dealer wins here, not implemented
			return true;
		}
		return false;
	}
	
	private void selectWinner() {

	}
	
	public void initialize(Deck deck) {
		human = new HumanPlayer(deck);
	    dealer = new AIDealer(deck);
	
	    System.out.println(human.printHand());
	    System.out.println(((AIDealer)dealer).printHand(false));
	}
	

	public void consolePlay(){
		initialize(gameDeck);
		
		if(instantWinBJ()) {
			
		}
		
		human.turnHandler(gameDeck);
		dealer.turnHandler(gameDeck);     
		selectWinner();
	}

	public void filePlay() {
		
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
		
	    