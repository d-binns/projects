package Lab3;
public class RecursionLab {
	public static void main(String args[]) {
		System.out.println(reverseString("Hello world."));
		System.out.println(sumDigits(3919));
		System.out.println(sumPositives(new int[] {9, 0, -3, 5, 7, -6}, 0));
		System.out.println(solvable1DMaze(new int[] {0, 1, 1, 0, 0, 1, 0}, 0, 
		2));
		System.out.println(targetSum(new int[] {-51, -99, 88, -89, 34, 63, -
		100, 77, 43, 12}, 0, 0, 33));
		}

	private static String reverseString(String s) {
		if(s.length() > 1) {
			return reverseString(s.substring(1)) + s.substring(0,1);
		}
		else {
			return s;
		}
	}		
	
	private static int sumDigits(int num) {
		if (num == 0) {
			return num;
		}
		return num % 10 + sumDigits(num/10);
	}
	
	private static int sumPositives(int arr[], int idx) {
		if(idx >= arr.length ) {	
			return 0;		
		}		
				
		if (arr[idx] > 0) {
			return arr[idx] + sumPositives(arr, idx + 1);
		}
		else {
			return sumPositives(arr, idx + 1);
		}	
	}
	
	private static boolean solvable1DMaze(int maze[], int idx, int jump) {
		if (idx >= maze.length) {
			return true;
		}
		if (maze[idx] == 1) {
			return false;
		}
		if (maze[idx]== 0 && idx == 0) {
			maze[idx] = 1;
			return (solvable1DMaze(maze, idx + 1, jump) || solvable1DMaze(maze, idx + jump, jump));
		}
		if (maze[idx]==0) {
			maze[idx] = 1;
			return (solvable1DMaze(maze, idx + 1, jump) || solvable1DMaze(maze, idx - 1, jump) || solvable1DMaze(maze, idx + jump, jump));
		}
		return false;
	}
	
	private static boolean targetSum(int arr[], int idx, int sum, int target) {
		if(idx >= arr.length) {
			return sum == target;
		}
		
		return targetSum(arr, idx + 1, sum + arr[idx], target) ||
			   targetSum(arr, idx + 1, sum, target);
	}
}
