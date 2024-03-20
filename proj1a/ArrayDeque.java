/** Second part of project1A.
 *  deque implemented by array
 *  @author lin
 *
 * Invariants:
 * size: the size of the array
 * length: the length of the Deque
 * nextFirst and nextLast: the index of the next First/Last item
 * the first item: (if exist)always are the item next to nextFirst
 * the last item: (if exist)always are the item in front of nextLast
 */

public class ArrayDeque<T> {
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

    /** The constructor function of the copy of ArrayDeque class */
//    public ArrayDeque(ArrayDeque other) {
//        items = (T[]) new Object[other.length];
//        System.arraycopy(other.items, 0, items, 0, other.length);
//        size = other.size;
//        length = other.length;
//        nextLast = other.nextLast;
//        nextFirst = other.nextFirst;
//    }

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

    /** To move the index to the front of the current index */
    private int moveForward(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /** To move the index to the next to the current index */
    private int moveBackward(int index) {
        return (index + 1) % items.length;
    }

    /** Adds an item in front of the Deque */
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
    public T get(int index) {
        check();
        if (isEmpty() || index < 0 || index > size - 1) {
            return null;
        }
        index += moveBackward(nextFirst);
        return items[index % items.length];
    }

    /** Return the size of the Deque */
    public int size() {
        return size;
    }

    /** To check whether the Deque is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Print each item of the Deque */
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

//    public static void main(String[] args) {
//        ArrayDeque homo = new ArrayDeque();
//        homo.addFirst(4);
//        homo.addFirst(1);
//        homo.addFirst(1);
//        homo.addLast(5);
//        homo.addLast(1);
//        homo.addLast(4);
//        ArrayDeque origin = new ArrayDeque(homo);
//        System.out.print("The Deque now is: ");
//        homo.printDeque();
//        System.out.println("Checking the usage of Deque... : " + homo.check());
//        System.out.println("The 4th item is: " + homo.get(4));
//        System.out.println("Now I am going to remove the two sides items");
//        System.out.println("The removed items are:" + homo.removeFirst() +
//                            " " + homo.removeLast());
//        System.out.print("The Deque now is: ");
//        homo.printDeque();
//        System.out.print("The origin Deque is: ");
//        origin.printDeque();
//    }
}


