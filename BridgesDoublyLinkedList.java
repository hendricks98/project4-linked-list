package com.gradescope.intlist;

import bridges.base.DLelement;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;

import com.gradescope.intlist.MyListInterface;

/**
 * This is the class skeleton that you should use for Project 4
 *   @author Jamel Hendricks
 *   Computer Science Department
 *   College of Engineering
 *   Virginia Commonwealth University
 */

public class BridgesDoublyLinkedList<E> implements MyListInterface<E> {
	
	@SuppressWarnings("rawtypes")
	private DLelement first, last;
	private int numElements = 0;
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BridgesDoublyLinkedList<String> bridgeList = new BridgesDoublyLinkedList<String>();
		
		bridgeList.add("1");
		bridgeList.add("2");
		bridgeList.add("3");
		bridgeList.add("4");
		bridgeList.add("5");
		bridgeList.add("6");
		bridgeList.add("7");
		bridgeList.add("8");
		bridgeList.add("9");
		bridgeList.add("10");
		bridgeList.add("11");
		bridgeList.add("12");
		bridgeList.add("13");
		bridgeList.add("14");
		bridgeList.add("15");
		bridgeList.add("16");
		bridgeList.add("17");
		bridgeList.add("18");
		bridgeList.add("19");
		bridgeList.add("20");
		
		bridgeList.add(1,"new first");
		bridgeList.remove("1");
		bridgeList.remove(20);
		bridgeList.clear();
		
		bridgeList.add("new list 1");
		bridgeList.add("new list 2");
		bridgeList.replace(1, "new list head");
		bridgeList.replace(2, "new list tail");
		
		
		System.out.println("Method tests:");
		System.out.println("=======================\n");
		System.out.println("getEntry(1): " + bridgeList.getEntry(1));
		System.out.println("getLast().getValue(): " + bridgeList.getLast().getValue());
		System.out.println("getFirst().getValue(): " + bridgeList.getFirst().getValue());
		bridgeList.add("foobar");
		System.out.println("contains('foobar') " + "Expecting: true, Actual: " + bridgeList.contains("foobar"));
		System.out.println("getLength(): " + bridgeList.getLength());	
		System.out.println("isEmpty(): " + bridgeList.isEmpty());	
		System.out.println("indexOf('new list head'): " + bridgeList.indexOf("new list head"));
		System.out.println("lastIndexOf('new list head'): " + bridgeList.lastIndexOf("new list head"));
		System.out.println("getNodeAt(3).getValue(): " + bridgeList.getNodeAt(3).getValue());
		System.out.println("toString(); " + bridgeList.toString());
		System.out.println("\n=======================");

		Bridges bridges = new Bridges(0,"hendricks98","413698040896");
		
		// add labels to each node to display in bridges
		for (int i = 1; i <= bridgeList.getLength(); i++) {
			bridgeList.getNodeAt(i).setLabel(bridgeList.getEntry(i).toString());
		}
		
		bridges.setDataStructure(bridgeList.first);
		bridges.setTitle("CMSC 256 SPRING 2019 Jamel Hendricks Project 4");
		
