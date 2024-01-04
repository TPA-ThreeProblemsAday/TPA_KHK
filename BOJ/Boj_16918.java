import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16918 {
	// 봄버맨

	static int[] dr = { 0, -1, 1, 0, 0 }, dc = { 0, 0, 0, -1, 1 }; // 제자리, 상, 하, 좌, 우
	static int R, C, N;
	static char[][] map;
	static Queue<Pos> queue = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) map[i][j] = str.charAt(j);
		}

		bomb(N);
		System.out.print(sb.toString());
	}

	static void bomb(int n) {
		if (n % 2 == 0) {
			// 모든 자리가 'O'
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append('O');
				}
				sb.append('\n');
			}
		} else if (n == 1) {
			// 원래 입력과 같다
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
			return;
		} else {
			// 홀수일 때 폭탄이 터진 모양
			Pos pos;
			int r, c, nr, nc;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == 'O') queue.add(new Pos(i, j));
					map[i][j] = 'O';
				}
			}
			while (!queue.isEmpty()) {
				pos = queue.poll();
				r = pos.r;
				c = pos.c;
				for (int i = 0; i < 5; i++) {
					nr = r + dr[i];
					nc = c + dc[i];
					if (nr >= R || nr < 0 || nc >= C || nc < 0) continue;
					map[nr][nc] = '.';
				}
			}
			if (n % 4 == 3) {
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						sb.append(map[i][j]);
					}
					sb.append('\n');
				}
			}
		}

		// 1일 때와 다른 경우가 있어 한번 더 폭탄 터뜨리기
		if (n % 4 == 1) bomb(3);
	}
}

class Pos {
	int r, c;

	public Pos(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}