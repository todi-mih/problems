import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Criptat {
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
			String inFile = "criptat.in";
			String outFile = "criptat.out";
			MyScanner scanner = new MyScanner(new FileReader(inFile));
			int N = scanner.nextInt();
			List<String> words = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				String word = scanner.next();
				words.add(word);
			}
			int maxResult = Integer.MIN_VALUE;
			for (char c = 'a'; c <= 'z'; c++) {
				int result = dostuff(words, c);
				maxResult = Math.max(maxResult, result);
			}
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
				writer.write(Integer.toString(maxResult));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int dostuff(List<String> words, char dominantChar) {
		Map<String, Double> wordValues = new HashMap<>();
		for (String word : words) {
			double value = (double) countDomCharTimes(word, dominantChar) / word.length();
			wordValues.put(word, value);
		}
		Collections.sort(words, (a, b) -> Double.compare(wordValues.get(b), wordValues.get(a)));
		StringBuilder result = new StringBuilder();
		int dominantCharTotal = 0;
		for (String word : words) {
			int dominantCharCount = countDomCharTimes(word, dominantChar);
			if ((result.length() + word.length()) / 2 < dominantCharTotal + dominantCharCount) {
				result.append(word);
				dominantCharTotal += dominantCharCount;
			} else {
				continue;
			}
		}
		return result.length();
	}

	public static int countDomCharTimes(String word, char dominantChar) {
		int count = 0;
		for (char c : word.toCharArray()) {
			if (c == dominantChar) {
				count++;
			}
		}
		return count;
	}
}