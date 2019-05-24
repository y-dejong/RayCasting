public class Ray {

	public static final double[] xStep, yStep;
	public static final double dStep;

	private Level map;

	public Ray(Level map) {
		this.map = map;
	}

	public double castRay(double x, double y, int angle){
		//Given the angle of the ray, not the angle of the player
		//returns the literal distance traveled by the ray fired
		
		if(angle < 0) angle = 360 + angle;
		// Angle is negative, so adding the angle subtracts from 360
		if(angle >= 360) angle -= 360;
		
		double rayX = x;
		double rayY = y;
		int intCount=0; //tracks the amount of intervals traveled by ray

		while (map.get((int)rayX, (int)rayY) == 0){
			//System.out.println(rayX + " " + rayY);

			rayX+=xStep[angle];
			rayY+=yStep[angle];
			intCount++;
			if(rayX < 0 || rayY < 0 ||
					rayX > map.xSize() || rayY > map.ySize()) return 0;
		}

		return dStep*(double) intCount;
	}

	//INIT trigvals
	static {

		double[] _xStep = new double[360],
				_yStep = new double[360];


		double divisor = 100;

		for(int i = 0; i < 90; i++) {
			_xStep[i] = Math.cos(i*Math.PI/180)/divisor;
			_yStep[i] = Math.sin(i*Math.PI/180)/divisor;
		}

		_xStep[90] = 0;
		_yStep[90] = .01;

		for (int i = 0; i < 90; i++) {
			_xStep[i+90] = _xStep[90-i]*-1;
			_yStep[i+90] = _yStep[90-i];
		}

		_xStep[180] = -.01;
		_yStep[180] = 0;

		for (int i = 0; i < 90; i++) {
			_xStep[i+180] = _xStep[i]*-1;
			_yStep[i+180] = _yStep[i]*-1;
		}

		_xStep[270] = 0;
		_yStep[270] = -.01;

		for (int i = 0; i < 90; i++) {
			_xStep[i+270] = _xStep[90-i];
			_yStep[i+270] = _yStep[90-i]*-1;
		}

		xStep = _xStep;
		yStep = _yStep;
		dStep = 1/divisor;
	}
}
