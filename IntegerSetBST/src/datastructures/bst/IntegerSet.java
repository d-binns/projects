package datastructures.bst;

import java.util.LinkedList;
import java.util.Queue;

public class IntegerSet {

	private Node root;
    private int size;
    
    public IntegerSet() {
    	//Empty set
    	clear();
    }
    
    public IntegerSet(int array[]) {
    	//Add elements of array to Binary Search Tree
    	for (int i = 0; i < array.length; i++) {
    		add(array[i]);
    	}
    }
    
    public int magnitude() {
        return size;
    }
    
    public void clear() {
    	root = null;
    	size = 0;
    }
    
    public boolean isEmpty() {
    	return size == 0;
    }

	public boolean add(int item) {
		boolean added = false;
		if(isEmpty()) {
			root = new Node(item);
			size += 1;
			added = true;
		} else {
			added = add(null, root, item);
		}
		
		return added;
	}
	
	private boolean add(Node parent, Node current, int data) {
		boolean added = false;
		if(current == null) {
			if(data < parent.data) {
				parent.leftChild = new Node(data);
			} else {
				parent.rightChild = new Node(data);
			}
			size += 1;
			return true;
		} else if(data  < current.data) {
			added = add(current, current.leftChild, data);
		} else if(data > current.data) {
			added = add(current, current.rightChild, data);
		} else {
			return false;
		}
		
		fixHeight(current);
		
		if(added) {
			rebalance(parent, current);
		}
		
		return added;
	}
    
	public boolean remove(int item) {
		if(isEmpty()) {
			return false;
		} else if(size == 1 && root.data == item) {
			clear();
			return true;
		} else if(removeTraversal(null, root, item)){
			size -= 1;
			return true;
		} else {
			return false;
		}
	}
	
	private boolean removeTraversal(Node parent, Node current, int data) {
		boolean removed = true;
		if(current == null) {
			return false;
		} else if(data < current.data) {
			removed = removeTraversal(current, current.leftChild, data);
		} else if(data > current.data) {
			removed = removeTraversal(current, current.rightChild, data);
		} else {
			removeNode(parent, current);
		}
		
		fixHeight(current);
		
		if(removed) {
			rebalance(parent, current);
		}
		
		return removed;
	}
	
	private void removeNode(Node parent, Node current) {
		if(current.leftChild == null && current.rightChild == null) {
			//Remove leaf node
			removeCase1(parent, current);
		} else if(current.leftChild != null && current.rightChild == null) {
			//Remove node with no right child
			removeCase2(parent, current);
		} else if(current.leftChild == null && current.rightChild != null) {
			//Remove node with no left child
			removeCase3(parent, current);
		} else {
			//Remove node with both children
			removeCase4(parent, current);
		}
		
		fixHeight(parent);
		
	}
	
	private void removeCase1(Node parent, Node current) {
		if(parent == null) {
			root = null;
		} else if(parent.leftChild == current) {
			parent.leftChild = null;
		} else {
			parent.rightChild = null;
		}
	}
	
	private void removeCase2(Node parent, Node current) {
		if(parent == null) {
			root = root.leftChild;
		} else if(parent.leftChild == current) {
			parent.leftChild = current.leftChild;
		} else {
			parent.rightChild = current.leftChild;
		}
		current.leftChild = null;
	}
	
	private void removeCase3(Node parent, Node current) {
		if(parent == null) {
			root = root.rightChild;
		} else if(parent.leftChild == current) {
			parent.leftChild = current.rightChild;
		} else {
			parent.rightChild = current.rightChild;
		}
		current.rightChild = null;
	}
	
	private void removeCase4(Node parent, Node current) {
		Node leftMost = current.rightChild;
		Node leftMostParent = current;
		
		while(leftMost.leftChild != null) {
			leftMostParent = leftMost;
			leftMost = leftMost.leftChild;
		}
		
		current.data = leftMost.data;
	
		removeNode(leftMostParent, leftMost);
		rebalance(current, current.rightChild);
	}
	

