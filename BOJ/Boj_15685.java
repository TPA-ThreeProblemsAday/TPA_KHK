import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_15685 {
    // 15685. 드래곤 커브

    static int[] dy = {0, -1, 0, 1}, dx = {1, 0, -1, 0}; // 우, 상, 좌, 하 (시계로 돌린 방향)
    static boolean[][] map = new boolean[101][101];
    static List<Integer> dir = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        int N = Integer.parseInt(reader.readLine());
        int x, y, d, g;

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            x = Integer.parseInt(tokenizer.nextToken());
            y = Integer.parseInt(tokenizer.nextToken());
            d = Integer.parseInt(tokenizer.nextToken());
            g = Integer.parseInt(tokenizer.nextToken());

            curve(d, g);
            move(x, y);
        }
        System.out.println(checkMap());
    }

    static void curve(int d, int g) {
        // 회전하는 방향 기록
        dir.add(d);
        int size;

        for (int i = 0; i < g; i++) {
            size = dir.size();

            // 지금까지 저장된 방향 거꾸로 읽은 것 + 1
            for (int j = 1; j <= size; j++) {
                dir.add((dir.get(size - j) + 1) % 4);
            }
        }
    }

    static void move(int x, int y) {
        map[y][x] = true;

        // 리스트에 저장된 방향대로 움직이기
        for (int d : dir) {
            y += dy[d];
            x += dx[d];

            map[y][x] = true;
        }
        // 리스트 비우기
        dir.clear();
    }

    static int checkMap() {
        int cnt = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 정사각형 체크
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) cnt++;
            }
        }

        return cnt;
    }
}
