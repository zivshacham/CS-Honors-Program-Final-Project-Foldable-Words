import java.util.LinkedList;

/*
*/

public class FoldedWord2 {
	public static void main(String[] args) {
		Lexicon temp = new Lexicon("words");
		// System.out.println(temp.IsExist("i"));
		// System.out.println(temp.IsExist("o"));
		// System.out.println(temp.IsExist("a"));
		// System.out.println(temp.IsExist("ziv"));
		// System.out.println(temp.IsExist("vomer"));
		System.out.println(Normalize("It's a pleasure to serve you!"));

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

	public static void Algo1(){

	}

	public static void Algo2(){
		
	}

	public static LinkedList foldablesByCounting(Lexicon lexicon, String text){
		text = Normalize(text);
		int length = text.length();
		LinkedList words = new LinkedList<String>();
		for(int i = 0; i < Math.pow(2, length) - 1; i++ ){     //iterate over all the binaray sequance 
			String BinaryWord = Integer.toBinaryString(i);
			StringBuilder generatedWord = new StringBuilder();
			for(int pos = 0; pos < BinaryWord.length() ; pos++){ //this loop generates a new word from binaray sequence
				if(BinaryWord.charAt(pos)=='1'){                  
					generatedWord.append(BinaryWord.charAt(pos));
				}
			}
			String word = generatedWord.toString();
			if (lexicon.IsExist(word)){
				words.addLast(word);
			}
		}
		PrintOrderedList(words,text);
		return words;
		
	}

	public static void Algo4(){
		
	}
}	