import java.awt.Graphics;

public abstract class PolygonModel {

	double x; // x position
	double y; // y position
	int A;    // Angle
	static final int radius = 60;
	
	final int[][] struct_x = struct_x();
	final int[][] struct_y = struct_y();
	
	public abstract int[][] struct_x();
	public abstract int[][] struct_y();
	

	/*
	 * Where would you like this tank to be born? The origin should be unique
	 * for the tanks and the position it's born in
	 */
	public PolygonModel(int x, int y, int A) {
		this.x = x;
		this.y = y;
		this.A = A;
	}

	public void draw(Graphics g) {
		// used to translate the whole tank to another part of the screen
		int[] xp = new int[3];
		int[] yp = new int[3];

		double sinA = lookup.sin[A];
		double cosA = lookup.cos[A];

		for (int poly = 0; poly < struct_x.length; poly++) {
			System.out.println(poly);

			for (int vert = 0; vert < struct_x[poly].length; vert++) {
				xp[vert] = (int) (struct_x[poly][vert] * cosA - struct_y[poly][vert] * sinA + x);
				yp[vert] = (int) (struct_x[poly][vert] * sinA + struct_y[poly][vert] * cosA + y);
			}
			g.drawPolygon(xp, yp, 3);
		}

	}

	public void moveBy(int dx, int dy) {
		x += dx;
		y += dy;

	}

	/*
	 * public void rotateBy (int dA) { //adding the delta angle to the already
	 * set A angle A+= dA; }
	 */

	public void rotateLeftBy(int dA) {

		A -= dA;
		if (A < 0) {
			A += 360;
		}
	}

	public void rotateRightBy(int dA) {

		A += dA;
		if (A >= 360) {
			A -= 360;
		}
	}

	public void moveForwardBy(int distance) {
		x += distance * Math.cos(A * Math.PI / 180);
		y += distance * Math.sin(A * Math.PI / 180);
	}

	public void moveBackwardBy(int distance) //
	{
		x -= distance * Math.cos(A * Math.PI / 180); // move in the x direction
														// * the distance
		y -= distance * Math.sin(A * Math.PI / 180); // move in the y direction
														// * the distance

	}

	public boolean contains(int mx, int my) {
		double dist2 = (mx - x) * (mx - x) + (my - y) * (my - y);

		return (dist2 < radius * radius);
	}

}
