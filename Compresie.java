import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Compresie {
	private static class MyScanner {
		private BufferedReader br;
		private StringTokenizer st;

		public MyScanner(Reader reader) {
			br = new BufferedReader(reader);
		}

		public String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}
	}

	public static void main(String[] args) {
		try {
			String inFile = "compresie.in";
			String outFile = "compresie.out";
			MyScanner scanner = new MyScanner(new FileReader(inFile));
			int n = scanner.nextInt();
			int[] A = new int[n];
			for (int i = 0; i < n; i++) {
				A[i] = scanner.nextInt();
			}
			int m = scanner.nextInt();
			int[] B = new int[m];
			for (int i = 0; i < m; i++) {
				B[i] = scanner.nextInt();
			}
			int maxLength = computeMaxLength(A, B);
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
				writer.write(maxLength + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int computeMaxLength(int[] A, int[] B) {
		HashMap<Integer, Integer> indexMap = new HashMap<>();
		int sol = 0;
		int sumA = 0;
		for (int i = 0; i < A.length; i++) {
			sumA += A[i];
			indexMap.putIfAbsent(sumA, i);
		}
		int sumB = 0;
		for (int num : B) {
			sumB += num;
			if (indexMap.containsKey(sumB)) {
				sol++;
			}
		}
		if (sol == 1) {
			return -1;
		} else {
			return sol;
		}
	}
}