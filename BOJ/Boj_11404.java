import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11404 {
	// 11404. 플루이드
	
	 static int[][] node;
	    
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st;
	        StringBuilder sb = new StringBuilder();
	        
	        int n = Integer.parseInt(br.readLine());
	        int m = Integer.parseInt(br.readLine());
	        int s, e, cost;
	        
	        node = new int[n + 1][n + 1];
	        for(int i = 1; i <= n; i++) {
	            for(int j = 1; j <= n; j++) {
	                node[i][j] = 100000001;
	            }
	            node[i][i] = 0;
	        }
	        
	        for(int i = 0; i < m; i++) {
	        	st = new StringTokenizer(br.readLine());
	            
	        	s = Integer.parseInt(st.nextToken());
	            e = Integer.parseInt(st.nextToken());
	            cost = Integer.parseInt(st.nextToken());
	            if(node[s][e] > cost) node[s][e] = cost;
	        }
	        
	        // 경-출-도
	        for(int k = 1; k < n + 1; k++) {
	            for(int i = 1; i < n + 1; i++) {
	                for(int j = 1; j < n + 1; j++) {
	                    if(node[i][j] > node[i][k] + node[k][j])
	                        node[i][j] = node[i][k] + node[k][j];
	                }
	            }
	        }
	        
	        for(int i = 1; i < n + 1; i++) {
	            for(int j = 1; j < n + 1; j++) {
	                sb.append(node[i][j] == 100000001 ? "0 " : node[i][j] + " "); //i -> j로 갈 수 없는 경우 0출력
	            }
	            sb.append("\n");
	        }
	        
	        System.out.println(sb.toString());
	    }
}
