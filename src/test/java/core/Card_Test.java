package core;

import org.junit.Test;
import junit.framework.TestCase;

public class Card_Test extends TestCase{
	@Test
	public void testCard() {
		//card with type, value type, and value
		Card card = new Card("S", "5", 5);
		
		assertEquals("S", card.getType());
		assertEquals("5", card.getKey());
		assertEquals(5, card.getValue());
		
		card = new Card("C", "K", 10);
		assertEquals("C", card.getType());
		assertEquals("K", card.getKey());
		assertEquals(10, card.getValue());
	}
}
