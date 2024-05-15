package synthesizer;
// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
import java.util.Arrays;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>

/**
 * Maintain one integer instance variable <b>first</b>
 * that stores the <u>index of the least recently inserted item</u>;<br>
 * <br> Maintain a second integer instance variable <b>last</b>
 * that stores the <u>index one beyond the most recently inserted item</u>.<br>
 * <br> To <b>insert</b> an item, put it at <u>index last</u> and <u>increment</u> last.<br>
 * <br> To <b>remove</b> an item, take it from <u>index first</u> and <u>increment</u> first.
 * When either index <b>equals capacity</b>, make it wrap-around by <u>changing the index to 0</u>.
 */
public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    private int first; // Index for the next dequeue or peek
    private int last; // Index for the next enqueue.
    private T[] rb; // Array for storing the buffer data.

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
         /* TODO:
             1. Create new array with capacity elements.
             2. first, last, and fillCount should all be set to 0.
             3. this.capacity should be set appropriately.
             4. Note that the local variable here shadows the field
                    we inherit from AbstractBoundedQueue,
                so you'll need to use this.capacity to set the capacity. */
        try {
            rb = (T[]) new Object[capacity];
        } catch (Exception e) {
            System.out.println("The capacity must be a positive integer!");
        }
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) throws RuntimeException {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue the oldest item in the ring buffer.
     * If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() throws RuntimeException {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T returnItem = rb[first];
        first = (first + 1) % capacity;
        fillCount -= 1;
        return returnItem;
    }

    /**
     * Return the oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    /**
     * Show the information of the buffer.
     */
    @Override
    public String toString() {
        return Arrays.toString(rb);
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    private class ArrayRingBufferIterator implements Iterator<T> {
        private int position;
        private int counter;

        public ArrayRingBufferIterator() {
            position = first;
            counter = 0;
        }

        @Override
        public boolean hasNext() {
            return counter < fillCount;
        }

        @Override
        public T next() {
            T returnItem = rb[position];
            position = (position + 1) % capacity;
            counter += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
}
