import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite extends Rect{
	
	int x;
	int y;
	Animation[] animation;
	
	boolean selected = false;
	boolean moving = false;
	
	static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int UP = 2;
	static final int DOWN = 3;

	int pose = LEFT;
	
	public Sprite(int x, int y, String file, String[] action, int count, int duration) {
			super(x, y, 20, 50);
			animation = new Animation[action.length];
			
			for(int i = 0; i < animation.length; i++) {
				animation[i] = new Animation(file + action[i] + "_", count, duration);
			}
	}
	
	public void moveUpBy(int dy) {
		y -= dy;
		moving = true;
		pose = UP;
	}
	
	public void moveDownBy(int dy) {
		y += dy;
		moving = true;
		pose = DOWN;
	}
	
	public void moveLeftBy(int dx) {
		x -= dx;
		moving = true;
		pose = LEFT;
	}
	
	public void moveRightBy(int dx) {
		x += dx;
		moving = true;
		pose = RIGHT;
	}
	
	
	public void draw(Graphics g) {
		
		if(moving){
			g.drawImage(animation[pose].nextImage(),x, y, null);
		}
		else{
			g.drawImage(animation[pose].stillImage(), x, y, null);
			moving = false;
		}
		
		if(selected) g.drawLine(x, y, x+w, y);
		}
	
}
