package com.argenti.poker.enums;

import java.util.HashMap;
import java.util.Map;

public enum Suit {

	D("Diamond"),
	H("Heart"),
	S("Spade"),
	C("Club");

	private String value;

	public String getValue() {
		return value;
	}

	private Suit(String value) {
		this.value = value;
	}

	private static Map<Object, String> map = new HashMap<>();

	static {
		for (Suit card : Suit.values()) {
			map.put(card.name(), card.value);

		}
	}
	
	public static String getValueOf(String cardValue) {
		return  map.get(cardValue);
	}

}
