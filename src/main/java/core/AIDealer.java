package core;

public class AIDealer extends Participants {

	public AIDealer(Deck deck) {
		super(deck);
		// TODO Auto-generated constructor stub
	}

	public String printHand(boolean visible) {
		// TODO Auto-generated method stub
		if(visible == false) {
			return hand.getCards().get(0).toString();
		}
		return super.printHand();	
	}

	public boolean dealerHitHandler() {
		
	}
	
	@Override
	public void turnHandler(Deck deck) {
		// TODO Auto-generated method stub
		
	}
	
}
