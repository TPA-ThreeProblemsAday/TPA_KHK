import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj_16120 {
	// 16120. PPAP
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		String str = br.readLine();
		boolean ppap = true;
		char a, b;

		for (int i = 0; i < str.length(); i++) {
			// A가 1,2번째 자리에 있거나 A로 끝나서 PPAP가 될 수 없는 경우
			if (str.charAt(i) == 'A' && (stack.size() < 2 || i == str.length() - 1)) {
				ppap = false;
				break;
			}

			// 전 글자가 A이고 현재 글자가 P면 그 앞 두자리를 검사해서 PPAP면 P로 치환
			if (stack.size() > 1 && stack.peek() == 'A' && str.charAt(i) == 'P') {
				stack.pop();
				a = stack.pop();
				b = stack.pop();

				if (a != 'P' || b != 'P') {
					ppap = false;
					break;
				}
				stack.push('P');
				continue;
			}

			stack.add(str.charAt(i));
		}
		
		// P가 두개 이상이면 PPAP가 아니다
		if(stack.size() > 1) ppap = false;

		System.out.print(ppap ? "PPAP" : "NP");
	}

}
