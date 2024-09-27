package IntegerSetCode;

import java.util.Arrays;

public class IntegerSet {

    // The array that represents the set.
    private final int set[];

    /**
     * The constructor for IntegerSet. When an IntegerSet is created it must be
     * initialized with an integer array. The set will then pull out the duplicated
     * items and keep the unique integers.
     * 
     * @param arr
     *            The array to create the set from.
     */
    public IntegerSet(int arr[]) {
		if (arr == null) {
			throw new IllegalArgumentException("The array must not be null");
		}
		set = uniqueElements(arr);
    }

    /**
     * This is the size of the set which, in this case, is just the length of the
     * array.
     * 
     * @return The length of the set.
     */
    public int magnitude() {
		return set.length;
    }

    /**
     * This method is private and is used to help set up the set array. An integer
     * set is one in which the elements are unique (no duplicates) and are sorted.
     * 
     * @param arr
     *            The array that will be used to retrieve the unique elements from.
     * @return The new integer array that contains the unique elements from arr.
     */
    private int[] uniqueElements(int arr[]) {
    	
    	int temp[] = IntArrayOperations.getCopy(arr);
    	sort(arr);
    	int index= 0;
    	
    	for(int i = 0; i < arr.length; i++) {
    		if (i == 0) {
    			temp[index] = arr[i];
    			index++;
    		}
    		else {
    			if (arr[i] != temp[index - 1]) {
    				temp[index] = arr[i];
    				index++;
    			}
    		}
    	}
    	temp = IntArrayOperations.truncateArray(temp, index);
    	return temp;
    }

    /**
     * This method returns whether or not value is located in the set. If the value
     * is in the set then return true otherwise return false. <br />
     * Example:
     * <pre>
     * 		IntegerSet iS1 = new IntegerSet([1,2,3,4]); 
     * 		iS1.contains(3); //returns true
     * 		iS2.contains(6); //returns false
     * </pre>
     * 
     * @param value
     *            The integer to look for.
     * @return True if value is located in the set otherwise false.
     */
    public boolean contains(int value) {
    	if(binarySearch(set, value) >= 0) {
    		return true; 
    	}
    	return false;
    }

    /**
     * A union of two sets is a new set that contains all elements from both sets.
     * This method takes another set and unions it with the set that calls this
     * method. A new IntegerSet is returned that contains the union of both sets.<br />
     * Example:
     * <pre>
     * 		IntegerSet is1 = new IntegerSet([1, 2, 3, 4]); 
     * 		IntegerSet is2 = new IntegerSet([3, 4, 5, 6, 7, 8]);
     * 		is1.union(is2) //returns new IntegerSet([1, 2, 3, 4, 5, 6, 7, 8]);
     * </pre>
     * 
     * @param otherSet
     *            The set to be unioned with.
     * @return A new IntegerSet that is the union of the calling set with the
     *         otherSet.
     */
    public IntegerSet union(IntegerSet otherSet) {
    	int [] temp = new int [magnitude() + otherSet.magnitude()];
    	int index = 0;
    	for (int i = 0; i < magnitude()  ;  i++){
    		temp[index] = set[i]; 
    		index ++;
    	}
    	for(int j = 0; j < otherSet.magnitude() ; j++) {
    		temp[index] = otherSet.set[j];
    		index++;
    	}
    	IntegerSet newSet = new IntegerSet (temp);
		return newSet;
    }

    /**
     * The intersection of two sets is a new set that contains elements that occur
     * in both sets. This method takes another set and intersects it with the set
     * that calls this method. A new IntegerSet is returned that contains the
     * intersection of the two sets. <br />
     * Example:
     * <pre>
     * 		IntegerSet is1 = new IntegerSet([1,2,3,4]);
     * 		IntegerSet is2 = new IntegerSet([3,4,5]);
     * 		is1.intersection(is2) //returns new IntegerSet([3, 4]);
     * </pre>
     * 
     * @param otherSet
     *            The set to be intersected with.
     * @return A new IntegerSet that is the intersection of the calling set with the
     *         otherSet.
     */
    public IntegerSet intersection(IntegerSet otherSet) {

    	int temp [];
    	if (magnitude() >= otherSet.magnitude()) {
    		temp = IntArrayOperations.getCopy(set);
    	}
    	else {
    		temp = IntArrayOperations.getCopy(otherSet.set);
    	}
    	
    	int index = 0;
    	for (int i = 0; i < (magnitude()); i++) {
    		if(binarySearch(otherSet.set, set[i]) >= 0) {
    			temp[index] = set[i]; 
    			index++;
    		}

    	}
    	temp = IntArrayOperations.truncateArray(temp, index);
    	IntegerSet newSet = new IntegerSet (temp);
		return newSet;
    }

    /**
     * Returns a string representation of an IntegerSet type. The returned string
     * will have the following structure.
     * 
     * set{ elements in the set separated by a comma }.
     */
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("set{ ");
		for (int i = 0; i < set.length; i++) {
			sb.append(set[i]);
			if (i < set.length - 1) {
			sb.append(", ");
			}
		}
		sb.append(" }");
		return sb.toString();
    }
    
    

//Merge Sort
	public static void sort(int arr[]) {
		mergeSort(arr, new int[arr.length], 0, arr.length - 1);
	}
	
	private static void mergeSort(int arr[], int tempArray[], int first, int last){
		if(first < last) {
			int mid = first + (last - first) / 2;
			mergeSort(arr, tempArray, first, mid);
			mergeSort(arr, tempArray, mid + 1, last);
			merge(arr, tempArray, first, mid, last);
		}
	}
	
	private static void merge(int arr[], int tempArray[], int first, int mid, int last) {
		
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;	
		int index = 0;

		while(beginHalf1 <= endHalf1 && beginHalf2 <= endHalf2) {
			if(arr[beginHalf1] <= arr[beginHalf2]) {
				tempArray[index] = arr[beginHalf1];
				beginHalf1++;
				index++;	
			}	
			else {
				tempArray[index] = arr[beginHalf2];
				beginHalf2++;
				index++;
			}
		}
				while(beginHalf1 <= endHalf1) {
					tempArray[index] = arr[beginHalf1]; 
					index++;
					beginHalf1++;				
				}	
				while(beginHalf2 <= endHalf2) {
					tempArray[index] = arr[beginHalf2]; 
					index++;
					beginHalf2++;
				}	
				
			for(int i = 0, j = first; i <= (last - first); i++, j++) {
				arr[j] = tempArray[i];
				}
	}	
	
//Binary Search	
public static int binarySearch(int[] arr, int search) {
	int low = 0;
	int high = arr.length -1;
	int mid = 0;

	while ( low <= high){
		mid = (low + high)/2;
		if (arr[mid] < search) {
			low = mid + 1;
			}
		else if (arr[mid] > search) {
			high = mid - 1;
		}
		else {
			return mid;	 
		}
	}
	return -1;
}

}
