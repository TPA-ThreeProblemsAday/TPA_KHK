import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17144 {
	// 17144. 미세먼지 안녕!
	static int R, C, T, room[][], copy[][], dir;
	static int[] or = { -1, 0, 1, 0 }, oc = { 0, 1, 0, -1 }; // 시계방향
	static int[] cr = { 1, 0, -1, 0 }, cc = { 0, 1, 0, -1 }; // 반시계방향

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		copy = new int[R][C];

		int sum = 0;

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			diffuse();
			cleanAir();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0) sum += room[i][j];
			}
		}
		
		System.out.println(sum);
	}

	// 미세먼지 확산
	static void diffuse() {
		for (int i = 0; i < R; i++) {
			copy[i] = room[i].clone();
		}

		int nr, nc, amount;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (room[r][c] < 5)
					continue; // 5보다 작으면 확산되지 않는다

				amount = room[r][c] / 5; // 인접 방향에 퍼질 먼지의 양

				for (int j = 0; j < 4; j++) {
					nr = r + cr[j];
					nc = c + cc[j];

					// 영역 밖이거나 공청기인 경우
					if (nr >= R || nr < 0 || nc >= C || nc < 0 || room[nr][nc] < 0)
						continue;

					copy[nr][nc] += amount;
					copy[r][c] -= amount;
				}
			}
		}
	}

	// 공기청정기 가동
	static void cleanAir() {
		dir = 0;
		int pr, pc, nr, nc;
		for (int r = 2; r < R - 2; r++) {
			if (room[r][0] > -1)
				continue;

			// 반시계방향 공기순환
			nr = r - 2;
			nc = 0;
			pr = r - 1;
			pc = 0;

			while (room[nr][nc] > -1) {
				copy[pr][pc] = copy[nr][nc];
				pr = nr;
				pc = nc;

				nr = pr + or[dir];
				nc = pc + oc[dir];

				if (nr < 0 || nr > r || nc < 0 || nc >= C) {
					dir++;

					nr = pr + or[dir];
					nc = pc + oc[dir];
				}
			}

			// 공기청정기에서 나온 공기
			copy[r][1] = 0;

			// 시계방향 공기순환
			nr = r + 3;
			nc = 0;
			pr = r + 2;
			pc = 0;

			dir = 0;
			
			while (room[nr][nc] > -1) {
				copy[pr][pc] = copy[nr][nc];
				pr = nr;
				pc = nc;

				nr = pr + cr[dir];
				nc = pc + cc[dir];

				if (nr <= r || nr >= R || nc < 0 || nc >= C) {
					dir++;

					nr = pr + cr[dir];
					nc = pc + cc[dir];
				}
			}

			// 공기청정기에서 나온 공기
			copy[r + 1][1] = 0;

			for (int i = 0; i < R; i++) {
				room[i] = copy[i].clone();
			}

			break;
		}
	}
}
