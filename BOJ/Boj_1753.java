import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1753 {
	// 1753. 최단경로
	// 간선이 많아서 인접행렬로 하면 메모리 초과

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		final int INF = 100_000_000;
		int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());

		int k = Integer.parseInt(br.readLine());

		List<Node>[] list = new ArrayList[V + 1];
		int[] dis = new int[V + 1];
		boolean[] visited = new boolean[V + 1];

		for (int i = 0; i <= V; i++) {
			list[i] = new ArrayList<>();
		}

		int u, v, w;

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			list[u].add(new Node(v, w));
		}

		Arrays.fill(dis, INF);
		dis[k] = 0;

		int cur;
		Node node;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 시작점 넣어주기
		pq.add(new Node(k, 0));

		while (!pq.isEmpty()) {
			node = pq.poll();
			visited[node.end] = true; // 방문처리
			cur = node.end;

			for (Node n : list[cur]) {
				// 저장된 최단 경로보다 현재가중치 + 다음 정점으로 가는 가중치가 작으면 갱신
				if (!visited[n.end] && dis[n.end] > dis[cur] + n.weight) {
					dis[n.end] = dis[cur] + n.weight;
					pq.add(new Node(n.end, dis[n.end]));
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			if (dis[i] == INF) sb.append("INF\n");
			else sb.append(dis[i] + "\n");
		}

		System.out.println(sb.toString());
	}

}

class Node implements Comparable<Node> {
	int end, weight;

	public Node(int end, int weight) {
		super();
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}

}
