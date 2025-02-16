import java.util.*;

public class HashMapSorting {

    public static void main(String[] args) {
        // Sample HashMap
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 50);
        map.put("Banana", 30);
        map.put("Orange", 40);
        map.put("Pineapple", 60);
        map.put("Mango", 20);

        System.out.println("Original HashMap:");
        printMap(map);

        // Sorting in ascending order
        Map<String, Integer> sortedAscMap = sortByValue(map, true);
        System.out.println("\nHashMap Sorted by Values (Ascending):");
        printMap(sortedAscMap);

        // Sorting in descending order
        Map<String, Integer> sortedDescMap = sortByValue(map, false);
        System.out.println("\nHashMap Sorted by Values (Descending):");
        printMap(sortedDescMap);
    }

    // Method to sort the HashMap by values
    public static Map<String, Integer> sortByValue(Map<String, Integer> map, boolean ascending) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

        // Sort the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return ascending ? o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue());
            }
        });

        // Create a new LinkedHashMap to maintain the order of insertion
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    // Method to print the map
    public static void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
