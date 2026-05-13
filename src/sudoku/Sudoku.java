package sudoku;
import java.util.Scanner;
import java.util.Random;
public class Sudoku {

	public static void main(String[] args) {
int[][] grid = new int[9][9];
Scanner scanner = new Scanner(System.in);
int clues = selectDifficulty(scanner);   // ask player for difficulty
generatePuzzle(grid, clues);      // generate the puzzle

while (true) {
    printGrid(grid);//display current state of the sudoku board
    
    if (isFull(grid)) {//check if the grid is already complete
        System.out.println("YOU WIN!");
		scanner.close();
        break;
    }
    	//get users input for row, col, and, value
		System.out.print("what cell would you like input"
				+ "\n row 0 to 8:");

		if(!scanner.hasNextInt()) {//makes sure only numbers can be added
			System.out.println("Invalid value!");
			scanner.next();//takes user input


			continue;
		}
		int row =scanner.nextInt();
		
		System.out.print("col  0 to 8:");
		if(!scanner.hasNextInt()) {
			System.out.println("Invalid value!");
			scanner.next();
			continue;
		}
		int col=scanner.nextInt();
		
		if(row < 0 || row > 8 || col < 0 || col > 8) {
		    System.out.println("Row and column must be between 0-8.");
		    continue;
		}
		
		System.out.print("input a value");
		if(!scanner.hasNextInt()) {
			System.out.println("Invalid value!");
			scanner.next();
			continue;
		}
		 int value= scanner.nextInt();
		 if (grid[row][col] != 0) {
			    System.out.println("Cell already has a value!");
			} else if (value < 1 || value > 9) {
			    System.out.println("Please enter a number between 1 and 9.");
			} else if (isValid(grid, row, col, value)) {
			    grid[row][col] = value;
			} else {
			    System.out.println("Invalid move!");
			}
}


	}
		
		public static void printGrid(int[][] grid) {
	    for (int row = 0; row < 9; row++) {
	        // every 3 rows print a horizontal line
	        if (row % 3 == 0 && row != 0) {
	            System.out.println("---------+---------+---------");
	        }

	        for (int col = 0; col < 9; col++) {
	            // every 3 columns print a vertical line
	            if (col % 3 == 0 && col != 0) {
	                System.out.print("|");
	            }
	            
	         
	            System.out.print(" " + grid[row][col] + " ");
	        }
	        System.out.println(" ");
	    }
	    System.out.println("\n ");
	}
	
		static boolean isValid(int[][] grid, int row, int col, int num) // check if num can be placed at grid[row][col]
		{
		    for (int i = 0; i < 9; i++){ // loop through the row and column to check for dupilcates
		    if (grid[row][i] == num) return false; //same number in row
		    if (grid[i][col] == num) return false; //same number in column
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

		static void generatePuzzle(int[][] grid, int clues) {
		    solve(grid);                       // fill the whole board using the solver
		    Random rand = new Random();
		    int toRemove = 81 - clues;         // figure out how many cells to erase
		    while (toRemove > 0) {
		        int r = rand.nextInt(9);        // pick a random row
		        int c = rand.nextInt(9);        // pick a random col
		        if (grid[r][c] != 0) {         // only erase if the cell isn't already empty
		            grid[r][c] = 0;
		            toRemove--;
		        }
		    }
		}

		static int selectDifficulty(Scanner scanner) {
		    System.out.println("Select difficulty:");
		    System.out.println("  1 = Easy   (45 clues)");
		    System.out.println("  2 = Medium (35 clues)");
		    System.out.println("  3 = Hard   (25 clues)");
		    System.out.print("Enter 1, 2, or 3: ");
		    int choice = scanner.nextInt();
		    switch (choice) {
		        case 1: return 45; // easy
		        case 3: return 25; // hard
		        default: return 35; // medium
		    }
		}	
	
}