/**
 *  An abstract class that defines the basic properties and methods
 *  All the other rotor classes will use those
 */
public abstract class Rotor 
{
	/**
	 * Properties of the rotors
	 */
	private String name;
	private int position;
	protected int mapping[];
	protected final int ROTORSIZE = 26;
	
	/**
	 * Constructor for class Rotor
	 * Takes the type as a string
	 * @param name
	 */
	public Rotor (String name)
	{
		initialise(name);
	}
	
	/**
	 * Setter for the position of the rotor
	 * @param position
	 */
	public void setPosition(int position)
	{
		this.position = position;
	}
	
	/**
	 * Getter for the position of the rotor
	 * @return the position
	 */
	public int getPosition()
	{
		return position;
	}
	
	/**
	 * Abstract function that is  by the other rotor classes
	 * @param name
	 */
	public abstract void initialise(String name);
	
	/**
	 * Abstract function that is  by the other rotor classes
	 * @param numInArray
	 * @return an integer representing a letter
	 */
	public abstract int substitute(int numInArray);

}
