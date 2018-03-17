/**
 * Main window, where I add all the panels and create the design
 */
import java.awt.*;     
import javax.swing.*;

public class GUI {

	
	//constructor
	public GUI() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Digital Doilies");
		
		//instances of the different panels
		DisplayPanel display = new DisplayPanel();
		
		GalleryPanel gallery = new GalleryPanel();
		
		ControlPanel control = new ControlPanel(display, gallery);
		
		//setting the layout of the frame
		Container panel = window.getContentPane();
		panel.setLayout(new BorderLayout());
		panel.add(display, BorderLayout.CENTER);
		panel.add(control, BorderLayout.WEST);
		panel.add(gallery, BorderLayout.SOUTH);
		
		window.setSize(new Dimension(1200,1080));;
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
}
