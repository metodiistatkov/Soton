import java.util.ArrayList;
/**
 * The mechanism used to decode the enigma messages
 */
public class Bombe 
{
	//creating an enigma machine
	private EnigmaMachine enigma = new EnigmaMachine();
	//an arraylist to store information
	private ArrayList<String> store = new ArrayList<String>();
	
	/**
	 * Configuring what options there are for the missing plugs 
	 * Going through all possible cases and investigates which contain the word given as a hint
	 */
	public void challenge1()
	{
		//all possible options for the missing plugs
		char[] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		//looping through all possible combinations
		for (char unknownPlug1: letter)
		{
			for (char unknownPlug2: letter)
			{
				String containWord = "ANSWER";
				
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
				
				//checking if the hint is contained in the combinations and also ensures that the plugs don't overlap
				if(list.contains(containWord) && unknownPlug1 != 'D' && unknownPlug2 != 'S')
				{
					//displaying the desired outcomes with the configuration of each
					System.out.println(list + " " + "Plugs:" + "[D-" + unknownPlug1 + "]" + "[S-" + unknownPlug2 + "]");
				}
				
				//reseting the storage space and the plugboard of the enigma before the next check
				store.clear();
				enigma.clearPlugboard();
			}
		}
	}
	
	/**
	 * Configuring what options there are for the missing initial rotor positions 
	 * Going through all possible cases and investigates which contain the word given as a hint
	 */
	public void challenge2()
	{
		//all possible options for the missing positions
		int[] positions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
		
		//looping through all possible combinations
		for (int position1: positions)
		{
			for (int position2: positions)
			{
				for (int position3: positions)
				{
					String containWord = "ELECTRIC";
					
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
					
					//checking if the hint is contained in the combinations
					if(list.contains(containWord))
					{
						//displaying the desired outcomes with the configuration of each
						System.out.println(list);
						System.out.println("Initial position of first rotor:" + position1 + "; " + "Initial position of second rotor:" + position2 + "; " + "Initial position of third rotor:" + position3);
					}
					//reseting the storage space and the plugboard of the enigma before the next check
					store.clear();
					enigma.clearPlugboard();
				}
			}
		}
	}
	
	/**
	 * Configuring what options there are for the missing initial rotor positions 
	 * Going through all possible cases and investigates which contain the word given as a hint
	 */
	public void challenge3()
	{
		//all possible options for the missing rotor types
		String[] types = {"I", "II", "III", "IV", "V"};
		
		//looping through all possible combinations
		for (String type1: types)
		{
			for(String type2: types)
			{
				for(String type3: types)
				{
					String containWord = "JAVA";
					
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
					
					//checking if the hint is contained in the combinations
					if(list.contains(containWord))
					{
						System.out.println(list);
						System.out.println("Type of first rotor:" + type1 + "; " + "Type of second rotor:" + type2 + "; " + "Type of third rotor:" + type3);
					}
					
					//reseting the storage space and the plugboard of the enigma before the next check
					store.clear();
					enigma.clearPlugboard();
				}
			}
		}
	}
}
