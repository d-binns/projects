import java.util.Arrays;
import java.util.Random;

public class SortedList<T extends Comparable<? super T>> {
	
	//Fields
	private Node <T> head;
	private Node <T> tail;
	
	private int size;
	private boolean reversedList = false;
	
	//Constructor
	public SortedList() {
		clear();
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public void add(T data) {
		if (reversedList) {
			reverseAdd(data);
			return;
		}
		if (isEmpty()) {
			addToEmptyList(data);
		}
		
		else if (data.compareTo(head.data) <= 0) {
			prepend(data);
		}
		else if (data.compareTo(tail.data)>= 0) {
			append(data);
		}
		else {
			Node<T> temp = head.next;
			for(int index = 1; index < size ; index++) {
				if ((data.compareTo(temp.data)<= 0 ) && (data.compareTo(temp.prev.data) >= 0) ) {
					temp = getNode(index);
					Node<T> newNode = new Node<T>(data);
					temp.prev.next = newNode;
					newNode.prev = temp.prev;
					temp.prev = newNode;
					newNode.next =temp;
					size += 1;
				break;
				}
				temp = temp.next;
			}
		} 
		
	}
	
	public T removeAt(int index) {
		if(isEmpty()) {
			throw new EmptyCollectionException("Cannot remove from an empty collection");
		}
		validateIndex(index, 0, size - 1);
		if(index == 0) {
			return removeFirst();
		}
		else if (index == size - 1) {
			return removeLast();
		}
		else {
			Node<T> toRemove = getNode(index);
			toRemove.prev.next = toRemove.next;
			toRemove.next.prev = toRemove.prev;
			toRemove.next = null;
			toRemove.prev = null;
			size -= 1;
			return toRemove.data;
	}
		}
	
		public T removeFirst() {
			if(isEmpty()) {
				throw new EmptyCollectionException("Cannot remove from an empty collection");
			}
			Node<T> temp = head;
			if (size == 1) {
				clear();
			}
			else {
				head = head.next;
				head.prev.next = null;
				head.prev = null;
				size -= 1;
				return temp.data;
			}
			return temp.data;
			
		}
		
		public T removeLast() {
			if(isEmpty()) {
				throw new EmptyCollectionException("Cannot remove from an empty collection");
			}
			Node<T> temp = tail;
			if (size == 1) {
				clear();
			}
			else {
				tail = tail.prev;
				tail.next.prev = null;
				tail.next = null;
				size -= 1;
			}
			return temp.data;
		}
	
	public T get(int index) {
		if(isEmpty()) {
			throw new EmptyCollectionException("Cannot remove from an empty collection");
		}
		validateIndex(index, 0, size - 1);
		Node<T> temp = getNode(index);
		return temp.data;
	}
		
	
	
	public boolean contains(T data) {
		if (find(data) !=  -1) {
			return true;
		}
		
		return false;
	}
	
	public int find(T data) {
		Node<T> temp = head;
		for (int index = 0; index < size; index++) {
			if (temp.data.compareTo(data) == 0) {
				return index;
			}
			temp = temp.next;
		}
		return -1;
	}
	
	public int count(T data) {
		int counter = 0;
		Node<T> temp = head;
		for(int i = 0; i< size; i++) {
			if (temp.data.compareTo(data) == 0) {
				counter ++;
			}
			temp = temp.next;
		}
		return counter;
	}
	
	public void removeAll(SortedList<T> otherList) {
		if (reversedList) {
			reverseRemoveAll(otherList);
			return;
		}
		Node<T> temp = head;
		Node<T> temp2;
		Node<T> otherTemp = otherList.head;
		int index = 0;
		while ((temp != null)||(otherTemp !=null)) {
			if (temp.data.compareTo(otherTemp.data) == 0) {
				temp2 = temp.prev;
				removeAt(index);
				temp = temp2 == null ? head : temp2.next;	
			}
			else if (otherTemp.data.compareTo(temp.data) > 0) {
				temp = temp.next;
				index++;
			}
			else if (temp.data.compareTo(otherTemp.data) > 0) {
				otherTemp = otherTemp.next;
			}
			if ( (temp == null)) {
				break;
			}
			
			if (otherTemp == null) {
				break;
			}
		}
	}
	
	private void addToEmptyList(T obj) {
		head = new Node<T>(obj) ;
		tail = head;
		size += 1;	
	}
	private void append(T obj) {
		if (isEmpty()) {
			addToEmptyList(obj);
		}
		Node<T> newNode = new Node<T>(obj);
		newNode.data = obj;
		tail.next= newNode;
		newNode.prev = tail;
		tail = newNode;
		size+= 1;
	}
	
	
	private void prepend(T obj) {
		if (isEmpty()) {
			addToEmptyList(obj);
		}
		else {
			Node <T> newNode = new Node <T> (obj);
			newNode.data = obj;
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
			size += 1;
		}
	}
	
	public void reverse() {
		Node <T> temp = head;
		SortedList <T> tempList = new SortedList <T>();
		Node <T> tempNode;
		if(reversedList == false ) { 
			for (int i = 0; i < size; i ++) {
				tempList.add(temp.data);
				temp= temp.next;
				}
			}
			else {
				for (int i = 0; i < size; i ++) {
					tempList.reverseAdd(temp.data);
					temp= temp.next;
				}
			}
		clear();
		reversedList = reversedList ? false : true;	
		tempNode = tempList.head;
		for (int i = 0; i < tempList.size; i ++) {
			if(!reversedList ) { 
				add(tempNode.data);
				tempNode = tempNode.next;
			}
			else { 
				reverseAdd(tempNode.data);
				tempNode = tempNode.next;
			}
		}
		tempList.clear();	
	}
	
	public void reverseAdd(T data) {
		if (isEmpty()) {
			addToEmptyList(data);
		}
		else if (data.compareTo(head.data) >= 0) {
			prepend(data);
		}
		else if (data.compareTo(tail.data)<= 0) {
			append(data);
		}
		else {
			Node<T> temp = head.next;
			for(int index = 1; index < size ; index++) {
				if ((data.compareTo(temp.data)>= 0 ) && (data.compareTo(temp.prev.data) <= 0) ) {
					temp = getNode(index);
					Node<T> newNode = new Node<T>(data);
					temp.prev.next = newNode;
					newNode.prev = temp.prev;
					temp.prev = newNode;
					newNode.next =temp;
					size += 1;
				break;
				}
				temp = temp.next;
			}
		} 
		
	}
	
	public void reverseRemoveAll(SortedList<T> otherList) {
		Node<T> temp = head;
		Node<T> temp2;
		Node<T> otherTemp = otherList.head;
		int index = 0;
		while ((temp != null)||(otherTemp !=null)) {
			if (temp.data.compareTo(otherTemp.data) == 0) {
				temp2 = temp.prev;
				removeAt(index);
				temp = temp2 == null ? head : temp2.next;
			}
			else if (otherTemp.data.compareTo(temp.data) < 0) {
				temp = temp.next;
				index++;
			}
			else if (temp.data.compareTo(otherTemp.data) < 0) {
				otherTemp = otherTemp.next;
			}
			if ( (temp == null)) {
				break;
			}
			
			if (otherTemp == null) {
				break;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("((");
		if(!isEmpty()) {
			Node<T> temp = head;
			while (temp.next != null) {
				sb.append(temp.data.toString());
				sb.append(", ");
				temp = temp.next;
			}
			sb.append(temp.data.toString());
		}
		sb.append("))");
		return sb.toString();
	}
	
	private void validateIndex(int index, int lowerBound, int upperBound) {
		if(!(index >= lowerBound && index <= upperBound)) {
			throw new IndexOutOfBoundsException(String.format("Index must be between %d and %d", lowerBound, upperBound));
		}
	}
	
	private Node <T> getNode(int index){// ----------------------------------look at the 
		Node<T> temp = index <= size/2 ? head : tail;
		if(temp == head) {
			for(int i = 0; i < index; i++) {
				temp = temp.next;
			}
			return temp;
		}
		else {
			for(int i = size - 1; i > index; i--) {
				temp = temp.prev;
			}
			return temp;
		}
	}
	
	//Your node class.  You may move it to a different file but
	//you will need to change this to public class Node
	private class Node <E> {
		Node<E> next;
		Node<E> prev;
		E data;
		public Node (E data) {
			this.data = data;
		}
		
		
	}
	

}
