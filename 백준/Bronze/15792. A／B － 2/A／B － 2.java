import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int a = sc.nextInt(); int b = sc.nextInt();
		
		// 정수 부분
		sb.append(a / b).append(".");
		
		// 소수 부분
		int rem = a % b;
		for (int i = 0; i < 2000; i++) {
			rem *= 10;
			int digit = rem / b;
			sb.append(digit);
			rem %= b;
			if (rem == 0) break;
		}
		System.out.println(sb);
	}
}
