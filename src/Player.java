
public class Player {
	private int x, y;
	private double angle;
	private int[] dStep, xStep, yStep;
	private int[][] map;

	public Player(int[][] floor, int[] d, int[] xs, int[] ys) { // Constructor
		x = 0;
		y = 0;
		angle = 0.0;
		dStep = d;
		xStep = xs;
		yStep = ys;
		map = floor;
	}	}

	public double cast(int angle){
		//Given the angle of the ray, not the angle of the player
		//returns the distance traveled by the ray fired
		double u = x;
		double v = y;
		int intCount=0; //tracks the amount of intervals traveled by ray
		while (floor[(int) u][(int) v]==0){
			u+=xStep[angle];
			v+=yStep[angle];
			intCount++;
		}
		return intCount*dStep[angle];
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