package edu.ncsu.csc216.er.util;

/**
 * Generic iterator for traversing a linked list
 * @author Josh Stetson
 *
 * @param <E> Element type to traverse
 */
public interface SimpleIterator<E> {
	
	/**
	 * Checks if there are more elements in the list
	 * @return True if there is another element in the list
	 */
	public boolean hasNext();
	
	/**
	 * Gets the next element in the list
	 * @return Next element in the list
	 */
	public E next();

}
