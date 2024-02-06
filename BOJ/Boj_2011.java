import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2011 {
    // 2011. 암호코드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        final int MOD = 1000000;

        int len = code.length();
        int pre, now;

        int[][] dp = new int[len][2];   // dp[n][0]: 한자릿수 dp[n][1]: 두자릿수

        if (code.charAt(0) != '0') {
            // 첫자리가 0이면 그냥 불가능
            dp[0][0] = 1;

            for (int i = 1; i < len; i++) {
                pre = code.charAt(i - 1) - '0';
                now = code.charAt(i) - '0';

                if (pre == 0 && now == 0) break;

                if (now == 0) {
                    // 현재 숫자가 0이면 무조건 두자릿수여야 함
                    if (pre * 10 + now > 26) break;
                    dp[i][1] = dp[i - 1][0];
                } else if (pre == 0) dp[i][0] = dp[i - 1][1]; // 이전 숫자가 0이면 이번 숫자는 한자릿수 숫자만 가능
                else {
                    // 한자릿수 숫자는 이전 수가 한자리였거나 두자리였거나 상관이 없다.
                    dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
                    if (pre * 10 + now <= 26) { // 두자릿수 숫자이려면 이전수와 합쳤을 때 26 안에 들어가야함
                        dp[i][1] = dp[i - 1][0];
                    }
                }
            }
        }

        System.out.print((dp[len - 1][0] + dp[len - 1][1]) % MOD);
    }
}