		try {
			bridges.visualize();
		} catch (IOException | RateLimitException e) {
			e.printStackTrace();
		} 
		
	}
	
	// default constructor - clear all entries, first, and last node pointers
	public BridgesDoublyLinkedList() {
		initializeDataFields();
	}
	
	// parameterized constructor - create a new doubly linked list with one element
	@SuppressWarnings("rawtypes")
	public BridgesDoublyLinkedList(DLelement element) {
		initializeDataFields();
		first = element;
		last = element;
		numElements = 1;
	}
	
	// add an element to the list
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void add(E element) {
		
		// create a new node for the element and set its value
		DLelement newNode = new DLelement();
		newNode.setValue(element);
		
		// if the list is empty, set first and last pointers to the new node
		if (isEmpty()) {
			first = newNode;
			last = newNode;
			numElements++;
		} else {
		// if not empty, add new node to the end of the list and connect the point the previous node to new node
			numElements++;
			DLelement nodeBefore = getNodeAt(numElements - 1);
			nodeBefore.setNext(newNode);
			newNode.setPrev(nodeBefore);
			newNode.setNext(null);
			last = newNode;
		}
	}
	
	// add a new node to a specific index in the list
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void add(int index, E element) {
		
		// make sure the index is a legal position, if not throw error
		if (index < 1 || index > getLength() +1) {
			throw new IndexOutOfBoundsException("Invalid list position!");
		}
		
		// create a new node with the passed value
		DLelement newNode = new DLelement();
		newNode.setValue(element);
		
		// if adding to position 1
		if (index == 1) {
			// if the list is empty add a new node and set first and last
			if (isEmpty()) {
				first = newNode;
				last = newNode;
				numElements++;
			} else {
			// set the old first node's previous node to the new node and set first to the new node
				numElements++;
				DLelement nodeBefore = newNode;
				nodeBefore.setNext(first);
				first.setPrev(nodeBefore);
				nodeBefore.setNext(first);
				first = nodeBefore;
			}
		// if adding to the last position
		} else if (index == numElements) {
			
			// set the old last node's next node to the new node and set last to the new node
			last.setNext(newNode);
			newNode.setPrev(last);
			last = newNode;			
			numElements++;
		
		// if adding to a position greater than list size
		} else if(index > numElements) {
			
			// set the last node to the new node
			last.setNext(newNode);
			newNode.setPrev(last);
			last = newNode;
			numElements++;
		
		// if adding a node in between other nodes
		} else {
			
			// get the node before and after the new node and set ties
			numElements++;
			DLelement nodeBefore = getNodeAt(index - 1);
			DLelement nodeAfter = getNodeAt(index);
			
			nodeBefore.setNext(newNode);
			newNode.setPrev(nodeBefore);
			newNode.setNext(nodeAfter);
			nodeAfter.setPrev(newNode);
			
			last.setNext(null);
			
		}		
	}
	
	// remove a node at a passed index
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public E remove(int index) {
		
		// make sure the index is a legal argument
		if (index < 1 || index > getLength()) {
			throw new IndexOutOfBoundsException("Invalid index for removal!");
		}
		
		// if removing the first and only node
		if (numElements == 1 && index == 1) {
			
			// set first and last to null and return the data
			E oldData = (E) first.getValue();
			first = null;
			last = null;
			numElements = numElements - 1;
			return oldData;
		}
		
		// if removing the first node
		if (index == 1) {
			
			// set the second node to the first node and return the old first node's data
			E oldData = (E) first.getValue();
			DLelement newFirst = first.getNext();
			newFirst.setPrev(null);
			first = newFirst;
			numElements = numElements - 1;
			return oldData;
		}
		
		// if deleting the last node
		if (index == numElements) {
			
			// set the last node to the next to last node and return the old last node's data
			E oldData = (E) last.getValue();
			DLelement newLast = getNodeAt(numElements-1);
			last = newLast;
			last.setNext(null);
			
			numElements = numElements - 1;
			return oldData;
		
		// if removing a middle node
		} else {
			
			// get the nodes before and after the deleting node and point them to each other (remvoing the middle node)
			// and return the middle node's data
			E oldData = getNodeAt(index).getValue();
			DLelement oldNode = getNodeAt(index);
			DLelement previousNode = getNodeAt(index -1);
			DLelement nextNode = getNodeAt(index +1);
			
			previousNode.setNext(nextNode);
			nextNode.setPrev(previousNode);
			
			oldNode.setPrev(null);
			oldNode.setNext(null);
			
			numElements = numElements -1;
			return oldData;
		}
	}
	
	// remove  a specific element
	@Override
	public boolean remove(E element) {
		
		// find the index of the node and remove the node at that index
		try {
			remove(indexOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// clear all nodes from list
	@Override
	public void clear() {
		initializeDataFields();
	}
	
	
	// replace a node entry at an index with a new entry
	@SuppressWarnings("unchecked")
	@Override
	public E replace(int givenPosition, E newEntry) {
		
		// make sure index is a legal position
		if (isEmpty() || (givenPosition > getLength()) || givenPosition < 1) {
			throw new IndexOutOfBoundsException();
		}
		
		// get the original node's data
		E original = getNodeAt(givenPosition).getValue();
		
		if (givenPosition == indexOf((E) last.getValue())) {
			remove(givenPosition);
			add(newEntry);
		} else {
			remove(givenPosition);
			add(givenPosition, newEntry);
		}
		
		return original;
	}

	@Override
	public E getEntry(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition <= numElements))	{
			assert !isEmpty();
			return getNodeAt(givenPosition).getValue();
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DLelement<E> getLast() {
		return last;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DLelement<E> getFirst() {
		return first;
	}

	@Override
	public E[] toArray() {
		@SuppressWarnings("unchecked")
		E[] arr = (E[]) new Object[getLength()];
		
		for (int i = 1; i <= numElements; i++) {
			arr[i-1] = getNodeAt(i).getValue();
		}
		
		return arr;
	}

	@Override
	public boolean contains(E anEntry) {
		for (int i = 1; i <= numElements; i++) {
			if (getNodeAt(i).getValue().equals(anEntry)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int getLength() {
		return numElements;
	}

	@Override
	public boolean isEmpty() {
		if (numElements == 0 && first == null && last == null) {
			return true;
		}
		return false;
	}

	@Override
	public int indexOf(E element) {
		
		for (int i = 1; i <= numElements; i++) {
			if (getNodeAt(i).getValue().equals(element)) {
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public int lastIndexOf(E element) {
		
		for (int i = numElements; i >= 1; i = i - 1) {
			if (getNodeAt(i).getValue().equals(element)) {
				return i;
			}
		}
		
		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DLelement<E> getNodeAt(int givenPosition) {		
		if (isEmpty() || givenPosition == 0 || givenPosition > numElements) {
			throw new IllegalArgumentException();
		}
		
		@SuppressWarnings("rawtypes")
		DLelement currentNode = first;

		// Traverse the chain to locate the desired node
		// (skipped if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNext();

		assert currentNode != null;

		return currentNode;
	}
	
	// Initializes the class's data fields to indicate an empty list.
	private void initializeDataFields()  {
		first = null;
		last = null;
		numElements = 0;
	}
	
	public String toString() {
		String list = (String) getNodeAt(1).getValue();
		
		for (int i = 2; i <= numElements; i++) {
			list = (String) list + ", " + getNodeAt(i).getValue();
		}
		
		return list;
	}
}
