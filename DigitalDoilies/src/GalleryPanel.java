/**
 * A panel, which has the function of a gallery and displays the saved photos 
 */
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GalleryPanel extends JPanel{
	//storing the saved images here
	public ArrayList<JLabel> photos;
	//constructor, where the layout of the panel is set
	public GalleryPanel() {
		super(); 
		photos = new ArrayList<JLabel>();
		this.setPreferredSize(new Dimension(400,400));
		this.setLayout(new GridLayout(2,6));
		this.setBackground(null);
		setVisible(true);
	}
	/**
	 * adding a photo to the gallery
	 * @param photo
	 */
	public void addPhoto(JLabel photo) {
		photos.add(photo);
		this.add(photo);
	}
	/**
	 * deleting a photo from the gallery
	 * @param i
	 */
	public void deletePhoto(int i) {
		photos.remove(photos.get(i));
		this.remove(i);
		repaint();
	}
	/**
	 * getter for the photos
	 * @return photos that are in the gallery
	 */
	public ArrayList<JLabel> getPhoto(){
		return photos;
	}
	
	
	
	

}
