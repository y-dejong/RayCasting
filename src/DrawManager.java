import gpdraw.*;

import java.awt.Color;

public class DrawManager {
	
	private static final int
			WIDTH = 800,
			HEIGHT = 600,
			FOV = 60,
			RAYCOUNT= 160,
			RAYWIDTH = WIDTH/RAYCOUNT;
	
	private SketchPad pad;
	private DrawingTool pen;
	private Color topC, botC, frontC, backC;
	
	int[] lines; // One int will determine height and shade on gradient
	
	public DrawManager() {
		this(new Color(0x3030FF),
				new Color(0x551111),
				new Color(0xFFFFFF),
				new Color(0x000000));
	}
	
	public DrawManager(Color topC, Color botC) {
		this(topC,
				botC,
				new Color(0xFFFFFF),
				new Color(0x000000));
	}
	
	public DrawManager(Color topC, Color botC, Color frontC, Color backC) {
		pad = new SketchPad(800, 600);
		pen = new DrawingTool(pad);
		pen.setWidth(RAYWIDTH);
		this.topC = topC;
		this.botC = botC;
		this.frontC = frontC;
		this.backC = backC;
		
		lines = new int[RAYCOUNT];
	}
	
	public void setLine(int index, int val) {
		lines[index] = val;
	}
	
	public void drawFrame() {
		pen.up();
		pen.move((-1 * WIDTH/2) + (RAYWIDTH/2+1),0); //center of leftmost ray
		for(int i : lines) {
			pen.move(pen.getXPos(), i/2);
			pen.setDirection(270);
			pen.down();
			pen.forward(i);
			pen.up();
			pen.move(pen.getXPos() + RAYWIDTH, 0);
		}
	}
}