    public boolean contains(int itemToFind) {
    	if(isEmpty()) {
			throw new EmptyCollectionException("Cannot find an item in an empty tree");
		}
    	Node temp = root;
    	while (temp != null) {
    		if(temp.data < itemToFind) {
    			temp = temp.rightChild;
    		}
    		else if (temp.data > itemToFind) {
    			temp = temp.leftChild;
    		}
    		else if (temp.data == itemToFind) {
    			return true;
    		}
    	}
    	return false;
    }

    public IntegerSet union(IntegerSet other) {
    	IntegerSet newTree = new IntegerSet();
    	int [] arr = new int [this.size];
    	int [] arr2 = new int [other.size];    	
    	arr = this.copyToArray(arr);
    	for (int i = 0; i < this.size; i++) {
    		newTree.add(arr[i]);
    	}
    	arr2 = other.copyToArray(arr2);
    	for (int i = 0; i < other.size; i++) {
    		newTree.add(arr2[i]);
    	}
    	
    	return newTree;
    }
    

	
	public int [] copyToArray(int [] arr) {
		StringBuffer sb = new StringBuffer();
		int count = 0;
		
		if(!isEmpty()) {
			Queue<Node> q = new LinkedList<>();
		
			q.add(root);
			Node current = null;
			arr[count] = root.data;
			count++;
			while(!q.isEmpty()) {
				
				current = q.remove();
				if(current.leftChild != null) {
					q.add(current.leftChild);
					arr[count] = current.leftChild.data;
					count++;				}
				
				 if(current.rightChild != null) {
					q.add(current.rightChild);
					arr[count] = current.rightChild.data;
					count ++;
				}
			}		
				sb.append(current);		
		}
		return arr;
	}
    
	private void inOrderTraverse(Node current, Queue<Node> q) {
			if (current != null) {
				if(current.leftChild != null) {
					inOrderTraverse(current.leftChild ,q);
				}
				q.add(current);
			
				if(current.rightChild != null) {
					inOrderTraverse(current.rightChild, q);
				}
			}
	}
	

    public IntegerSet intersection(IntegerSet other) {
    	IntegerSet newSet = new IntegerSet();
    	int [] arr = this.copyToArray(new int [this.size]);
    	for (int i = 0; i < this.size; i++) {
    		if(other.contains(arr[i])) {
    			newSet.add(arr[i]);
    		}
    	}
        return newSet;
    }
    
    public IntegerSet difference(IntegerSet other) {
    	IntegerSet newSet = new IntegerSet();
    	int [] arr = this.copyToArray(new int [this.size]);
    	for (int i = 0; i < this.size; i++) {
    		if(!other.contains(arr[i])) {
    			newSet.add(arr[i]);
    		}
    	}
        return newSet;
    }
    
    public IntegerSet symmetricDifference(IntegerSet other) {
    	int arr [] = this.copyToArray(new int [this.size]); 
    	int arr2[] = other.copyToArray(new int [other.size]); 
    	int index = 0;
    	int index2 = 0;
    		
 
		if(!isEmpty()) {
		Queue<Node> q = new LinkedList<>();
		Queue<Node> q2 = new LinkedList<>();
		inOrderTraverse(root,q);
		inOrderTraverse(other.root,q2); 
		IntegerSet newSet = new IntegerSet();
		
			for(int i = 0; i < size; i++) {
				arr[i] = q.remove().data;
			}
			for(int i = 0; i < other.size; i++) {
				arr2[i] = q2.remove().data;
			}
			
			while (index < size && index2 < other.size) {
				if(arr[index] > arr2[index2]  ) {
					newSet.add(arr2[index2]);
					index2++;
				}
				
				else if(arr2[index2] > arr[index]) {
					newSet.add(arr[index]);
					index++;
				}
				else if (arr[index] == arr2[index2]) {
					index++;
					index2++;
				}

			}
			if (index < size) {
				for(;index < size; index++) {
					newSet.add(arr[index]);
				}
			}
			else if (index2 < other.size) {
				for(;index2 < other.size; index2++) {
					newSet.add(arr2[index2]);
				}
			}
			return newSet;
		}
        	return null;
    }
    
    public int getMin() {
    	if(isEmpty()) {
			throw new EmptyCollectionException("Cannot retrieve min from an empty tree");
		}
    	Node temp = this.root;
    	if (this.size == 1) {
    		return root.data;
    	}
    	else {
    		while (temp.leftChild !=null) {
    			temp = temp.leftChild;
    		}
    		return temp.data;
    	}

    }
    
