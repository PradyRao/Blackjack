package core;

import org.junit.Test;
import junit.framework.TestCase;

public class Card_Test extends TestCase{
	@Test
	public void testCard() {
		//card with type, value type, and value
		Card card = new Card("S", "5", 5);
		
		assertEqual("S", card.getType());
		assertEqual("5", card.getKey());
		assertEqual(5, card.getValue());
		
		card = new Card("C", "K", 10);
		assertEqual("C", card.getType());
		assertEqual("K", card.getKey());
		assertEqual(10, card.getValue());
	}
}
