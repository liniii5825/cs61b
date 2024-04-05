public class OffByN implements CharacterComparator {
    private int n;

    OffByN(int N) {
        this.n = N;
    }

    public boolean equalChars (char x, char y) {
        return Math.abs(x - y) == n;
    }
}
