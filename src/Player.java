import gpdraw.DrawingTool;

public class Player {
	private int x, y;
	private double angle;
	
	
	public Player() {
		x = 0;
		y = 0;
		angle = 0.0;
	}
}


/*
Define each ray using slope
Each ray has a horizontal direction, and vertical direction can be determined from slope
^ I'm not sure if it's quicker to to store both horiz. and vert. direction or to store one and calculate one
Check vertical intersections by solving for y with given x values incremented as an int
Check horizontal intersections by solving for x given y

After deciding not to be an idiot I realize that delta(y) per x val is a fixed constant, and vice versa for y
Calc will be different for the first horiz. and vert. intersections, and the following intersections will be fixed
increments from the initial intersections

Check Vertical:
Store x direction as +1 or -1
Calc y step constant once per new rotation

 */