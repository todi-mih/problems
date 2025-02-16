import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trenuri {

	private static class MyScanner {
		private BufferedReader br;

		public MyScanner(String fileName) throws IOException {
			br = new BufferedReader(new FileReader(fileName));
		}

		public String next() throws IOException {
			StringBuilder sb = new StringBuilder();
			int c;
			while ((c = br.read()) != -1) {
				if (c == ' ' || c == '\n') {
					break;
				}
				sb.append((char) c);
			}
			return sb.toString();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public void close() throws IOException {
			br.close();
		}
	}

	private static Map<String, List<String>> graph = new HashMap<>();

	private static Map<String, Integer> longestPathCache = new HashMap<>();

	public static int findLongestPath(String current, String destination) {
		// If the current node is the destination, return 1 
		if (current.equals(destination)) {
			return 1;
		}
		// If the longest path length for the current node is already cached, return it
		if (longestPathCache.containsKey(current)) {
			return longestPathCache.get(current);
		}
		int maxLength = 0;

		// If the current node has outgoing edges
		if (graph.containsKey(current)) {
			// Explore each neighbor of the current node
			for (String neighbor : graph.get(current)) {
				int pathLength = findLongestPath(neighbor, destination);
				// If a path exists from the neighbor to dest, update the maximum path length
				if (pathLength > 0) {
					maxLength = Math.max(maxLength, pathLength + 1);
				}
			}
		}

		longestPathCache.put(current, maxLength);
		return maxLength;
	}

	public static void main(String[] args) {
		String inFile = "trenuri.in";
		String outFile = "trenuri.out";

		try {
			MyScanner scanner = new MyScanner(inFile);
			String source = scanner.next();
			String dest = scanner.next();
			int M = scanner.nextInt();

			for (int i = 0; i < M; i++) {
				String x = scanner.next();
				String y = scanner.next();
				graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
			}

			scanner.close();

			int longestPathLength = findLongestPath(source, dest);

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
				writer.write(Integer.toString(longestPathLength));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}