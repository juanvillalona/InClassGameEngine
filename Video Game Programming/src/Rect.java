import java.awt.*;

public class Rect {
	
	//coordinates of the upper left corner
	int x;
	int y;
	
	//width and height
	int w;
	int h;

	boolean held = false;
	
	public Rect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public boolean overlaps (Rect r) {
		return (x < r.x + r.w) && (x + w > r.x) && (y < r.y + r.h) && (y + h > r.y);
	}
	
	public void grab() {
		held = true;
	}
	
	public void drop() {
		held = false;
	}
	
	public boolean contain(int mx, int my) {
		return (mx > x) && (mx < x+w) && (my > y) && (my < y+h);
	}

	public void draw (Graphics g) {
		g.drawRect(x, y, w, h);
	}
	
	public void resizeBy(int dw, int dh) {
		w += dw;
		h += dh;
	}
	
	public void moveBy (int dx, int dy) {
		
		x+= dx;
		y+= dy; 
	}
}
 