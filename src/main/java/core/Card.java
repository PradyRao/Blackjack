package core;

public class Card {
	String cardType;
	String cardKey;
	int cardValue;
	
	public Card(String type, String key, int value) {
		cardType = type;
		cardKey = key;
		cardValue = value;
	}
	
	public String getType() {
		return cardType;
	}
	public String getKey() {
		return cardKey;
	}
	public int getValue() {
		return cardValue;
	}
	
	public void setType(String type) {
		cardType = type;
	}
	public void setKey(String key) {
		cardKey = key;
	}
	public void setValue(int value) {
		cardValue = value;
	}
	
	@Override
	public String toString() {
		return cardType + cardKey;
	}
}
