package com.tkorg.search;

import java.util.ArrayList;

/**
 * @author danhit
 * 
 */
public class SearchEnginesAction {

    static ArrayList<String> googleSearchResult = null;
    static ArrayList<String> yahooSearchResult = null;

	public SearchEnginesAction() {
	}
	
	/**
	 * Submit Query To Google
	 * @param queryString : A String for query 
	 * @param googleChecked: if true submit query to google . 
	 * @param maxGoogleResult : Max reuslt from user
	 * @return: Arraylist String from Google include HTML Tag,...
	 * @throws InterruptedException
	 */
	
	public  ArrayList < String > submitQueryToGoogle(String queryString, boolean googleChecked, int maxGoogleResult) throws InterruptedException {
		if (googleChecked == true) {
			Thread googleSearchEngineThread = new GoogleSearchEngine();	
			((GoogleSearchEngine) googleSearchEngineThread).setQueryString(queryString);
			((GoogleSearchEngine) googleSearchEngineThread).setMaxResult(maxGoogleResult);
			googleSearchEngineThread.start();
			googleSearchEngineThread.join();
			googleSearchResult = ((GoogleSearchEngine) googleSearchEngineThread).getResultGoogleArrayList();
			googleSearchEngineThread.stop();
		} 
		return googleSearchResult;
	}
	
	/**
	 * Submit Query To Yahoo
	 * @param queryString : String for query
	 * @param yahooChecked : if true submit to yahoo
	 * @param maxYahooResult : Max reuslt from user
	 * @return : ArrayList String from yahoo after sumbit to Yahoo
	 */
	
	public ArrayList<String> submitQueryToYahoo(String queryString, boolean yahooChecked, int maxYahooResult) {
		
		if (yahooChecked == true) {
			Thread yahooSearchEngineThread = new  YahooSearchEngine("Jm3V0PbV34GKpO58IjWbVvW26XjoKlrkriC2D4idXRBm8No3VDoCCjQLhBqsjJ9wRVI");
			((YahooSearchEngine)yahooSearchEngineThread).setQuery(queryString);
			((YahooSearchEngine)yahooSearchEngineThread).setLimit(maxYahooResult);			
			yahooSearchEngineThread.start();
			try {
				yahooSearchEngineThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			yahooSearchResult = new ArrayList<String>();
			for (int i = 0; i < maxYahooResult; i++) {
				yahooSearchResult.add(((YahooSearchEngine) yahooSearchEngineThread).getUrls()[i] + "<br>" + ((YahooSearchEngine) yahooSearchEngineThread).getTitles()[i]);
			}	
			yahooSearchEngineThread.stop();
		} 
		
		return yahooSearchResult;
	}
}
				 
		

