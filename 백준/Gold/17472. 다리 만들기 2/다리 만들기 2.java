import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
    static StringTokenizer st;
    
    static String next() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
     
    static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }
    
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};
    static int N, M, map[][], parent[], rank[];
    static List<int[]> edges;
    static boolean visited[][];
    
    static boolean inRange(int y, int x) {
    	return y < N && y >= 0 && x < M && x >= 0;
    }
    
    static void bfs(int y, int x, int label) {
    	Queue<int[]> q = new ArrayDeque<>();
    	q.offer(new int[] {y, x});
    	visited[y][x] = true;
    	
    	while (!q.isEmpty()) {
    		int[] cur = q.poll();
    		int cy = cur[0], cx = cur[1];
    		map[cy][cx] = label;
    		
    		for (int d = 0; d < 4; d++) {
    			int ny = cy + dy[d], nx = cx + dx[d];
    			if (!inRange(ny, nx)) continue;
    			if (visited[ny][nx] || map[ny][nx] == 0) continue;
    			q.offer(new int[] {ny, nx});
    	    	visited[ny][nx] = true;
    		}
    	}
    }
    
    static void makeSet(int V) {
    	parent = new int[V + 1];
    	rank = new int[V + 1];
    	for (int i = 1; i <= V; i++) {
    		parent[i] = i;
    		rank[i] = 0;
    	}
    }
    
    static int find(int x) {
    	if (parent[x] == x) return x;
    	parent[x] = find(parent[x]);
    	return parent[x];
    }
    
    static void union(int a, int b) {
    	int ra = find(a);
    	int rb = find(b);
    	if (ra == rb) return;
    	
    	if (rank[ra] < rank[rb]) parent[ra] = rb;
    	else if (rank[ra] > rank[rb]) parent[rb] = ra;
    	else {
    		parent[rb] = ra;
    		rank[ra]++;
    	}
    }
    
    static int kruskalMST(int V) {
    	edges.sort((a, b) -> Integer.compare(a[2], b[2]));
    	
    	makeSet(V);
    	int total = 0;
    	int used = 0;
    	
    	for (int[] e : edges) {
    		int u = e[0], v = e[1], w = e[2];
    		if (find(u) != find(v)) {
    			union(u, v);
    			total += w;
    			used++;
    			if (used == V - 1) break;
    		}
    	}
    	
    	if (used != V - 1) return -1;  // 모든 섬을 연결할 수 없음
    	return total;
    }
    
    static void makeEdges(int y, int x) {
    	int u = map[y][x];  // 시작 정점
    	
    	for (int d = 0; d < 4; d++) {
    		for (int dist = 1; dist < Math.max(N,  M); dist++) {
    			int ny = y + dy[d] * dist;
    			int nx = x + dx[d] * dist;
    			if (!inRange(ny, nx) || map[ny][nx] == u) break;
    			if (map[ny][nx] != 0) {
    				int v = map[ny][nx];  // 도착 정점
    				int w = dist - 1;  // 다리 길이
    				if (w < 2) break;  // 다리 길이는 최소 2
    				
    				edges.add(new int[] {u, v, w});
    				break;
    			}
    		}
    	}
    }
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        N = nextInt(); M = nextInt();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < M; j++) {
        		map[i][j] = nextInt();
        	}
        }
        visited = new boolean[N][M];
        
        
        // 섬 라벨링 (1, ..., 6으로 섬 구분)
        int V = 0;  // 정점(섬) 개수
        int label = 1;
        for (int y = 0; y < N; y++) {
        	for (int x = 0; x < M; x++) {
        		if (map[y][x] == 1 && !visited[y][x]) {
        			bfs(y, x, label);
        			label++; V++;
        		}
        	}
        }
        
        // 간선 리스트 만들기
        edges = new ArrayList<>();  // (u, v, w) : 두 섬 u-v를 길이 w로 연결
        for (int y = 0; y < N; y++) {
        	for (int x = 0; x < M; x++) {
        		if (map[y][x] != 0) makeEdges(y, x);
        	}
        }
        
        System.out.println(kruskalMST(V));
    }
}
