package Programmers21to24;

import java.util.Arrays;
import java.util.Collections;

public class IntSort {
    public long solution(long n) {
        long answer = 0;

        String[] array = ("" + n).split("");
        Arrays.sort(array);
        Collections.reverse(Arrays.asList(array));
//        Arrays.toString(array);
        answer = Long.parseLong(String.join("", array));
        return answer;
    }
}
