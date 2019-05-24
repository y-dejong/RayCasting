import javax.swing.*;
import java.awt.event.ActionEvent;

public class Player {
	private double x, y;
	private int angle, SPEED;
	private Ray[] rays;

	private Level map;

	private DrawManager dm;

	public Player(Level l) { // Constructor
		x = 2;
		y = 2;
		angle = 90;
		SPEED = 5;
		rays = new Ray[DrawManager.FOV];
		map = l;
		dm = new DrawManager();
		for(int i = 0;i<rays.length;i++){
			rays[i] = new Ray(l);
		}
	}

	public void update() {
		
		this.handleInput();
		if(angle > 359) angle = 0;
		this.castAll();
		dm.drawFrame();
	}



	public void castAll() {
		int cAngle = this.angle + DrawManager.FOV/2 - 1;
		for(int i = 0; i < DrawManager.FOV; i++) {
			double rayDist = rays[i].castRay(x, y, cAngle);
			
			double litDist = (rayDist/Ray.dStep)*Ray.xStep[Math.abs(this.angle-cAngle)];
			//double litDist = rayDist;
			
			dm.setLine(i,
					10-litDist // TODO: convert from literaldist to viewdist
					);
			cAngle--;
		}
	}
	public void handleInput() {
		
		dm.padPanel.getActionMap().put("handleKey", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				double tempX,tempY;
				switch(actionEvent.getActionCommand()) {
					
					// Movement
					case "w":
						tempX = x;
						tempY = y;
						x+=SPEED*Ray.xStep[angle];
						y+=SPEED*Ray.yStep[angle];
						if (map.get((int) x,(int) y) == 1) {
							x = tempX;
							y = tempY;
						}
						break;
					case "a":
						angle++;
						if (angle>360) {
							angle = 0 + angle;
						}
						
						break;
					case "s":
						tempX = x;
						tempY = y;
						x-=SPEED*Ray.xStep[angle];
						y-=SPEED*Ray.yStep[angle];
						if (map.get((int) x,(int) y) == 1) {
							x = tempX;
							y = tempY;
						}
						break;
					case "d":
						angle--;
						if (angle<0) {
							angle = 360 + angle;
						}
						break;
						
						// Developer shortcut
					case "p":
						System.out.print(angle + ": ");
						dm.printLines();
						break;
						
						// Default
					default:
						System.out.println("Unhandled action: " + actionEvent.getActionCommand());
						break;
					
				}
			}
		});
		
		// Movement
		dm.padPanel.getInputMap().put(KeyStroke.getKeyStroke("W"), "handleKey");
		dm.padPanel.getInputMap().put(KeyStroke.getKeyStroke("A"), "handleKey");
		dm.padPanel.getInputMap().put(KeyStroke.getKeyStroke("S"), "handleKey");
		dm.padPanel.getInputMap().put(KeyStroke.getKeyStroke("D"), "handleKey");
		
		// Developer shortcuts
		dm.padPanel.getInputMap().put(KeyStroke.getKeyStroke("P"), "handleKey");
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