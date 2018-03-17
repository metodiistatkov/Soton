/**
 * A panel, which represents the Drawing Area
 */
import java.awt.*; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class DisplayPanel extends JPanel{
	//default number of sectors
	private int numberOfSectors = 12;

	PointDraw myPoints;
	//storing all points as single objects of many points, so that undo can remove last drawn line(without releasing the mouse)
	private List<ArrayList<PointDraw>> points = new ArrayList<ArrayList<PointDraw>>();
	//storing objects that have been removed by undo
	private List<ArrayList<PointDraw>> redoDrawing = new ArrayList<ArrayList<PointDraw>>();
	//default size of pen
	private int pointSize = 10;
	//default size of color
	private Color color = Color.WHITE;

	//creating flags for the toggle buttons
	private boolean reflect = false;
	private boolean showSectorLines = true;
	private boolean erase = false;

	//constructor, where the layout is set and mouse listener and mouse motion listener are created
	public DisplayPanel() {
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(400,400));
		this.setLayout(new FlowLayout());

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//when making new drawing remove savings for previous redos
				redoDrawing.clear();
				//making many points a single object in ArrayList
				points.add(new ArrayList<PointDraw>());
				//draw point when the mouse is clicked
				addPoint(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				//draw points when the mouse is dragged
				addPoint(e);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
	/**
	 * setter for the color
	 * @param c is setting the color
	 */
	public void setCurrentColor(Color c) {
		this.color = c;
	}

	/**
	 * adding points to the ArrayList of points
	 * @param e is used to get the mouse x and y coordinates
	 */
	private void addPoint(MouseEvent e) {
		myPoints = new PointDraw(e.getX(), e.getY(), color, pointSize);
		points.get(points.size()-1).add(myPoints);
		repaint();
	}

	/**
	 * setter for the number of sectors
	 * @param newnumberOfSectors is used to change the number of sectors shown
	 */
	public void setNumberOfSectors(int newnumberOfSectors) {
		this.numberOfSectors = newnumberOfSectors;
		repaint();
	}
	/**
	 * getter method
	 * @return the number of sectors created
	 */
	public int getNumberOfSectors() {
		return numberOfSectors;
	}
	/**
	 * enabling reflection
	 * @param reflect mode can be turned on(true) or off(false)
	 */
	public void reflect (boolean reflect) {
		this.reflect = reflect;
		repaint();
	}
	/**
	 * enabling sector lines
	 * @param showSectorLines is a mode, which can be turned on(true) or off(false)
	 */
	public void showSectorLines(boolean showSectorLines) {
		this.showSectorLines = showSectorLines;
		repaint();
	}


	/**
	 * setter for the current size of the pen
	 * @param size of the pen
	 */
	public void setCurrentSize(int size) {
		this.pointSize  = size;
	}
	/**
	 * enabling eraser mode
	 * @param erase can be on(true) or off(false)
	 */
	public void setErase(boolean erase) {
		this.erase  = erase;
	}

	/**
	 * checking if the eraser is on or off
	 * @return eraser state
	 */
	public boolean getErasorState() {
		return erase;
	}

	/**
	 * translating the lines's position
	 * @param radians 
	 * @param radius
	 * @return the new coordinates
	 */
	private Point2D pointAt(double radians, double radius) {
		double x = radius * Math.cos(radians);
		double y = radius * Math.sin(radians);

		return new Point2D.Double(x, y);
	}
	/**
	 * starting the draw of the sector lines from the center of the panel
	 * @param point where to start from
	 * @param to where to point to
	 * @return point created from the center
	 */
	private Point2D drawFromCenter(Point2D point, Point2D to) {
		Point2D newPoint = new Point2D.Double(point.getX(), point.getY());
		newPoint.setLocation(point.getX() + to.getX(), point.getY() + to.getY());
		return newPoint;
	}
	//everything that is drawn on the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;


		//Mathematical calculations 
		double startAngle = 0;  
		double delta = 360.0 / getNumberOfSectors();
		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;
		int radius = Math.min(centerX, centerY) * 2; // Go beyond the screen

		Point2D centerPoint = new Point2D.Double(centerX, centerY);
		double angle = startAngle;

		//eraser mode
		if (erase) {
			myPoints.setPointColor(this.getBackground());
		} 
		
		//drawing points in every sector simultaneously
		for (ArrayList<PointDraw> pointsAsOneDrawing: this.points) {
			for (PointDraw p: pointsAsOneDrawing) {                  
				for (int i = 0; i<numberOfSectors; i++) {
					g2d.setColor(p.getColor());
					g2d.rotate(Math.toRadians(360.d/numberOfSectors),centerX, centerY);
					g2d.fillOval((int)p.getX(), (int)p.getY(), p.getSize(), p.getSize());
					//draw reflected points
					if(reflect) {
						g2d.fillOval(2*centerX-(int)p.getX(), (int)p.getY(), p.getSize(), p.getSize());
					}
				}
			}
		} 

		//creating sector lines
		if(showSectorLines) {
			for (int i = 0; i < numberOfSectors; i++) {
				g2d.setColor(Color.WHITE);
				Point2D point = pointAt(Math.toRadians(angle), radius);
				point = drawFromCenter(point, centerPoint);
				g2d.draw(new Line2D.Double(centerPoint, point));
				angle += delta;
			}
		}
	}


	//undo last drawing action
	public void undo() {
		if(points.size() != 0) {
			redoDrawing.add(points.remove(points.size()-1));
		}
		repaint();

	}
	//redo last drawing action
	public void redo() {
		if (redoDrawing.size()!=0) {
			this.points.add(this.redoDrawing.remove(this.redoDrawing.size() - 1));
		}
		//points.add((PointDraw)undo.pop());
		repaint();

	}
	//clear the screen
	public void clear() {
		points.clear();
		repaint();
	}
	//screenshot of the screen
	public BufferedImage saveScreen(JPanel panel) {
		BufferedImage screenShot = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		panel.paint(screenShot.getGraphics());
		return screenShot;
	}
}

