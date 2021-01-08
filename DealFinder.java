// authors: Victoria Gorski, Joseph Mannarino, and Julia Wilkinson
// description: The DealFinder class is used to parse the keywords specified by the user into the dealsea.com web address in order to search for deals on the website

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class DealFinder {

	private static String URL = "https://dealsea.com/";
	private static String select_all = "div.dealcontent";
	private static String select_title = "div.dealcontent > strong > a";//select elements where div class = "dealcontent" and look for the <a> tag that includes the title of the deal
	private static DealsPanel deals_popUp;
	static ArrayList<String> deals_arr = new ArrayList<String>();
	
	public static void main(String[] args) throws InterruptedException {
		// Get User input
		UserInput search = new UserInput();
		
		String[] key_words = search.getKeyWords();
		int frequency = search.getFrequency();
		long frequency_millis = frequency * 60 * 1000;
		
		while (true) {
			long startTime = System.currentTimeMillis();
			// Parse Web Address using keyword
			for (int i = 0; i < key_words.length; i ++) {
				DealFinder(key_words[i]);
			}
			// Display Pop-up with search results
			if (deals_popUp == null || !deals_popUp.isDisplayable()) {
				deals_popUp = new DealsPanel();
			}
			deals_popUp.displayDeals(deals_arr);
			//Stop the thread based on frequency input 
			long timeElapsed = System.currentTimeMillis() - startTime;
			Thread.sleep(frequency_millis - timeElapsed);
		}
	}
	public static void DealFinder(String keywords) {
		Document document = new Document(keywords);
		//download html home page of dealsea.com
		try {
			document = Jsoup.connect(URL).get();
		} catch (Exception e){
			System.err.println(e);
		}
		Elements results_exp = new Elements();
		Elements results = new Elements();
		if (document == null) {
			results_exp = new Elements();
			results = new Elements();
		}else {
			// Parse elements from url (dealsea home page) matching selector  (div class = deals content / strong tag / a tag)
			// The selector decides which elements of the html to parse on 
			results_exp = document.select(select_all);
			results = document.select(select_title);
		}
		// Finding elements where the title contains the keywords and does not contain the expired tag
		for(int k = 0; k < results.size(); k++) {
			if(!results_exp.get(k).hasClass("colr_red xxsmall")) {
				String element = results.get(k).text();
				keywords = keywords.toLowerCase();
				element = element.toLowerCase();
				if (element.contains(keywords) && !deals_arr.contains(element)) {
					deals_arr.add(element);
				}
		  }
		}
	}
}
