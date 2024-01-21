import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1106 {
    // 1106. 호텔

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken()),
                N = Integer.parseInt(st.nextToken());

        int[] cost = new int[N + 1], cnt = new int[N + 1];
        int[][] customer = new int[N + 1][C + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            cost[i] = Integer.parseInt(st.nextToken());
            cnt[i] = Integer.parseInt(st.nextToken());

        }

        for (int i = 0; i <= C; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == 0 || j == 0) {
                    customer[j][i] = 10000; // 최솟값 비교를 위해 최댓값으로 초기화
                    continue;
                }

                // 현재 인덱스에서 도시의 비용 + 가중치를 뺀 배열에 저장된 비용
                // 지금까지 저장된 비용 비교
                if (cnt[j] < i) {
                    customer[j][i] = Math.min(customer[j - 1][i], customer[j][i - cnt[j]] + cost[j]);
                } else {
                    customer[j][i] = Math.min(customer[j - 1][i], cost[j]);
                }
            }
        }

//        for (int i = 0; i <= N; i++) {
//            for (int j = 0; j <= C; j++) {
//                System.out.print(customer[i][j]);
//            }
//            System.out.println();
//        }

        System.out.println(customer[N][C]);
    }
}
