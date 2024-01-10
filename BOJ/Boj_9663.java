import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_9663 {
	// N-Queen

	static int[] arr;
	static int cnt = 0, n;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		arr = new int[n];

		nQueen(0);

		System.out.print(cnt);
	}

	static void nQueen(int depth) {
		if (depth == n) {
			cnt++;
			return;
		}

		for (int i = 0; i < n; i++) {
			// depth 행의 퀸 열 지정
			arr[depth] = i;

			if(isPossible(depth)) nQueen(depth + 1);
		}
	}
	
	static boolean isPossible(int col) {
		for (int i = 0; i < col; i++) {
			// 세로줄에 퀸이 있나 검사
			if (arr[i] == arr[col]) return false;
			
			// 대각선에 있나 검사
			if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) return false;
		}
		
		return true;
	}

}
