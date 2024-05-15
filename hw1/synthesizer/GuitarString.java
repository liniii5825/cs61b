package synthesizer;
// TODO: Make sure to make this class a part of the synthesizer package
//package <package name>;

//Make sure this class is public

/**
 * The <b>Karplus-Algorithm</b> is simply the following three steps:<br>
 * 1. Replace every item in a BoundedQueue with random noise (double values <u>between -0.5 and 0.5</u>).<br>
 * 2. Remove the <u>front</u> double in the BoundedQueue and average it with the <u>next</u> double in the BQ.
 * (hint: use dequeue() and peek()) multiplied by an energy decay factor of <u>0.996</u>.<br>
 * 3. Play the double that you dequeued in step 2.
 * Go back to step 2 (repeating forever).
 */
public class GuitarString {
    /** <b>Constants!</b> <br>
     * <b>Do not change!</b><br>
     * The keyword <b>final</b> means
     * the values <u>cannot be changed at runtime</u>. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /** Buffer for storing sound data. */
    private final BoundedQueue<Double> buffer;

    /** Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
         /* TODO: Create a buffer with capacity = SR / frequency.
             1. You'll need to cast the result of this division operation into an int.
             For better accuracy, use the Math.round() function before casting.
             2. Your buffer should be initially filled with zeros. */
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }


    /** Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
         /* TODO: Dequeue everything in the buffer, and replace it
                with random numbers between -0.5 and 0.5.
             You can get such a number by using:
             >>> double r = Math.random() - 0.5;
             Make sure that your random numbers are different from each other. */
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

    /** Advance the simulation one time step by performing
     * one iteration of the Karplus-Strong algorithm. */
    public void tic() {
         /* TODO: Dequeue the front sample and enqueue a new sample
                that is the average of the two multiplied by the DECAY factor.
             Do not call StdAudio.play(). */
        Double frontSample = buffer.dequeue();
        Double newSample = DECAY * ((frontSample + buffer.peek()) * 0.5) ;
        buffer.enqueue(newSample);
    }

    /** Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.peek();
    }
}
