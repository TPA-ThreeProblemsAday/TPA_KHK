import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_5557 {
    // 5557. 1학년

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[] num = new int[N];
        long[][] equation = new long[N][21];    // 최댓값이 263-1이므로 long
        int diff, sum;

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
        equation[0][num[0]] = 1;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (equation[i - 1][j] == 0) continue;  // 이 전에 나올 수 없는 결과였으면 넘어가기

                diff = j - num[i];
                if (diff >= 0) {
                    // 이 전에 나온 결과에서 뺀 값의 위치에 더하기
                    equation[i][diff] += equation[i - 1][j];
                }

                sum = j + num[i];
                if (sum <= 20) {
                    // 이 전에 나온 결과에서 더한 값의 위치에 더하기
                    equation[i][sum] += equation[i - 1][j];
                }
            }
        }

        // 마지막으로 나온 수에 해당하는 위치에 있는 값 확인
        System.out.print(equation[N - 2][num[N - 1]]);
    }
}
