import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		
		// 조합 테이블 채우기
		int[][] combi = new int[31][31];
		for (int i = 1; i < 30; i++) {
			combi[i][1] = i;
			combi[i][i] = 1;
		}
		
		for (int i = 2; i < 30; i++) {
			for (int j = 2; j < i; j++) {
				combi[i][j] = combi[i - 1][j - 1] + combi[i - 1][j];
			}
		}
		
		for (int tc = 1; tc <= T; tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			sb.append(combi[m][n]).append("\n");
		}
		
		System.out.println(sb);
	}
}
