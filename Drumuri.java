import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Drumuri {
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
	}

	static class Pair implements Comparable<Pair> {
		int node;
		int cost;

		Pair(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pair other) {
			return Integer.compare(this.cost, other.cost);
		}
	}

	private static Map<Integer, List<Pair>> graph = new HashMap<>();

	private static int dijkstra(int start, int end, int N) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(start, 0));

		while (!pq.isEmpty()) {
			Pair current = pq.poll();
			int currentNode = current.node;
			int currentCost = current.cost;

			if (currentNode == end) {
				break;
			}

			if (graph.containsKey(currentNode)) {
				for (Pair neighbor : graph.get(currentNode)) {
					int newCost = currentCost + neighbor.cost;
					if (newCost < dist[neighbor.node]) {
						dist[neighbor.node] = newCost;
						pq.add(new Pair(neighbor.node, newCost));
					}
				}
			}
		}

		return dist[end];
	}

	public static void main(String[] args) {
		String inFile = "drumuri.in";
		String outFile = "drumuri.out";

		try {
			MyScanner scanner = new MyScanner(inFile);
			int N = scanner.nextInt();
			int M = scanner.nextInt();

			for (int i = 0; i < M; i++) {
				int u = scanner.nextInt();
				int v = scanner.nextInt();
				int cost = scanner.nextInt();
				graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair(v, cost));
			}

			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int z = scanner.nextInt();

			int costFromXtoZ = dijkstra(x, z, N);
			int costFromYtoZ = dijkstra(y, z, N);

			int totalCost = costFromXtoZ + costFromYtoZ;

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
				writer.write(Integer.toString(totalCost));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}