/**
 * All tests are called here
 */
public class Main 
{	
	public static void main(String[] args)
	{
		EnigmaMachine enigma = new EnigmaMachine();
		enigma.start1();
		enigma.start2();
		enigma.start3();
		enigma.start4();
		System.out.println();
		enigma.start5();
		System.out.println();
		System.out.println("--------------EXTENSION ON BOMBE--------------");
		System.out.println();
		enigma.start6();
	}
}
