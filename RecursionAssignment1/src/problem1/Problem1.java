package problem1;

public class Problem1 {
	
	public static void printCharRow(char c, int count) {
		
		System.out.println("What");
	}

	public static void main(String[] args) {
		System.out.println("Print 4 'x' chars");
		printCharRow('x', 4);
		System.out.println("Should have printed xxxx");
		
		System.out.println("Print nothing with a negative count");
		printCharRow('-', -10);
		System.out.println("Nothing should have been printed.");
	}

}
