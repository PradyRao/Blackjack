package core;


public abstract class Participants {
	Hand hand;
	Hand[] split = new Hand[2];
	Hand currHand;
	boolean canSplit = false;
	boolean busted = false;
	boolean stand = false;
	boolean hasSplit = false;
	boolean hand1bust = false;
	
	public Participants(Deck deck) {
		hand = new Hand();
		for(int i = 0; i < 2; i++) {
			hand.addCard(deck.drawCard());
		}	
		currHand = hand;
	}
	
	//constructor for testing purposes
	public Participants(){
	  hand = new Hand();
	  currHand = hand;
	}

	public Hand getHand() {
		return currHand;
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
		split[0] = new Hand();
		split[1] = new Hand();
		
		split[0].addCard(hand.getCards().get(0));
		split[0].addCard(deck.drawCard());	
		
		split[1].addCard(hand.getCards().get(1));
		split[1].addCard(deck.drawCard());
		
		hasSplit = true;
	}
	
	public void setCurrHand(Hand newHand) {
		currHand = newHand;
	}
	public abstract boolean checkCanSplit();
}
