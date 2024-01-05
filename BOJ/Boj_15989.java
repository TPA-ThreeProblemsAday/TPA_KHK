import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_15989 {
	// 1, 2, 3 더하기 4
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine()), num;
		int[][] sum = new int[10001][4];
		sum[1][1] = 1; // 1
		sum[2][1] = 1; // 1+1
		sum[2][2] = 1; // 2
		sum[3][1] = 1; // 1+1+1
		sum[3][2] = 1; // 1+2
		sum[3][3] = 1; // 3
		// 중복 방지를 위해 오름차순으로만 더하기
		for (int i = 4; i <= 10000; i++) {
			sum[i][1] = sum[i - 1][1];
			sum[i][2] = sum[i - 2][1] + sum[i - 2][2];
			sum[i][3] = sum[i - 3][1] + sum[i - 3][2] + sum[i - 3][3];
		}

		for (int i = 0; i < n; i++) {
			int t = Integer.parseInt(br.readLine());
			num = sum[t][1] + sum[t][2] + sum[t][3];
			sb.append(num + "\n");
		}
		System.out.println(sb.toString());
	}
}
