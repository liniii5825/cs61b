package synthesizer;

/**
 * An abstract class which implements BoundedQueue, capturing the redundancies between methods in BoundedQueue.<br>
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{
    /* Only subclasses of AbstractBoundedQueue and classes in the
    same package as AbstractBoundedQueue can access this variable. */
    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

//    @Override
//    public abstract T peek();
//
//    @Override
//    public abstract T dequeue();
//
//    @Override
//    public abstract void enqueue(T x);
}
