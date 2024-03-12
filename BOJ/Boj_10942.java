import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_10942 {
    // 10942. 팰린드롬?

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];
        int[][] arr = new int[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
            input[j] = Integer.parseInt(st.nextToken());
            for (int i = 0; i <= j; i++) {
                if (i == j) arr[i][j] = 1;  // 1개일때
                else if (j - i == 1) arr[i][j] = input[j] == input[i] ? 1 : 0;  // 2개일때
                else {
                    // 3개 이상이면 i와 j번째 원소가 같고 그 사이가 팰린드롬이어야 한다
                    arr[i][j] = (input[j] == input[i] && arr[i + 1][j - 1] == 1) ? 1 : 0;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        int S, E;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            sb.append(arr[S - 1][E - 1] + "\n");
        }
        System.out.print(sb.toString());
    }
}
