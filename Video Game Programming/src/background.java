import java.awt.*;

public class background {
	
	int x;
	int y;
	Image myImage;
	int yPos = 800;
	int xPos = 800;

	public background(int x, int y, String name) {
	
		this.x = x;
		this.y = y;
		myImage = Toolkit.getDefaultToolkit().getImage(name + ".jpg");
	}


	public void draw(Graphics g)
	{
		g.drawImage(myImage, x, y, xPos, yPos, null);
	}
}
