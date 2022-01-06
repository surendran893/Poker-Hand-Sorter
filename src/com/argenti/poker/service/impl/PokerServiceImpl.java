package com.argenti.poker.service.impl;

import static com.argenti.poker.util.ApplicationConstant.HAND_NOT_FOUND;
import static com.argenti.poker.util.ApplicationConstant.TOTAL_CARDS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import com.argenti.poker.service.PokerHandSort;
import com.argenti.poker.service.PokerService;
import com.argenti.poker.util.ApplicationUtil;

public class PokerServiceImpl extends PokerHandSort implements PokerService {

	ApplicationUtil util = new ApplicationUtil();
	
	String [] hand1 = null;
	String [] hand2 = null;

	@Override
	public List<String[][]> loadFromFile(String filePath){

		List<String[][]> handList = new ArrayList<>();

		System.out.println("Input file path is ==> "+filePath);

		try {
			Stream<String> stream = Files.lines(Paths.get(filePath));

			stream.forEach(action -> {

				StringTokenizer tokenizer = new StringTokenizer(action, " ");

				if(tokenizer.countTokens() <= 0) {
					System.out.println("Empty Hand value cannot be acceptable");
				}else if(tokenizer.countTokens() > 10) {
					System.out.println("Hand value cannot be more than 10");
				}else {

					String [] player1 = new String[TOTAL_CARDS];
					String [] player2 = new String[TOTAL_CARDS];
					int i=0;
					while (tokenizer.hasMoreTokens()){
						if(i < TOTAL_CARDS) {
							player1[i % TOTAL_CARDS] = tokenizer.nextToken();
						} else {
							player2[i % TOTAL_CARDS] = tokenizer.nextToken();
						}
						i++;
					}
					String [][] turn = new String[][]{player1, player2};
					handList.add(turn);
				}

			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return handList;
	}

	@Override
	public void calculateHandsWon(List<String[][]> totalList) {
		
		int player1Win =0;
		int player2Win =0;

		for (String[][] turn : totalList) {
			String [] player1 = turn[0];
			String [] player2 = turn[1];

			util.insertionSort(player1);
			int[] hand1Eval = evaluateHand(player1);
			
			util.insertionSort(player2);
			int[] hand2Eval =  evaluateHand(player2);

			if(hand1Eval[0] == hand2Eval[0]){

				if(hand1Eval[1] == hand2Eval[1]){

					for(int i = 4; 0< i;i--) {
						
						int p1Rank = util.getCardValueOf(player1[i].charAt(0));
						int p2Rank = util.getCardValueOf(player2[i].charAt(0));
						
						if(p1Rank > p2Rank){
							player1Win += 1;
							util.logData("Player 1 Wins", new String[0]);
							break;
						}  else if(p1Rank < p2Rank){
							player2Win +=1;
							util.logData("Player 2 Wins", new String[0]);
							break;
						}
					}
					
				} else if(hand1Eval[1] > hand2Eval[1]){
					player1Win += 1;
					util.logData("Player 1 Wins", new String[0]);
				} else {
					player2Win +=1;
					util.logData("Player 2 Wins", new String[0]);
				}
			} else if(hand1Eval[0] > hand2Eval[0]){
				player1Win += 1;
				util.logData("Player 1 Wins", new String[0]);
			} else {
				player2Win +=1;
				util.logData("Player 2 Wins", new String[0]);
			}
		}

		System.out.println("Player 1: "+player1Win);
		System.out.println("Player 2: "+player2Win);
	}


	private int [] evaluateHand (String [] hand){
		int handValue = isRoyalFlush(hand);
		if(handValue  != HAND_NOT_FOUND) {
			return new int []{10,handValue };
		}

		handValue = isStraightFlush(hand);
		if(handValue  != HAND_NOT_FOUND) {
			return new int []{9,handValue };
		}

		handValue = isFourOfAKind(hand);
		if(handValue  != HAND_NOT_FOUND) {
			return new int []{8,handValue };
		}

		handValue = isFullHouse(hand);
		if(handValue  != HAND_NOT_FOUND) {
			return new int []{7,handValue };
		}

		handValue = isFlush(hand);
		if(handValue  != HAND_NOT_FOUND) {
			return new int []{6,handValue };
		}

		handValue = isStraight(hand);
		if(handValue  != HAND_NOT_FOUND) {
			return new int []{5,handValue };
		}

		handValue = isThreeOfAKind(hand);
		if(handValue  != HAND_NOT_FOUND) {
			return new int []{4,handValue };
		}

		handValue = isTwoPairs(hand);
		if(handValue  != HAND_NOT_FOUND) {
			return new int []{3,handValue };
		}

		handValue = isOnePair(hand);
		if(handValue  != HAND_NOT_FOUND) {
			return new int []{2,handValue };
		}

		handValue = isHighCard(hand);
		if(handValue  != HAND_NOT_FOUND) {
			return new int []{1,handValue };
		}

		return null;
	}

}
