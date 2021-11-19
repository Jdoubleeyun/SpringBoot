package Programmers13to16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivideArr {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {-1};
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0; i< arr.length; i++){
            if(arr[i]%divisor == 0){
                list.add(arr[i]);
            }
        }
        if(list.size() == 0){
            return answer;
        }
        else{
            answer = new int[list.size()];
            for(int i=0; i<answer.length; i++){
                answer[i] = list.get(i);
            }
            Arrays.sort(answer);
            return answer;
        }
}}
