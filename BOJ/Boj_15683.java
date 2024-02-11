import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_15683 {
    // 15683. 감시
    static List<Point> pointList = new ArrayList<>();
    static int blindCnt = 0, N, M, office[][], min = 64;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; //상, 우, 하, 좌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        office = new int[N][M];
        boolean[][] visited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());

                office[i][j] = input;

                if (input == 0) blindCnt++; // 0인 위치 개수 저장
                else if (input < 6) pointList.add(new Point(i, j, input));
            }
        }
        makeList();
        findBlindSpot(0, blindCnt, visited);

        System.out.print(min);
    }

    static void makeList() {
        // cctv가 감시하는 좌표를 미리 계산하여 리스트에 넣기

        int r, c, n;
        int nr, nc;

        for (Point p : pointList) {
            r = p.r;
            c = p.c;
            n = p.num;

            for (int i = 0; i < 4; i++) {
                // 회전하는 방향

                p.lists[i] = new ArrayList<>();

                // 상하, 좌우 두 방향만 체크
                if (n == 2 && i > 1) continue;
                else if (n == 5 && i > 0) continue;
                // 사방 한번만 체크

                for (int j = 0; j < 4; j++) {
                    // 회전한 방향에서 해당 cctv가 보는 방향
                    if (n == 1 && j != i) continue;
                    else if (n == 2 && j != i && j != i + 2) continue;
                    else if (n == 3 && j != i && j != (i + 1) % 4) continue;
                    else if (n == 4 && j == i) continue;

                    nr = r;
                    nc = c;
                    while (true) {
                        nr += dr[j];
                        nc += dc[j];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M || office[nr][nc] == 6) break;

                        if (office[nr][nc] == 0) p.lists[i].add(new Point(nr, nc));
                    }
                }
            }
        }
    }

    static void findBlindSpot(int n, int cnt, boolean[][] visit) {
        if (n == pointList.size()) {
            min = Math.min(min, cnt);
            return;
        }
        boolean[][] copy = new boolean[N][M];

        Point p = pointList.get(n);
        int cntCopy;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                copy[j] = visit[j].clone();
            }
            cntCopy = cnt;

            // 리스트에 저장된 위치들 체크
            for (Point point : p.lists[i]) {
                if (!copy[point.r][point.c]) {
                    copy[point.r][point.c] = true;
                    cntCopy--;
                }
            }
            findBlindSpot(n + 1, cntCopy, copy); // 다음 cctv로 넘어가기
        }
    }
}

class Point {
    int r, c, num;
    List<Point>[] lists;
    // 각 방향마다 cctv가 보는 사무실의 좌표

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public Point(int r, int c, int num) {
        this.r = r;
        this.c = c;
        this.num = num;
        lists = new List[4]; // 4방향으로 회전하기 때문
    }
}