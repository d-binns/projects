
public class QueueTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedQueue<Integer> intQ = new LinkedQueue<>();
		
		for (int i =0; i<10; i++) {
			intQ.enqueue(i);
		}
		
		while(!intQ.isEmpty()) {
			System.out.println(intQ.getFront());
			intQ.dequeue();
		}
		
		intQ.dequeue();
	}

}
