import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/*
This class defines four algorithms for generalizing all foldable words from given text and lexicon.
Those functions are based on a post by Brian Hayes in Bit-Player website 
http://bit-player.org/2021/foldable-words     
*/

public class FoldedWord {
	public static void main(String[] args) {
		// File to use: "words" or "Scrabble"
		// String to use: "It's a pleasure to serve you!"
		String file, text, algoNum;
		Scanner sc = new Scanner(System. in );
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		System.out.println("Welcome to \"Foladable words\" game!");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.println("Please enter the source file for creating the Lexicon:");
		file = sc.nextLine();
		Lexicon lexicon = new Lexicon(file);
		if (!lexicon.isValid()) {
			System.out.println("WRONG INPUT, TRY AGAIN!");
			System.out.println("Please enter the source file for creating the Lexicon:");
			file = sc.nextLine();
			lexicon = new Lexicon(file);
		}
		System.out.println("It's works! We build your lexicon!");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");

		System.out.println("Please enter your text:");
		text = sc.nextLine();
		System.out.println("Please choose which algorithem to use:");
		System.out.println("1 - randomFoldableWords");
		System.out.println("2 - FoldableStrings3");
		System.out.println("3 - foldablesByCounting");
		System.out.println("4 - wordIsFoldableAlgo");
		algoNum = sc.nextLine();

		while (!algoNum.equals("1") && !algoNum.equals("2") && !algoNum.equals("3") && !algoNum.equals("4")){
			System.out.println("WRONG INPUT, TRY AGAIN!");
			System.out.println("Please choose which algorithem to use:");
			System.out.println("1 - randomFoldableWords");
			System.out.println("2 - FoldableStrings3");
			System.out.println("3 - foldablesByCounting");
			System.out.println("4 - wordIsFoldableAlgo");
			algoNum = sc.nextLine();
		}
		
		if (algoNum.equals("1")){
			System.out.println("For using algorithem 'randomFoldableWords' you should enter the length of the word and the reps.");
			System.out.println("Please enter the length of the word: (int between 1-15)");
			String length = sc.nextLine();
			int lengthInt;
			while (true) {
				try{
					lengthInt = Integer.parseInt(length);
					if (lengthInt < 1 || lengthInt > 15) {
						System.out.println("WRONG INPUT, TRY AGAIN!");
						System.out.println("Please enter the length of the word: (int between 1-15)");
						length = sc.nextLine();
					} else { break; }
				}
				catch (NumberFormatException ex){
					System.out.println("WRONG INPUT, TRY AGAIN!");
					System.out.println("Please enter the length of the word: (int between 1-15)");
					length = sc.nextLine();
				}
			}
			System.out.println("Please enter the reps:");
			String reps = sc.nextLine();
			int repsInt;
			while (true) {
				try{
					repsInt = Integer.parseInt(reps);
					break;
				}
				catch (NumberFormatException ex){
					System.out.println("WRONG INPUT, TRY AGAIN!");
					System.out.println("Please enter the reps:");
					reps = sc.nextLine();
				}
			}
			System.out.println("Using Alghorithm 'randomFoldableWords':");
			randomFoldableWords(text, lexicon, lengthInt, repsInt);
		} else if (algoNum.equals("2")){
			System.out.println("Using Alghorithm 'foldableStrings3':");
			foldableStrings3(lexicon, text);
		} else if (algoNum.equals("3")){
			System.out.println("Using Alghorithm 'foldablesByCounting':");
			foldablesByCounting(lexicon, text);
		} else if (algoNum.equals("4")){
			System.out.println("Using Alghorithm 'wordIsFoldableAlgo':");
			wordIsFoldableAlgo(lexicon, text);
		}
		sc.close();
	}
    
	/** this function Normalizing the string means: lowercase only, no spaces, no punctuation.
	 * @param str string that you want to normalize
	 * @return normalized string
	 */
	public static String Normalize(String str){
		
		String normalized_str = str.toLowerCase();
		normalized_str = normalized_str.replaceAll("[^a-z]", "");
		return normalized_str;
	}
	
	/** this function organize and prints the words into groups based on each word’s starting position 
	 * within the text. Within each group, the words are sorted according to the position of their last
	 * character.
	 * @param lst the linked list that you wish to print
	 * @param text the reference text for the groups - explained at top of this documantaion 
	 */
	public static void PrintOrderedList(LinkedList<String> lst, String text){
		text = Normalize(text);
		int length = text.length();
		LinkedList<String>[] table = new LinkedList[length];
		for(int i = 0; i < length; i++) {
			table[i] = new LinkedList<String>();
		}
		Iterator<String> itr = lst.iterator();
		while (itr.hasNext()) {
			String word = itr.next();
			int index = text.indexOf(word.charAt(0));
			table[index].add(word); 
		}
		for (int j = 0; j < length; j++) {
			System.out.print("Group " + j + ":");
			System.out.print(table[j].toString());
			System.out.println();
		
		}
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		System.out.println("There was printed " + lst.size() + " words.\n");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}

