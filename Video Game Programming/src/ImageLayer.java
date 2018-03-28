import java.awt.*;

public class ImageLayer {

	Image image;
	int x;
	int y;
	int z;
	
	public ImageLayer (String file, int x, int y, int z) {
		image = Toolkit.getDefaultToolkit().getImage(file);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void moveLeftBy(int dx) {
		x -=dx;
	}
	
	public void moveRightBy(int dx) {
		x += dx;
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(image, x/z, y, null);
		g.drawImage(image, x/z , y, null);

	}
}
