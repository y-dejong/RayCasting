import gpdraw.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class DrawManager {
	
	public static final int FOV = 60;
	private static final int
			WIDTH = 800,
			HEIGHT = 600,
			RAYWIDTH = WIDTH/FOV;
	
	private SketchPadPanel padPanel;
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
		
		padPanel = new SketchPadPanel(0);
		padPanel.setSize(800, 600);
		pad = new SketchPad(padPanel);
		pad.setSize(800, 600);
		pen = new DrawingTool(pad);
		pen.setWidth(RAYWIDTH);
		this.topC = topC;
		this.botC = botC;
		this.frontC = frontC;
		this.backC = backC;
		
		lines = new int[FOV];
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
	
	public void handleInput() {
		
		pen.forward(1);
		this.padPanel.getActionMap().put("handleKey", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				switch(actionEvent.getActionCommand()) {
				
				case "w":
					pen.setDirection(90);
					break;
				case "a":
					pen.turnLeft(5);
					break;
				case "s":
					pen.setDirection(270);
					break;
				case "d":
					pen.turnRight(5);
					break;
				default:
					System.out.println("Unhandled action: " + actionEvent.getActionCommand());
					
				}
			}
		});
		
		this.padPanel.getInputMap().put(KeyStroke.getKeyStroke("W"), "handleKey");
		this.padPanel.getInputMap().put(KeyStroke.getKeyStroke("A"), "handleKey");
		this.padPanel.getInputMap().put(KeyStroke.getKeyStroke("S"), "handleKey");
		this.padPanel.getInputMap().put(KeyStroke.getKeyStroke("D"), "handleKey");
	}
}
