package sudoku;
import java.util.Scanner;
public class Sudoku {

	public static void main(String[] args) {
int[][] grid = new int[9][9];
Scanner scanner=new Scanner(System.in);
grid[0][0] = 5; 
grid[0][1] = 3; 
grid[0][4] = 7;
grid[1][0] = 6; 
grid[1][3] = 1; 
grid[1][4] = 9; 
grid[1][5] = 5;
grid[2][1] = 9; 
grid[2][2] = 8; 
grid[2][7] = 6;

while (true) {
    printGrid(grid);
    
    if (isFull(grid)) {
        System.out.println("YOU WIN!");
        break;
    }

		System.out.print("what cell would you like input"
				+ "\n row 0 to 8:");
		int row =scanner.nextInt();
		System.out.print("col  0 to 8:");
		int col=scanner.nextInt();
		
		System.out.print("input a value");
		 int value= scanner.nextInt();
		 if (grid[row][col] != 0) {
			    System.out.println("Cell already has a value!");
			} else if (value < 1 || value > 9) {
			    System.out.println("Please enter a number between 1 and 9.");
			} else if (isValid(grid, row, col, value)) {
			    grid[row][col] = value;
			} else {
			    System.out.println("\"Invalid move!\"");
			}
		 

		 printGrid(grid);
}
	
//		if (solve(grid)) {
//		    System.out.println("Solved:");
//		} else {
//		    System.out.println("No solution");
//		}
//		printGrid(grid);


	
		
		
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
	        System.out.println(" ");
	    }
	    System.out.println("\n ");
	}
	
		static boolean isValid(int[][] grid, int row, int col, int num) // check if num can be placed at grid[row][col]
		{
		    for (int i = 0; i < 9; i++){ // loop through the row and column
		    if (grid[row][i] == num) return false; //same nunmber in row
		    if (grid[i][col] == num) return false; //same nunmber in column
		    }
		    int boxRow = (row / 3) * 3; // find the top left corner of the 3x3 box
		    int boxCol = (col / 3) * 3; // find the top left corner of the 3x3 box
		        for (int r = boxRow; r < boxRow + 3; r++) // loop through the 3 rows of the box
		            {for (int c = boxCol; c < boxCol + 3; c++){
		                if (grid[r][c] == num) return false;}} // same number in 3x3 box

		        return true;  // all good moving on
		    }

		public static boolean isFull(int[][] grid) {
	        for (int r = 0; r < 9; r++)
	            for (int c = 0; c < 9; c++)
	                if (grid[r][c] == 0) return false;
	        return true;
	    }

		static boolean solve(int[][] grid) { // backtracking solver
		    for (int row = 0; row < 9; row++) {
		        for (int col = 0; col < 9; col++) {
		            if (grid[row][col] == 0) { // find an empty cell
		                for (int num = 1; num <= 9; num++) { // try numbers 1-9
		                    if (isValid(grid, row, col, num)) { // check if it's valid
		                        grid[row][col] = num; // place the number

		                        if (solve(grid)) return true; // recursively solve the rest of the board, goes deeper

		                        grid[row][col] = 0; // backtrack if it doesnt lead to a solution
		                    }
		                }
		                return false; // no valid number found, trigger backtracking
		            }
		        }
		    }
		    return true; // solved
		}
		
	
}
