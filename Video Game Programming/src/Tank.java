import java.awt.*;

public class Tank extends PolygonModel{
	
	
	public Tank (int x, int y, int A) {
		super(x, y, A);
	}
	
	public int[][] struct_x() {
		final int[][] struct_x =  { 
				{ 35, 35, -35, -35}, 
				{ 5, 5, -5, -5},
				{ 30, 30, 5, 5 },
				{ 27, 27, -27, -27 },
				{ 27, 27, -27, -27 },

		};
		return struct_x;
	}
	
	public int[][] struct_y() {
		final int[][] struct_y = 
			{ 
				{ -25, 25, 25, -25 }, 
				{ -12, 12, 12, -12},
				{ -3, 3, 3, -3 },
				{ 25, 32, 32, 25 }, 
				{ -26, -32, -32, -25 } 
			};
		return struct_y;			
	}
}

	