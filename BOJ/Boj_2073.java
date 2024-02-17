import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2073 {
    // 2073. 수도배관공사

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken()),
                P = Integer.parseInt(st.nextToken());

        int[] dp = new int[D + 1];

        dp[0] = Integer.MAX_VALUE;
        int l, c;   // 길이, 용량

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            for (int j = D; j >= l; j--)    // 뒤부터 진행해야 현재 보는 값에 영향을 미치지 않는다
                dp[j] = Math.max(dp[j], Math.min(dp[j - l], c));
            // 연결한 파이프와 현재 파이프 비교해서 작은 값과 저장된 값 중 큰 값을 저장
        }
        System.out.print(dp[D]);
    }

}
