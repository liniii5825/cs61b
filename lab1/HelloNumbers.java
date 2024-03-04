public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0, i = 0;
        while (x <= 45) {
            System.out.print(x + " ");
            i = i + 1;
            x = x + i;
        }
        System.out.println();
    }
}
