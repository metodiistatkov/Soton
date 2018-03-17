import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Reading from a file
 * Encoding the text read
 * Writing the encoded text into a new file
 */
public class EnigmaFile 
{	
	private BufferedReader reader;
	private BufferedWriter writer;
	//array list where the encoded character from the file is stored
	private ArrayList <String>stringEncode = new ArrayList<String>();
	//creating an enigma machine
	private EnigmaMachine enigma = new EnigmaMachine();
		
	
	/**
	 * Constructor for class EnigmaFile
	 * Reads from a text file
	 */
	public EnigmaFile()
	{
		try 
		{
			reader = new BufferedReader(new FileReader("read.txt"));
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage() + "The file cannot be found");
		
		}
	}
	
	/**
	 * Gets every next line from a text file
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
	 * While there is a next line this line is encoded 
	 * The line is encoded by using the enigma machine's encodeLetter method
	 */
	public void encodeTextFromFile() 
	{
		//assigning setUps to the enigma machine
		enigma.addPlug('A', 'M');
		enigma.addPlug('G', 'L');
		enigma.addPlug('E', 'T');
		enigma.addRotor(new BasicRotor("I",6), 0);
		enigma.addRotor(new BasicRotor("II",12), 1);
		enigma.addRotor(new BasicRotor("III",5), 2);
		enigma.addReflector(new Reflector("ReflectorI"));
		
		EnigmaFile file = new EnigmaFile();
		String line = file.getLine();
		
		while(line != null)
		{
			for(char character: line.toCharArray()) 
			{
				//the encodded character is stored in an instance arraylist
				stringEncode.add(String.valueOf(enigma.encodeLetter(character)));
			}
			//writes the output as a single string
			writeTo(String.join("", stringEncode));
			stringEncode.clear();
			enigma.clearPlugboard();
			line = file.getLine();
		}
	}
	
	/**
	 * Writing the encoded message from the original file into a new file
	 * @param infoWrite - the encoded string that is read from the text file
	 */
	public void writeTo(String infoWrite)
	{
		try 
		{
			writer = new BufferedWriter(new FileWriter("write.txt", true));
			writer.write(infoWrite);
			writer.newLine();
			writer.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage() + "Error writing to file");
		}
	}
}
