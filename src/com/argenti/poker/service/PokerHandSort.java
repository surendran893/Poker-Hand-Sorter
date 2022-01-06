package com.argenti.poker.service;

import static com.argenti.poker.util.ApplicationConstant.HAND_NOT_FOUND;

import com.argenti.poker.enums.Ranking;
import com.argenti.poker.util.ApplicationUtil;

public abstract class PokerHandSort {

	ApplicationUtil util = new ApplicationUtil();

	/**
	 * @param hand  String array as Input
	 * @return Integer based on Enum value.
	 */
	public int isRoyalFlush(String [] hand) {
		char suit = hand[0].charAt(1);
		for(int i=0;i<hand.length;i++) {
			char card = hand[i].charAt(0);

			if(suit != hand[i].charAt(1)){
				return HAND_NOT_FOUND;
			}
			if(util.getCardValueOf(card) != 10 +i){
				return HAND_NOT_FOUND;
			}
		}
		util.logData(Ranking.ROYAL_FLUSH.getValue(), hand);

		return 65;
	}

	/**
	 * @param hand  String array as Input
	 * @return Integer based on Enum value.
	 */
	public int isStraightFlush(String [] hand) {
		int handValue = 0;
		char suit = hand[0].charAt(1);
		int startValue = util.getCardValueOf(hand[0].charAt(0));
		for(int i=0;i<hand.length;i++) {
			char card = hand[i].charAt(0);

			if(suit != hand[i].charAt(1)){
				return HAND_NOT_FOUND;
			}
			if(util.getCardValueOf(card) != startValue +i){
				return HAND_NOT_FOUND;
			}
			handValue = handValue + util.getCardValueOf(card);
		}

		util.logData(Ranking.STRAIGHT_FLUSH.getValue(), hand);


		return handValue;
	}

	/**
	 * @param hand  String array as Input
	 * @return Integer based on Enum value.
	 */
	public int isFourOfAKind(String [] hand) {

		char card = hand[0].charAt(0);
		char suit = hand[0].charAt(1);
		int counter = 1;

		for(int i=1;i<hand.length;i++) {
			char nextCard = hand[i].charAt(0);
			if(nextCard == card){
				counter ++;
				if(counter == 4) {
					int handValue = 4 * util.getCardValueOf(card);

					String cardName = util.getCardNameOf(card);
					String suitName = util.getSuitNameOf(suit);

					util.logData(Ranking.QUADS.getValue() + " " +cardName + " ==> "+suitName, hand);


					return handValue;
				}
			} else {
				card = nextCard;
				counter = 1;
			}
		}
		return HAND_NOT_FOUND;
	}

	/**
	 * @param hand  String array as Input
	 * @return Integer based on Enum value.
	 */
	public int isFullHouse(String [] hand) {

		int handValue = 0;

		if(hand[0].charAt(0) == hand[1].charAt(0) && hand[1].charAt(0) == hand[2].charAt(0) ) {
			if(hand[0].charAt(0) != hand[3].charAt(0) && hand[3].charAt(0) == hand[4].charAt(0)) {
				handValue = 3 * util.getCardValueOf(hand[0].charAt(0));
			}
		}

		if(hand[0].charAt(0) == hand[1].charAt(0) && hand[1].charAt(0) != hand[2].charAt(0) ) {
			if(hand[2].charAt(0) == hand[3].charAt(0) && hand[3].charAt(0) == hand[4].charAt(0)) {
				handValue = 3 * util.getCardValueOf(hand[4].charAt(0));
			}
		}
		if(handValue > 0){
			util.logData(Ranking.FULL_HOUSE.getValue(), hand);

			return handValue;
		}

		return HAND_NOT_FOUND;
	}

	/**
	 * @param hand  String array as Input
	 * @return Integer based on Enum value.
	 */
	public int isStraight(String [] hand) {
		int handValue = 0;

		int startValue = util.getCardValueOf(hand[0].charAt(0));
		for(int i=0;i<hand.length;i++) {
			char card = hand[i].charAt(0);

			if(util.getCardValueOf(card) != startValue +i){
				return HAND_NOT_FOUND;
			}
			handValue = handValue + util.getCardValueOf(card);
		}

		util.logData(Ranking.STRAIGHT.getValue(), hand);

		return handValue;
	}

