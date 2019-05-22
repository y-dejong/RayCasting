import java.awt.*;
import java.util.concurrent.TimeUnit;

import gpdraw.*;

public class App {

	Level level1;
	Player p1;
	boolean quit = false;

	private void trigvalsVisualTest() {
		DrawingTool p = new DrawingTool(0);
		for (int i = 0; i < 360; i++) {
			p.home();
			p.down();
			p.setColor(Color.BLUE);
			p.fillRect(500, 500);
			p.setDirection(0);
			p.setColor(Color.BLACK);
			p.drawString(String.valueOf(i));
			p.forward(Ray.xStep[i]*500);
			p.turnLeft();
			p.forward(Ray.yStep[i]*500);
			p.turnLeft(90+i);
			p.forward(Ray.dStep*500);
			p.up();
			try { TimeUnit.MILLISECONDS.sleep(16); }
			catch (InterruptedException e) { System.out.println("InterruptedException while sleeping in trigvals test"); }

		}
	}

	private int load() {

		//trigvalsVisualTest();
		
		System.out.println(System.getProperty("os.name"));
		level1 = new Level("C:\\Users\\APCS\\Documents\\RayCasting\\data\\1.map");
		p1 = new Player(level1);

		return 0;
	}

	private void update() {

		p1.update();

		try {
			TimeUnit.MILLISECONDS.sleep(16);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException while sleeping in main loop");
		}
	}

	private void unload() {

	}

	public static void main(String[] args) {

		App a = new App();

		if(a.load() != 0) {
			System.out.println("Whoops");
			return;
		}


		while(!a.quit) {
			a.update();

		}

		a.unload();
	}
}
