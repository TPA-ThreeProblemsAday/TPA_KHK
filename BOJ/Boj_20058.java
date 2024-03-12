import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_20058 {
    // 마법사 상어와 파이어스톰
    static int N, Q, size, remainCnt = 0, max = 0;
    static int[][] ice;
    static int[] L, dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        size = (int) Math.pow(2, N);
        ice = new int[size][size];
        L = new int[Q];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < size; j++) {
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        fireStorm();
        calSize();
        System.out.println(remainCnt);
        System.out.print(max);
    }

    static void fireStorm() {
        int[][] copy = new int[size][size];
        int divSize, or, oc, cr, cc;    // 원래 좌표, 회전된 좌표

        for (int l = 0; l < Q; l++) {

            divSize = (int) Math.pow(2, L[l]);  // 나눈 격자의 크기

            // 돌리기
            for (int r = 0; r < size; r += divSize) {
                for (int c = 0; c < size; c += divSize) {
                    // 원래 배열은 왼쪽 밑부터, 복사될 배열은 왼쪽 위부터 채우면서 시계방향으로 돌리기 실행
                    cr = r;
                    cc = c;

                    or = r + divSize - 1;
                    oc = c;
                    for (int i = 0; i < divSize; i++) {
                        for (int j = 0; j < divSize; j++) {
                            copy[cr + i][cc + j] = ice[or - j][oc + i];
                        }
                    }
                }
            }

            // 방금 녹은 얼음이 영향 안주도록 카피
            for (int i = 0; i < size; i++) ice[i] = copy[i].clone();

            // 얼음 줄이기
            int nr, nc, cnt;

            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    cnt = 0;
                    for (int i = 0; i < 4; i++) {
                        nr = r + dr[i];
                        nc = c + dc[i];

                        if (nr < 0 || nc < 0 || nr >= size || nc >= size) continue;

                        if (copy[nr][nc] > 0) cnt++;
                    }
                    if (cnt < 3) ice[r][c]--;
                }
            }
        }

    }

    static void calSize() {
        // 얼음 양의 합과 가장 큰 크기 계산
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[size][size];
        int nr, nc, cnt;
        Point point;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // 돌면서 가장 큰 덩어리를 본다.
                if (!visited[r][c] && ice[r][c] > 0) {
                    cnt = 0;
                    queue.add(new Point(r, c));
                    visited[r][c] = true;

                    while (!queue.isEmpty()) {
                        point = queue.poll();
                        cnt++;
                        // 덩어리 사이즈++

                        remainCnt += ice[point.r][point.c];
                        // 남은 얼음 양에 더해주기

                        for (int i = 0; i < 4; i++) {
                            nr = point.r + dr[i];
                            nc = point.c + dc[i];

                            if (nr < 0 || nc < 0 || nr >= size || nc >= size ||
                                    visited[nr][nc] || ice[nr][nc] <= 0) continue;

                            visited[nr][nc] = true;
                            queue.add(new Point(nr, nc));
                        }
                    }
                    max = Math.max(max, cnt);   // 제일 큰 덩어린지
                }
            }
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
