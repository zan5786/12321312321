package utilities;

/**
 * Interface for a queue data structure.
 * A queue is a collection of objects that are inserted and removed according to the first-in-first-out principle.
 * @param <E> the type of elements held in this queue
 */
public interface QueueADT<E> {

    /**
     * Inserts the specified element into this queue.
     * Pre-condition: The queue is not full, or it can dynamically resize.
     * Post-condition: The element is added to the end of the queue.
     *
     * @param item the element to add.
     * @throws NullPointerException if the specified item is null.
     */
    void enqueue(E item) throws NullPointerException;

    /**
     * Retrieves and removes the head of this queue.
     * Pre-condition: The queue is not empty.
     * Post-condition: The head of the queue is removed.
     *
     * @return the head of this queue.
     * @throws NoSuchElementException if this queue is empty.
     */
    E dequeue();

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     * Pre-condition: None.
     * Post-condition: The queue remains unchanged.
     *
     * @return the head of this queue, or null if this queue is empty.
     */
    E peek();

    /**
     * Tests if this queue is empty.
     * Pre-condition: None.
     * Post-condition: The queue remains unchanged.
     *
     * @return true if this queue contains no elements; false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this queue.
     * Pre-condition: None.
     * Post-condition: The queue remains unchanged.
     *
     * @return the number of elements in this queue.
     */
    int size();
}

