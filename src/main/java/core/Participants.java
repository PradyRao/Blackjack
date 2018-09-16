package core;

public abstract class Participants {
	Hand hand;
	
	public Participants(Deck deck) {
		hand = new Hand();
		for(int i = 0; i < 2; i++) {
			hand.addCard(deck.drawCard());
		}	
	}
	
	public Hand getHand() {
		return this.hand;
	}
	
	public boolean instantWin() {
		if(this.hand.calcScoreWithAces() == 21) {
			return true;
		}
		return false;
	}
	
	public abstract String printHand();
	public abstract void turnHandler(Deck deck);
}
