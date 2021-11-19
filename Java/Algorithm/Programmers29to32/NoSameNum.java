package Programmers29to32;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class NoSameNum {
//    public int[] solution(int []arr) {
//        int[] answer = {};
//        HashSet<Integer> set = new HashSet<Integer>();
//        for(int ar: arr){
//            set.add(ar);
//        }
//        answer = new int[set.size()];
//        Iterator<Integer> iter = set.iterator();
//        for(int i=0; i< answer.length; i++){
//            answer[i] = iter.next();
//        }


    //        List<Integer> list = new ArrayList<Integer>();
//        for(int ar : arr){
//            list.add(ar);
//        }
//        for(int i =0; i< list.size(); i++){
//            if(list.get(i).equals(list.get(i+1))){
//                list.remove(list.get(i));
//            }
//        }
//        answer  = new int[list.size()];
//        for(int i =0; i< list.size(); i++){
//            answer[i] = list.get(i);
//        }
//        System.out.println("Hello Java");
//
//        return answer;
//    }
    public int[] solution(int[] arr) {
        List<Integer> tempList = new ArrayList<Integer>();
        int preNum = 10;
        for (int num : arr) {
            if (preNum != num)
                tempList.add(num);
            preNum = num;
        }
        int[] answer = new int[tempList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = tempList.get(i);
        }
        return answer;
    }

}