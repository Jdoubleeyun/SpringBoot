package Programmers37to40;

import java.util.ArrayList;
import java.util.List;

public class NumStringEng {
    public int solution(String s) {
        int answer = 0;
        String[] str = {"zero","one", "two","three","four","five","six","seven","eight","nine"};
        String[] num = {"0","1","2","3","4","5","6","7","8","9"};
        for(int i=0; i<str.length; i++){
            s = s.replace(str[i],num[i]);
        }
        answer = Integer.parseInt(s);
        return answer;
    }
}
