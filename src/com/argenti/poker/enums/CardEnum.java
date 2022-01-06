package com.argenti.poker.enums;

import java.util.HashMap;
import java.util.Map;

public enum CardEnum {

//	TWO(2),
//	THREE(3),
//	FOUR(4),
//	FIVE(5),
//	SIX(6),
//	SEVEN(7),
//	EIGHT(8),
//	NINE(9),
//	T(10),
//	J(11),
//	Q(12),
//	K(13),
//	A(14);
	
	TWO(2,"Two"),
	THREE(3,"Three"),
	FOUR(4,"Four"),
	FIVE(5,"Five"),
	SIX(6,"Six"),
	SEVEN(7,"Seven"),
	EIGHT(8,"Eight"),
	NINE(9,"Nine"),
	T(10,"Ten"),
	J(11,"Jack"),
	Q(12,"Queen"),
	K(13,"King"),
	A(14,"Ace");

	private int cardValue;
	
	private String cardName;
	
	private CardEnum(int cardValue, String cardName) {
		this.cardValue = cardValue;
		this.cardName = cardName;
	}

	public int getCardValue() {
		return cardValue;
	}

	public String getCardName() {
		return cardName;
	}
	
	private static Map<Object, Object> map = new HashMap<>();
	
	private static Map<Object, String> cardMap = new HashMap<>();

	static {
		for (CardEnum card : CardEnum.values()) {
			
			String key = card.name().replace("TWO", "2").replace("THREE", "3").replace("FOUR", "4")
					.replace("FIVE", "5").replace("SIX", "6").replace("SEVEN", "7")
					.replace("EIGHT", "8").replace("NINE", "9");
			map.put(key, card.cardValue);
			cardMap.put(key, card.cardName);

		}
	}


	public static int cardValueOf(String cardValue) {
		return (int) map.get(cardValue);
	}
	
	public static String cardNameOf(String cardValue) {
		return cardMap.get(cardValue);
	}
}
