import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class solve {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
		int[][] powers = new int[N][100];
		int[] line = new int[N];
       int start = scanner.nextInt();
	   for(int i = 0;i < N - 1;i++){
		int M = scanner.nextInt();
		line[i] = M;
		for (int j = 0; j < M; j++) {
			powers[i][j] = scanner.nextInt();
		}
	   }
	   
	   List<List<Integer>> sol = new ArrayList<>();
	   List<Integer> linee = new ArrayList<>();
	  Map<List<Integer>,Integer> ga = new HashMap<>();
	  int answ = 0;
 for(int i = start;i < N - 1;i++){
	
	linee.clear();
	for (int j = 0; j < 4; j++) {
      
		if(powers[i][j] != 0){
		linee.add(powers[i][j]);
		}
		
		
		//System.out.println(powers[i][j]);
	}
	//System.out.println(linee);
	
	int max = getbig(linee);
	i += max - 1;
	//System.out.println(max + "  "  + i);
	answ ++;

	   }
	
System.out.println(answ);

    }
public static int getbig(List<Integer> line){
	int max = 0;
	for(Integer in : line){
    max = Math.max(in,max);

	}
	return max;
}
	public static void printMap(Map<List<Integer>, Integer> map) {
        for (Map.Entry<List<Integer>, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
