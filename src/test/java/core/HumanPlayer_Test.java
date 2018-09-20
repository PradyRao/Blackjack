package core;

import javax.security.auth.login.AccountException;

import org.junit.Test;
import junit.framework.TestCase;

public class HumanPlayer_Test extends TestCase{
	@Test
	public void testVisible() {
		Deck deck = new Deck();
		Participants human = new HumanPlayer(deck);
		
		human.printHand();
	}

	
	
}