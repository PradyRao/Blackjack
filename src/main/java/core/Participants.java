package core;


public abstract class Participants {
	Hand hand;
	Hand split1 = new Hand();
	Hand split2 = new Hand();
	boolean split = false;
	boolean busted = false;
	boolean stand = false;
	
	public Participants(Deck deck) {
		hand = new Hand();
		for(int i = 0; i < 2; i++) {
			hand.addCard(deck.drawCard());
		}	
	}
	
	//constructor for testing purposes
	public Participants(){
	  hand = new Hand();
	}

	public Hand getHand() {
		return hand;
	}
	
	public boolean instantBJ() {
		if(this.hand.calcScoreWithAces() == 21) {
			return true;
		}
		return false;
	}
	
	public String printHand() {
		// TODO Auto-generated method stub
		return this.getHand().displayHand();
	}
	
	public abstract void turnHandler(Deck deck);
	
	//for the future
	public void splitHand(Deck deck) {
		split1.addCard(hand.getCards().get(0));
		split1.addCard(deck.drawCard());	
		
		split2.addCard(hand.getCards().get(1));
		split2.addCard(deck.drawCard());
		
		split = true;		
	}
}
