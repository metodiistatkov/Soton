/**
 * Plug class of the enigma machine
 * In the original machine plugs are used to connect any two sockets
 */
public class Plug 
{
	private char end1;
	private char end2;
	
	/**
	 * Constructor for Plug
	 * The plug connects two end sockets
	 * @param end1
	 * @param end2
	 */
	public Plug(char end1, char end2)
	{
		this.end1 = end1;
		this.end2 = end2;
	}
	
	/**
	 * Getter for the first end of the plug
	 * @return first end
	 */
	public char getEnd1()
	{
		return end1;
	}
	
	/**
	 * Getter for the second end of the plug
	 * @return second end
	 */
	public char getEnd2()
	{
		return end2;
	}
	
	/**
	 * Setter for the first end of the plug
	 * @param end1
	 */
	public void setEnd1(char end1)
	{
		this.end1 = end1;
	}
	
	/**
	 * Setter for the second end of the plug
	 * @param end2
	 */
	public void setEnd2(char end2)
	{
		this.end2 = end2;
	}
	
	/**
	 * A letter is entered 
	 * If there is a plug connected to that letter socket 
	 * The plugboard returns the value of the letter at the other end of the plug
	 * Otherwise returns the inputed letter
	 * @param letterIn
	 * @return the value of the letter at the other end of the plug
	 */
	public char encode(char letterIn)
	{
		if (letterIn == end1)
		{
			return end2;
		}
		else if (letterIn == end2)
		{
			return end1;
		}
		else
			return letterIn;
	}
	
	/**
	 * Checks whether a plug can be connected to the plugboard or whether one of the sockets is already in use
	 * @param plugin
	 * @return true if any of the sockets is in use or false if none is
	 */
	public boolean clashesWith(Plug plugin)
	{
		if (this.end1 == plugin.end1 || this.end1 == plugin.end2 ||  this.end2 == plugin.end1 || this.end2 == plugin.end2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
