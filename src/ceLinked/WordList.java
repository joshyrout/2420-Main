package ceLinked;

import edu.princeton.cs.algs4.ResizingArrayStack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * WordList is a singly-linked list of Strings.
 * It is designed as a practice opportunity to
 * learn how to manipulate linked sturctures.
 * 
 * @author ..........
 */
public class WordList implements Iterable<String>  {
	private Node head; // first node of the list or null
	private Node tail; // last node of the list or null
	private int n;     // number of words in the list

	@Override
	public Iterator<String> iterator() {
		return new TestIterator(this);
	}

	private class TestIterator implements Iterator<String> {
		private Node pointer;
		private int count;

		public TestIterator(WordList word) {
			pointer = word.head;
			count = 0;
		}

		public boolean hasNext() {
			if(count < n){return true;}
			return false;
		}

		public String next() {
			String data = pointer.item;
			pointer = pointer.next;
			count++;
			return data;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Node of LinkedList that stores the item and a
	 * single reference to the next node.
	 */
	private class Node {
		private String item;
		private Node next;
	}
	
	/** 
	 * Adds a node containing the new item at the
	 * end of the list.
	 * 
	 * @param newItem
	 */
	public void append(String newItem) {
		// create a new node based on the word provided by the user
		Node newNode = new Node();
		newNode.item = newItem;
		
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
		n++;
	}
	
	/** 
	 * Adds a node containing the new item at the
	 * front of the list.
	 * 
	 * @param newItem
	 */
	public void prepend(String newItem) {
		Node newNode = new Node();
		newNode.item = newItem;

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}
		else {
			newNode.next = head;
			head = newNode;
		}
		n++;
	}
	
	/** 
	 * Returns the index of the first occurrence of the specified item.
	 * If the specified item in not part of the list
	 * the method indexOf returns -1
	 * 
	 * @param item
	 * @return index of the first occurrence of the item; -1 if the word was not found.
	 */
	public int indexOf(String item) {
		int index = 0;
		Node testNode = head;
		while(testNode.item != item) {
			testNode = testNode.next;
			index++;
			if(index == n){
				return -1;
			}
		}
		return index;
	}
	
	/** 
	 * Checks whether the list contains the given item.
	 * 
	 * @param item
	 * @return true if the item is contained in the list; false otherwise.
	 */
	public boolean contains(String item) {
		int index = 0;
		Node testNode = head;
		while(testNode.item != item) {
			testNode = testNode.next;
			index++;
			if(index == n){
				System.out.println(item + " is not included in the list");
				return false;
			}
		}
		System.out.println(item + " is included in the list");
		return true;
	}
	
	/**
	 * Returns the number of elements in the list
	 * @return the number of elements
	 */
	public int size() {
		return n;
	}
	
	/** 
	 * Determines whether the list is empty or not.
	 * @return true if there are no elements in the list.
	 */
	public boolean isEmpty() {
		return n == 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = head;
		
		while(current != null) {
			sb.append(current.item).append(" ");
			current = current.next;
		}
		return sb.toString();
	}


	
	/* * * * * * * * Test Client * * * * * * */
	public static void main(String[] args) {
		WordList list = new WordList();
		System.out.println("size: " + list.size());
		

		// Dynamically determine whether the list is empty. If so, print 
		// 'The list is empty.' otherwise print 'The list is not empty.'

		if(list.isEmpty()){
			System.out.println("TODO 1: The list is empty.");
		} else {
			System.out.println("TODO 1: The list is not empty.");
		}

		System.out.println();
		System.out.println("TODO 2: prepend 'ape'");
		list.prepend("ape");
		list.append("ant");
		list.append("bat");
		list.append("cow");
		list.append("dog");
		System.out.println("list: " + list);
		System.out.println();
		System.out.println("TODO 2: prepend 'auk'");
		list.prepend("auk");
		System.out.println("list: " + list);
		System.out.println("size: " + list.size());
		System.out.println();
		System.out.println("TODO 3: indexOf");
		System.out.println("Index of dog: " + list.indexOf("dog"));
		System.out.println("Index of auk: " + list.indexOf("auk"));
		System.out.println("Index of yak: " + list.indexOf("yak"));
		System.out.println();
		System.out.println("TODO 4: Contains");
		list.contains("cow");
		list.contains("yak");
		System.out.println();
		System.out.println("list: " + list);
		System.out.println("size: " + list.size());

		for (String i : list){
			System.out.print(i + " ");
		}

	}

}
