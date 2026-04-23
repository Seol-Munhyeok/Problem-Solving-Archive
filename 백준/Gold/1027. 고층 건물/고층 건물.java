import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int maxAnswer = -1_000_000_000;
		
		for (int i = 1; i <= N; i++) {
			int count = 0;
			
			double maxSlope = -1_000_000_000.0;
			for (int j = i + 1; j <= N; j++) {
				double slope = (double)(arr[j] - arr[i]) / (j - i);
				
				if (slope > maxSlope) {
					count++;
					maxSlope = slope;
				}
			}
			
			double minSlope = 1_000_000_000.0;
			for (int j = i - 1; j >= 1; j--) {
				double slope = (double)(arr[j] - arr[i]) / (j - i);
				
				if (slope < minSlope) {
					count++;
					minSlope = slope;
				}
			}
			
			maxAnswer = Math.max(maxAnswer, count);
		}
		
		System.out.println(maxAnswer);
	}
}
