/**
 * This class represents the creation of the actual enigma machine
 */

public class EnigmaMachine 
{
	/**
	 * The composing parts of the machine
	 */
	private Plugboard plugboard;
	private BasicRotor rotors[];
	private Reflector reflector;
	private char characters[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
								'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	/**
	 * Constructor for class EnigmaMachine
	 * A plugboard and an array of Basicrotors are instantiated
	 */
	
	public EnigmaMachine()
	{
		//Instantiating a plugboard inside the enigma
		plugboard = new Plugboard();
		//Instantiating the three rotors of the enigma
		rotors = new BasicRotor[3];
	}
	
	/**
	 * Adds a plug to the plugboard
	 * @param end1
	 * @param end2
	 */
	public void addPlug(char end1, char end2)
	{
		plugboard.addPlug(end1, end2);
	}
	
	/**
	 * Clears the plugboard
	 */
	public void clearPlugboard()
	{
		plugboard.clear();
	}
	
	/**
	 * Assigns the rotor a slot
	 * @param rotor
	 * @param slot
	 */
	public void addRotor(BasicRotor rotor, int slot)
	{
		rotors[slot] = rotor;
	}
	
	/**
	 * Getter for the rotor with assigned slot
	 * @param slot
	 * @return the slot of the rotor
	 */
	public BasicRotor getRotor(int slot)
	{
		return rotors[slot];
	}
	
	/**
	 * Adding a reflector
	 * @param reflector
	 */
	public void addReflector(Reflector reflector) 
	{
		this.reflector=reflector;
		
	}
	
	/**
	 * Getting a reflector
	 * @return the reflector
	 */
	public Reflector getReflector()
	{
		return reflector;
	}
	/**
	 * The slot of the rotor is set to the position of rotation
	 * @param slot
	 * @param position
	 */
	public void setPosition(int slot, int position)
	{
		rotors[slot].setPosition(position);
	}
	
	/**
	 * Converting a character to an integer
	 * @param letterToEncode
	 * @return integer
	 */
	public int convertToInteger(char letterToEncode)
	{
		for (int i = 0; i<characters.length; i++)
		{
			if(letterToEncode == characters[i])
			{
				return i;
			}
		}
		return 0;
		
		
	}
	
	/**
	 * Converting an integer back to a character
	 * @param letterThatIsNumber
	 * @return character
	 */
	public char convertToCharacter(int letterThatIsNumber)
	{
		return characters[letterThatIsNumber];
	}
	
	/**
	 * Implementing the pseudo-code, which gives the algorithm that every characters undergoes when being encoded
	 * @param letterToEncode
	 * @return decoded letter
	 */
	public char encodeLetter(char letterToEncode)
	{
		int charAsInt;
		char intAsChar;
		
		letterToEncode = plugboard.substitute(letterToEncode);
		charAsInt = convertToInteger(letterToEncode);
		
		//loops through each of the 3 rotors assigning them substitute() mehtod
		for(int slot = 0; slot < rotors.length; slot++)
		{
			charAsInt = rotors[slot].substitute(charAsInt);
		}
		
		charAsInt = reflector.substitute(charAsInt);
		
		//loops once again through each of the three rotors assigning them substituteBack method
		for(int slot = rotors.length-1; slot >= 0; slot--)
		{
			charAsInt = rotors[slot].substituteBack(charAsInt);
		}
		
		intAsChar = convertToCharacter(charAsInt);
		intAsChar = plugboard.substitute(intAsChar);
		rotors[0].rotate();
		
		return intAsChar;
	}
	
	/**
	 *Inputs the settings that should be used to decode for the first test 
	 */
	public void start1()
	{
		addPlug('A', 'M');
		addPlug('G', 'L');
		addPlug('E', 'T');		
		
		addRotor(new BasicRotor ("I",6), 0);
		addRotor(new BasicRotor("II",12), 1);
		addRotor(new BasicRotor("III",5), 2);
		
		reflector = new Reflector("ReflectorI");
		addReflector(reflector);
		
		String encodedMessage = "GFWIQH";
		
		//loops through each character from the string and decodes it
		for (char character: encodedMessage.toCharArray())
		{
			character = encodeLetter(character);
			System.out.print(character);
		}
	
	}
	
	/**
	 * Inputs the settings that should be used to decode the second test 
	 */
	
	public void start2()
	{
		System.out.println();
		plugboard.clear();
		addPlug('B', 'C');
		addPlug('R', 'I');
		addPlug('S', 'M');
		addPlug('A', 'F');		
		
		addRotor(new BasicRotor ("IV", 23), 0);
		addRotor(new BasicRotor("V", 4), 1);
		addRotor(new BasicRotor("II", 9), 2);
		
		reflector = new Reflector("ReflectorII");
		addReflector(reflector);
		
		String encodedMessage = "GACIG";
		
		//loops through each character from the string and decodes it
		for (char character: encodedMessage.toCharArray())
		{
			character = encodeLetter(character);
			System.out.print(character);
		}
	}
	
	/**
	 * Reading from a text file
	 * Encoding the text
	 * Writing the text to another file
	 */
	public void start3()
	{
		System.out.println();
		EnigmaFile file = new EnigmaFile();
		file.encodeTextFromFile();
	}
	
	/**
	 * Setting up the machine to decode the third test
	 * This time turnover rotors are used
	 */
	public void start4()
	{
		System.out.println();
		plugboard.clear();
		addPlug('Q', 'F');
		
		TurnoverRotor turnoverRotor1 = new TurnoverRotor("I", 23);
		TurnoverRotor turnoverRotor2 = new TurnoverRotor("II", 11);
		TurnoverRotor turnoverRotor3 = new TurnoverRotor("III", 7);
		
		turnoverRotor1.setNextRotor(turnoverRotor2);
		turnoverRotor2.setNextRotor(turnoverRotor3);
		turnoverRotor3.setNextRotor(null);
		
		addRotor(turnoverRotor1, 0);
		addRotor(turnoverRotor2, 1);
		addRotor(turnoverRotor3, 2);
		
		reflector = new Reflector("ReflectorI");
		addReflector(reflector);
		
		String encodedMessage = "OJVAYFGUOFIVOTAYRNIWJYQWMXUEJGXYGIFT";
		
		//loops through each character from the string and decodes it
		for (char character: encodedMessage.toCharArray())
		{
			character = encodeLetter(character);
			System.out.print(character);
		}
	}
	
	/**
	 * Creating a bombe in the enigma machine	
	 * Adding all bombe challenges to the enigma machine
	 */
	public void start5()
	{
		System.out.println();
		Bombe bombe = new Bombe();
		bombe.challenge1();
		System.out.println();
		bombe.challenge2();
		System.out.println();
		bombe.challenge3();
	}
	
	/**
	 * An Extension
	 * The bombe doesn't need to have a keyword provided for which to check in the decoded strings
	 * Instead it compares the decoded strings to a list of words (dictionary), which are read from a text document
	 */
	public void start6()
	{
		ExtensionBombe extension = new ExtensionBombe();
		extension.challenge1();
		System.out.println();
		extension.challenge2();
		System.out.println();
		extension.challenge3();
	}
	
	
	
	

}
