import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Oferta {
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
			String inFile = "oferta.in";
			String outFile = "oferta.out";
			MyScanner scanner = new MyScanner(new FileReader(inFile));
			int N = scanner.nextInt();
			int k = scanner.nextInt();
			List<Integer> oferte = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				oferte.add(scanner.nextInt());
			}
			int sum = listSum(oferte);
			HashMap<List<Integer>, Double> combinations = generateCombinations(oferte);

			// Sort the HashMap by values in descending order
			LinkedHashMap<List<Integer>, Double> sortedCombinations = sort(combinations);

			double sol = findMaxSum(sortedCombinations);

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
				String formattedMaxPower = String.format("%.1f", sum - sol);
				writer.write(formattedMaxPower + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<List<Integer>, Double> generateCombinations(List<Integer> nums) {
		HashMap<List<Integer>, Double> combinations = new HashMap<>();

		// Generate pairs
		for (int i = 0; i < nums.size() - 1; i++) {
			List<Integer> pair = new ArrayList<>();
			pair.add(nums.get(i));
			pair.add(nums.get(i + 1));
			double minValueHalf = Math.min(nums.get(i), nums.get(i + 1)) / 2.0;
			combinations.put(pair, minValueHalf);
		}

		// Generate triplets
		for (int i = 0; i < nums.size() - 2; i++) {
			List<Integer> triplet = new ArrayList<>();
			triplet.add(nums.get(i));
			triplet.add(nums.get(i + 1));
			triplet.add(nums.get(i + 2));
			double minValue = Math.min(nums.get(i), Math.min(nums.get(i + 1), nums.get(i + 2)));
			combinations.put(triplet, minValue);
		}

		return combinations;
	}

	public static LinkedHashMap<List<Integer>, Double> sort(HashMap<List<Integer>, Double> map) {
		List<Map.Entry<List<Integer>, Double>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<List<Integer>, Double>>() {
			public int compare(Map.Entry<List<Integer>, 
				Double> o1,Map.Entry<List<Integer>, Double>	o2) {
				return Double.compare(o2.getValue(), o1.getValue());
			}
		});
	
		LinkedHashMap<List<Integer>, Double> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<List<Integer>, Double> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public static double findMaxSum(LinkedHashMap<List<Integer>, Double> sortedCombinations) {
		double sum = 0;
		List<Integer> blacklist = new ArrayList<>();

		for (List<Integer> combination : sortedCombinations.keySet()) {
			boolean canUse = true;
			for (int num : combination) {
				if (blacklist.contains(num)) {
					canUse = false;
					break;
				}
			}
			if (canUse) {
				sum += sortedCombinations.get(combination);
				for (int num : combination) {
					blacklist.add(num);
				}
			}
		}
		return sum;
	}

	public static int listSum(List<Integer> nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		return sum;
	}
}
