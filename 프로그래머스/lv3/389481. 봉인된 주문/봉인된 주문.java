import java.util.*;
/**
[문자열을 26진법으로 표현]
a=1, b=2,...,z=26
aa=26+1, ab=26+2, ..., az=26+26
30	["d", "e", "bb", "aa", "ae"] =
    [4, 5, 54, 27, 31]
    정렬
    [4, 5, 27, 31, 54]
"n보다 작은 문자를 하나 삭제할 때마다 찾아야할 n도 한 칸씩 밀린다."
*/
class Solution {
    public String solution(long n, String[] bans) {
        long[] longValues = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            longValues[i] = strToLong(bans[i]);
        }
        Arrays.sort(longValues);
        
        long target = n;
        for (int i = 0; i < longValues.length; i++) {
            if (longValues[i] <= target) {
                target++;
            } else {
                break;
            }
        }

        return longToStr(target);
    }
    
    private long strToLong(String str) {
        long value = 0;
        for (int i = 0; i < str.length(); i++) {
            value = value * 26 + (str.charAt(i) - 'a' + 1);
        }
        return value;
    }
    
    private String longToStr(long value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            value--;
            long remainer = value % 26;
            value /= 26;
            sb.append((char)('a' + remainer));
        }
        return sb.reverse().toString();
    }
}