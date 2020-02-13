package solution;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * A maze game.
 * 
 * @author Troy Choplin.
 * @version 1.0
 *
 */
public class MazeGame
{
    /**
     * The size of each side of the game map.
     */
    private final static int HEIGHT = 19;
    private final static int WIDTH = 39;

    /**
     * The game map, as a 2D array of ints.
     */
    private boolean[][] blocked;

    /**
     * The current location of the player vertically.
     */
    private int userRow;

    /**
     * The current location of the player horizontally.
     */
    private int userCol;

    /**
     * The scanner from which each move is read.
     */
    private Scanner moveScanner;

    /**
     * The row and column of the goal.
     */
    private int goalRow;
    private int goalCol;

    /**
     * The row and column of the start.
     */
    private int startRow;
    private int startCol;
    private boolean[][] visited;
    private boolean cont = true;
    /**
     * Constructor initializes the maze with the data in 'mazeFile'.
     * @param mazeFile the input file for the maze
     */
    public MazeGame(String mazeFile)
    {
        // TODO
        loadMaze(mazeFile);
        moveScanner = new Scanner(System.in);
    }

    /**
     * Constructor initializes the maze with the 'mazeFile' and the move 
     * scanner with 'moveScanner'.
     * @param mazeFile the input file for the maze
     * @param moveScanner the scanner object from which to read user moves
     */
    public MazeGame(String mazeFile, Scanner moveScanner)
    {
        // TODO
        loadMaze(mazeFile);
        this.moveScanner = moveScanner;
    }


