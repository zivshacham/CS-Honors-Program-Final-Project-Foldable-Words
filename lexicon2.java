import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Node;
import java.math.*;

/**
 * A library of words lexicon functions.
 */
public class Lexicon2 {
	
	private String filename;                   
	private LinkedList<String>[] table;
    private static int SIZE = 250007;


	/**
    * Creates a lexicon by reading in a list of words from
    * the given filename.
    * 
    * @param filename words file name
    */
	 public Lexicon2(String filename) {
		this.filename = filename;
		createLexiconTable(filename);
	// 	// head.addLast("I");
	// 	// head.addLast("a");
	// 	// head.add("O");
    

	/**
    * The function gets a word, and return whether the word
	* is exist in the lexicon. 
    * 
    * @param word a string of a word
	* @return True/False if the word is exist
    */
	public boolean IsExist(String word) {
        while(table(hashFunction(word)).
		return head.contains(word);
	}

	/**
    * The function gets a word, and return whether the word
	* is exist in the lexicon. 
    * 
    * @param word a string of a word
	* @return True/False if the word is exist
    */
	// public String ReadWord() {
		
	// 	return "";}
	// 

    public LinkedList<String>[] createLexiconTable(String fileName){
        table = new LinkedList<String>[SIZE];
        for (int i = 0; i <= SIZE; i++){
            String word = StdIn.readLine();
            table[hashFunction(word)].add(word);
        }
    }
    public int hashFunction(String word){
        BigInteger sum = BigInteger.ZERO;
        word.toLowerCase();
        for(int i = 0; i < word.length(); i++){
            int c = word.charAt(i);
            c -= 'a'+ 1;
            c = c *  (int)Math.pow(26,i); 
            BigInteger charVal = BigInteger.valueOf(c);
            sum.add(charVal);
        }
        BigInteger bigSize = BigInteger.valueOf(SIZE);
        return (sum.remainder(bigSize)).intValue(); // convert bigInt to int  
    }
    

	public static void main(String[] args) {
		// hash func check
        String word = "aaa";
        System.out.println("hash func on  aaa , should print 703. result: \n" + hashFunction("aaa"));
        
	}
}

