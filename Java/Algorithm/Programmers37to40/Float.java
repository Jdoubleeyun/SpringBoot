package Programmers37to40;

public class Float {
    public int solution(int[] nums) {
        int answer = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (isPrime(nums[i] + nums[j] + nums[k])) {
                        answer += 1;
                    }
                }
            }
        }
        return answer;
    }

    public Boolean isPrime(int num) {
        int cnt = 0;
        for (int i = 1; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) cnt += 1;
        }
        return cnt == 1;
    }

}