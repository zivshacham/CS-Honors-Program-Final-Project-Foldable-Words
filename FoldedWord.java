import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/*
*/

public class FoldedWord {
	public static void main(String[] args) {
		Lexicon lexicon = new Lexicon("words");
		String text = "It's a pleasure to serve you!";
		System.out.println("Using Alghorithm 'randomFoldableWords':");
		randomFoldableWords(text, lexicon, 3, 1000000);
		System.out.println();
		System.out.println("Using Alghorithm 'FoldableStrings3':");
		FoldableStrings3(lexicon, text);
	}

	public static String Normalize(String str){
		// Normalizing the string means: lowercase only, no spaces, no punctuation.
		String normalized_str = str.toLowerCase();
		normalized_str = normalized_str.replaceAll("[^a-z]", "");
		return normalized_str;
	}

	public static void PrintOrderedList(LinkedList<String> lst, String text){
		// printing the ordered list in groups, based on each word’s starting position within the text.
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
		System.out.println("There was printed " + lst.size() + " words.");
	}

	public static void randomFoldableWords(String text, Lexicon lexicon, int k, int reps){
		/** generates random foldable sequences of letters drawn from a given source text.
		* then returns those sequences that are found in the lexicon given.
		* The parameter k is the length of the words to be generated, and reps specifies the number of random trials)
		*/
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

	public static void FoldableStrings3(Lexicon lexicon, String text){
		/** generates all three-letter strings that can be folded from the given text,
		*  and returns the subset of those strings that appear in the lexicon given.
		*/
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

	public static void Algo3(){
		
	}

	public static void Algo4(){
		
	}
}	