import java.io.*;
import java.util.*;

public class cycle {

    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static int N; // Number of nodes
    private static int M; // Number of edges

    public static void main(String[] args) throws IOException {
        String fileName = "graph.txt"; 
        boolean includeWeights = true; // Set to true if weights are included in the file

        readGraph(fileName, includeWeights);

        if (hasCycle()) {
            System.out.println("The graph has a cycle.");
        } else {
            System.out.println("The graph does not have a cycle.");
        }
    }

    private static void readGraph(String fileName, boolean includeWeights) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String[] firstLine = br.readLine().trim().split("\\s+");
        N = Integer.parseInt(firstLine[0]);
        M = Integer.parseInt(firstLine[1]);

        for (int i = 0; i < M; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);

            if (includeWeights && parts.length == 3) {
                int weight = Integer.parseInt(parts[2]); // Read weight, but ignore it for cycle detection
            }
            addEdge(u, v);
        }
        br.close();
    }

    private static void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    }

    private static boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();

        for (Integer node : graph.keySet()) {
            if (dfs(node, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    private static boolean dfs(int node, Set<Integer> visited, Set<Integer> recStack) {
        if (recStack.contains(node)) {
            return true; // Cycle detected
        }
        if (visited.contains(node)) {
            return false;
        }

        visited.add(node);
        recStack.add(node);

        for (Integer neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (dfs(neighbor, visited, recStack)) {
                return true;
            }
        }
        recStack.remove(node);
        return false;
    }
}
