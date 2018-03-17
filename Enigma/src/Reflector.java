/**
 * The reflector is a type of rotor
 */
public class Reflector extends Rotor 
{
	/**
	 *Constructor for class Reflector
	 *Takes the type as a string 
	 * @param name
	 */
	public Reflector(String name)
	{
		super(name);
	}
	
	/**
	 * According to the type of the reflector a certain mapping is set
	 * @param name
	 */
	public void initialise(String name) 
	{
		if (name.equals("ReflectorI"))
		{
			mapping = new int[]{24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19};
		}
		else if (name.equals("ReflectorII"))
		{
			mapping = new int[]{5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11};
		}
		
	}

	/**
	 * Returns the number corresponding to that element of the array
	 * @param numInArray
	 */
	public int substitute(int numInArray) 
	{
		return mapping[numInArray];
		
	}
	
	
}
