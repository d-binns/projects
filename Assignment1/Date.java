package Assignment1;

public class Date implements Comparable<Date>{
	
	private int year;
	private int month;
	private int day;
	

	public Date(int year, int month, int day) throws IllegalArgumentException{
	    if(year < 2021 || year > 2080) {
	        throw new IllegalArgumentException("Year must be between 2021 and 2080");
	    }
	    /*Checks for month and day*/
	    else {
	    	this.year = year;
	    }
	    /*Set month and day here*/
	    if(month < 1 || month > 12) {
	        throw new IllegalArgumentException("Month must be between 1 and 12");
	    }
	    else {
	    	this.month = month;
	    }
	    
	    if(day < 1 || day > 31) {
	        throw new IllegalArgumentException("Day must be between 1 and 31");
	    }
	    else {
	    	this.day = day;
	    }
	}
	
	public int getYear  () {
		
		return year;
	}

	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		
		return day;
	}
	
	public String toString() {
		return (month + "/" + day + "/" + year);
	}
	
	public boolean equals(Object obj) {
	    //If obj is null, return false
	    if(obj == null)  {
	        return false;
	    }
	    //If obj is not an instance of Date
	    //return false
	    if(!(obj instanceof Date)) {
	        return false;
	    }
	    //If obj is this object return true
	    if(this == obj) {
	        return true;
	    }
	    //Coerce obj into a Date object
	    //so the data fields can be accessed.
	    Date otherDate = (Date) obj;
	    /*Perform necessary comparisons*/
	    
	    if ((otherDate.month == this.month) && (otherDate.day == this.day) && (otherDate.year == this.year) ) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	public int compareTo (Date otherDate) {
		if ((otherDate.year < this.year) || ((otherDate.year == this.year) && (otherDate.month < this.month)) || ((otherDate.year == this.year) && (otherDate.month == this.month) && (otherDate.day < this.day))){
			return 1;
		}
		else if ((otherDate.year > this.year) || ((otherDate.year == this.year) && (otherDate.month > this.month)) || ((otherDate.year == this.year) && (otherDate.month == this.month) && (otherDate.day > this.day))) {
			return -1;
		}
		else {
			return 0;
		}
		}

	
	
	public static void main(String[] args) {

	}
	
}


