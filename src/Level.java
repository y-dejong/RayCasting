import gpdraw.DrawingTool;

import java.io.File;
import java.util.Scanner;

public class Level {

	int[][] matrix;

	public Level(String file) {
		
		try {
			Scanner matrixfile = new Scanner(new File(file));
			int width = matrixfile.nextInt();
			int height = matrixfile.nextInt();
			matrix = new int[height][width];
			matrixfile.nextLine();
			int row = 0;
			while(matrixfile.hasNextLine()) {

				String line = matrixfile.nextLine();
				for(int i = 0; i < line.length(); i++) {
					matrix[row][i] = Integer.parseInt(line.substring(i,i+1));
					System.out.printf("Added %s to matrix[%d][%d]\n", line.charAt(i), row, i);
				}
				row++;
			}
			System.out.println("Made the matrix");
			/*for(int[] x : matrix) {
				System.out.println(x);
			}*/
		} catch (Exception e) {
			System.err.println("OOPSIE WOOPSIE!! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this! " + e.getMessage());
		}
	}

	public int get(int x, int y) {
		return matrix[y][x];
	}
	
	public int xSize() { return matrix[0].length; }
	public int ySize() { return matrix.length; }
}
