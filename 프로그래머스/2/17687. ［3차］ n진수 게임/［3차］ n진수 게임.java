class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder all = new StringBuilder();
        
        int num = 0;
        while (all.length() < t * m) {
            all.append(intToStr(num, n));
            num++;
        }
        
        StringBuilder answer = new StringBuilder();
        for (int i = p - 1; answer.length() < t; i += m) {
            answer.append(all.charAt(i));
        }
        
        return answer.toString();
    }
    
    private String intToStr(int num, int base) {
        if (num == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        
        while (num > 0) {
            int rem = num % base;
            sb.append(toStr(rem));
            num /= base;
        }
        return sb.reverse().toString();
    }
    
    private String toStr(int n) {
        if (n < 10) return String.valueOf(n);
        return String.valueOf((char) ('A' + (n - 10)));
    }
}