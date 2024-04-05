public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            /* From the Internet, Get string character by index.
             * """
             * The method you're looking for is charAt.
             * Here's an example:
             *  String text = "foo";
             *  char charAtZero = text.charAt(0);
             *  System.out.println(charAtZero); // Prints f
             * """
             */
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /* The helper method of recursive isPalindrome */
    private boolean helper0(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        }
        if (deque.removeFirst() == deque.removeLast()) {
            return helper0(deque);
        } else {
            return false;
        }
    }

    /** Check whether the word is a palindrome */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        /* check word using the get method
        int size = deque.size();
        for (int i = 0; i < size / 2; i += 1) {
            if (deque.get(i) != deque.get(size - i - 1)) {
                return false;
            }
        }
        */

        /* check word iteratively
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
        */

        /* check word recursively */
        return helper0(deque);
    }

    /** The overload method of isPalindrome */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len -i -1))) {
                return false;
            }
        }
        return true;
    }
}
