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
	
	@Test
	public void testJQK() {
		Deck deck = new Deck();
		int a=0,b=0,c=0;
		for(int i = 0; i < deck.getSize(); i++) {
			if(deck.getCardList().get(i).getKey().equals("J")){
				a = deck.getCardList().get(i).getValue();
			}
			if(deck.getCardList().get(i).getKey().equals("Q")){
				b = deck.getCardList().get(i).getValue();
			}
			if(deck.getCardList().get(i).getKey().equals("K")){
				c = deck.getCardList().get(i).getValue();
			}		
		}
		
		assertEquals(10, a);
		assertEquals(10, b);
		assertEquals(10, c);
	}
}
