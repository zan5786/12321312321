package utilities;

/**
 * Interface for a stack data structure.
 * A stack is a collection of objects that are inserted and removed according to the last-in-first-out principle.
 * @param <E> the type of elements held in this stack
 */
public interface StackADT<E> {

    /**
     * Pushes an item onto the top of this stack.
     * Pre-condition: The stack is not full, or it can dynamically resize.
     * Post-condition: The item is added to the top of the stack.
     *
     * @param item the item to be pushed onto the stack.
     * @throws NullPointerException if the specified item is null.
     */
    void push(E item) throws NullPointerException;

    /**
     * Removes the item at the top of this stack and returns it.
     * Pre-condition: The stack is not empty.
     * Post-condition: The item at the top of the stack is removed.
     *
     * @return the item at the top of the stack.
     * @throws NoSuchElementException if this stack is empty.
     */
    E pop();

    /**
     * Looks at the item at the top of this stack without removing it.
     * Pre-condition: The stack is not empty.
     * Post-condition: The stack remains unchanged.
     *
     * @return the item at the top of the stack.
     * @throws NoSuchElementException if this stack is empty.
     */
    E peek();

    /**
     * Tests if this stack is empty.
     * Pre-condition: None.
     * Post-condition: The stack remains unchanged.
     *
     * @return true if and only if this stack contains no items; false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of items in this stack.
     * Pre-condition: None.
     * Post-condition: The stack remains unchanged.
     *
     * @return the number of items in this stack.
     */
    int size();
}
