import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static int[] getPi(String p) {
		int m = p.length();
		int[] pi = new int[m]; // pi[x] = p[0:x+1] 에서 접두사 = 접미사가 되는 최대 길이
		
		int j = 0;  // 현재까지 맞은 접두사 길이
		for (int i = 1; i < m; i++) {
			while (j > 0 && p.charAt(i) != p.charAt(j)) {
				j = pi[j - 1];
			}
			
			if (p.charAt(i) == p.charAt(j)) {
				pi[i] = ++j;
			}
		}
		
		return pi;
	}
	
	static List<Integer> kmp(String t, String p) {
		List<Integer> res = new ArrayList<>();
		int[] pi = getPi(p);
		
		int j = 0;
		for (int i = 0; i < t.length(); i++) {
			
			while (j > 0 && t.charAt(i) != p.charAt(j)) {
				j = pi[j - 1];
			}
			
			if (t.charAt(i) == p.charAt(j)) {
				if (j == p.length() - 1) {
					res.add(i - p.length() + 2);  // 1-based 시작 위치
					j = pi[j];					  // 겹치는 패턴 처리
				} else {
					j++;
				}
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String T = br.readLine();
		String P = br.readLine();
		
		List<Integer> answer = kmp(T, P);

        sb.append(answer.size()).append('\n');
        for (int pos : answer) {
            sb.append(pos).append(' ');
        }

        System.out.println(sb);
	}
}
