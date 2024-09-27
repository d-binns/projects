package problem3;

import java.util.Arrays;

public class Problem3 {
	
	//DO NOT CHANGE THIS METHOD HEADER. You may modify it's body.
	//You should implement a separate method that accepts additional information
	//that does the actual processing and modification of array.  For instance, you'll need
	//an index variable.
	public static void negation(int arr[]) {
		
	}

	public static void main(String[] args) {
		int arr[] = {0, 0, 1, 1, 0, 1, 0, 1, 1, 1};
		System.out.println(Arrays.toString(arr));
		
		negation(arr);
		System.out.println("Changed to " + Arrays.toString(arr));
		boolean correct = true;
		int check[] = {1, 1, 0, 0, 1, 0, 1, 0, 0, 0};
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != check[i]) {
				String res = "Should be %s.  Produced %s";
				String arrStr = Arrays.toString(arr);
				String checkStr = Arrays.toString(check);
				System.out.println("[X] Incorrect!" + String.format(res, arrStr, checkStr));
				correct = false;
				break;
			}
		}
		
		if(correct) {
			System.out.println("[!] Correct!");
		}
	}

}
