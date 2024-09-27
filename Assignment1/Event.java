package Assignment1;

public class Event  {
	
	private Date date;
	private int start;
	private int end;
	private String description;
	
		
	public Event(Date date, int start, int end, String description) throws IllegalArgumentException{
		if (start > end) {
			throw new IllegalArgumentException("The start time/hour cannot be after the end time/hour");
		}
		else {
			this.date = date;
		}
		
	    if(start < 0 || start > 23) {
	        throw new IllegalArgumentException("The end time/hour must be between 0-23");
	    }
	    else {
	    	this.start = start;
	    }
	    
	    if(end < 0 || end > 23) {
	        throw new IllegalArgumentException("The end time/hour must be between 0-23");
	    }
	    else {
	    	this.end = end;
	    	this.description = description;
	    }   
	}

	public Date getDate() {	
	   	return date;
	}
	    
	public int getStart() {
	    return start;
	    }
	    
    public int getEnd() {
	    return end;
	    }
	    
    public String getDescription() {
    	return description;
	    }
	    
    public void setDescription(String newDescription) {
    	this.description = newDescription;
    }
	    
    public String toString() {
    	return (this.getDate()+ " " + this.start + "--" + this.end + ":" + this.description);
    }
	
    @Override
    public boolean equals(Object obj) {
    	if ((obj == null) || !(obj instanceof Event)) {
    		
    		return false; 
    	}
    	else {
    	Event other = (Event) obj;
    	
    	return this.date.equals(other.date) && this.description.equals(other.description) && (this.start == other.start) && (this.end == other.end);
    	} 	
    	
    }
	

	public static void main(String[] args) {	
	
		
	}
		
	
	
	
}