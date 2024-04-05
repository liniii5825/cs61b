/** First part of project1A/
 * deque implemented by linklist
 * @auther lin
 *  Invariants:
 *  size: the size of the naked linked list
 *  sentA and sentB: always points to the sentinel node 
 *  the first item: (if exist) always are the item next to sentA
 *  the last item: (if exist) always are the item in front of sentB
 */

public class LinkedListDeque<T> {
        /** The naked-linked list class */
        private class StuffNode {
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
                sentA.next = new StuffNode(sentA, (T) "coke", null);
                sentB = sentA.next;
                size = 0;
        }

        /** Create a LinkedListDeque with single item */
        public LinkedListDeque(T i) {
                LinkedListDeque();
                sentA.next = new StuffNode(sentA, i, sentA.next);
                sentB.prev = sentA.next;
        }

        /** Adds an item to the front of the Deque */
        public void addFirst(T i) {
                StuffNode tmp = sentA.next;
                sentA.next = new StuffNode(sentA, i, sentA.next);
                tmp.prev = sentA.next;
                size += 1;
        }

        /** Adds an item to the end of the Deque */
        public void addLast(T i) {
                StuffNode tmp = sentB.prev;
                sentB.prev = new StuffNode(sentB.prev, i, sentB);
                tmp.next = sentB.prev;
                size += 1;
        }

        /** Check whether the Deque is empty */
        public boolean isEmpty() {
                return sentA.next == sentB;
        }

        /** Return the size of the Deque */
        public int size() {
                return size;
        }

        /** Print the whole Deque (with each item) */
        public void printDeque() {
                if (isEmpty()) {
                        return;
                }
                StuffNode p = sentA.next;
                while (p != null) {
                        System.out.print(p.item + ' ');
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
                StuffNode first = sentA.next;
                T i = first.item;
                first = first.next;
                first.prev = sentA;
                size -= 1;
                return i;
        }

        /** 
         * Remove the last item of the deque
         * @return the item that have been removed
         */
        public T removeLast() {
                if (isEmpty()) {
                        return null;
                }
                StuffNode last = sentB.prev;
                T i = last.item;
                last = last.prev;
                last.next = sentB;
                size -= 1;
                return i;
        }

        /** Get the particular item by index in the Deque */
        public T get(int index) {
                if (isEmpty() || index < 0 || index >= size) {
                        return null;
                }
                StuffNode p = sentA.next;
                for (int i = 0; i < index; i += 1) {
                        p = p.next;
                }
                return p.item;
        }

        /** The helper method of getRecursive */
        private T helper0(int index, StuffNode p) {
                if (index == 0) {
                        return p.item;
                }
                return helper0(index - 1, p.next);
        }

        /** Recursive version of get method */
        public T getRecursive(int index) {
                if (isEmpty() || index < 0 || index >= size) {
                        return null;
                }
                StuffNode p = sentA.next;
                return helper0(index, p);
        }
}
