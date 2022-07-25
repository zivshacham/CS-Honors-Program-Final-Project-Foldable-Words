import java.util.LinkedList;
import java.util.Random;

/*
*/

public class FoldedWord {
	public static void main(String[] args) {
		Lexicon lexicon = new Lexicon("words");
		String text = "It's a pleasure to serve you!";
		randomFoldableWords(text, lexicon, 4, 1);
	}

	public static String Normalize(String str){
		// Normalizing the string means: lowercase only, no spaces, no punctuation.
		String normalized_str = str.toLowerCase();
		normalized_str = normalized_str.replaceAll("[^a-z]", "");
		return normalized_str;
	}

	public static void PrintOrderedList(LinkedList<String> lst){
		// printing the ordered list in groups, based on each word’s starting position within the text.
	}

	public static void randomFoldableWords(String text, Lexicon lexicon, int k, int reps){
		/** generates random foldable sequences of letters drawn from a given source text.
		* then returns those sequences that are found in the lexicon given.
		* The parameter k is the length of the words to be generated, and reps specifies the number of random trials)
		*/
		String norm_text = Normalize(text);
		int length = norm_text.length();
		LinkedList<String> lst = new LinkedList<>();
		for (int i = 0; i < reps; i++) {
			int[] arr = new int[k];
			Random rnd = new Random();
			arr = rnd.ints(k, 0, length).sorted().toArray();
			for (int j = 0; j < k; j++){
				System.out.println(arr[j]);
			}
		}
	}

	public static void Algo2(){
		
	}

	public static void Algo3(){
		
	}

	public static void Algo4(){
		
	}
}	