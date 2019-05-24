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
		//	RAYWIDTH = 1;

	public SketchPadPanel padPanel;
	private SketchPad pad;
	private DrawingTool pen;
	private Color topC, botC, frontC, backC;

	double[] lines; // One int will determine height and shade on gradient

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

		lines = new double[FOV];
	}

	public void setLine(int index, double val) {
		lines[index] = val;
	}

	public void drawFrame() {
		pen.up();
		pen.move(0, HEIGHT/4);
		pen.setColor(topC);
		pen.down();
		pen.fillRect(WIDTH, HEIGHT/2);
		pen.up();
		pen.move(0, -HEIGHT/4);
		pen.setColor(botC);
		pen.down();
		pen.fillRect(WIDTH, HEIGHT/2);
		pen.up();
		pen.move((-1 * WIDTH/2) + (RAYWIDTH/2+1),0); //center of leftmost ray
		for(double i : lines) {
			pen.move(pen.getXPos(), 30*i);
			pen.setDirection(270);
			int color = ((int)(i*25));
			if (color<0) color = 0;
			if(color>255) color = 255;
			
			pen.setColor(new Color(color,color,color));
			pen.down();
			pen.forward(60*i);
			pen.up();
			pen.move(pen.getXPos() + RAYWIDTH, 0);
		}
	}
	
	public void printLines() {
		
		for(double i : lines) {
			System.out.println(i + ", ");
		}
		System.out.println();
	}
/*
	public void handleInput() {

		this.padPanel.getActionMap().put("handleKey", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				switch(actionEvent.getActionCommand()) {

				case "w":
					
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
	**/
}
