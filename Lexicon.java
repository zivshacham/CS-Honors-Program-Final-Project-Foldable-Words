import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A library of words lexicon functions.
 */
public class Lexicon {
	
	final static int NUM_OF_SIGNIFICANT_LETTERS = 3;							// number of letters to refer in hash function
	final static int ARR_SIZE = (int) Math.pow(27, NUM_OF_SIGNIFICANT_LETTERS); // size of the hash table
	
	private	LinkedList<String>[] lexicon;		// hash table, using chaining 
	private Iterator<String> current;			// used for iterating each of the list in the table
	private int counter;						// the counter is used when "reading" words from the lexicon

	/**
    * Creates a lexicon by reading in a list of words from
    * @param filename words file name
    */
	public Lexicon(String filename) {
		lexicon = new LinkedList[ARR_SIZE];
		for(int i = 0; i < ARR_SIZE; i++) {
			lexicon[i] = new LinkedList<String>();
		}
		counter = 0;
		current = lexicon[counter].iterator();
		// insert("i");
		// insert("o");
		// insert("a");
		
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
    * The function gets a word, insert it to the hash table
	* according to the hash function. 
    * 
    * @param word a string of a word
	* @return True/False if the word is exist
    */
	public void insert(String word) {
		lexicon[Hash_Formula(word)].addLast(word);
	}

	/**
    * The function calculate the value of the word according 
	* to hash formula. 
    * 
    * @param word a string of a word
	* @return the hash value between 0 to ARR_SIZE
    */
	private int Hash_Formula(String word){
		int result = 0;
		for (int i = 1; i <= NUM_OF_SIGNIFICANT_LETTERS; i++) {
			if (i <= word.length()) {
				int char_value = word.charAt(i - 1) - 'a' + 1;
				result += char_value * Math.pow(26, i - 1);
			}
		}
		return result;
	}

	/**
    * @param word a string of a word
	* @return True/False if the word is exist
    */
	public boolean IsExist(String word) {
		if(Hash_Formula(word) < 0 || Hash_Formula(word) >= ARR_SIZE){
			return false;
		}
		return lexicon[Hash_Formula(word)].contains(word);
	}

	/**
    * The function is used for iterating the whole hash table,
    * in each call to the function, the function return the next
	* word in the table (in the same list or the first in the next list)
	*
	* @return The next word in the table
    */
	public String ReadWord() {
		while(!current.hasNext()) {
			counter++;
			current = lexicon[counter].iterator();
			if(counter == ARR_SIZE - 1) return ""; 
		}
		return current.next();
	}
    /**
    * The function returns the current word in the hashTable
	*
	* @return The currnet word in the table
    */
	public String getCurrentWord() {
		return current.next();
	}

	/**
    * Used for debugging manners.
    * printing the maximum length of a cell in the hash table, and 
	* the number of that cell.
    */
	private void MaxinCell() {
		int maxWords = 0;
		int maxCell = 0;
		for (int i=0; i<lexicon.length; i++) {
			if (lexicon[i].size() > maxWords) {
				maxWords = lexicon[i].size();
				maxCell = i;
			}
		}
		System.out.println("For the NUM_OF_SIGNIFICANT_LETTERS: " + NUM_OF_SIGNIFICANT_LETTERS);
		System.out.println("The maximum words in 1 cell is: " + maxWords);
		System.out.println("The biggest cell is: " + maxCell);
	}

	public static void main(String[] args) {
		Lexicon lex = new Lexicon("words");
		// System.out.println(lex.Get_Table()[1].toString());
		// System.out.println(lex.Get_Table()[18278].toString());
		// System.out.println(lex.IsExist("a"));
		// System.out.println(lex.IsExist("zz"));
		// System.out.println(lex.IsExist("zzz"));
		// System.out.println(lex.IsExist("zzzaad"));
		// for (int i = 0; i <= 1000; i++) {
		// 	System.out.println(lex.ReadWord());
		// }
		lex.MaxinCell();
		System.out.println("done succesfuly");
	}
}