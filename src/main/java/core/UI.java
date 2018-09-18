package core;

public class UI {
	
	public void outputGamePrompt() {
		System.out.println("choose play type - c for console and f for file. q to quit: ");
	}
	public void invalidPrompt() {
		System.out.println("Invalid Input!");
	}
	public void outputDealerWin() {
		System.out.println("Better luck next time! The dealer has won the game");
	}
	public void outputPlayerWin() {
		System.out.println("Congrats! You have won the game!");
	}
	public void emptyLine() {
		System.out.println();
	}
	public void initialBJOutput(String name) {
		System.out.println("result of checking initial blackjack winner: " + name);
	}
	public void displayHand(String name, String cards) {
		System.out.println( name + "'s Hand: " + cards);
	}
	public void bustedOutput(String name, int score) {
		System.out.println(name + " has busted, score: " + score);
	}
	public void standOutput(String name, int score) {
		System.out.println(name + " has chosen to stand, score: " + score);
	}
	public void displayScore(String name, int score) {
		System.out.println(name + "'s score is: " + score);
	}
	public void outputResponsePrompt() {
		System.out.println("would you like to hit (H) or stand (S): ");
	}
	
}
