import java.io.*;
import java.util.*;

public class topo {

    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static int N; // Number of nodes
    private static int M; // Number of edges

    public static void main(String[] args) throws IOException {
        String fileName = "graph.txt"; 
        boolean includeWeights = true; // Set to true if weights are included in the file

        readGraph(fileName, includeWeights);

        try {
            List<Integer> topoSort = topologicalSort();
            System.out.println("Topological Sorting: " + topoSort);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
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
                int weight = Integer.parseInt(parts[2]); // Read weight, but ignore it for topological sorting
            }
            addEdge(u, v);
        }
        br.close();
    }

    private static void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    }

    private static List<Integer> topologicalSort() {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        Set<Integer> recStack = new HashSet<>();

        for (Integer node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (!dfs(node, visited, stack, recStack)) {
                    throw new IllegalStateException("Graph has a cycle, topological sorting not possible.");
                }
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            topoOrder.add(stack.pop());
        }
        return topoOrder;
    }

    private static boolean dfs(int node, Set<Integer> visited, Stack<Integer> stack, Set<Integer> recStack) {
        if (recStack.contains(node)) {
            return false; // Cycle detected
        }
        if (visited.contains(node)) {
            return true;
        }

        visited.add(node);
        recStack.add(node);

        for (Integer neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!dfs(neighbor, visited, stack, recStack)) {
                return false;
            }
        }
        recStack.remove(node);
        stack.push(node);
        return true;
    }
}
