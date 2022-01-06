package com.argenti.poker.starter;

import java.util.List;

import com.argenti.poker.service.PokerService;
import com.argenti.poker.service.impl.PokerServiceImpl;

public class PokerSolution {
	
	

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		
		if(args.length > 0 && args.length <= 1) {
			if(!args[0].equalsIgnoreCase("")) {
				PokerService pokerService = new PokerServiceImpl();

				List<String[][]>  totalRecords = pokerService.loadFromFile(args[0]);
				
				System.out.println("Total Records fetched from file is "+totalRecords.size());
			
				pokerService.calculateHandsWon(totalRecords);
			}else {
				System.out.println("Input File path cannot be empty.");
			}
		}else {
			System.out.println("Arguments cannot be empty or more than one.");
		}


		 System.out.println("Run time took " + (System.nanoTime() - startTime)/10000000L + " milli seconds");
		 
	}

}
