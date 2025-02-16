import java.io.*;
import java.util.*;

public class ex {

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();

    int mod = scanner.nextInt();
    int answ = fib(N) % mod;
   
    System.out.println(answ);

    }

	public static int fib(int n) {
		if (n <= 1) {
			return n;
		}
		int[] fibArray = new int[n + 1];
		fibArray[0] = 0;
		fibArray[1] = 1;
		for (int i = 2; i <= n; i++) {
			fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
		}
		return fibArray[n];
	}
	
}