package Programmers17to20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clap {
    public String solution(int n) {
        String answer = "";
        if(n>0){
            answer += "수박".repeat(n/2);
            if(n%2 == 1){
                answer += "수";
            }
        }
        return answer;
    }
}
