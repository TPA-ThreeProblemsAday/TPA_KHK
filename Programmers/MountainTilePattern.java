package programmers;

public class MountainTilePattern {
    // 2024 KAKAO WINTER INTERNSHIP-산 모양 타일링

    public static int solution(int n, int[] tops) {
        int answer = 0;
        final int MOD = 10007;

        int[] left = new int[n];
        int[] right = new int[n];

        // 뿔이 있으면 왼쪽을 채우는 경우는 3개, 없으면 2개
        left[0] = tops[0] == 1 ? 3 : 2;
        right[0] = 1;

        for(int i = 1; i < n; i++){
            // 오른쪽을 채우는 경우는 앞에서 왼, 오 어디를 채웠던지 상관 없음
            right[i] = left[i - 1] + right[i - 1];

            // 뿔이 있으면 이전에 왼쪽을 채운 경우 3가지(평행사변형, 삼각형, 마름모), 오른쪽을 채운 경우 2가지(삼각형, 마름모)
            if(tops[i] == 1) left[i] = (3 * left[i -  1] + 2 * right[i - 1]) % MOD;
            else left[i] = (2 * left[i -  1] + right[i - 1]) % MOD;
            // 뿔이 없으면 이전에 왼쪽을 채운 경우 2가지(평행사변형, 삼각형), 오른쪽을 채운 경우 1가지(삼각형)
        }

        answer = (left[n - 1] + right[n - 1]) % MOD;

        return answer;
    }

    public static void main(String[] args) {
        boolean isAnswer = true;

        int[][] tops = {
                {1, 1, 0, 1},
                {0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        int[] n = {4, 2, 10};
        int[] results = {149, 11, 7704};

        for(int i = 0; i<3; i++){
            if(results[i] != solution(n[i], tops[i])){
                isAnswer = false;
                break;
            }
        }

        System.out.println(isAnswer ? "정답입니다!" : "틀렸습니다ㅠ");
    }
}
