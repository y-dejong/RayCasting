import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import gpdraw.*;

public class App {
	
	public static void main(String[] args) {
		
		double[] xStep, yStep, dStep;
		xStep = yStep = dStep = new double[360];
		
		try {
			
			Scanner trigFile = new Scanner(new File("data/trigvals.txt"));
			
			int i = 0;
			
			while(trigFile.hasNextLine()) {
				
				i = trigFile.nextInt();
				xStep[i] = trigFile.nextDouble();
				yStep[i] = trigFile.nextDouble();
				dStep[i] = trigFile.nextDouble();
			}
			
		} catch(Exception e) {
			
			if(e instanceof FileNotFoundException) System.err.println("Trigvals file not found");
			else System.err.println("Other Exception: " + e.getMessage());
		}
		
		
		Level level1 = new Level("data/1.map");
		Player player = new Player(level1, xStep, yStep, dStep);
	}
}
