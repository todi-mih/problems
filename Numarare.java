import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Numarare {
	private static final int MOD = 1000000007;

	private static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		public FastScanner(String fileName) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(fileName));
		}

		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			return st.nextToken();
		}

		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		void close() throws IOException {
			br.close();
		}
	}
	public static void main(String[] args) throws IOException {
		String inputFileName = "numarare.in";
		String outputFileName = "numarare.out";
		FastScanner scanner = new FastScanner(inputFileName);
		int N = scanner.nextInt();
		int M = scanner.nextInt();

		// Create lists to store the graphs as sets of edges
		List<Set<Integer>> graph1 = new ArrayList<>();
		List<Set<Integer>> graph2 = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph1.add(new HashSet<>());
			graph2.add(new HashSet<>());
		}

		for (int i = 0; i < M; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			graph1.get(x).add(y);
		}

		for (int i = 0; i < M; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			graph2.get(x).add(y);
		}

		scanner.close();

		int[] dp = new int[N + 1];
		dp[1] = 1; // Base case: there is one elementary chain of length 0 from node 1 to itself

		for (int node = 1; node < N; node++) {
			// If there is at least one elementary chain reaching node 'node'
			if (dp[node] > 0) {
				// Check if the neighbors of 'node' in graph1 are also neighbors in graph2
				for (int neighbor : graph1.get(node)) {
					if (graph2.get(node).contains(neighbor)) {
						// Update the count of elementary chains reaching 'neighbor'
						dp[neighbor] = (dp[neighbor] + dp[node]) % MOD;
					}
				}
			}
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
			writer.write(Integer.toString(dp[N]));
		}
	}
}