package com.argenti.poker.service;

import java.util.List;


public interface PokerService {
	
	/**
	 * filPath -> Used to load file from the given input path.
	 *
	 */
	public List<String[][]> loadFromFile(String filePath);
	
	/**
	 * filPath -> Used to load file from the given input path.
	 *
	 */
	public void calculateHandsWon(List<String[][]> totalList);


}
