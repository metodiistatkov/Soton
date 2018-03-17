/**
 * A class where I create my own Point object
 */
import java.awt.Color;

public class PointDraw {
	//properties of the point
	private Color color = Color.BLUE;
	private int x;
	private int y;
	private int pointSize = 10;
	/**
	 * Constructor
	 * @param x x-coordinates
	 * @param y y-coordinates
	 * @param color the desired color
	 * @param pointSize the desired size of the pen
	 */
	public PointDraw(int x, int y, Color color, int pointSize) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.pointSize = pointSize;
	}
	/**
	 * getter
	 * @return x-coordinates
	 */
	public int getX() {
		return x;
	}
	/**
	 * getter
	 * @return y-coordinates
	 */
	public int getY() {
		return y;
	}
	/**
	 * getter
	 * @return color of the point
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * setter for the color of the point
	 * @param color 
	 */
	public void setPointColor(Color color) {
		this.color = color;
	}
	/**
	 * getter 
	 * @return the size of the point
	 */
	public int getSize() {
		return pointSize;
	}
	
}
