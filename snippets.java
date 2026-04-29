public class snippets {
static boolean isValid(int row, int col, num) // check if num can be placed at grid[row][col]
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



static boolean solve() { // backtracking solver
    for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {
            if (grid[row][col] == 0) { // find an empty cell
                for (int num = 1; num <= 9; num++) { // try numbers 1-9
                    if (isValid(row, col, num)) { // check if it's valid
                        grid[row][col] = num; // place the number

                        if (solve()) return true; // recursively solve the rest of the board, goes deeper

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