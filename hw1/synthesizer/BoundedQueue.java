package synthesizer;
import java.util.Iterator;
/**
 * An interface which declares all the methods that must be implemented by any class that implements BoundedQueue.<br>
 * <br>items can only be enqueued at the back of the queue, and can only be dequeued from the front of the queue.
 * Unlike our Deque, the BoundedDeque has a fixed capacity, and nothing is allowed to enqueue if the queue is full.<br>
 *
 * <br>[Side Note]<br><p>
 *     It can be a little unclear when to use an interface and when to use an abstract class.<br>
 *     One mostly accurate metaphor that might help is that<br>
 *     you can think of an <b>interface</b> as defining a <u>“can-do”</u> or an <u>“is-a”</u> relationship,
 *     whereas an <b>abstract class</b> should be a <u>stricter “is-a”</u> relationship.<br>
 *     The difference can be subtle, and you can often use one instead of the other.
 * </p>
 */
public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();     // return size of the buffer
    int fillCount();    // return number of items currently in the buffer
    void enqueue(T x);  // add item x to the end
    T dequeue();        // delete and return item from the front
    T peek();           // return (but do not delete) item from the front

    // Is the buffer empty (fillCount equals zero)?
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    // Is the buffer full (fillCount is the same as capacity)?
    default boolean isFull() {
        return capacity() == fillCount();
    }
}
