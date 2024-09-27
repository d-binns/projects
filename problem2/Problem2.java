package problem2;

import java.util.Arrays;

public class Problem2 {
	
	//DO NOT CHANGE THIS METHOD HEADER. You may modify it's body.
	public static int count(char characters[], char toCount) {
		if(characters.length > 0) {
			if (characters[0] == toCount) {
				char arr [] = Arrays.copyOfRange(characters, 1,characters.length);
				return 1 + count(arr, toCount);
			}
			else {
				char arr [] = Arrays.copyOfRange(characters, 1,characters.length);
				return count(arr, toCount);
			}
		}
				return 0;
	}
	

	public static void main(String[] args) {
		char arr[] = {'f', 'a', 'c', 'a', 'a', 'e', 'e', 'f'};
		
		int countA = count(arr, 'a');
		System.out.println(Arrays.toString(arr));
		if(countA == 3) {
			System.out.println("[!] Correct!");
		} else {
			System.out.println("[X] Incorrect! The count should be 3 for 'a'. Returned " + countA);
		}
		
		int countD = count(arr, 'd');
		if(countD == 0) {
			System.out.println("[!] Correct!");
		} else {
			System.out.println("[X] Incorrect! The count should be 0 for 'd'. Returned " + countD);
		}
	}

}
