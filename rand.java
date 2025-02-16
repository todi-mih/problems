import java.io.*;
import java.util.*;

public class rand {

    static class Edge {
        int node;
        int cost;

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        Edge(int node) {
            this.node = node;
            this.cost = 0; // Default cost
        }
    }

    private static int N; // Number of nodes
    private static int M; // Number of edges
    private static Map<Integer, List<Edge>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String fileName = "graph.txt"; // Replace with your file name
        boolean includeCosts = true;   // Set to true if the costs are included in the file

        readGraph(fileName, includeCosts);

        // Perform DFS and BFS
        System.out.println("DFS Traversal:");
        dfs(0); // Starting from node 0

        System.out.println("\nBFS Traversal:");
        bfs(0); // Starting from node 0
    }

    private static void readGraph(String fileName, boolean includeCosts) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String[] firstLine = br.readLine().trim().split("\\s+");
        N = Integer.parseInt(firstLine[0]);
        M = Integer.parseInt(firstLine[1]);

        for (int i = 0; i < M; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);

            if (includeCosts && parts.length == 3) {
                int cost = Integer.parseInt(parts[2]);
                addEdge(u, v, cost);
            } else {
                addEdge(u, v);
            }
        }
        br.close();
    }

    private static void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v));
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u)); // Assuming undirected graph
    }

    private static void addEdge(int u, int v, int cost) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, cost));
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, cost)); // Assuming undirected graph
    }

    private static void dfs(int start) {
        boolean[] visited = new boolean[N];
        dfsUtil(start, visited);
    }

    private static void dfsUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited[edge.node]) {
                dfsUtil(edge.node, visited);
            }
        }
    }

    private static void bfs(int start) {
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
                if (!visited[edge.node]) {
                    visited[edge.node] = true;
                    queue.offer(edge.node);
                }
            }
        }
    }
}
