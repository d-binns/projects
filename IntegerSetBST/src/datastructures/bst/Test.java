package datastructures.bst;


public class Test {

	public static void main(String args[]) {
		int arr [] = {1,2,3,4,5,6,7,8,9,10,11};
		IntegerSet avlTree = new IntegerSet(arr);
		
		System.out.println("First Tree: " + avlTree);
		for(int i = 20; i >= 11; i--) {
			avlTree.add(i);
		}
		int arr2 [] = {12,22,23,24,25,35,26,27,28,29,30,15,51};
		IntegerSet avlTree2 = new IntegerSet(arr2);
		

		System.out.println("After adding 11 - 20 (with 11 being a duplicate): " + avlTree);
		System.out.println("Removed 99: " + avlTree.remove(99));
		System.out.println("Removed 2: " + avlTree.remove(2));
		System.out.println("After removing 2: " + avlTree);
		
		System.out.println("Height: " + avlTree.getHeight());
		System.out.println("Min: " + avlTree.getMin());
		System.out.println("Max: " + avlTree.getMax());
		System.out.println("Contains 79: " + avlTree.contains(79));
		System.out.println("Contains 10: " + avlTree.contains(10));
		System.out.println("Second Tree: " + avlTree2.toString());
		System.out.println("Intersection: " + avlTree2.intersection(avlTree));
		IntegerSet avlTree3 = avlTree.union(avlTree2);
		
		System.out.println("Union of AVL Tree 1 and 2 (AVL Tree 3): " + avlTree3);
		System.out.println("Height of AVL Tree 3: " + avlTree3.getHeight());
		
		System.out.println("Difference between AVL Tree 3 and 1: " + avlTree3.difference(avlTree));
		IntegerSet avlTree4 = (avlTree.symmetricDifference(avlTree2));
		System.out.println("Symmetric Difference of AVL Tree and AVL Tree 2: " + avlTree4);
	}
} 


