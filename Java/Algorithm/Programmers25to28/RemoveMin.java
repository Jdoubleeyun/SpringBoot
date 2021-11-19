package Programmers25to28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveMin {
    //    public int[] solution(int[] arr) {
//        if(arr.length>1){
//            int[] answer = new int[arr.length-1];
//            for(int i=0; i<arr.length-1; i++){
//                if(arr[i]<arr[i+1]){
//                    answer[i]=arr[i+1];
//                    arr[i+1]=arr[i];
//                }
//                else{ answer[i]=arr[i];}
//            }
//            return answer;
//        }
//        else {
//            int[] answer = {-1};
//            return answer;
//        }
//}
    public int[] solution(int[] arr) {
        int[] answer = {};
        List<Integer> arr2 = new ArrayList<Integer>();
        if (arr.length <= 1) {
            answer = new int[]{-1};
            return answer;
        }
        for (int i = 0; i < arr.length; i++) {
            arr2.add(arr[i]);
        }

        int min = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[min] > arr[i]) {
                min = i;
            }
        }
        arr2.remove(min);
        answer = new int[arr2.size()];

        for (int i = 0; i < arr2.size(); i++) {
            answer[i] = arr2.get(i);
        }
        return answer;
    }
}