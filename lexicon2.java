import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Node;
import java.util.Scanner;
import java.math.*;

/**
 * A library of words lexicon functions.
 */
public class Lexicon2 {
	
	private String filename;                   
	private LinkedList<String>[] wordsTable;
    private static int ARR_SIZE = 250007;


	/**
    * Creates a lexicon by reading in a list of words from
    * the given filename.
    * 
    * @param filename words file name
    */
	 public Lexicon2(String filename) {
        setLexiconTable(filename);		
	}

    
	/**
    * The function gets a word, and return whether the word
	* is exist in the lexicon. 
    * 
    * @param word a string of a word
	* @return True/False if the word is exist
    */
	public boolean IsExist(String word) {
        return(wordsTable[hashFunction(word)].contains(word));
	}


    /**
    * The function gets a word, and insert it to
	* the correct list in the table
    * 
    * @param word a string of a word
    */
    private void insert(String word) {
        wordsTable[hashFunction(word)].add(word);
	}


	/**
    * The function gets a filename, and set the field wordsTable
    * to the correspond filename. 
    * @param fileName a source file of words
    */
    private void setLexiconTable(String fileName){
        wordsTable = new LinkedList [ARR_SIZE];
		for(int i = 0; i < ARR_SIZE; i++) {
			wordsTable[i] = new LinkedList<String>();
		}
		
		
      	try {
         // try to read from file 
        	File file = new File (filename);
			Scanner reader = new Scanner(file);
			while(reader.hasNextLine()) {
				String word  = reader.nextLine();
				word = word.toLowerCase();
				insert(word);
			}
			reader.close();
      	}
      	catch (IOException e) {
        	// throw new RuntimeException ("Could not open file: " + filename);
			System.out.println("Could not open file: " + filename);
		}
    }



    /**
    * The function gets a word, and return a uniqe hash value  
    * which correspond to the index of the array\hash table
    * @param word a string of a word
	* @return True/False if the word is exist
    */
    private int hashFunction(String word){
        BigInteger sum = BigInteger.ZERO;       //creating a big int in purpose of summing by "Horner's rule" 
        word.toLowerCase();                     //the function refers to capitalized letters and small letters as equivelent
        for(int i = 0; i < word.length(); i++){
            int c = word.charAt(i);
            c -= 'a'+ 1;
            c = c *  (int)Math.pow(26,i); 
            BigInteger charVal = BigInteger.valueOf(c);// data type coversetion compulsion
            sum.add(charVal);
        }
        BigInteger bigSize = BigInteger.valueOf(ARR_SIZE);
        return (sum.remainder(bigSize)).intValue(); // convert bigInt to int  
    }
    



	public static void main(String[] args) {
        // constructor test
		Lexicon2 lex = new Lexicon2("words");
        // isExist test
        System.out.println(lex.IsExist("a"));
		System.out.println(lex.IsExist("zz"));
		System.out.println(lex.IsExist("zzz"));
        System.out.println(lex.IsExist("Zyzomys"));
        System.out.println(lex.IsExist("Zyzzogeton"));
		
	}
}

