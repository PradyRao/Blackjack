package core;

public class HumanPlayer extends Participants {

	public HumanPlayer(Deck deck) {
		super(deck);
		// TODO Auto-generated constructor stub
	}

	public HumanPlayer() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void turnHandler(Deck deck) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkCanSplit() {
		// TODO Auto-generated method stub
		if(this.getHand().getCards().get(0).getKey().equals
			(this.getHand().getCards().get(1).getKey())) {
			return true;	
		}
		return false;
	}
}
