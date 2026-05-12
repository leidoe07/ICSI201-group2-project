import java.util.Random;
import java.util.Scanner;

public class snippets {

    static boolean isValid(int[][] grid, int row, int col, int num) // check if num can be placed at grid[row][col]
    {
        for (int i = 0; i < 9; i++){ // loop through the row and column
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
        solve(grid);                          // step 1: fill the whole board using the solver
        Random rand = new Random();
        int toRemove = 81 - clues;           // figure out how many cells to erase
        while (toRemove > 0) {
            int r = rand.nextInt(9);          // pick a random row
            int c = rand.nextInt(9);          // pick a random col
            if (grid[r][c] != 0) {           // only erase if the cell isn't already empty
                grid[r][c] = 0;
                toRemove--;
            }
        }
    }


    static int selectDifficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select difficulty:");
        System.out.println("  1 = Easy   (45 clues)");
        System.out.println("  2 = Medium (35 clues)");
        System.out.println("  3 = Hard   (25 clues)");
        System.out.print("Enter 1, 2, or 3: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1: return 45; // easy
            case 3: return 25; // hard
            default: return 35; // medium (also catches any wrong input)
        }
    }


// IMPORTANTTTTT! also I fixed a bug with the original functions

    // int[][] grid = new int[9][9];
    // int clues = selectDifficulty();   // ask player for difficulty
    // generatePuzzle(grid, clues);      // generate the puzzle

}
