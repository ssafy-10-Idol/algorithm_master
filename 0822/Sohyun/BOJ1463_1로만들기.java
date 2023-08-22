import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ1463_1로만들기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static Deque<int[]> dq;
	static int[] answer;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		dq = new ArrayDeque<>();
		dq.add(new int[] {N,0}); //n, cnt
		
		answer = new int[1_000_001];
		while(!dq.isEmpty()) {
			int n = dq.peek()[0];
			int cnt = dq.poll()[1];
			
			if(n==1) break;
			
			addToDeque(n/3,n%3,cnt);
			addToDeque(n/2,n%2,cnt);
			addToDeque(n-1,0,cnt);
		}
		System.out.println(answer[1]);
		
		
	}
	private static void addToDeque(int n, int isZero, int cnt) {
		if(isZero!=0) return;
		if(answer[n]!=0) return;
		
		dq.add(new int[] {n,cnt+1});
		answer[n]=cnt+1;
	}

}
