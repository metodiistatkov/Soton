/**
 * A type of rotor that rotates
 */
public class TurnoverRotor extends BasicRotor
{

	private int turnoverPosition;
	private BasicRotor nextRotor;
	
	/**
	 * Constructor for TurnoverRotor
	 * Depending on the type of the turnover rotor a certain turnover position is set to it 
	 * @param name
	 * @param position
	 */
	public TurnoverRotor(String name, int position)
	{
		super(name);
		super.setPosition(position);
		
		if(name.equals("I"))
		{
			turnoverPosition = 24;
		}
		else if(name.equals("II"))
		{
			turnoverPosition = 12;
		}
		else if(name.equals("III"))
		{
			turnoverPosition = 3;
		}
		else if(name.equals("IV"))
		{
			turnoverPosition = 17;
		}
		else if(name.equals("V"))
		{
			turnoverPosition = 7;
		}
	}
	
	/**
	 * Setter for the next rotor, after the rotation happens
	 * @param nextRotor
	 */
	public void setNextRotor(BasicRotor nextRotor)
	{
			this.nextRotor = nextRotor;
	}
	
	/**
	 * Rotating with one position
	 * If the new position is the same as the turnover position, the rotor on the right in the machine is rotated with one position
	 */
	public void rotate()
	{
		int newPosition = (getPosition()+1) % ROTORSIZE;
		setPosition(newPosition);
		if (newPosition == turnoverPosition && this.nextRotor != null)
		{
			nextRotor.rotate();
		}
	}
}
