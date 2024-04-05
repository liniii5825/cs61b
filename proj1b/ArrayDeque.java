/** Second part of project1A.
 *  Deque implemented by array
 *  @author lin
 *
 * Invariants:
 * size: the size of the array
 * length: the length of the Deque
 * nextFirst and nextLast: the index of the next First/Last item
 * the first item: (if exist)always are the item next to nextFirst
 * the last item: (if exist)always are the item in front of nextLast
 */

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size, first, nextFirst, nextLast;

    /** The constructor function of the ArrayDeque class */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        first = nextFirst;
    }

    /** To check whether the usage of Deque is good */
    private void check() {
        if (items.length >= 16 && (double)size / items.length < 0.25) {
            T[] a = (T[])new Object[items.length / 4];
            int length = items.length - first;
            System.arraycopy(items, first, a,
                    a.length / 2 - 1, length);
            if (length != items.length) {
                System.arraycopy(items, 0, a,
                        a.length / 2 - 1 + length, first);
            }
            items = a;
            nextFirst = (items.length / 2 - 1 - 1 + items.length) % items.length;
            first = (nextFirst + 1) % items.length;
            nextLast = (nextLast + size + 1) % items.length;
        }
    }

    /** Expend twice the size of array */
    private void resize() {
        T[] a = (T[]) new Object[items.length * 2];
        int length = items.length - first;
        if (length < size) {
            System.arraycopy(items, first, a, a.length / 2 - 1, length);
            int left = size - length;
            System.arraycopy(items, nextLast - 1, a, a.length / 2 - 1 + length, left);
        } else {
            System.arraycopy(items, first, a, a.length / 2 - 1, size);
        }
        items = a;
        nextFirst = (items.length / 2 - 1 - 1 + items.length) % items.length;
        first = (nextFirst + 1) % items.length;
        nextLast = (nextLast + size + 1) % items.length;
    }

    /** To move the index to the front of the current index */
    private int moveForward(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /** To move the index to the next to the current index */
    private int moveBackward(int index) {
        return (index + 1) % items.length;
    }

    /** Adds an item in front of the Deque */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize();
        }
        first = nextFirst;
        items[nextFirst] = item;
        nextFirst = moveForward(nextFirst);
        size++;
    }

    /** Adds an item to the end of the Deque */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize();
        }
        items[nextLast] = item;
        nextLast = moveBackward(nextLast);
        size++;
    }

    /**
     * Remove the first item of the Deque
     * @return the item that have been removed
     */
    @Override
    public T removeFirst() {
        check();
        if (isEmpty()) {
            return null;
        }
        T trash = items[first];
        items[first] = null;
        first = moveBackward(first);
        nextLast = moveBackward(nextLast);
        size--;
        return trash;
    }

    /**
     * Remove the last item of the Deque
     * @return the item that have been removed
     */
    @Override
    public T removeLast() {
        check();
        if (isEmpty()) {
            return null;
        }
        nextLast = moveForward(nextLast);
        T trash = items[nextLast];
        items[nextLast] = null;
        size--;
        return trash;
    }

    /** Get the particular index of item in the Deque */
    @Override
    public T get(int index) {
        check();
        if (isEmpty() || index < 0 || index > size - 1) {
            return null;
        }
        index += moveBackward(nextFirst);
        return items[index % items.length];
    }

    /** Return the size of the Deque */
    @Override
    public int size() {
        return size;
    }

    /** To check whether the Deque is empty */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Print each item of the Deque */
    @Override
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        int ptr = nextFirst + 1;
        while (ptr != nextLast) {
            System.out.print(items[ptr] + " ");
            ptr = moveBackward(ptr);
        }
        System.out.println();
    }
}


