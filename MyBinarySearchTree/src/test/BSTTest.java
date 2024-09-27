package test;

import datastructures.bst.BinarySearchTree;

public class BSTTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		
		bst.add(155);
		bst.add(90);
		bst.add(200);
		bst.add(175);
		bst.add(225);
		bst.add(165);
		bst.add(185);
		bst.add(160);
		bst.add(170);
		
		bst.remove(155);
		System.out.println(bst.levelOrderToString());
	}

}
