package com.gradescope.intlist;

import bridges.base.DLelement;

/*
 Models a doubly-linked list with dummy header and tail.
  Entries in a list have positions that begin with 1.
 */
public interface MyListInterface<E> {

	/** Adds a new entry to the end of this list.
       Entries currently in the list are unaffected.
       The list's size is increased by 1.
       @param element  The object to be added as a new entry. 
	 */
	public void add(E element);


	/** Adds a new entry at a specified position within this list.
       Entries originally at and above the specified position
       are at the next higher position within the list.
       The list's size is increased by 1.
       @param index       An integer that specifies the desired
                              position of the new entry.
       @param element     The object to be added as a new entry.
       @throws  IndexOutOfBoundsException if either
                newPosition < 1 or newPosition > getLength() + 1. 
	 */
	public void add(int index, E element);


	/** Removes the entry at a given position from this list.
       Entries originally at positions higher than the given
       position are at the next lower position within the list,
       and the list's size is decreased by 1.
       @param index  An integer that indicates the position of
                             the entry to be removed.
       @return  A reference to the removed entry.
       @throws  IndexOutOfBoundsException if either 
                givenPosition < 1 or givenPosition > getLength(). 
	 */
	public E remove(int index);

	/** Removes the given element from this list, if it is present in the list.
       Entries originally at positions higher than the given
       position are at the next lower position within the list,
       and the list's size is decreased by 1.
       @param element  The element to be removed.
       @return  True if the element was in the list and was removed, 
                false otherwise.
	 */
	public  boolean remove(E element);


	/** Removes all elements from this list. 
	 */  
	public void clear();


	/** Replaces the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the entry to be replaced.
       @param newEntry  The object that will replace the entry at the
                        position givenPosition.
       @return  The original entry that was replaced.
       @throws  IndexOutOfBoundsException if either
                givenPosition < 1 or givenPosition > getLength(). */
	public E replace(int givenPosition, E newEntry);


	/** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either
                givenPosition < 1 or givenPosition > getLength(). 
	 */
	public E getEntry(int givenPosition);


	/** Retrieves the last entry in this list.
   		@return  A reference to the indicated entry 
   			or null if the list is empty.
	 */
	public DLelement<E> getLast();


	/** Retrieves the first entry in this list.
		@return  A reference to the indicated entry 
			or null if the list is empty.
	 */
	public DLelement<E> getFirst();


	/** Retrieves all entries that are in this list in the order in which
       they occur in the list.
       @return  A newly allocated array of all the entries in the list.
                If the list is empty, the returned array is empty. 
	 */
	public E[] toArray();


	/** Sees whether this list contains a given entry.
       @param anEntry  The object that is the desired entry.
       @return  True if the list contains anEntry, or false if not. 
	 */
	public boolean contains(E anEntry);


	/** Gets the length of this list.
       @return  The integer number of entries currently in the list. 
	 */
	public int getLength();


	/** Determines whether this list is empty.
       @return  True if the list is empty, or false if not. 
	 */
	public boolean isEmpty();


	/** Locates the index of the given element by searching from the front 
        of the list.
      @return  The first index where the given element occurs in this list, 
              or -1 if the element is not in this list.
	 */
	public int indexOf(E element);


	/** Locates the index of the given element by searching from the back 
        of the list.
        @return  The last index where the given element occurs in this list, 
              or -1 if the element is not in this list.
	 */
	public int lastIndexOf(E element);

	/** Returns a reference to the node at a given position.
		 Precondition: The chain is not empty;
		               1 <= givenPosition <= numberOfEntries.	
	 * @param givenPosition
	 * @return The DLelement<E> at the given index position
	 * @throws IllegalArgumentException if index is outside of
	 * 			the range of this list.
	 */
	public DLelement<E> getNodeAt(int givenPosition);


	/** 
	     @return a String representation of this list. 
	 */  
	public String toString();
}