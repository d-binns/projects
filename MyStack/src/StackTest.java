
public class StackTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedStack<String> stringStack = new LinkedStack<>();
	
		stringStack.push("A");
		stringStack.push("B");
		stringStack.push("C");
		
		while (!stringStack.isEmpty()) {
			System.out.println(stringStack.pop());
//			stringStack.pop();
		}
		stringStack.peek();
	}

}
 