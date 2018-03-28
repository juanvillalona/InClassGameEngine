import java.awt.*;

public class UFO extends PolygonModel{
	
	
	public UFO (int x, int y, int A) {
		super(x, y, A);
	}
	
	public int[][] struct_x() {
		final int[][] struct_x =  
			{ 
				{0, 20, -20},
				{0, 20, -20},
		};
		return struct_x;
	}
	
	public int[][] struct_y() {
		final int[][] struct_y = 
			{ 
				{30, -20, -20},
				{30, -20,  20},
			};
		System.out.println(struct_y.length);
		
		return struct_y;			
	}
}
	