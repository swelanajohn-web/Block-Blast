import java.util.Random;

/**
 * ***********************************************************************
 * Compilation: javac BlockBlast.java Execution: java BlockBlast gridSize
 *
 * @author Ram Buditi
 * @author Sarah Benedicto
 *
 ************************************************************************
 */
public class BlockBlast {

    /* Create the game grid and the functionality for block placement into the grid.
       Implement functionality for clearing completed rows and columns.
       Update player score based on cleared lines.

       gridSize: The size of the blockblast grid (ex. inputting 4 would create a size 4x4 grid)
     */

    public static void main(String[] args) {
        boolean[][] verticalLine = { { true }, { true }, { true }, { true } };
        boolean[][] smallSquare = { { true, true }, { true, true } };
        boolean[][] bigSquare = { { true, true, true }, { true, true, true }, { true, true, true } };
        boolean[][] zShape = { { false, true }, { true, true }, { true, false } };
        boolean[][] tShape = { { true, true, true }, { false, true, false } };
        boolean[][] horizontalLine = { { true, true, true } };
        boolean[][][] blocks = { verticalLine, smallSquare, bigSquare, zShape, tShape, horizontalLine };
        int numBlocks = blocks.length;
        boolean[][] gameGrid = null;

        // 1) STUDENT TASK: Initialize game grid
        int gridSize = Integer.parseInt(args[0]);
        gameGrid = new boolean[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gameGrid [i][j] = false;
            }
        }

        /**
         * *******************************************************************
         */

        // BASE GAME LOGIC
        boolean[][] currentBlock;
        boolean gameActive = true; // Change this to false if you don't want to run the while loop for the game
        int score = 0;
        // int randomIndex;
        Random rand = new Random(2); // Have to set manual seed for testing (keep this at 2)
        int randomIndex = 0;

        while (gameActive) {
            randomIndex = (int) Math.floor(Math.random() * numBlocks);
            randomIndex = rand.nextInt(5);
            currentBlock = blocks[randomIndex];


            // // TESTING PRINT STATEMENT FOR CHOOSING NEW BLOCK. YOU MUST COMMENT OUT UPON SUBMISSION
            // System.out.println("Current Block:");
            // for (int i = 0; i < currentBlock.length; i++) {
            //     for (int j = 0; j < currentBlock[i].length; j++) {
            //         if (currentBlock[i][j]) {
            //             System.out.print("X ");
            //         } else {
            //             System.out.print("  ");
            //         }
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            // END OF TESTING PRINT STATEMENT FOR CHOOSING NEW BLOCK
            /**
             * *******************************************************************
             */


            // 2) STUDENT TASK: Find block placement
            boolean placed = false;
            for (int i = 0; i <= gameGrid.length - currentBlock.length; i++) {
                for (int j = 0; j <= gameGrid[0].length - currentBlock[0].length; j++) {
                    if (canPlace(gameGrid, currentBlock, i, j)) {
                        placeBlock(gameGrid, currentBlock, i, j);
                        placed = true;
                        break;
                    }
                }
                if (placed) break;
            }

            if(!placed) {
                gameActive = false;
                break;
            }

            // TESTING PRINT STATEMENT FOR TASK 2. YOU MUST COMMENT OUT UPON SUBMISSION
            // System.out.println("Game Board (after placing blocks):");
            // for (int i = 0; i < gameGrid.length; i++) {
            //     for (int j = 0; j < gameGrid[i].length; j++) {
            //         if (gameGrid[i][j]) {
            //             System.out.print("X ");
            //         } else {
            //             System.out.print("_ ");
            //         }
            //     }
            //     System.out.println();
            // }
            // System.out.println("Current score: " + score + "\n");
            // END OF TESTING PRINT STATEMENT FOR TASK 2
            /**
             * *******************************************************************
             */


            // 3) STUDENT TASK: Clear filled rows/columns
        int clearedLines = clearCompleted(gameGrid);


            // TESTING PRINT STATEMENT FOR TASK 3. YOU MUST COMMENT OUT UPON SUBMISSION
            // System.out.println("Game Board (after clearing rows/columns):");
            // for (int i = 0; i < gameGrid.length; i++) {
            //     for (int j = 0; j < gameGrid[i].length; j++) {
            //         if (gameGrid[i][j]) {
            //             System.out.print("X ");
            //         } else {
            //             System.out.print("_ ");
            //         }
            //     }
            //     System.out.println();
            // }
            // System.out.println("Current score: " + score + "\n");
            // END OF TESTING PRINT STATEMENTS FOR TASK 3
            /**
             * *******************************************************************
             */


            // 4) STUDENT TASK: Update score
            score += clearedLines * 10;

            // TESTING PRINT STATEMENT FOR TASK 4. YOU MUST COMMENT OUT UPON SUBMISSION
            // System.out.println("Game Board (after calculating the score):");
            // for (int i = 0; i < gameGrid.length; i++) {
            //     for (int j = 0; j < gameGrid[i].length; j++) {
            //         if (gameGrid[i][j]) {
            //             System.out.print("X ");
            //         } else {
            //             System.out.print("_ ");
            //         }
            //     }
            //     System.out.println();
            // }
            // System.out.println("Current score: " + score + "\n\n\n");
            // END OF TESTING PRINT STATEMENTS FOR TASK 4
            /**
             * *******************************************************************
             */
        }


        // SUBMITTING TEST STATEMENT. YOU MUST UNCOMMENT OUT UPON SUBMISSION
         System.out.println("Game Board:");
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (gameGrid[i][j]) {
                    System.out.print("X ");
               } else {
                System.out.print("_ ");
                 }
             }
            System.out.println();
         }
         System.out.println("Final Score: " + score);
        // END OF SUBMITTING PRINT STATEMENTS
    }

private static boolean canPlace(boolean[][] grid, boolean[][] block, int row, int col) {
    int gridRows = grid.length;
    int gridCols = grid[0].length; 
    int blockRows = block.length;
    int blockCols = block[0].length;

    if (row + blockRows > gridRows || col + blockCols > gridCols) return false;

    for (int i = 0; i < blockRows; i++) {
        for (int j = 0; j < blockCols; j++) {
            if (block[i][j] && grid[row + i][col + j]) return false;
        }
    }
    return true;
}

private static void placeBlock (boolean[][] grid, boolean[][] block, int row, int col) {
    for (int i = 0; i < block.length; i++) {
        for (int j = 0; j < block[i].length; j++) {
            if (block[i][j]) {
                grid[row + i][col + j] = true;
            }
        }
    }
}

private static int clearCompleted(boolean[][] grid) {
            int size = grid.length;
    boolean[] fullRows = new boolean[size];
    boolean[] fullCols = new boolean[size];

    for (int i = 0; i < size; i++) {
        boolean full = true;
        for (int j = 0; j < size; j++) {
            if (!grid[i][j]) {
                full = false;
                break;
            }
        }
        fullRows[i] = full;
    }

    for (int j = 0; j < size; j++) {
        boolean full = true;
        for (int i = 0; i < size; i++) {
            if (!grid[i][j]) {
                full = false;
                break;
            }
        }
        fullCols[j] = full;
    }

    int cleared = 0;

    for (int i = 0; i < size; i++) {
        if (fullRows[i]) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = false;
            }
            cleared++;
        }
    }

    for (int j = 0; j < size; j++) {
        if (fullCols[j]) {
            for (int i = 0; i < size; i++) {
                grid[i][j] = false;
            }
            cleared++;
        }
    }

    return cleared;
    }
}






