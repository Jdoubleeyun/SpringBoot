package Programmers1to4;

import java.util.Scanner;

public class Star {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        char[] line;
        for(int i=0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                System.out.print("*");
            }
            System.out.println("");
            //줄 바꿈은 그냥 공란으로 println
            //print는 일렬로 나열. ,없음
            //println은 개행되어 출력됨
            //println("\n")은 개행하고 ,빈 줄 하나 더 추가
    }
}}
