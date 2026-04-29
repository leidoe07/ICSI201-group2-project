package sudoku;
import java.util.Scanner;
public class Sudoku {

	public static void main(String[] args) {
int[][] grid = new int[9][9];
Scanner scanner=new Scanner(System.in);
		System.out.print("what cell would you like input"
				+ "\n row :");
		int row =scanner.nextInt();
		System.out.print("col :");
		int col=scanner.nextInt();
		
		System.out.print("input a value");
		 int value= scanner.nextInt();
	 grid[row][col]=value;
		
		
		grid[0][0] = 5;
		grid[0][1] = 3;
		grid[1][0] = 6;
		
		printGrid(grid);
	}

	public static void printGrid(int[][] grid) {
	    for (int row = 0; row < 9; row++) {
	        // Every 3 rows, print a horizontal line
	        if (row % 3 == 0 && row != 0) {
	            System.out.println("---------+---------+---------");
	        }

	        for (int col = 0; col < 9; col++) {
	            // Every 3 columns, print a vertical line
	            if (col % 3 == 0 && col != 0) {
	                System.out.print("|");
	            }
	            
	            // Print the cell value with padding
	            System.out.print(" " + grid[row][col] + " ");
	        }
	        System.out.println();
	    }
	}

	//public static void inputCell()
}
