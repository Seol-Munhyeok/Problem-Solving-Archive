import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            fileList.add(getFile(i, files[i]));
        }
        
        Collections.sort(fileList);
        int idx = 0;
        String[] answer = new String[files.length];
        for (File f : fileList) {
            answer[idx++] = files[f.index];
        }
        return answer;
    }
    
    private File getFile(int index, String fileName) {
        String head = "";
        int number = -1;
        int startIdx = -1;  // 숫자 시작 인덱스
        for (int i = 0; i < fileName.length(); i++) {
            char ch = fileName.charAt(i);
            if (startIdx == -1 && ch >= '0' && ch <= '9') {
                head = fileName.substring(0, i);
                startIdx = i;
            }
            if (startIdx >= 0 && !(ch >= '0' && ch <= '9')) {
                number = Integer.parseInt(fileName.substring(startIdx, i));
                break;
            }
        }
        if (number == -1) {
            number = Integer.parseInt(fileName.substring(startIdx));
        }
        return new File(index, head, number);
    }
    
    static class File implements Comparable<File> {
        int index;  // 입력된 순서
        String head;
        int number;
        
        File (int index, String head, int number) {
            this.index = index;
            this.head = head;
            this.number = number;
        }
        
        @Override
        public int compareTo(File other) {
            int cmp = this.head.compareToIgnoreCase(other.head);
            if (cmp != 0) return cmp;
            
            cmp = Integer.compare(this.number, other.number);
            if (cmp != 0) return cmp;
            
            return Integer.compare(this.index, other.index);
        }
    }
}