    public int getMax() {
    	if(isEmpty()) {
			throw new EmptyCollectionException("Cannot retrieve max from an empty tree");
		}
    	Node temp = this.root;
    	if (this.size == 1) {
    		return root.data;
    	}
    	else {
    		while (temp.rightChild !=null) {
    			temp = temp.rightChild;
    		}
    		return temp.data;
    	}
    	
    }

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{ ");
		inOrderToString(root,sb);
		sb.append(" }");
		return sb.toString();
	}
    
 
	public String levelOrderString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		
		if(!isEmpty()) {
			Queue<Node> q = new LinkedList<>();
			
			q.add(root);
			Node current = null;
			
			while(!q.isEmpty()) {
				
				current = q.remove();
				if(current.leftChild != null) {
					q.add(current.leftChild);
				}
				
				if(current.rightChild != null) {
					q.add(current.rightChild);
				}
				
				sb.append(current);
				if(!q.isEmpty()) {
					sb.append(", ");
				}
			}
			
		}
		
		sb.append("}");
		
		return sb.toString();
	}
	
	private void inOrderToString(Node current, StringBuffer sb) {
		if (current != null) {
			inOrderToString(current.leftChild, sb);
			if(current.leftChild != null) {
				sb.append(", ");
			}
			
			sb.append(current.data);
			
			if(current.rightChild != null) {
				sb.append(", ");
			}
			inOrderToString(current.rightChild, sb);
		}
	}
	

    
    //You may add any methods or constructors
    //to this class that you see fit.
	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(Node node) {
		if (node == null) {
			return -1;
		}
		return Math.max(node.leftHeight,  node.rightHeight);
	}
	
	private void fixHeight(Node node) {
		if(node != null) {
			node.leftHeight = getHeight(node.leftChild ) + 1;
			node.rightHeight = getHeight(node.rightChild) + 1; 
		}
	}
	
	private int balance (Node node) {
		//If the balance > 1 the imbalance is in the left subtree
		//If the balance < -1 the imbalance is in the right subtree
		return node.leftHeight - node.rightHeight;
	}
	
	private Node rightRotation(Node n) {
		Node c = n.leftChild;
		Node t2 = c.rightChild;
		
		c.rightChild = n;
		n.leftChild = t2;
		
		fixHeight(n);
		fixHeight(c); 
		return c;
		
	}
	
	private Node leftRotation(Node n) {
		Node c = n.rightChild;
		Node t2 = c.leftChild;
		
		c.leftChild = n;
		n.rightChild = t2;
		
		fixHeight(n);
		fixHeight(c); 
		return c;
		
	}
	
	private void rebalance(Node parent, Node node) {
		if (node == null) {
			return;
		}
		
		//Imbalance in left subtree
		if (balance(node) > 1) {
			//Handles case III 
			if(balance(node.leftChild) < 0) {
				//leftRotation
				node.leftChild = leftRotation(node.leftChild);
			}
			
			if (parent == null) {
				root = rightRotation(node);
			}
			else if (parent.leftChild == node) {
				parent.leftChild = rightRotation(node);
			}
			else {
				parent.rightChild = rightRotation(node);
			}
		}
		// Imbalance in right subtree
		else if(balance(node) < -1) {
			//Handles case IV
			if(balance(node.rightChild) > 0) {
				node.rightChild = rightRotation(node.rightChild);
			}
			
			if(parent == null) {
				root = leftRotation(node);
			}
			else if(parent.leftChild == node) {
				parent.leftChild = leftRotation(node);
			}
			else {
				parent.rightChild = leftRotation(node); 
			}
		}
	}
	
    private class Node {
    	private int data;
    	private Node leftChild;
    	private Node rightChild;
    	
    	private int leftHeight;
    	private int rightHeight;
    	
		public Node(int data) {
			this.data = data;
		}
		
		public String toString() {
			String formatter = "(%s | %s | %s)";
			String leftString = leftChild != null ? String.valueOf(leftChild.data) : "";
			String rightString = rightChild != null ? String.valueOf(rightChild.data) : "";
			return String.format(formatter, String.valueOf(data), leftString, rightString);
		}
    }
}