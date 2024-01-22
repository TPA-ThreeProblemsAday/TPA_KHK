import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2294 {
    // 2294. 동전 2

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int max = 10001;

        int n = Integer.parseInt(st.nextToken()),
                k = Integer.parseInt(st.nextToken());

        int[] w = new int[n + 1];
        int[][] cost = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            w[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= n; i++) Arrays.fill(cost[i], max);

        for (int j = 0; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                if (j == 0) {
                    cost[i][j] = 0;
                    continue;
                }
                if (w[i] > j) {
                    cost[i][j] = cost[i - 1][j];
                    continue;
                }

                cost[i][j] = Math.min(cost[i][j - w[i]] + 1, cost[i - 1][j]);
            }
        }
        // 마지막 행과 열을 보았을 때 max 값이면 불가능해서 갱신이 되지 않았다는 뜻이므로 -1 출력
        System.out.println(cost[n][k] == max ? -1 : cost[n][k]);
    }
}