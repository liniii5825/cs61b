package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        System.out.println(arb);
        arb.peek();
        System.out.println(arb.isEmpty());
        System.out.println(arb.fillCount);
        System.out.println(arb.isFull());
        System.out.println(arb.dequeue());
        System.out.println(arb.dequeue());
        System.out.println(arb.dequeue());
        System.out.println(arb.isEmpty());
        System.out.println(arb.fillCount);
        System.out.println(arb.isFull());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