	/**
	 * @param hand  String array as Input
	 * @return Integer based on Enum value.
	 */
	public int isTwoPairs(String [] hand) {

		int handValue = 0;
		char firstPair = 0;
		char secondPair = 0;

		String cardName = "";
		String suitName = "";

		for(int i=1;i<hand.length;i++) {
			char prevCard = hand[i-1].charAt(0);
			char card = hand[i].charAt(0);
			char suit = hand[i].charAt(1);

			if(prevCard == card){
				if(firstPair == 0){
					firstPair = card;
					handValue = 2 * util.getCardValueOf(card);;
					i++;
				} else if(secondPair == 0 && card != firstPair){
					secondPair = card;
					handValue = handValue + 2 * util.getCardValueOf(card);

					cardName = util.getCardNameOf(card);
					suitName = util.getSuitNameOf(suit);

					break;
				}
			}
		}

		if(firstPair != 0 && secondPair != 0){

			util.logData(Ranking.TWO_PAIR.getValue() + " " +cardName + " ==> "+suitName, hand);

			return handValue;
		}

		return HAND_NOT_FOUND;
	}

	/**
	 * @param hand  String array as Input
	 * @return Integer based on Enum value.
	 */
	public int isFlush(String [] hand) {
		int handValue = 0;
		String cardName = "";
		String suitName = "";

		char suit = hand[0].charAt(1);
		char card = hand[0].charAt(0);

		for(int i=0;i<hand.length;i++) {

			if(suit != hand[i].charAt(1)){
				return HAND_NOT_FOUND;
			}
			handValue += util.getCardValueOf(hand[i].charAt(0));			
		}

		util.logData(Ranking.FLUSH.getValue() + " ==> "+suitName, hand);

		return handValue;
	}

	/**
	 * @param hand  String array as Input
	 * @return Integer based on Enum value.
	 */
	public int isThreeOfAKind(String [] hand) {
		char card = hand[0].charAt(0);
		char suit = hand[0].charAt(1);
		int counter = 1;

		for(int i=1;i<hand.length;i++) {

			char nextCard = hand[i].charAt(0);
			if(nextCard == card){
				counter ++;
				if(counter == 3) {
					int handValue = 3  * util.getCardValueOf(card);

					String cardName = util.getCardNameOf(card);
					String suitName = util.getSuitNameOf(suit);

					util.logData(Ranking.TRIPS.getValue() + " " +cardName + " ==> "+suitName, hand);

					return handValue;
				}
			} else {
				card = nextCard;
				counter = 1;
			}
		}
		return HAND_NOT_FOUND;
	}

	/**
	 * @param hand  String array as Input
	 * @return Integer based on Enum value.
	 */
	public int isOnePair(String [] hand) {

		for(int i=1;i<hand.length;i++) {

			char prevCard = hand[i-1].charAt(0);
			char card = hand[i].charAt(0);
			char suit = hand[i].charAt(1);

			if(prevCard == card){
				int handValue = 2 * util.getCardValueOf(card);

				String cardName = util.getCardNameOf(card);
				String suitName = util.getSuitNameOf(suit);				

				util.logData(Ranking.PAIR.getValue() + " " +cardName + " ==> "+suitName, hand);

				return handValue;
			}
		}

		return HAND_NOT_FOUND;
	}

	/**
	 * @param hand  String array as Input
	 * @return Integer based on Enum value.
	 */
	public int isHighCard(String [] hand) {

		char handValue = hand[hand.length -1].charAt(0);
		char handName = hand[hand.length -1].charAt(1);

		String cardName = util.getCardNameOf(handValue);
		String suitName = util.getSuitNameOf(handName);

		util.logData(Ranking.HIGH_CARD.getValue() + " " +cardName + " ==> "+suitName, hand);

		return util.getCardValueOf(handValue);
	}

}
