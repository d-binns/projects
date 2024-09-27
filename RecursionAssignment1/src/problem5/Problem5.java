package problem5;

public class Problem5 {
	//DO NOT CHANGE THIS METHOD HEADER. You may modify it's body.
	//You should implement a separate method that accepts additional information
	//that does the actual processing and returns the max value.  For instance, you'll need
	//an index variable.
	public static int maxValue(int arr[]) {
		return 0;
	}
	
	//DO NOT CHANGE THIS METHOD HEADER. You may modify it's body.
	//You should implement a separate method that accepts additional information
	//that does the actual processing and returns the min value.  For instance, you'll need
	//an index variable.
	public static int minValue(int arr[]) {
		return 0;
	}

	public static void main(String[] args) {
		int arr[] = {1, 1, 2, 3, 3, 3, 4, 5, 4, 4, 3, 4, 5, 2};
		
		System.out.println("Testing maxValue");
		int max = maxValue(arr);
		if(max == 5) {
			System.out.println("[!] Correct!");
		} else {
			System.out.println("[X] Incorrect! Value should be 5 for max call.  Returned " + max);
		}

		System.out.println("Testing minValue");
		int min = minValue(arr);
		if(min == 1) {
			System.out.println("[!] Correct!");
		} else {
			System.out.println("[X] Incorrect! Value should be 1 for min call.  Returned " + min);
		}
		
		System.out.println("Testing IllegalArgumentException for maxValue.");
		try {
			int nums[] = {};
			maxValue(nums);
			System.out.println("[X] Incorrect! IllegalArgumentException should be returned for empty array in maxValue.");
		} catch(IllegalArgumentException iae) {
			System.out.println("[!] Correct!");
		}
		
		System.out.println("Testing IllegalArgumentException for minValue.");
		try {
			int nums[] = {};
			minValue(nums);
			System.out.println("[X] Incorrect! IllegalArgumentException should be returned for empty array in minValue.");
		} catch(IllegalArgumentException iae) {
			System.out.println("[!] Correct!");
		}
	}

}
