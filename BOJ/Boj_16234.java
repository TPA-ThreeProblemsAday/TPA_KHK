package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16234 {
    // 16234. 인구 이동

    static int N, L, R, day = 0;
    static int[][] A;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(0);
        System.out.println(day);
    }

    // 인구 이동
    static void move(int date) {
        // 인구 이동은 최대 2000번만 일어나므로 매번 새로 할당해도 메모리 충분
        boolean[][] visited = new boolean[N][N];
        boolean canMove = false;    // 연합이 존재하는지 여부를 저장할 변수
        int r, c, nr, nc, dif, sum;
        Queue<Country> union = new LinkedList<>();  // 연합한 나라들을 저장해둘 큐
        Queue<Country> queue = new LinkedList<>();  // bfs를 위한 큐
        Country country;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                visited[i][j] = true;
                sum = 0;

                union.add(new Country(i, j));
                queue.add(new Country(i, j));

                // 연합이 있는지 bfs 돌리기
                while (!queue.isEmpty()) {
                    country = queue.poll();

                    r = country.r;
                    c = country.c;

                    sum += A[r][c];

                    for (int k = 0; k < 4; k++) {
                        nr = r + dr[k];
                        nc = c + dc[k];

                        // 경계를 벗어났거나 이미 방문했던 곳인지 체크
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

                        dif = Math.abs(A[r][c] - A[nr][nc]);
                        if (dif >= L && dif <= R) {
                            // 큐에 넣기 전에 방문체크
                            visited[nr][nc] = true;
                            queue.add(new Country(nr, nc));
                            union.add(new Country(nr, nc));
                        }
                    }
                }

                // union의 크기가 1이면 주변에 연합이 없었다는 것
                if (union.size() > 1) {
                    int population = sum / union.size();
                    canMove = true;

                    while (!union.isEmpty()) {
                        country = union.poll();
                        A[country.r][country.c] = population;
                    }
                } else {
                    union.clear();
                }
            }
        }

        if (canMove) move(date + 1);
        else day = date;
    }
}

class Country {
    int r, c;

    public Country(int r, int c) {
        this.r = r;
        this.c = c;
    }
}