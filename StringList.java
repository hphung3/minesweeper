/**
 * A List that can hold strings.
 * 
 * @author Eric
 * @version 1.0
 */
public interface StringList {
	
	/**
	 * Add an element to the end of the list.
	 * 
	 * @param string
	 *            element
	 */
	void add(String string);
	
	/**
	 * Add an element to the list at an index.
	 * 
	 * @param index
	 *            index to add the element
	 * @param string
	 *            element
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index > size())
	 */
	void add(int index, String string) throws IndexOutOfBoundsException;
	
	/**
	 * Get the element at the index position
	 * 
	 * @param index
	 *            index to get the element of
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index > size())
	 */
	String get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Get the size of the list
	 * 
	 * @return the list size
	 */
	int size();
	
	/**
	 * Get index of element.
	 * 
	 * @param e
	 *            element
	 * @return index of element, or -1 if not in list
	 */
	int indexOf(String e);
	
	/**
	 * Checks if the list contains an element
	 * 
	 * @param e
	 *            element
	 * @return true if element in list, false otherwise
	 */
	boolean contains(String e);
	
	/**
	 * Remove the element at the index position
	 * 
	 * @param index
	 *            index to remove the element of
	 * @return the removed element
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index > size())
	 */
	String remove(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Remove all elements from the list.
	 */
	void clear();
}
