package Programmers29to32;

import java.util.*;

public class Two {
    private Object ArrayList;

//    public int[] solution(int[] numbers) {
//        int[] answer = {};
//        List<Integer> list = new ArrayList<Integer>();
//        for(int i=0; i<numbers.length-1; i++){
//            for(int j=i+1; j<numbers.length; j++){
//                list.add(numbers[i] + numbers[j]);
//            }
//        }
//        list.sort(Comparator.naturalOrder());
//        for(int i =1; i< list.size(); i++){
//            if(list.get(i-1).equals(list.get(i))){
//                list.remove(i);
//                i--;
//            }
//        }
//        answer = new int[list.size()];
//        for(int i=0; i< list.size(); i++){
//            answer[i] = list.get(i);
//        }
//        return answer;
//    }
public int[] solution(int[] numbers) {
    int[] answer = {};
    Set<Integer> set = new HashSet<Integer>();
    for(int i=0; i<numbers.length-1; i++){
        for(int j=i+1; j<numbers.length; j++){
            set.add(numbers[i] + numbers[j]);
        }
    }
    answer = new int[set.size()];
    Iterator<Integer> iter = set.iterator();
    while(iter.hasNext()) {
        for(int i=0; i< set.size(); i++){
            answer[i] = iter.next();
        }}
    Arrays.sort(answer);
    return answer;
}
}
