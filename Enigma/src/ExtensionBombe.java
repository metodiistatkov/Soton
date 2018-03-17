import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * Extension
 * The bombe doesn't need to have a keyword provided for which to check in the decoded strings
 * Instead it compares the decoded strings to a list of words (dictionary), which are read from a text document
 */
public class ExtensionBombe 
{
	//creating an enigma machine
	private EnigmaMachine enigma = new EnigmaMachine();
	//an arraylist to store the decoded lines
	private ArrayList<String> store = new ArrayList<String>();
	//the words that are included in the dictionary are stored here
	private HashSet<String> dictionary = new HashSet<String>();
	private  BufferedReader reader;
	//words that occur in the decoded messages are stored her
	private HashSet<String> occuringWords = new HashSet<String>();
	
	/**
	 * Constructor for the ExtensionBombe class
	 * Reads from a text file
	 */
	public ExtensionBombe()
	{
		try 
		{
			  reader = new BufferedReader(new FileReader(new File("dictionary_extension.txt")));
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage() + "The file cannot be found");
		
		}
	}
	
	/**
	 * Gets every next line from a text file
	 * @return each line from a text file
	 */
	public String getLine()
	{	
		try 
		{
			return reader.readLine();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage() + "Error reading file");
			return null;
		}
	}
	
	/**
	 * Fills the dictionary with words
	 */
	public void fillDictionary()
	{
		ExtensionBombe file = new ExtensionBombe();
		String line = file.getLine();
		while(line != null)
		{
			//all words added are presented with capital letters
			dictionary.add(line.toUpperCase());
			line = file.getLine();
		}
	}
	
	/**
	 * Configuring what options there are for the missing plugs 
	 * Going through all possible cases and investigates which contain the words from the dictionary
	 */
	public void challenge1()
	{
		//filling the dictionary
		fillDictionary();
		
		//all possible options for the missing plugs
		char[] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		//looping through all possible combinations
		for (char unknownPlug1: letter)
		{
			for (char unknownPlug2: letter)
			{
				
				
				//setting up the enigma
				enigma.addPlug('D', unknownPlug1);
				enigma.addPlug('S', unknownPlug2);
				
				enigma.addRotor(new BasicRotor("IV",8), 0);
				enigma.addRotor(new BasicRotor("III",4), 1);
				enigma.addRotor(new BasicRotor("II",21), 2);
				
				enigma.addReflector(new Reflector("ReflectorI"));
				
				String encodedMessage = "JBZAQVEBRPEVPUOBXFLCPJQSYFJI";
				
				for (char character: encodedMessage.toCharArray())
				{
					character = enigma.encodeLetter(character);
					store.add(String.valueOf(character));
				}
				
				//converting the arraylist to a single string
				String list = String.join("", store);
				
				//clears the hashset where occuring words are stored in order to prevent repeating words
				occuringWords.clear();
				//loops through all words from the dictionary
				for(String word: dictionary)
				{
					
					//checking if the words from the dictionary are contained in the combinations and also ensures that the plugs don't overlap
					if(list.contains(word) && unknownPlug1 != 'D' && unknownPlug2 != 'S')
					{
						//adding occuring words to a new hashset
						occuringWords.add(word);
						//having only one word in a decoded message might be a pure coincidence therefore only messages with two or more words are displayed
						if (occuringWords.size() >= 2)
						{
							//displaying the desired outcomes with the configuration of each
							System.out.println(list + " " + "Plugs:" + "[D-" + unknownPlug1 + "]" + "[S-" + unknownPlug2 + "]" + " Dictionary words contained:" + occuringWords);
						}
					}
				}
	
				//reseting the storage space and the plugboard of the enigma before the next check
				store.clear();
				enigma.clearPlugboard();			
			} 
		}
	}
	
	/**
	 * Configuring what options there are for the missing initial rotor positions 
	 * Going through all possible cases and investigates which contain the words from the dictionary
	 */
	public void challenge2()
	{
		//filling the dictionary
		fillDictionary();
		
		//all possible options for the missing positions
		int[] positions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
		
		//looping through all possible combinations
		for (int position1: positions)
		{
			for (int position2: positions)
			{
				for (int position3: positions)
				{
					
					//setting up the enigma
					enigma.addPlug('H', 'L');
					enigma.addPlug('G', 'P');
					
					enigma.addRotor(new BasicRotor("V",position1), 0);
					enigma.addRotor(new BasicRotor("III",position2), 1);
					enigma.addRotor(new BasicRotor("II",position3), 2);
					
					enigma.addReflector(new Reflector("ReflectorI"));
					
					String encodedMessage = "AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD";
					
					for (char character: encodedMessage.toCharArray())
					{
						character = enigma.encodeLetter(character);
						store.add(String.valueOf(character));
					}
					//converting the arraylist to a single string
					String list = String.join("", store);
					
					//clears the hashset where occuring words are stored in order to prevent repeating words
					occuringWords.clear();
					//loops through all words from the dictionary
					for (String word: dictionary)
					{
						//checking if the words from the dictionary are contained in the combinations
						if(list.contains(word))
						{
							//adding occuring words to a new hashset
							occuringWords.add(word);
							//having only one word in a decoded message might be a pure coincidence therefore only messages with two or more words are displayed
							if(occuringWords.size() >= 2)
							{
								//displaying the desired outcomes with the configuration of each
								System.out.println(list + " Dictionary words contained:" + occuringWords + " Initial position of first rotor:" + position1 + "; " + "Initial position of second rotor:" + position2 + "; " + "Initial position of third rotor:" + position3);
							}
						}	
					}
					//reseting the storage space and the plugboard of the enigma before the next check
					store.clear();
					enigma.clearPlugboard();
				}
			}
		}
	}
	
	/**
	 * Configuring what options there are for the missing types of rotor 
	 * Going through all possible cases and investigates which contain the words from the dictionary
	 */
	public void challenge3()
	{
		//filling the dictionary
		fillDictionary();
		
		//all possible options for the missing rotor types
		String[] types = {"I", "II", "III", "IV", "V"};
		
		//looping through all possible combinations
		for (String type1: types)
		{
			for(String type2: types)
			{
				for(String type3: types)
				{
					
					//setting up the enigma
					enigma.addPlug('M', 'F');
					enigma.addPlug('O', 'I');
					
					enigma.addRotor(new BasicRotor(type1,22), 0);
					enigma.addRotor(new BasicRotor(type2,24), 1);
					enigma.addRotor(new BasicRotor(type3,23), 2);
					
					enigma.addReflector(new Reflector("ReflectorI"));
					
					String encodedMessage = "WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW";
					
					for (char character: encodedMessage.toCharArray())
					{
						character = enigma.encodeLetter(character);
						store.add(String.valueOf(character));
					}
					
					//converting the arraylist to a single string
					String list = String.join("", store);
					
					//clears the hashset where occuring words are stored in order to prevent repeating words
					occuringWords.clear();
					//loops through all words from the dictionary
					for (String word: dictionary)
					{
						//checking if the dictionary words are contained in the combinations
						if(list.contains(word))
						{
							//adding occuring words to a new hashset
							occuringWords.add(word);
							//having only one word in a decoded message might be a pure coincidence therefore only messages with two or more words are displayed
							if(occuringWords.size() >= 2)
							{
								System.out.println(list + " Dictionary words contained:" + occuringWords + "; Type of first rotor:" + type1 + "; " + "Type of second rotor:" + type2 + "; " + "Type of third rotor:" + type3);
							}
						}
					}
					
					//reseting the storage space and the plugboard of the enigma before the next check
					store.clear();
					enigma.clearPlugboard();
				}
			}
		}
	}
}
