import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashAssignment {

    public static void main(String args[]) {
        List<List<Integer>> intLists = new LinkedList<>();

        intLists.add(new ArrayList<Integer>(Arrays.asList(3, 4, 9, 8, 12, 15, 7, 13)));
        intLists.add(new ArrayList<Integer>(Arrays.asList(15, 24, 50, 12, 3, 9)));
        intLists.add(new ArrayList<Integer>(Arrays.asList(78, 65, 24, 13, 9, 3, 12)));
        intLists.add(new ArrayList<Integer>(Arrays.asList(15, 78, 14, 3, 2, 9, 44, 12)));

        List<Integer> intResult = findCommonElements(intLists);

        System.out.println("Common elements of the integer list");
        System.out.println(intResult + "\n");

        List<List<String>> stringLists = new LinkedList<>();

        stringLists.add(new ArrayList<String>(Arrays.asList("a", "b", "d", "c", "h", "e")));
        stringLists.add(new ArrayList<String>(Arrays.asList("g", "b", "e", "j", "u", "z", "h", "d")));
        stringLists.add(new ArrayList<String>(Arrays.asList("y", "p", "b", "d")));

        List<String> stringResult = findCommonElements(stringLists);

        System.out.println("Common elements of the string list");
        System.out.println(stringResult);
//-----------Additional Cases      
        
        List<List<Integer>> intLists2 = new LinkedList<>();

        intLists2.add(new ArrayList<Integer>(Arrays.asList(23, 14, 7, 13)));
        intLists2.add(new ArrayList<Integer>(Arrays.asList(1, 23, 0, 2, 53, 92)));
        intLists2.add(new ArrayList<Integer>(Arrays.asList(7, 4, 5, 9, 23)));
        intLists2.add(new ArrayList<Integer>(Arrays.asList(23, 32, 34, 50)));

        List<Integer> intResult2 = findCommonElements(intLists2);

        System.out.println("\n" + "Common elements of the integer list");
        System.out.println(intResult2 + "\n");
        
        List<List<String>> stringLists2 = new LinkedList<>();

        stringLists2.add(new ArrayList<String>(Arrays.asList("a", "q", "x", "p", "c", "h", "f")));
        stringLists2.add(new ArrayList<String>(Arrays.asList("p", "r", "e", "j", "u", "z", "h", "d", "w", "x")));

        List<String> stringResult2 = findCommonElements(stringLists2);

        System.out.println("Common elements of the string list");
        System.out.println(stringResult2 + "\n");
        
        
        List<List<Integer>> intLists3 = new LinkedList<>();

        intLists3.add(new ArrayList<Integer>(Arrays.asList(0, 23, 25, 34, 55)));
 
 
        List<Integer> intResult3 = findCommonElements(intLists3);

        System.out.println("Common elements of the integer list");
        System.out.println(intResult3 + "\n");
        
        List<List<String>> stringLists3 = new LinkedList<>();
        
        stringLists3.add(new ArrayList<String>(Arrays.asList()));
        stringLists3.add(new ArrayList<String>(Arrays.asList()));
        stringLists3.add(new ArrayList<String>(Arrays.asList()));

        List<String> stringResult3 = findCommonElements(stringLists3);

        System.out.println("Common elements of the string list");
        System.out.println(stringResult3);
        
        
    }

    public static <T> List<T> findCommonElements(List<List<T>> collections) {
        if (collections.isEmpty()) {
        	return new ArrayList<T>(); 
        }
        
        else { 
            HashSet<T> setOfElements = new HashSet<>(); 
            Iterator<List<T>> listOfLists = collections.iterator(); 
        	Iterator <T> firstList = listOfLists.next().iterator();
        	
        	while (firstList.hasNext()) {
        		setOfElements.add(firstList.next());	
            }  
        	
	        while (listOfLists.hasNext()){ 
	             Iterator<T> commonElements = setOfElements.iterator(); 
	             HashSet<T> currentList = new HashSet<T>(listOfLists.next()); 
	             
	             while (commonElements.hasNext()) { 
	            	  if(!(currentList.contains(commonElements.next()))) { 
	                    commonElements.remove(); 
	                } 
	            }
	        } 	        	
	        return new ArrayList<T>(setOfElements);
        }
    }
}

