class Solution {
    static long gcd(long a, long b) {
		while (b != 0) {
			long t = a % b;
			a = b;
			b = t;
		}
		return a;
	}
	
	static long lcm(long a, long b) {
		return a / gcd(a, b) * b;
	}
	
	static boolean isYellow(int t, int g, int y, int r) {
		int cycle = g + y + r;
	    long mod = (t - 1) % cycle;
	    return g <= mod && mod < g + y;
	}
	
	public int solution(int[][] signals) {
	    long period = 1;
	    
	    for (int[] s : signals) {
            long cycle = s[0] + s[1] + s[2];
            period *= cycle;
        }
	    
	    for (int t = 1; t <= period; t++) {
	    	boolean ok = true;
	    	for (int[] s : signals) {
	    		if (!isYellow(t, s[0], s[1], s[2])) {
	    			ok = false;
	    			break;
	    		}
	    	}
	    	if (ok) return t;
	    }
	    
	    return -1;
	}
}