import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class ex1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        BigInteger[] links = new BigInteger[N];

        for (int i = 0; i < N; i++) {
            links[i] = scanner.nextBigInteger();
        }

        Arrays.sort(links, Collections.reverseOrder());

        BigInteger totalProduct = BigInteger.ONE;
        for (BigInteger num : links) {
            totalProduct = totalProduct.multiply(num);
        }

        BigInteger subsetProduct = BigInteger.ONE;
        BigInteger remainingProduct = new BigInteger(totalProduct.toString());

        int answ = 0;
        for (int i = 0; i < N; i++) {
            subsetProduct = subsetProduct.multiply(links[i]);
            remainingProduct = remainingProduct.divide(links[i]);

            if (subsetProduct.compareTo(remainingProduct) >= 0) {
                answ = i + 1; // i + 1 because it's the count of elements
                break;
            }
        }
        System.out.println(answ);
    }
}
