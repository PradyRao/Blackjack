package core;

import java.util.ArrayList;

public class Hand {
	ArrayList<Card> cardList = new ArrayList<Card>();
	private boolean isAce = false;
	
	public int getSize() {
		return cardList.size();
	}
	
	public boolean getIsAce() {
		return isAce;
	}
	
	public void setIsAce(boolean ace) {
		isAce = ace;
	}
	
	public ArrayList<Card> getCards() {
		return cardList;
	}
	
	public void addCard(Card card) {
		cardList.add(card);
	}
	
	public String displayHand() {
		String a = "";
		for(Card s: this.cardList) {
			a += s.toString() + " ";
		}
		return a;
	}
	
	/*
	 * calculates current total score by
	 * first calculating sum without any changes
	 * then keep an index of each time an Ace card appears 
	 * then as long as the score is above 21 - for each index, reduce the score by 10 (11-10 = 1) 
	 * returns final score
	 */
	public int calcScoreWithAces() {
		int sum = 0;
		int count = 0;
		for(int i = 0; i < this.getSize(); i++) {
			sum += this.getCards().get(i).getValue();
			if(this.getCards().get(i).getKey().equals("A")) {
				this.setIsAce(true); //says there is an Ace card worth 11
				count++;
			}	
		}
		while(sum > 21 && count > 0) {
			sum -= 10;
			count--;
		}
		if(count == 0) {
			this.setIsAce(false); //to tell dealer that there is no Ace worth 11 in his hand
		}
		return sum;
	}
	
	public Card hitMe(Deck deck) {
		Card draw = deck.drawCard();
		this.cardList.add(draw);
		return draw;
	}
}