    /**
     * Makes a move based on the String.
     * 
     * @param fileName
     *            name of file.
     * @param array araay.           
     */
    public void readInputFile(String fileName,
        boolean[][] array)
    {
        Scanner fileIn = null;
        try 
        {
            fileIn = new Scanner(new FileReader(fileName));
        } 
        catch (FileNotFoundException ioe) 
        {
            System.out.println("Could not open the file.");
            System.exit(0);
        }
        int row = 0;
        int col = 0;
        int count1 = 0;
        while (fileIn.hasNext() && col < blocked[1].length 
            && row < blocked.length)
        {
            String loc = fileIn.next();
            if (loc.equals("S"))
            {
                startRow = row;
                startCol = col;
                userRow = row;
                userCol = col;
                blocked[row][col] = false;
            }
            else if (loc.equals("1"))
            {
                blocked[row][col] = true;  
            }
            else if (loc.equals("0"))
            {
                blocked[row][col] = false;
            }
            else if (loc.equals("G"))
            {
                goalRow = row;
                goalCol = col;
                blocked[row][col] = false;
            }
            count1++;
            col++;
            if (count1 == blocked[1].length)
            {
                col = 0;
                count1 = 0;
                row++;
            }

        }
        fileIn.close();
    }
    /**
     * getMaze returns a copy of the current maze for testing purposes.
     * 
     * @return the grid
     */
    public boolean[][] getMaze()
    {
        if (blocked == null)
        {
            return null;
        }
        boolean[][] copy = new boolean[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                copy[i][j] = blocked[i][j];
            }
        }
        return copy;
    }

    /**
     * setMaze sets the current map for testing purposes.
     * 
     * @param maze
     *            another maze.
     */
    public void setMaze(boolean[][] maze)
    {
        this.blocked = maze;
    }

    /**
     * Function loads the data from the maze file and creates the 'blocked' 
     * 2D array.
     *  
     * @param mazeFile the input maze file.
     */
    // TODO: private void loadMaze(String mazeFile)
    private void loadMaze(String mazeFile)
    {
        blocked = new boolean[HEIGHT][WIDTH];
        visited = new boolean[HEIGHT][WIDTH];
        readInputFile(mazeFile, blocked);
    }
    /**
     * Actually plays the game.
     */
    public void playGame()
    {

        String move;
        int numMoves = 0;
        while (cont == true)
        {
            printMaze();
            System.out.print("Please enter a move: ");
            move = moveScanner.next();
            makeMove(move);
            numMoves++;
            if (playerAtGoal())
            {
                cont = false;
                System.out.print("Congratulations! You won in " +
                    numMoves + " moves!");
            }
        }

    }

    /**
     * Checks to see if the player has won the game.
     * @return true if the player has won.
     */
    // TODO: public boolean playerAtGoal()
    public boolean playerAtGoal()
    {
        if (userCol == goalCol)
        {
            if (userRow == goalRow)
            {
                return true;
            }
        }
        return false;

    }
    /**
     * Makes a move based on the String.
     * 
     * @param move
     *            the direction to make a move in.
     * @return whether the move was valid.
     */
    public boolean makeMove(String move)
    {
        // TODO
        if (move.equals("quit"))
        {
            this.cont = false;
            return false;
        }
        if (move.equals("up") || move.charAt(0) == 'U' || move.charAt(0) == 'u')
        {
            if (userRow != 0 && !blocked[userRow - 1][userCol])
            {
                if (userRow != 0)
                {

                    userRow--;
                    visited[userRow][userCol] = true;
                    return true;
                }
            }
        }
        if (move.equals("down") || move.charAt(0) == 'd'
            || move.charAt(0) == 'D')
        {
            if (userRow != blocked.length - 1 && !blocked[userRow + 1][userCol])
            {
                userRow++;
                visited[userRow][userCol] = true;
                return true;
            }
        }
        if (move.equals("right") || move.charAt(0) == 'R'
            || move.charAt(0) == 'r')
        {
            if (userCol != blocked[userRow].length - 1 
                && !blocked[userRow][userCol + 1])
            {
                userCol++;
                visited[userRow][userCol] = true;
                return true;
            }
        }
        if (move.equals("left") || move.charAt(0) == 'L' 
            || move.charAt(0) == 'l')
        {
            if (userCol != 0) 
            {
                if (userCol != blocked[userRow].length 
                    && !blocked[userRow][userCol - 1])
                {

                    userCol--;
                    visited[userRow][userCol] = true;
                    return true;

                }
            }
        }
        return false;

    }

    /**
     * Prints the map of the maze.
     */
    public void printMaze()
    {
        // TODO
        for (int i = 0; i < blocked.length; i++)
        {
            if (i == 0)
            {
                System.out.println("*---------------------------------------*");
            }
            for (int j = 0; j < blocked[i].length + 1; j++)
            {
                if (j == 0)
                {
                    System.out.print("|");
                }
                if (j == blocked[1].length)
                {
                    if (i != blocked.length)
                    {
                        System.out.print("|");
                    }
                } 
                else 
                {
                    if (i == userRow && j == userCol)
                    {
                        System.out.print("@");   
                    }
                    else if (visited[i][j])
                    {
                        System.out.print(".");
                    }
                    else
                    {
                        if (i == startRow && j == startCol)
                        {
                            System.out.print("S");
                        }
                        else
                        {
                            if (blocked[i][j] == true)
                            {
                                System.out.print("X");
                            }
                            if (i == goalRow && j == goalCol)
                            {
                                System.out.print("G");
                            }
                            else
                            {
                                if (blocked[i][j] == false)
                                {
                                    System.out.print(" ");
                                }
                            }
                        } 

                    }
                }

            }
            System.out.println(); 
        }
        System.out.println("*---------------------------------------*");
    }


    /**
     * Makes a move based on the String.
     * 
     * @return whether the move was valid.
     */
    public boolean[][] getBlocked()
    {
        return this.blocked;   
    }


    /**
     * Makes a move based on the String.
     * @param blocked block.
     */
    public void setBlocked(boolean[][] blocked)
    {
        this.blocked = blocked;
    }

    /**
     * Makes a move based on the String.
     * 
     * @return boolean.
     */
    public boolean[][] getVisited()
    {
        return this.visited;   
    }


    /**
     * Makes a move based on the String.
     * 
     * @param visited
     *            array.
     */
    public void setVistied(boolean[][] visited)
    {
        this.visited = visited;
        for (int i = 0; i < blocked.length; i++)
        {
            for (int j = 0; j < blocked[j].length; j++)
            {
                visited[i][j] = false;
            }
        }
    }


    /**
     * Makes a move based on the String.
     * 
     * @return int 
     *             number.
     */
    public int getUserRow()
    {
        return this.userRow;

    }


    /**
     * Makes a move based on the String.
     * 
     * @param userRow
     *            row.
     */
    public void setUserRow(int userRow)
    {
        this.userRow = userRow;
    }


    /**
     * Makes a move based on the String.
     * 
     * @return int int.
     */
    public int getUserCol()
    {
        return this.userCol;
    }


    /**
     * Makes a move based on the String.
     * 
     * @param userCol
     *            column.
     */
    public void setUserCol(int userCol)
    {
        this.userCol = userCol;
    }


    /**
     * Makes a move based on the String.
     * 
     * @return Scanner.
     */
    public Scanner getMoveScanner()
    {
        return this.moveScanner;
    }


    /**
     * Makes a move based on the String.
     * 
     * @param moveScanner
     *            scanner.
     */
    public void setMoveScanner(Scanner moveScanner)
    {
        this.moveScanner = moveScanner;
    }
    /**
     * Creates a new game, using a command line argument file name, if one is
     * provided.
     * 
     * @param args the command line arguments
     */

    public static void main(String[] args)
    {
        String mapFile = args[0];
        Scanner scan = new Scanner(System.in);
        MazeGame game = new MazeGame(mapFile, scan);
        game.playGame();
    }
}
