package core;
import org.junit.Test;
import junit.framework.TestCase;
public class Deck_Test extends TestCase{
	@Test
	public void testDeck() {
		Deck deck = new Deck();
		Deck deck2 = new Deck();
		
		assertEquals(52, deck.getSize());
		assertEquals(52, deck2.getSize());
		assertNotSame(deck.cards, deck2.cards);
	}
}