	/** generates random foldable sequences of letters drawn from a given source text.
		* then returns those sequences that are found in the lexicon given.
		* The parameter k is the length of the words to be generated, and reps specifies the number of random trials) 
		@param text the text that the function checks for folderable words in.
		@param lexicon the lexicon that the function refers to.
		@param k 
		@param reps number of char sequences to be randomize
		*/
	public static void randomFoldableWords(String text, Lexicon lexicon, int k, int reps){
		String norm_text = Normalize(text);
		int length = norm_text.length();
		LinkedList<String> lst = new LinkedList<>();
		int[] arr = new int[k];
		for (int i = 0; i < reps; i++) {
			// the following loop is conducted reps time, for each time constructing array with distinct k indexes
			Random rnd = new Random();
			arr = rnd.ints(k, 0, length).sorted().distinct().toArray(); // generating k random numbers to be the indexes from the text
			while(arr.length < k) {
				arr = rnd.ints(k, 0, length).sorted().distinct().toArray(); // generating again if there are less then k indexes
			}
			String word = "";
			for (int j = 0; j < k; j++){
				word += norm_text.charAt(arr[j]);
			}
			if (lexicon.IsExist(word) && !lst.contains(word)) lst.add(word);
		}
		PrintOrderedList(lst, norm_text);
	}

	/** generates all three-letter strings that can be folded from the given text,
		*  and returns the subset of those strings that appear in the lexicon given.
		@param lexicon the lexicon that the function refers to.
		@param text the text that the function checks for folderable words in.
        */
	public static void foldableStrings3(Lexicon lexicon, String text){
	
		String norm_text = Normalize(text);
		int length = norm_text.length();
		LinkedList<String> lst = new LinkedList<>();
		for (int i = 0; i < length - 2; i++){
			for (int j = i + 1; j < length - 1; j++){
				for (int s = i + 2; s < length; s++){
					String word = "";
					word += norm_text.charAt(i);
					word += norm_text.charAt(j);
					word += norm_text.charAt(s);
					if (lexicon.IsExist(word) && !lst.contains(word)) lst.add(word);
				}
			}
		}
		PrintOrderedList(lst, norm_text);
	}

	/** generates all strings that can be folded from the given text.
		* the function works by creating all the binary sequence that shorter than the text length,
		* converting those sequences to words and check if they are exist in the lexicon.
		* eventually it returns the subset of those strings that appear in the given lexicon.
		@param lexicon the lexicon that the function refers to.
		@param text the text that the function checks for folderable words in.
		*/
	public static void foldablesByCounting(Lexicon lexicon, String text){
		text = Normalize(text);
		int length = text.length();
		LinkedList words = new LinkedList<String>();
		for(int i = 0; i < Math.pow(2, length) - 1; i++ ){  //iterate over all the binaray sequance that shorter than the string length
			String BinaryWord = toBinaryStringWithLeadingZeroes(i,text);
			StringBuilder generatedWord = new StringBuilder();
			for(int pos = 0; pos < BinaryWord.length() ; pos++){ //this loop generates a new word from binaray sequence
				if(BinaryWord.charAt(pos)=='1'){                  
					generatedWord.append(text.charAt(pos));
				}
			}
			if (lexicon.IsExist(generatedWord.toString())){
				// System.out.println(BinaryWord);
				words.add(generatedWord.toString());
			}
		}
		PrintOrderedList(words,text);		
	}

	/** this function generates a 16 long binary string that includes the leading zeros.
	 * @param num number to be converted to binary string
	 * @return binary string represents the number
	 */
	private static String toBinaryStringWithLeadingZeroes(int num, String text){
		int length = text.length();
		String binWord = Integer.toBinaryString(num);
		return String.format("%16s", binWord).replace(' ', '0');
		}
		
	/** this function return true if the word can be formed by folding the given text
	 * @param word Check if the word is a foldable word in the text
	 * @param text Check if the word is a foldable word in the text
	 * @return true if the word is a folded text result  
	 */
	private static boolean wordIsFoldable(String word, String text){
		int textPos = 0;
		for(int wordPos = 0; wordPos < word.length(); wordPos++ ){
			for(; textPos < text.length(); textPos++ ){
				if(word.charAt(wordPos) == text.charAt(textPos)){
					wordPos++;
					if(wordPos == word.length()){
						return true;
					}
				}
			}
		}

		return false;
	}

	/** 
	@param lexicon the lexicon that the function refers to.
	@param text the text that the function checks for folderable words in.
	*/
	public static void wordIsFoldableAlgo(Lexicon lexicon, String text){
		LinkedList<String>  words = new LinkedList<>();
		String word = lexicon.ReadWord();
		while(word != ""){
			if(wordIsFoldable(word, text)){
				words.add(word);
			}
			word = lexicon.ReadWord();
		}
		PrintOrderedList(words, text);
	}	
}	