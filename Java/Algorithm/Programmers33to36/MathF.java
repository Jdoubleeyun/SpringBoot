package Programmers33to36;

import java.util.ArrayList;
import java.util.List;

public class MathF {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] student1 = {1,2,3,4,5};
        int[] student2 = {2,1,2,3,2,4,2,5};
        int[] student3 = {3,3,1,1,2,2,4,4,5,5};

        int answer1=0, answer2 =0, answer3 =0;

        for(int i =0; i<answers.length; i++){
            if(student1[i%student1.length] == answers[i]) answer1++;
            if(student2[i%student2.length] == answers[i]) answer2++;
            if(student3[i%student3.length] == answers[i]) answer3++;
        }
        int max = Math.max(Math.max(answer1, answer2), answer3);
        List<Integer> list = new ArrayList<>();
        if(max == answer1) list.add(1);
        if(max == answer2) list.add(2);
        if(max == answer3) list.add(3);
        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}
