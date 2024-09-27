import java.util.Stack;

/**
 * Notes:
 * 
 * The start and end position are contained within the maze object. Use the
 * getStart() and getEnd() methods.
 * 
 * @author Brian Rogers
 *
 */
public class MazeSolver {
    /**
     * You need to implement this method
     * 
     * @param maze: The maze to be solved.
     * @return An array of Position which stores a solution to the maze. If a
     *         solution is not found a null value should be returned.
     */
	int x;
	int y;

	
    public static Position[] solve(Maze maze) {
    	
    	maze.getStart();
    	Stack<Position> mazeStack = new Stack<Position>();
    	
    	mazeStack.push(maze.getStart());
    	System.out.println(mazeStack.pop());
    	mazeStack.peek();
    	Position [] mazeSolution  ;
   // 	mazeSolution [0] = Maze.Position(2,3);
    	
    	
    	
        return null;
    }



public static void main(String[] args) {
	
	//maze.getStart();
	Stack<Position> mazeStack = new Stack<Position>();
	
	//mazeStack.push(maze.getStart());
	System.out.println(mazeStack.pop());
	mazeStack.peek();
	//Position[] mazeSolution  ;
	
}




}













