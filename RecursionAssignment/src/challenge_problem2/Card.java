package challenge_problem2;

public class Card {
	public final int COST;
	public final int VALUE;
	
	public Card(int cost, int value) {
		COST = cost;
		VALUE = value;
	}
	
	public String toString() {
		String res = "(cost=%d, value=%d)";
		return String.format(res, COST, VALUE); 
	}
}
