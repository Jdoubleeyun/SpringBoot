package Programmers1to4;

public class MiddleText {
    public String solution(String s) {
        String answer = "";
        if (s.length() % 2 != 0) {
            answer = s.substring(s.length() / 2, s.length() / 2 + 1);
        }
        if (s.length() % 2 == 0) {
            answer = s.substring(s.length() / 2 - 1, s.length() / 2 + 1);
        }
        return answer;
    }
    }
}
