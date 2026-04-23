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
     
    static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }
    
    // UP : 0, FRONT = 1, LEFT = 2, BACK = 3, RIGHT = 4, DOWN = 5
    static char[][] U, F, L, B, R, D;
    
    static void initCube() {
    	U = new char[][] {{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}};
    	F = new char[][] {{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}};
    	L = new char[][] {{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}};
    	B = new char[][] {{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}};
    	R = new char[][] {{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}};
    	D = new char[][] {{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}};
    }
    
    // 면 자체 90도 회전
    static void rotateFace(char[][] face) {
    	char[][] temp = new char[3][3];
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			temp[j][2 - i] = face[i][j];
    		}
    	}
    	for (int i = 0; i < 3; i++) face[i] = temp[i].clone();
    }
    
    static void rotateFrontCW() {
    	rotateFace(F);  // F면 자체 회전
    	
    	// 시계 방향 순서대로 12조각 추출
    	char[] line = new char[12];
    	
    	// U의 [2][0,1,2]
        for(int i = 0; i < 3; i++) line[i] = U[2][i];
        // R의 [0,1,2][0]
        for(int i = 0; i < 3; i++) line[i + 3] = R[i][0];
        // D의 [0][2,1,0]
        for(int i = 0; i < 3; i++) line[i + 6] = D[0][2 - i];
        // L의 [2,1,0][2]
        for(int i = 0; i < 3; i++) line[i + 9] = L[2 - i][2];
        
        // 뒤의 3개가 앞으로 오도록 시프트
        char[] shifted = new char[12];
        for(int i = 0; i < 12; i++) {
            shifted[(i + 3) % 12] = line[i];
        }
        
        // 다시 제자리에 배치
        for (int i = 0; i < 3; i++) U[2][i] = shifted[i];
        for (int i = 0; i < 3; i++) R[i][0] = shifted[i + 3];
        for (int i = 0; i < 3; i++) D[0][2 - i] = shifted[i + 6];
        for (int i = 0; i < 3; i++) L[2 - i][2] = shifted[i + 9];
    }
    
    static void rotateLeftCW() {
    	rotateFace(L);  // L면 자체 회전
    	
    	char[] line = new char[12];
    	
    	// U의 [0,1,2][0]
        for(int i = 0; i < 3; i++) line[i] = U[i][0];
        // F의 [0,1,2][0]
        for(int i = 0; i < 3; i++) line[i + 3] = F[i][0];
        // D의 [0,1,2][0]
        for(int i = 0; i < 3; i++) line[i + 6] = D[i][0];
        // B의 [2,1,0][2]
        for(int i = 0; i < 3; i++) line[i + 9] = B[2 - i][2];
        
        char[] shifted = new char[12];
        for(int i = 0; i < 12; i++) {
            shifted[(i + 3) % 12] = line[i];
        }
        
        for (int i = 0; i < 3; i++) U[i][0] = shifted[i];
        for (int i = 0; i < 3; i++) F[i][0] = shifted[i + 3];
        for (int i = 0; i < 3; i++) D[i][0] = shifted[i + 6];
        for (int i = 0; i < 3; i++) B[2 - i][2] = shifted[i + 9];
    }
    
    static void rotateUpCW() {
    	rotateFace(U);  // U면 자체 회전
    	
    	char[] line = new char[12];
    	
    	// B의 [0][2,1,0]
    	for(int i = 0; i < 3; i++) line[i] = B[0][2 - i];
        // R의 [0][2,1,0]
    	for(int i = 0; i < 3; i++) line[i + 3] = R[0][2 - i];
        // F의 [0][2,1,0]
    	for(int i = 0; i < 3; i++) line[i + 6] = F[0][2 - i];
        // L의 [0][2,1,0]
    	for(int i = 0; i < 3; i++) line[i + 9] = L[0][2 - i];
        
        char[] shifted = new char[12];
        for(int i = 0; i < 12; i++) {
            shifted[(i + 3) % 12] = line[i];
        }
        
        for (int i = 0; i < 3; i++) B[0][2 - i] = shifted[i];
        for (int i = 0; i < 3; i++) R[0][2 - i] = shifted[i + 3];
        for (int i = 0; i < 3; i++) F[0][2 - i] = shifted[i + 6];
        for (int i = 0; i < 3; i++) L[0][2 - i] = shifted[i + 9];
    }
    
    static void rotateBackCW() {
    	rotateFace(B);  // B면 자체 회전
    	
    	char[] line = new char[12];
    	
    	// U의 [0][2,1,0]
    	for(int i = 0; i < 3; i++) line[i] = U[0][2 - i];
        // L의 [0,1,2][0]
    	for(int i = 0; i < 3; i++) line[i + 3] = L[i][0];
        // D의 [2][0,1,2]
    	for(int i = 0; i < 3; i++) line[i + 6] = D[2][i];
        // R의 [2,1,0][2]
    	for(int i = 0; i < 3; i++) line[i + 9] = R[2 - i][2];
        
        char[] shifted = new char[12];
        for(int i = 0; i < 12; i++) {
            shifted[(i + 3) % 12] = line[i];
        }
        
        for (int i = 0; i < 3; i++) U[0][2 - i] = shifted[i];
        for (int i = 0; i < 3; i++) L[i][0] = shifted[i + 3];
        for (int i = 0; i < 3; i++) D[2][i] = shifted[i + 6];
        for (int i = 0; i < 3; i++) R[2 - i][2] = shifted[i + 9];
    }
    
    static void rotateRightCW() {
    	rotateFace(R);  // R면 자체 회전
    	
    	char[] line = new char[12];
    	
    	// U의 [2,1,0][2]
    	for(int i = 0; i < 3; i++) line[i] = U[2 - i][2];
        // B의 [0,1,2][0]
    	for(int i = 0; i < 3; i++) line[i + 3] = B[i][0];
        // D의 [2,1,0][2]
    	for(int i = 0; i < 3; i++) line[i + 6] = D[2 - i][2];
        // F의 [2,1,0][2]
    	for(int i = 0; i < 3; i++) line[i + 9] = F[2 - i][2];
        
        char[] shifted = new char[12];
        for(int i = 0; i < 12; i++) {
            shifted[(i + 3) % 12] = line[i];
        }
        
        for (int i = 0; i < 3; i++) U[2 - i][2] = shifted[i];
        for (int i = 0; i < 3; i++) B[i][0] = shifted[i + 3];
        for (int i = 0; i < 3; i++) D[2 - i][2] = shifted[i + 6];
        for (int i = 0; i < 3; i++) F[2 - i][2] = shifted[i + 9];
    }
    
    static void rotateDownCW() {
    	rotateFace(D);  // D면 자체 회전
    	
    	char[] line = new char[12];
    	
    	// F의 [2][0,1,2]
    	for(int i = 0; i < 3; i++) line[i] = F[2][i];
        // R의 [2][0,1,2]
    	for(int i = 0; i < 3; i++) line[i+3] = R[2][i];
        // B의 [2][0,1,2]
    	for(int i = 0; i < 3; i++) line[i+6] = B[2][i];
        // L의 [2][0,1,2]
    	for(int i = 0; i < 3; i++) line[i+9] = L[2][i];
        
        char[] shifted = new char[12];
        for(int i = 0; i < 12; i++) {
            shifted[(i + 3) % 12] = line[i];
        }
        
        for (int i = 0; i < 3; i++) F[2][i] = shifted[i];
        for (int i = 0; i < 3; i++) R[2][i] = shifted[i + 3];
        for (int i = 0; i < 3; i++) B[2][i] = shifted[i + 6];
        for (int i = 0; i < 3; i++) L[2][i] = shifted[i + 9];
    }
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
         
        int T = nextInt();
        for (int tc = 1; tc <= T; tc++) {
            initCube();
            int N = nextInt();
            for (int i = 0; i < N; i++) {
            	String cmd = next();
            	char face = cmd.charAt(0);
            	char dir = cmd.charAt(1);
            	
            	// 반시계(-)는 시계(+) 3번
            	int repeat = (dir == '+') ? 1 : 3;
            	for (int r = 0; r < repeat; r++) {
            		if (face == 'U') rotateUpCW();
                    else if (face == 'D') rotateDownCW();
                    else if (face == 'F') rotateFrontCW();
                    else if (face == 'B') rotateBackCW();
                    else if (face == 'L') rotateLeftCW();
                    else if (face == 'R') rotateRightCW();
            	}
            }
        
        	// U면 출력
            for (int i = 0; i < 3; i++) {
            	for (int j = 0; j < 3; j++) {
            		sb.append(U[i][j]);
            	}
            	sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
