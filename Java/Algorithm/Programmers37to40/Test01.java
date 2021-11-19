package Programmers37to40;
import java.awt.List;
import java.util.*;
class Main {

    class Main {

        public static String solution(long n) {
            String answer = "";
            long sum =0;
            long num =n;
            while(num>9){
                sum+= num%10;
                num = num/10;
            }
            sum+= num;
            String str = Long.toString(n);
            String[] array = new String[str.length()];
            for(int i = 0; i<str.length(); i++){
                array[i] = String.valueOf((int)n%10);
                n = (int)(n/10);
            }

            answer = String.join("+", array) + "=" + sum;


            return answer;
        }



        public static void main(String[] args) {

            System.out.println((solution(12345)));
        }
    }

}



Integer.parseInt(s);
String[] array = s.split("");
String s = "" + n;
String.join("", array);

list.sort(Comparator.naturalOrder());
Collections.reverse(list);

Arrays.sort(array);
Arrays.sort(array, 0, 4);
Arrays.sort(arr,Collections.reverseOrder());

s=s.replace(,)
s += "수박".repeat();
s=s.toUpperCase();
