package Programmers17to20;

import java.util.ArrayList;
import java.util.Arrays;

public class Athletic {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);
        int i =0;
        for(; i<completion.length; i++){
            if(!participant[i].equals(completion[i])){
                answer = participant[i];
                return answer;
            }
        }

        return participant[i];
    }
}
