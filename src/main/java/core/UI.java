package core;

import java.util.Scanner;

public class UI {
	static Scanner sc;
	
	public static void outputGamePrompt() {
		println("choose play type - c for console and f for file. q to quit: ");
	}
	public static void invalidPrompt() {
		println("Invalid Input!");
	}
	public static void outputDealerWin() {
		println("Better luck next time! The dealer has won the game");
	}
	public static void outputPlayerWin() {
		println("Congrats! You have won the game!");
	}
	public static void emptyLine() {
		println();
	}
	public static void initialBJOutput(String name) {
		println("result of checking initial blackjack winner: " + name);
	}
	public static void displayHand(String name, String cards) {
		println(name + "'s Hand: " + cards);
	}
	public static void bustedOutput(String name, int score) {
		println(name + " has busted, score: " + score);
	}
	public static void standOutput(String name, int score) {
		println(name + " has chosen to stand, score: " + score);
	}
	public static void displayScore(String name, int score) {
		println(name + "'s score is: " + score);
	}
	public static void outputResponsePrompt() {
		System.out.println("would you like to hit (H), stand (S), split(D): ");
	}
	
	//get help from a fellow classmate for the functions below
	public static void initalizeScanner() {
		sc = new Scanner(System.in);
	}
	
	public static void closeScanner() {
		sc.close();
	}
	
	public static String input() {
		String input = "";
		input = sc.nextLine();
		return input;
	}
	
	public static void print(String... inputs) {
		for(String in: inputs) {
			System.out.print(in + " ");
		}
	}
	
	public static void println(String... inputs) {
		print(inputs);
		print(System.lineSeparator());
	}
	
}
