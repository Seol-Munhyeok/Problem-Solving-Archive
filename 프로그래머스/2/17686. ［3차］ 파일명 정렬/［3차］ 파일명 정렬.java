import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].toUpperCase();
            fileList.add(getFile(i, fileName));
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
            if (!other.head.equals(this.head)) {
                return this.head.compareTo(other.head);
            }
            if (other.number != this.number) {
                return Integer.compare(this.number, other.number);
            }
            return Integer.compare(this.index, other.index);
        }
    }
}