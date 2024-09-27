
public class TEST {

	static int Fibo (int n){
	    int sum = 0;
	    int num1 = 0;
	    int num2 = 1; 
	    int x = 0;

	   if (n > 0){/**/
	        while (x < n){
	            if ((x == 1) || (x==0)){
	                num1 = n;
	                System.out.println(x);
	                x++;

	            }
	            else {
	            	System.out.println( num1);
	                sum =  (num1 + num2);
	                num1 = num2;
	                num2 = sum;
	                x++;
	            }
	        }
	    }
	    else{
	        System.out.println("Please use a positive number.");
	    }
	        return 0;
	}

	       
	
	public static void main(String[] args) {
		 Fibo(32);
	}
}
