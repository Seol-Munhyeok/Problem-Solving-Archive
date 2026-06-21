class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (num < 100_000) {
            sb.append(intToStr(num, n));
            num++;
        }
        String result = sb.toString();
        sb = new StringBuilder();
        int count = 0;
        for (int i = p - 1; i < result.length(); i += m) {
            if (count >= t) break;
            sb.append(result.charAt(i));
            count++;
        }
        return sb.toString();
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