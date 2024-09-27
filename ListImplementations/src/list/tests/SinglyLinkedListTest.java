package list.tests;
import listi.SinglyLinkedList;

public class SinglyLinkedListTest {

	public static void main(String[] args) {
		SinglyLinkedList<Character> sll = new SinglyLinkedList<Character>();
		
	sll.append('i');
	sll.append('2');
	sll.append('b');;
	System.out.println(sll);
	sll.set(2, 'c');
	System.out.println(sll);
		
		
	}
}
