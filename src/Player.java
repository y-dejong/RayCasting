import gpdraw.DrawingTool;

public class Player {
	private int x, y;
	private double angle;
	
	Level currentLevel;
	
	public Player() {
		x = 0;
		y = 0;
		angle = 0.0;
	}
	
	public double castRay(int angle) {
		
		return -1;
	}
}

/*
1. Take first two values using offset for one coord, and tan(x) for the other.
2. Find the lesser of the first two distances
3. Iterate over the horiz. and vert. collisions, starting with the lesser of the first two collisions.
	(i.e. if the first collision is horizontal, iter over horiz. then vert. then horiz. then vert. etc.)
4. Upon collision, find viewpoint distance (as opposed to raw distance along the ray) use crazy math

*CRAZY MATH*
viewdist is the literal distance between player and the collision
playerdist is the distance along the ray of playerAngle
theta is the angle between viewdist and playerdist


Angles and things:
Ray angle increases from right to left
Least value (furthest right) is playerAngle - fov/2

theta = totalangle + rayangle

playerdist = (viewdist)cos(theta)

Use wolfenstein math if sqrts are too slow, but they probably aren't
 */