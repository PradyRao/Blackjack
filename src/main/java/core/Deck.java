package core;

import java.util.*;

public class Deck {
	List<Card> cardList;
	String[] types = {"S", "H", "C", "D"};
	Map<String, Integer> values = new HashMap<String, Integer>();
	
	
	public Deck() {
		cardList = new ArrayList<Card>();
		init();
		populateDeck();
		shuffleDeck();
	}
	
	/*public Deck(String x) {
		cardList = new ArrayList<Card>();
		init();
		populateDeck();
	}*/
	
	private void init() {
		values.put("2", 2);
		values.put("3", 3);
		values.put("4", 4);
		values.put("5", 5);
		values.put("6", 6);
		values.put("7", 7);
		values.put("8", 8);
		values.put("9", 9);
		values.put("10", 10);
		values.put("J", 10);
		values.put("Q", 10);
		values.put("K", 10);
		values.put("A", 11);	
	}
	
	private void populateDeck() {
		for(String type: types) {
			for(String value: values.keySet()) {
				cardList.add(new Card(type, value, values.get(value)));
			}
		}
	}
	
	private void shuffleDeck() {
		Collections.shuffle(cardList);
	}
	
	public Card drawCard() {
		return cardList.remove(0);		
	}
	
	public int getSize() {
		return cardList.size();
	}
	
	public List<Card> getCardList() {
		return cardList;
	}
}
