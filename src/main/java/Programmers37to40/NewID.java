package Programmers37to40;

public class NewID {
    public String solution(String new_id) {
        // 1. 소문자로 변경
        new_id = new_id.toLowerCase();

        // 2. 소문자 숫자 -_. 제외한 모든 문자 제거
        String id = "";
        for(int i = 0; i < new_id.length(); i++) {
            char ch = new_id.charAt(i);

            if(ch >= 'a' && ch <= 'z') {
                id += String.valueOf(ch);
            } else if(ch >= '0' && ch <= '9') {
                id += String.valueOf(ch);
            } else if(ch == '.' || ch == '-' || ch == '_') {
                id += String.valueOf(ch);
            }
        }

        // 3. . 하나만
        for(int i = 0; i < id.length(); i++) {
            if(id.charAt(i) == '.') {
                int j = i+1;
                String dot = ".";

                while(j != id.length() && id.charAt(j) == '.') {
                    dot += ".";
                    j++;
                }

                if(dot.length() > 1)
                    id = id.replace(dot, ".");
            }
        }

        // 4. 첫번째, 마지막 . 제거
        if(id.startsWith(".") ) {
            id = id.substring(1, id.length());
        } else if(id.endsWith(".")) {
            id = id.substring(0, id.length()-1);
        }

        // 5. 빈 문자열이라면 a 대입
        if(id.length() == 0) {
            id += "a";
        }

        // 6. 16자 이상이면, 15자만 남김
        if(id.length() >= 16) {
            id = id.substring(0, 15);
        }

        // 마지막 . 제거
        if(id.endsWith(".")) {
            id = id.substring(0, id.length()-1);
        }

        // 7. 2자 이하라면, 마지막 문자 길이 3까지 반복해서 붙임
        String last = id.charAt(id.length()-1) + "";
        if(id.length() == 2) {
            id = id + last;
        } else if(id.length() == 1) {
            id = id + last + last;
        }

        return id;
    }
}
