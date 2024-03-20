/** First part of project1A.
 *  deque implemented by linklist
 *  @author lin
 *
 * Invariants:
 * size: the size of the naked linked list
 * sentA and sentB: always points to the sentinel node
 * the first item: (if exist) always are the item next to sentA
 * the last item: (if exist) always are the item in front of sentB
 */

public class LinkedListDeque<T> {
    /** The naked-linked list class */
    public class StuffNode {
        /** The node points to both sides of an item */
        private StuffNode prev, next;
        private T item;

        /**
         * The constructor function of the StuffNode class
         * @param p the node points to the previous item
         * @param i the item
         * @param n the node points to the next item
         */
        public StuffNode(StuffNode p, T i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private StuffNode sentA, sentB;
    private int size;

    /** Create an empty LinkedListDeque */
    public LinkedListDeque() {
        sentA = new StuffNode(null, (T) "cake", null);
        sentA.next = new StuffNode(sentA, (T) "cookie", null);
        sentB = sentA.next;
        size = 0;
    }

    /** Adds an item to the front of the Deque */
    public void addFirst(T i) {
        sentA.next = new StuffNode(sentA, i, sentA.next);
        sentA.next.next.prev = sentA.next;
        size++;
    }

    /** Adds an item to the end of the Deque */
    public void addLast(T i) {
        sentB.prev = new StuffNode(sentB.prev, i, sentB);
        sentB.prev.prev.next = sentB.prev;
        size++;
    }

    /** To check whether the Deque is empty */
    public boolean isEmpty() {
        return sentA.next == sentB;
    }

    /** Return the size of the Deque */
    public int size() {
        return size;
    }

    /** Print each item of the Deque */
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        StuffNode p = sentA.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Remove the first item of the Deque
     * @return the item that have been removed
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentA.next.item;
        sentA.next = sentA.next.next;
        sentA.next.prev = sentA;
        size--;
        return first;
    }

    /**
     * Remove the last item of the Deque
     * @return the item that have been removed
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentB.prev.item;
        sentB.prev = sentB.prev.prev;
        sentB.prev.next = sentB;
        size--;
        return last;
    }

    /** Get the particular index of item in the Deque */
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        StuffNode p = sentA.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /**
     * The helper method of the recursion
     * @sourse PKUflyingPig
     */
    public StuffNode realGet(StuffNode p, int index) {
        if (index == 0) {
            return p;
        }
        p = p.next;
        return realGet(p, index - 1);
    }

    /** Recursive version of get method */
    public T getRecursive(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }
        return realGet(sentA.next, index).item;
    }

//    public static void main(String[] args) {
//        LinkedListDeque L = new LinkedListDeque();
//        L.addFirst(0);
//        L.addLast(7);
//        L.addLast(2);
//        L.addLast(1);
//        System.out.println("The size of Deque is: " + L.size());
//        System.out.print("The Deque before remove it's both sides is: ");
//        L.printDeque();
//        System.out.println("The second item is: " + L.get(1).item);
//        System.out.println("The third item is: " + L.getRecursive(2).item);
//        System.out.println("The item on the left side of Deque is: " + L.removeFirst().item);
//        System.out.println("The item on the left side of Deque is: " + L.removeLast().item);
//        System.out.println("The size of Deque now is: " + L.size());
//        System.out.print("The Deque after remove its both sides is: ");
//        L.printDeque();
//    }
    //
}

