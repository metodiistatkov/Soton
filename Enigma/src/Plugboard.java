import java.util.HashSet;
/**
 * The plugboard is the initial encoding mechanism of the machine
 * It has 26 sockets - one for each letter of the alphabet
 */
public class Plugboard 
{
	/**
	 * Creating a hashset, where the plugs will be stored
	 */
	private HashSet<Plug> plugs;
	
	/**
	 * Constructor for Plugboard, where the hashset is instantiated
	 */
	public Plugboard()
	{
		plugs = new HashSet<Plug>();
	}
	
	/**
	 * Initially checks if there are any plugs added
	 * If not, then plugs are added
	 * If plugs are already created checks if two ends aren't used
	 * If they are not, then a new Plug connecting them is created
	 * @param end1
	 * @param end2
	 * @return true
	 */
	public boolean addPlug(char end1, char end2)
	{
		Plug newPlug = new Plug(end1, end2);
		if(plugs.size() == 0)
		{
			plugs.add(newPlug);
		}
		else
		{
		  boolean isAdded = false;
		  for(Plug plug: plugs)
		  {
			if (plug.clashesWith(newPlug))
			{
				isAdded = true;
			}
			
		  }
		  
		  if(!isAdded)
		  {
			  plugs.add(newPlug);
		  }
		}
		
		return true;
	}

	/**
	 * Getter for the number of plugs
	 * @return the number of plugs
	 */
	public int getNumPlugs()
	{
		return plugs.size();
	}
	
	/**
	 * Removes all plugs from the plugboard
	 */
	public void clear()
	{
		plugs.clear();
	}
	
	/**
	 * If there is an appropriately connected plug the character entered is encoded
	 * Otherwise the character that was passed is returned
	 * @param newChar
	 * @return the character passed
	 */
	public char substitute(char newChar)
	{
		for (Plug plug: plugs)
		{
			newChar = plug.encode(newChar);
		}
		return newChar;
	}

}
