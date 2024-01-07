import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj_1715 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 카드 정렬하기

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		int bundle, sum = 0;

		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}

		// 작은 카드 두 장 뽑아서 합치고 합친 카드 다시 넣기
		while (pq.size() > 1) {
			bundle = pq.poll() + pq.poll();
			sum += bundle;
			pq.add(bundle);
		}
		System.out.print(sum);
	}

}
