import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static String next() throws Exception {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}
		return st.nextToken();
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int n = Integer.parseInt(next());
		
		String[] names = new String[n];
		double[] a = new double[n];
		double[] b = new double[n];
		
		for (int i = 0; i < n; i++) {
			String name = next();
			double firstLeg = Double.parseDouble(next());
			double secondLeg = Double.parseDouble(next());
			
			names[i] = name;
			a[i] = firstLeg;
			b[i] = secondLeg;
		}
		
		double minTime = Double.MAX_VALUE;
		int ans1 = -1, ans2 = -1, ans3 = -1, ans4 = -1;
		
		for (int i = 0; i < n; i++) {
			int j1 = -1, j2 = -1, j3 = -1;
			for (int j = 0; j < n; j++) {
				if (i == j) continue;
				
				if (j1 == -1 || b[j] < b[j1]) {
					j3 = j2;
					j2 = j1;
					j1 = j;
				} else if (j2 == -1 || b[j] < b[j2]) {
					j3 = j2;
					j2 = j;
				} else if (j3 == -1 || b[j] < b[j3]) {
					j3 = j;
				}
			}
			
			double time = a[i] + b[j1] + b[j2] + b[j3];
			if (minTime > time) {
				minTime = time;
				ans1 = i;
				ans2 = j1;
				ans3 = j2;
				ans4 = j3;
			}
		}
		
		sb.append(a[ans1] + b[ans2] + b[ans3] + b[ans4]).append("\n");
		sb.append(names[ans1]).append("\n");
		sb.append(names[ans2]).append("\n");
		sb.append(names[ans3]).append("\n");
		sb.append(names[ans4]).append("\n");
		
		System.out.println(sb);
	}
}
