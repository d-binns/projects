package challenge_problem2;

import java.util.Arrays;

public class ValueGame {
	
	//DO NOT CHANGE THIS METHOD HEADER. You may modify it's body.
	//You should implement a separate method that accepts additional information
	//that does the actual processing and returning of the maximum value.
	//Additional variables may include the index for the Card array, the current cost, the
	//current value, etc.  Call your method and return its result inside this method.
	public static int valueGame(Card hand[], int spendLimit) {
		return maxValue(hand, spendLimit, 0, hand[0].COST,0);
	}
	
	public static int maxValue(Card hand[], int spendLimit, int idx, int currentCost, int currentValue) {
		if (currentValue > spendLimit) {
			return 0 ;
		}
		else {
			if (idx < hand.length) {			
				if (maxValue(hand, spendLimit, idx + 1, hand[idx].COST , hand[idx].VALUE + currentValue) >= maxValue(hand, spendLimit, idx + 1, hand[idx].COST , currentValue)) {
					return maxValue(hand, spendLimit, idx + 1, hand[idx].COST , hand[idx].VALUE + currentValue);
				}
				else if (maxValue(hand, spendLimit, idx + 1, hand[idx].COST , hand[idx].VALUE + currentValue) < (maxValue(hand, spendLimit, idx + 1, hand[idx].COST , currentValue))){
					return maxValue(hand, spendLimit, idx + 1, hand[idx].COST , currentValue);
				}	
			}
		return currentValue;
		}
	}

	public static void main(String[] args) {
		Card hand[] = {
			new Card(12, 4),
			new Card(2, 2),
			new Card(1, 2),
			new Card(1, 1),
			new Card(4, 10)
		};
		System.out.println("Cards " + Arrays.toString(hand));
		int maxValue = valueGame(hand, 15);
		if(maxValue == 15) {
			System.out.println("[!] Correct!");
		} else {
			System.out.println("[X] Incorrect! Value should be 15. You returned " + maxValue);
		}
		
	}

}
