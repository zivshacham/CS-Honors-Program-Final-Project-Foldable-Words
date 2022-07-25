/*
*/

public class FoldedWord2 {
	public static void main(String[] args) {
		// Lexicon temp = new Lexicon("words");
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

	public static void Algo1(){

	}

	public static void Algo2(){
		
	}

	public static void Algo3(){
		
	}

	public static void Algo4(){
		
	}
}	