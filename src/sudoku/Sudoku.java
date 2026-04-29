package sudoku;
import java.util.Scanner;
public class Sudoku {

	public static void main(String[] args) {
int[][] grid = new int[9][9];
		System.out.println("")
		grid[0][0] = 5;
		grid[0][1] = 3;
		grid[1][0] = 6;
		
		printGrid(grid);
	}

	public static void printGrid(int[][] grid) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				System.out.print(" "+grid[row][col] + " ");
			}
			System.out.println();
		}
	}
}
