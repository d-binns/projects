package Assignment1;

public class Calendar {
	
	private static final int MAXEVENTS = 4;
	private Event[] events;
	private int numEvents;  
	
	
	public Calendar() {
		events = new Event[MAXEVENTS];
		numEvents = 0;
	}
	
	public boolean addEvent(Event e) {
		if (numEvents <= 3){
			
			for (int i = 0; i < events.length; i++) {
				if (events[i] == null) {
					events[i] = e;
					numEvents ++;
					return true;
				}
			}
			return false;
		}
		else {
			return false;
		}	
	}
	
	public int findEvent(Event e) {
		if (!(e == null)){
			for (int i = 0; i < events.length; i++) {
				if (events[i] == null) {
					
					
				}
				else if (events[i].equals(e)) {
					return i;
				}
			}
			return -1;
		}
		else {
			return -1;
		}
	}
	
	public boolean removeEvent(Event e) {	
		int index = findEvent(e);
		if(!(index == -1)) {
			events [index] = null;
			numEvents --;
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public void dump() {
		for (int i = 0; i <events.length; i++) {
			if (!(events[i] == null)) {
				
				System.out.println(events[i]);
			}
		}
	}
		public static void main(String[] args) {
		
		
		}
		
}

