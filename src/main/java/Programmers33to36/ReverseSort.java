package Programmers33to36;

import java.util.*;

public class ReverseSort {
    public String solution(String s) {
        String answer = "";
        String[] arr = s.split("");
        Arrays.sort(arr);
        Collections.reverse(Arrays.asList(arr));
// Arrays.sort(arr,Collections.reverseOrder());
        answer = String.join("", arr);
        return answer;
    }
//    public String solution(String s) {
//        String answer = "";
//        Character[] arr = new Character[s.length()];
//        for(int i=0; i<s.length(); i++){
//            arr[i] = s.charAt(i);
//        }
//        Arrays.sort(arr,Collections.reverseOrder());
//        for(int i=0; i<arr.length; i++){
//            answer += arr[i];
//        }
//        return answer;
//    }
}
