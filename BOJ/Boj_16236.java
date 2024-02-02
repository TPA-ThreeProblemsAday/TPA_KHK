import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16236 {
    // 16236. 아기 상어
    // memory: 12076KB	time: 80ms
    static final int MAX = 400;
    static int N, sharkSize = 2, cnt = 0;
    static int[][] map;
    static int[] fish = new int[7], dr = {-1, 0, 0, 1}, dc = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int num, r = 0, c = 0;
        map = new int[N][N];
        StringTokenizer st;

        // 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                num = Integer.parseInt(st.nextToken());
                map[i][j] = num;

                if (num == 9) {
                    r = i;
                    c = j;
                } else if (num > 0) fish[num]++;    // 물고기 크기에 해당하는 배열에 갯수 저장 (먹을 수 있는 물고기 판단하기 위해)
            }
        }

        System.out.println(hunt(r, c));
    }

    static int hunt(int r, int c) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        int eatableCnt = fish[1], sizeCnt = 0;  // 먹을 수 있는 물고기 수, 현재 사이즈에서 먹은 물고기 수
        Point ediblePos = new Point(20, 20, MAX);   // 가까운 먹을 수 있는 물고기
        queue.add(new Point(r, c, 0));  // 시작점 큐에 넣기

        visited[r][c] = true;
        map[r][c] = 0;

        int nr, nc, rr, cc, dis;
        Point point;

        while (eatableCnt > 0 && !queue.isEmpty()) {
            point = queue.poll();

            // 큐에 있는 지점의 위치와 거리
            rr = point.r;
            cc = point.c;
            dis = point.dis;

            for (int i = 0; i < 4; i++) {
                nr = rr + dr[i];
                nc = cc + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;   // 경계 밖이거나
                if (visited[nr][nc] || map[nr][nc] > sharkSize) continue;   // 이미 방문했거나 못지나가는 물고기

                queue.add(new Point(nr, nc, dis + 1));
                visited[nr][nc] = true;

                if (ediblePos.r < 20 && dis + 1 > ediblePos.dis) {
                    // 먹은 물고기가 있고 지금 저장된 물고기가 제일 조건에 맞는 물고기일 때
                    queue.clear();
                    break;
                }

                if (map[nr][nc] > 0 && map[nr][nc] < sharkSize) {
                    // 먹을 수 있는 물고기일 때
                    if (ediblePos.r > nr || (ediblePos.r == nr && ediblePos.c > nc)) {
                        // 가장 위 혹은 왼쪽 물고기
                        ediblePos = new Point(nr, nc, dis + 1);
                    }
                }
            }

            if (ediblePos.r < 20 && queue.isEmpty()) {
                cnt += ediblePos.dis;
                // 최적의 물고기까지 오는데 걸린 거리 더해주기

                r = ediblePos.r;
                c = ediblePos.c;

                // 물고기 위치 초기화
                ediblePos.r = 20;
                ediblePos.c = 20;
                ediblePos.dis = MAX;

                queue.add(new Point(r, c, 0));
                visited = new boolean[N][N];
                visited[r][c] = true;
                map[r][c] = 0;

                sizeCnt++;

                // 본인의 크기만큼 물고기 먹었으면 상어 크기 키워주기
                if (sizeCnt == sharkSize && sharkSize < 7) {
                    eatableCnt += fish[sharkSize++];
                    sizeCnt = 0;
                }
            }
        }

        return cnt;
    }
}

class Point {
    int r, c, dis;

    public Point(int r, int c, int dis) {
        this.r = r;
        this.c = c;
        this.dis = dis;
    }
}