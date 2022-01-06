package com.argenti.poker.enums;

public enum Ranking {
    HIGH_CARD("High Card"),
    PAIR("One Pair"),
    TWO_PAIR("Two Pair"),
    TRIPS("Three of Kind"),
    STRAIGHT("Straight"),
    FLUSH("Flush"),
    FULL_HOUSE("Full House"),
    QUADS("Four of Kind"),
    STRAIGHT_FLUSH("Straight Flush"),
    ROYAL_FLUSH("Royal Flush");
	
	private String value;

	public String getValue() {
		return value;
	}

	private Ranking(String value) {
		this.value = value;
	}

	
	
	
}
