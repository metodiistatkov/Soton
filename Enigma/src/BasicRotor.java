/**
 * A type of rotor
 * There are 5 different basic rotors
 */
public class BasicRotor extends Rotor 
{
	/**
	 * Constructor for class BasicRotor
	 * Takes the type as a string
	 * @param name, this represents the type of the rotor
	 */
	public BasicRotor (String name)
	{
		 super(name);
	}
	
	/**
	 * Overloading the basic constructor, so this can be called when doing the set up for the enigma test
	 * @param name, this represents the type of the rotor
	 * @param position to which the rotor is set
	 */
	public BasicRotor(String name, int position)
	{
		this(name);
		setPosition(position);
	}

	/**
	 * According to the type of the basic rotor a certain mapping is set
	 * @param name
	 */
	public void initialise(String name) 
	{
		if (name.equals("I"))
		{
			mapping = new int[]{4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
		}
		else if (name.equals("II"))
		{
			mapping = new int[]{0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
		}
		else if (name.equals("III"))
		{
			mapping = new int[]{1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14};
		}
		else if (name.equals("IV"))
		{
			mapping = new int[]{4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1};
		}
		else if (name.equals("V"))
		{
			mapping = new int[]{21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10};
		}
	}

	/**
	 * The position of the rotor is taken into account when making substitution
	 * @param numInArray, which is an integer representing a letter
	 * @return integer represented by the letter position in the mapping
	 */
	public int substitute(int numInArray) 
	{
		if(numInArray - getPosition() < 0)
		{
			return (mapping[numInArray - getPosition() + ROTORSIZE] + getPosition())%ROTORSIZE;
		}
		else
		{
			return (mapping[numInArray - getPosition()] + getPosition())%ROTORSIZE;
		}
	}
	
	/**
	 * Inverse mapping is used to change the integer passed to this method
	 * @param numInArray
	 * @return an integer with inverse mapping position
	 */
	public int substituteBack(int numInArray)
	{
		int inversedMapping[] = new int[mapping.length];
		for (int i=0; i<mapping.length; i++)
		{
			inversedMapping[mapping[i]] = i;
		}
		
		if(numInArray - getPosition() < 0)
		{
			return (inversedMapping[numInArray - getPosition() + ROTORSIZE] + getPosition())%ROTORSIZE;
		}
		else
		{
			return (inversedMapping[numInArray - getPosition()] + getPosition())%ROTORSIZE;
		}
	}
	
	/**
	 * The position of the rotor is advanced by one
	 * Since the rotors are circular the position is set back to 0 when a complete revoluton is performed
	 */
	public void rotate()
	{	 
		setPosition((getPosition()+1) % ROTORSIZE);
	}

	
}
