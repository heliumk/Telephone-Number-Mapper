/*************************************************

	File: [VampireHunt2]
 	By: [Eric Kunzel]
	Date: [12/20/16]

	Compile: [javac MapNumbers.java]
	Usage: [java byte code]
	System: [n/a]

	Description: [a program that translates numers to words]

*************************************************/
import java.util.Scanner;
import java.io.*;
public class MapNumbers {
    public static void main(String [] args) throws Exception {

	Scanner input = new Scanner(System.in);

	// prompt user for dictionary file
	System.out.print("Enter name of dictionary file: ");
	String fName = input.nextLine();

	// instantiate PhoneMapper for 3-letter words from dictionary file
	PhoneMapper myMap3 = new PhoneMapper(fName, 3);

	// initial test code
	System.out.print("Enter a test word (3 letters): ");
	String s = input.nextLine();
	String testNum = myMap3.findTelNum(s);
	System.out.println("Test word maps to " + testNum);

	// instantiate PhoneMapper for 4-letter words from dictionary file
        PhoneMapper myMap4 = new PhoneMapper(fName, 4);

        System.out.println(myMap4.numWords);
	// prompt user for phone number
	System.out.print("Enter telephone number ");
	System.out.print("(7 digits, no 0's or 1's, q to quit): ");

	String telNum = input.nextLine();
	while (! telNum.equals("q")) {  // process each non-negative number
	    // extract first 3 digits
	    String firstPart = telNum.substring(0,3);        //extracts area code

	    // get words for first 3 digits and display
	    String [] results = myMap3.findWords(firstPart);

	    System.out.println("Options for first 3 digits:");
	    for (int i=0; i< results.length; i++) {
		      if(results[i] != null) System.out.println(results[i]);
	    }

	    // extract last 4 digits
	    String secondPart = telNum.substring(3,7);       //extracts rest of number

	    // get words for last 4 digits and display
	    String [] results2 = myMap4.findWords(secondPart);

	    System.out.println("Options for second 4 digits:");
	    for (int i=0; i< results2.length; i++) {
                if(results[i] != null) System.out.println(results2[i]);
	    }

	    // prompt user for phone number
	    System.out.print("Enter telephone number ");
	    System.out.print("(7 digits, no 0's or 1's, q to quit): ");
	    telNum = input.nextLine();
	} // end while

    }
}

class PhoneMapper {
    final int MAXWORDS = 20000; // max number of words from dictionary
    String [] wordList = new String [MAXWORDS]; // list of dictionary words
    int numWords = 0; // number of words of correct length
                      // extracted from dictionary

    // fileName: name of dictionary file
    // wordLength: length of words to extract
    PhoneMapper(String fileName, int wordLength) throws Exception {
	// read dictionary file and
	// add each word of length wordLength to wordList[]
        File dict = new File(fileName + ".txt");
        Scanner dictScan = new Scanner(dict);                         //scans external file for words mapping to numbers

        while (dictScan.hasNextLine()) {                              //cycles through file looking for words of specific length

                String line = dictScan.nextLine();
                if(wordLength == line.length())
                {
                  wordList [numWords] = line;                         //adds words that match to array
                  numWords++;
                }
        }
    }

    // return array of words mapped to num
    String [] findWords(String num) {
            String [] allWordsLetters = new String[numWords];                   //arrays for storing letters and numbers
            String [] allWordsNum = new String [numWords];
            String[] results = new String[numWords];
            int numResults = 0;


            for(int i = 0; i < numWords; i++)
            {
              allWordsLetters[i] = wordList[i];
            }

            for(int i = 0; i < numWords; i++)
            {
              allWordsNum[i] = findTelNum(allWordsLetters[i]);                  //
            }

            System.out.println("USER NUMBER---> " + num);

            for(int i = 0; i < numWords; i++)
            {
              if(allWordsNum[i].equals(num))                          //adding matching number to results array
              {
                results[numResults] = allWordsLetters[i];
                numResults++;                                               //increments number of results matched
                System.out.println("RESULTS -->" + results[numResults-1]);
              }
            }

            return results;
    }

    // returns telephone number that inStr maps to
    String findTelNum(String inStr)
    {
        String telNum = "";

        for (int i=0; i < inStr.length(); i++) {
                char c = inStr.charAt(i);
                switch (c) {                      //switch statement translates letters to numbers based on rules provided
                        case 'a':
                        case 'b':
                        case 'c':
                                telNum += '2';
                                break;
                        case 'd':
                        case 'e':
                        case 'f':
                                telNum += '3';
                                break;
                        case 'g':
                        case 'h':
                        case 'i':
                                telNum += '4';
                                break;
                        case 'j':
                        case 'k':
                        case 'l':
                                telNum += '5';
                                break;
                        case 'm':
                        case 'n':
                        case 'o':
                                telNum += '6';
                                break;
                        case 'p':
                        case 'q':
                        case 'r':
                        case 's':
                                telNum += '7';
                                break;
                        case 't':
                        case 'u':
                        case 'v':
                                telNum += '8';
                                break;
                        case 'w':
                        case 'x':
                        case 'y':
                        case 'z':
                                telNum += '9';
                                break;
                }
        }
	return telNum;
    }
}
