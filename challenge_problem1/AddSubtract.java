package challenge_problem1;

import java.util.Arrays;

public class AddSubtract {
	
	//DO NOT CHANGE THIS METHOD HEADER. You may modify it's body.
	//You should implement a separate method that accepts additional information
	//that does the actual processing and returning of the maximum value.
	//Additional variables may include the index for the numbers array, the current total, etc.
	//Call your method and return its result inside this method.
	public static boolean addSub(int numbers[], int target) {
		return matchTarget(numbers, target, 0, 0);
	}
	
	public static boolean matchTarget(int numbers[], int target, int idx, int current) {
		if (idx < numbers.length) {
			return (matchTarget(numbers, target, idx + 1, current + numbers[idx]))||(matchTarget(numbers, target, idx + 1, current - numbers[idx]));
		}
		else {
			if (target == current) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		int numbers[] = new int[]{
				1, 2, 3, 4
		};
		
		System.out.println("Array " + Arrays.toString(numbers) + " target " + -2);
		boolean hasTarget = addSub(numbers, -2);
		if(hasTarget) {
			System.out.println("[!] Correct!");
		} else {
			System.out.println("[X] Incorrect! It is possible to produce a target of -2. Returned " + hasTarget);
		}
		
		numbers = new int[]{
				7, 1, 3, 5
		};
		
		System.out.println("Array " + Arrays.toString(numbers) + " target " + 9);
		hasTarget = addSub(numbers, 9);
		if(!hasTarget) {
			System.out.println("[!] Correct!");
		} else {
			System.out.println("[X] Incorrect! It is not possible to produce a target of 9. Returned " + hasTarget);
		}
		
	}

}
