// authors: Victoria Gorski, Joseph Mannarino, and Julia Wilkinson
// description: The UserInput class is used to receive the keywords the user wants to search for and at what frequency the user wants to search the dealsea.com website

import java.util.Scanner;

public class UserInput {

    private int frequency;
    private String[] keyWord;
    private Scanner scanner = new Scanner(System.in);
  
    public UserInput(){
    	//ask user for keywords and frequency of search
        System.out.println("Enter item(s) you want to search for! (space delimited)");
        keyWord = scanner.nextLine().split(" ");
        System.out.println("How often would you like to check for deals? (min)");
        System.out.println("Please enter a number greater than 30");
        frequency = scanner.nextInt();
        if (frequency < 30) {
        	System.out.println("Please enter a number greater than 30");
        	frequency = scanner.nextInt();
        }else {
        
        System.out.println("Item(s) Searched:");
        for (int i = 0; i < keyWord.length; i++) {
        	System.out.println(keyWord[i]);
        }
        System.out.println("Frequency of Search: " + frequency +" min");
        scanner.close();
        }
    }
    //helper methods
    public int getFrequency() {
        return this.frequency;
    }

    public String[] getKeyWords() {
        return this.keyWord;
    }

}
