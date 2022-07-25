import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Node;

/**
 * A library of words lexicon functions.
 */
public class Lexicon2 {
	
	private String filename;                   
	private LinkedList<String> head;


	/**
    * Creates a lexicon by reading in a list of words from
    * the given filename.
    * 
    * @param filename words file name
    */
	public Lexicon2(String filename) {
		this.filename = filename;
		head = new LinkedList<String>();
		head.addLast("I");
		head.addLast("a");
		head.add("O");
      	/* try {
         // try to read from file in working directory
        	File file = new File (filename);
        	image  = ImageIO.read (file);
        	width  = image.getWidth (null);
        	height = image.getHeight (null);
      }
      catch (IOException e) {
        	throw new RuntimeException ("Could not open file: " + filename);
      }

      // check that image was read in
      if (image == null) {
        	throw new RuntimeException ("Invalid image file: " + filename);
      }*/
	}

	/**
    * The function gets a word, and return whether the word
	* is exist in the lexicon. 
    * 
    * @param word a string of a word
	* @return True/False if the word is exist
    */
	public boolean IsExist(String word) {
		return head.contains(word);
	}

	/**
    * The function gets a word, and return whether the word
	* is exist in the lexicon. 
    * 
    * @param word a string of a word
	* @return True/False if the word is exist
    */
	public String ReadWord() {
		
		return "";
	}
    public LinkedList [] lexiconTable(String fileName){
        int size = 235886;
        LinkedList [] table = new LinkedList<String>()[size];
    }

	public static void main(String[] args) {
		//tetsing
	}
}

