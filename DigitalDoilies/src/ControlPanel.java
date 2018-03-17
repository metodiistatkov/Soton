/**
 * Panel with all the buttons and their functionality 
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel{
	//creating labels for showing the names of some fields
	private JLabel sizePen = new JLabel("Size of Pen");
	private JLabel numSectors = new JLabel("Number of Sectors");
	private JLabel galleryview = new JLabel("Gallery : ");

	private DisplayPanel displayPanel;
	private GalleryPanel gallery;
	
	/**
	 * constructor, where all the buttons and labels are added and the layout of the panel is set
	 * @param displayPanel instance of DisplayPanel class
	 * @param gallery  instance of GalleryPanel class
	 */
	public ControlPanel(DisplayPanel displayPanel, GalleryPanel gallery) {
		this.setBackground(null);
		this.setPreferredSize(new Dimension(250,600));
		this.setLayout(new GridLayout(17,1));
		this.clearDisplay();
		this.changeColor();
		this.add(sizePen);
		this.changeSize();
		this.add(numSectors);
		this.createSlider();
		this.add(new JLabel(" "));
		this.showSectors(); 
		this.reflect();
		this.undo();
		this.redo();
		this.eraser();
		this.save();
		this.removeDoily();
		this.add(galleryview);
		galleryview.setFont(new Font("Serif", Font.BOLD, 24));
		galleryview.setHorizontalAlignment(getWidth()/2);;
		this.displayPanel = displayPanel;
		this.gallery = gallery;
	}
	
	//clearing the display
	private void clearDisplay() {
		JButton clearDisplay = new JButton("Clear Display");
		clearDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayPanel.clear();
			}
		});
		this.add(clearDisplay);
	}
	
	//changing the color of the pen
	private void changeColor() {
		JButton changeColor = new JButton("Change Colour");
		changeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (displayPanel.getErasorState()) {
					JOptionPane.showMessageDialog(null, "You cannot change the color while in eraser mode.");
				}
				else {
				JColorChooser jcc = new JColorChooser();
				Color c = jcc.showDialog(null, "Select Color", Color.BLACK);
				displayPanel.setCurrentColor(c);
				}
			}
		});

		this.add(changeColor);
	}
	
	//changing the size of the pen
	private void changeSize() {
		JSpinner changeSize = new JSpinner();
		changeSize.setName("Size of Pen");
		changeSize.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				displayPanel.setCurrentSize(Integer.parseInt(changeSize.getValue().toString()));
			}});
		this.add(changeSize);
	}
	
	//Either displaying the sector lines or hiding them
	private void showSectors() {
		JCheckBox showSectors = new JCheckBox("Toggle Sector Lines");
		showSectors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showSectors.isSelected()) {
					displayPanel.showSectorLines(false);
				}
				else {
					displayPanel.showSectorLines(true);
				}
			}
		});
		this.add(showSectors);
	}
	
	//Can show reflected points if wanted
	private void reflect() {
		JCheckBox reflect = new JCheckBox("Toggle Reflection");
		reflect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reflect.isSelected()) {
					displayPanel.reflect(true);
				}
				else {
					displayPanel.reflect(false);
				}
			}
		});
		this.add(reflect);
	}
	
	//button for implementing undo
	private void undo() {
		JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayPanel.undo();
			}
		});
		this.add(undo);
		repaint();
	}
	
	//button for implementing redo
	private void redo() {
		JButton redo = new JButton("Redo");
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayPanel.redo();
			}
		});
		this.add(redo);
	}
	
	//button for implementing eraser
	private void eraser() {
		JCheckBox eraser = new JCheckBox("Eraser Toggled");
		eraser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						if(eraser.isSelected()) {
							displayPanel.setErase(true);
						}
						else {
							displayPanel.setErase(false);
						}
			}
		});
		this.add(eraser);
	}
	
	//button for making a screenshot of the Drawing area and saving it in a gallery
	private void save() {
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gallery.getPhoto().size() < 12) {
					BufferedImage screenShot = displayPanel.saveScreen(displayPanel);
					BufferedImage resizedScreenShot = resize(screenShot, 150, 150);
					gallery.addPhoto(new JLabel(new ImageIcon(resizedScreenShot)));
					gallery.revalidate();
				}
				else {
					JOptionPane.showMessageDialog(null, "You cannot save more than 12 doilies.");
				}
			}
		});
		this.add(save);
	} 
	
	/**
	 * A method that resizes the screenshot of the Drawing Area to a suitable(smaller) size
	 * @param image choose the image to be resized
	 * @param width set the desired width
	 * @param height set the desired height
	 * @return resized image
	 */
	public  BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage saved = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) saved.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return saved;
	}
	
	//deleting a screenshot from the gallery
	private void removeDoily() {
		JButton remove = new JButton("Remove Doily");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String index = JOptionPane.showInputDialog( "Delete Doily at selected index (index 0 removes first photo; 1 second and so on...)");

				if (Integer.parseInt(index) <= gallery.getPhoto().size() && Integer.parseInt(index) >= 0 ) {
					gallery.deletePhoto(Integer.parseInt(index));
					gallery.revalidate();
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no such doily.");
				}
			}
		});
		this.add(remove);
	}
	
	//creating a slider component for the number of sector lines
	private void createSlider() {
		JSlider changeNumberOfSectors = new JSlider(JSlider.HORIZONTAL,0,50,12);
		changeNumberOfSectors.setMajorTickSpacing(5);
		changeNumberOfSectors.setPaintTicks(true);
		changeNumberOfSectors.setPaintLabels(true);

		changeNumberOfSectors.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				displayPanel.setNumberOfSectors(changeNumberOfSectors.getValue());
			}});
		this.add(changeNumberOfSectors);
	}

}
