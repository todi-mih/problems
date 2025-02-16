import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

public class Servere {
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
			String inFile = "servere.in";
			String outFile = "servere.out";

			MyScanner scanner = new MyScanner(new FileReader(inFile));

			int N = scanner.nextInt();
			int[] powers = new int[N];
			int minPower = Integer.MAX_VALUE;
			int maxP = Integer.MIN_VALUE;
			for (int j = 0; j < N; j++) {
				powers[j] = scanner.nextInt();
			}

			int[] supplies = new int[N];
			for (int j = 0; j < N; j++) {
				int currentPower = scanner.nextInt();
				supplies[j] = currentPower;
				if (currentPower < minPower) {
					minPower = currentPower;
				}
				if (currentPower > maxP) {
					maxP = currentPower;
				}
			}

			double minPowerr = -Double.MAX_VALUE;   //for the solution
			double stepSize = 0.1;
			double low = minPower;
			double high = maxP;
			double prevMinPowerr = Double.NEGATIVE_INFINITY;   //help with convergence
			int numIterations = 0;      //help keep track for convergence

			while (low <= high) {
				double mid = (low + high) / 2;
				double midPlusStep = mid + stepSize;

				double iminMid = calculateIndividualMinP(mid, powers, supplies);
				double iminMidPps = calculateIndividualMinP(midPlusStep, powers, supplies);

				minPowerr = Math.max(minPowerr, Math.max(iminMid, iminMidPps));

				if (iminMidPps > iminMid) {
					low = midPlusStep;
				} else {
					high = mid;
				}

				if (minPowerr == prevMinPowerr) {
					numIterations++;
					if (numIterations >= 100) {
						break;
					}
				} else {
					numIterations = 0;
				}

				prevMinPowerr = minPowerr;
			}

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
				String formattedMaxPower = String.format("%.1f", minPowerr);
				writer.write(formattedMaxPower + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static double calculateIndividualMinP(double k, int[] powers, int[] supplies) {
		double individualMinPower = Double.MAX_VALUE;
		for (int j = 0; j < powers.length; j++) {
			double currentPower = powers[j];
			double individualPower = currentPower - Math.abs(k - supplies[j]);
			individualMinPower = Math.min(individualMinPower, individualPower);
		}
		return individualMinPower;
	}
}