import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_21922 {
    // 21922. 학부 연구생 민상

    static List<Pos> airConditioner = new ArrayList<>();
    static int N, M;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
    static int[][] lab;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 9) {
                    airConditioner.add(new Pos(i, j));
                }
            }
        }
        // 에어컨이 0개일 땐 굳이 돌 필요가 없다
        System.out.print(airConditioner.isEmpty() ? 0 : findSeat());
    }

    static int findSeat() {
        // 민상이가 앉을 자리의 개수 반환
        int cnt = 0;
        boolean[][] visited = new boolean[N][M];
        int r, c, dir, num;

        for (Pos pos : airConditioner) {
            for (int i = 0; i < 4; i++) {
                r = pos.r;
                c = pos.c;
                dir = i;
                do {
                    // 앉을 수 있는 자리인지 체크
                    if (!visited[r][c]) {
                        visited[r][c] = true;
                        cnt++;
                    }

                    // 방향 바꾸는 처리
                    num = lab[r][c];
                    if (0 < num && num < 9) {
                        if (num <= 2) { // 1이나 2번 물건이 있을 때
                            if (num % 2 == dir % 2) dir = (dir + 2) % 4;
                        } else {    // 3이나 4번 물건이 있을 때
                            if (num % 2 == dir % 2) dir = ((dir + 4) - 1) % 4;
                            else dir = (dir + 1) % 4;
                        }
                    }
                    r += dr[dir];
                    c += dc[dir];

                } while (r >= 0 && r < N && c >= 0 && c < M && lab[r][c] != 9);
                // 범위를 나갔거나 에어컨 자리로 오면 더이상 돌지 않는다
            }
        }
        return cnt;
    }
}

class Pos {
    int r, c;

    public Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}