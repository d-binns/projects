package problem4;

import java.util.Arrays;

public class Problem4 {

	//DO NOT CHANGE THIS METHOD HEADER. You may modify it's body.
	public static void reverseArrayPartA(int arr[]) {
		printReverse(arr, arr.length -1);	
	}
	
	//DO NOT CHANGE THIS METHOD HEADER. You may modify it's body.
	//You should implement a separate method that accepts additional information
	//that does the actual processing and reversing of array.
	public static void reverseArrayPartB(int arr[]) {
		
		if (arr.length > 0){
			arr = reverseArray(arr, arr.length - 1);
		}
	}
	
	
	public static int [] reverseArray(int arr[], int idx) {
		if (idx >= 0) {
			//System.out.println(arr[idx]);
			if (idx >= arr.length/2) {
				int temp = arr[idx];
				arr[idx] = arr[arr.length - idx - 1];
				arr[arr.length - idx - 1] = temp;
			}
			return reverseArray(arr, idx - 1);
			
		}
		return arr;
	}
	
	public static int [] printReverse(int arr[], int idx) {
		if(idx >= 0) {
			System.out.println(arr[idx]);
			return printReverse(arr, idx - 1);
		}
		return arr;
	}
	
	public static void main(String args[]) {
		int arr[] = {1, 2, 3};
		System.out.println("Checking Reverse Part A");
		System.out.println(Arrays.toString(arr));
		reverseArrayPartA(arr);
		System.out.println("Should print 3 2 1 each on a separate line.");
		

		System.out.println("\nChecking Reverse Part B");
		int check[] = {3, 2, 1};
		reverseArrayPartB(arr);
		boolean correct = true;
		for(int i = 0; i < check.length; i++) {
			if(arr[i] != check[i]) {
				String res = "Should be %s.  Produced %s";
				String arrStr = Arrays.toString(arr);
				String checkStr = Arrays.toString(check);
				System.out.println("[X] Incorrect! " + String.format(res, checkStr, arrStr));
				correct = false;
				break;
			}
		}
		
		if(correct) {
			System.out.println("[!] Correct!");
		}
	}
}
