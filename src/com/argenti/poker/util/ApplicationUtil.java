package com.argenti.poker.util;

import com.argenti.poker.enums.CardEnum;
import com.argenti.poker.enums.Suit;

public final class ApplicationUtil {

	public int getCardValueOf(char card){

		return CardEnum.cardValueOf(String.valueOf(card));
	}
	
	public String getCardNameOf(char card){

		return CardEnum.cardNameOf(String.valueOf(card));
	}
	
	public String getSuitNameOf(char card){

		return Suit.getValueOf(String.valueOf(card));
	}

	public boolean isGreaterThanSecond(String first, String second){
		
		if(getCardValueOf(first.charAt(0)) > getCardValueOf(second.charAt(0))){
			return true;
		}
		return false;
	}
	
	public void logData(String message, String [] hand) {
//		System.out.print(message);
//        Arrays.stream(hand).forEach(item-> System.out.print(" " + item));
//        System.out.println();
	}

	public void insertionSort(String [] arr) {
		for(int i=1;i<arr.length;i++){
			for(int y=i;0<y;y--) {
				String item = arr[y];
				String prevItem = arr[y - 1];

				if(isGreaterThanSecond(prevItem, item)){
					arr[y-1] = item;
					arr[y] = prevItem;
				}
			}
		}
	}
	
}
