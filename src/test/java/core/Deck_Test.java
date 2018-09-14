package core;
import org.junit.Test;
import junit.framework.TestCase;
public class Deck_Test extends TestCase{
	@Test
	public void testDeck() {
		Deck deck = new Deck();
		Deck deck2 = new Deck();
		int a = deck.getSize();
		
		assertEquals(52, deck.getSize());
		assertEquals(52, deck2.getSize());
		//both decks are populated the same way, thus checks whether shuffleDeck() reordered the arraylists 
		assertNotSame(deck.getCardList(), deck2.getCardList());
		
		for(int i = 0; i < 5; i++){
			deck.drawCard();
			a--;
		}
		
		assertEquals(a, deck.getSize());
	}
}
