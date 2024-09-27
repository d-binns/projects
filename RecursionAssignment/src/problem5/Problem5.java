
  package problem5;

public class Problem5 {
	//DO NOT CHANGE THIS METHOD HEADER. You may modify it's body.
	//You should implement a separate method that accepts additional information
	//that does the actual processing and returns the max value.  For instance, you'll need
	//an index variable.
	public static int maxValue(int arr[]) {
		if (arr.length >0){
			return findMax(arr, 0, arr[0]);
		}
		else {
			throw new IllegalArgumentException("Array cannot be empty"); 
		}
	}
	
	public static int findMax(int arr[], int idx, int max) {
		if (idx < arr.length) {
			if (arr[idx] > max) {
				max = arr[idx];
				return findMax(arr, idx + 1, max);
			}
			else {
				return findMax(arr, idx + 1, max);
			}
		}
		else {
			return max;
		}
	}
	
	//DO NOT CHANGE THIS METHOD HEADER. You may modify it's body.
	//You should implement a separate method that accepts additional information
	//that does the actual processing and returns the min value.  For instance, you'll need
	//an index variable.
	public static int minValue(int arr[]) {
		if (arr.length > 0) {
			return findMin(arr, 0, arr[0]);
		}
		else {
			throw new IllegalArgumentException("Cannot have an empty array");
		}
	}
	
	
	public static int findMin (int arr[], int idx, int min) {
		if (idx < arr.length) {
			if (arr[idx] < min) {
				min = arr[idx];
				return findMin(arr, idx + 1, min);
			}
			else {
				return findMin(arr, idx + 1, min);
			}
		}
		else {
			return min;
		}
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
