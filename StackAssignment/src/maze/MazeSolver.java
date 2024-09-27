package maze;
import java.util.Arrays;
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


	
    public static Position[] solve(Maze maze) {
    	Position [] mazeSolution;
    	Position start = maze.getStart();
    	Position current = start;
    	Position end = maze.getEnd();
    	Stack<Position> mazeStack = new Stack<Position>();
    	int endRow = end.getRow();
    	int endCol = end.getColumn();
    	int count = 0;
    	mazeStack.push(start);
    	maze.setAt(start, 'V');
    	
    	
    	while (mazeStack.isEmpty() == false) {    
    		
	    	if((( endRow == current.getRow()) && (current.getColumn() - 1  == endCol)) || ((current != start) && (maze.validPosition( current.getRow(),current.getColumn() - 1)) && ((maze.getAt(current.getRow(),current.getColumn() - 1)) != 'X')  && ((maze.getAt(current.getRow(), current.getColumn() - 1) != 'V')))){
	    		current = new Position( current.getRow(),current.getColumn() - 1);
	    		maze.setAt(current, 'V');
	    		mazeStack.push(current);
	    		count ++;
	    	}
	  
	    	else if((( endRow == current.getRow()) && (current.getColumn() + 1 == endCol)) || ((maze.validPosition(current.getRow(), current.getColumn() + 1)) && (maze.getAt(current.getRow(), current.getColumn() + 1) != 'X') && (maze.getAt(current.getRow(), current.getColumn() + 1) != 'V'))){
	    		current = new Position(current.getRow(), current.getColumn() + 1);
	    		maze.setAt(current, 'V');
	    		mazeStack.push(current);
	    		count ++;
	    	}
	    	
	    	else if ((( endRow == current.getRow() + 1) && (current.getColumn() == endCol)) ||((maze.validPosition(current.getRow() + 1,current.getColumn())) && (maze.getAt(current.getRow() + 1,current.getColumn()) != 'X') && (maze.getAt(current.getRow() + 1,current.getColumn()) != 'V'))){
	    		current = new Position(current.getRow() + 1,current.getColumn());
	    		maze.setAt(current, 'V');
	    		mazeStack.push(current);
	    		count ++;
	    	}
	    	
	    	else if((( endRow == current.getRow() - 1) && (current.getColumn() == endCol)) ||((maze.validPosition(current.getRow() - 1,current.getColumn())) && (maze.getAt(current.getRow() - 1,current.getColumn()) != 'X') && (maze.getAt(current.getRow() - 1,current.getColumn()) != 'V'))){
	    		current = new Position(current.getRow() - 1,current.getColumn());
	    		maze.setAt(current, 'V');
	    		mazeStack.push(current);
	    		count ++;
	    	}
	    	
	    	else {
	    		maze.setAt(current, 'X');
	    		mazeStack.pop();
	    		
	    		if (mazeStack.isEmpty()) {
	    			break;
	    		}
	    		current  = mazeStack.peek();
	    		count --;
	    	}
	    	
	    	
	    	if((current.getColumn() == start.getColumn()) && (current.getRow() == start.getRow())) {
	    		count = 0;
	    		mazeStack.pop();
	    		break;
	    	}
	    	
	    	
	    	if ((current.getColumn() == end.getColumn()) && (current.getRow() == end.getRow())) { 
	    		break;
	    	}
    	}
    	

		if (mazeStack.isEmpty() == false){
			mazeSolution = new Position [count+1];
			
    		for (int i = count; i >= 0; i--) {
    			mazeSolution[i] = mazeStack.pop();	
    		}
    		return mazeSolution;
    	}
		mazeSolution = new Position[0];
        return mazeSolution;
    }





}













