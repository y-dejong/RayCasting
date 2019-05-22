
public class Player {
	private int x, y;
	private int angle;
	private Ray[] rays;

	private Level map;

	private DrawManager dm;

	public Player(Level l) { // Constructor
		x = 2;
		y = 3;
		angle = 45;
		rays = new Ray[DrawManager.FOV];
		map = l;
		dm = new DrawManager();
		for(int i = 0;i<rays.length;i++){
			rays[i] = new Ray(l);
		}
	}

	public void update() {
		this.castAll();
		dm.drawFrame();
	}



	public void castAll() {
		int cAngle = this.angle + DrawManager.FOV/2 - 1;
		for(int i = 0; i < DrawManager.FOV; i++) {
			double rayDist = rays[i].castRay(x, y, cAngle);
			System.out.println("Got dist " + rayDist + "from angle " + cAngle);

			dm.setLine(i,
					2-rayDist // TODO: convert from literaldist to viewdist
					);
			cAngle--;
		}
	}
}

/*
1. Take first two values using offset for one coord, and tan(x) for the other.
2. Find the lesser of the first two distances
3. Iterate over the horiz. and vert. collisions, starting with the lesser of the first two collisions.
	(i.e. if the first collision is horizontal, iter over horiz. then vert. then horiz. then vert. etc.)
4. Upon collision, find viewpoint distance (as opposed to raw distance along the ray) use crazy math

*CRAZY MATH*
literaldist is the literal distance between player and the collision
viewdist is the distance along the ray of playerAngle
theta is the angle between viewdist and literaldist


Angles and things:
Ray angle increases from right to left (counterclockwise)
Least value (furthest right) is playerAngle - fov/2

theta = totalangle + rayangle

viewdist = (literaldist)cos(theta)

Use wolfenstein math if sqrts are too slow, but they probably aren't
 */