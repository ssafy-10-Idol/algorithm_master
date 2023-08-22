import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA8382_방향전환 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int[]start, end;
	static int[][] answer;
	static int result;
	static int[][][] delta= {{{1,0},{-1,0}},{{0,1},{0,-1}}}; //[0]:가로, [1]:세로
	public static void main(String[] args) throws Exception {

		int t = Integer.parseInt(br.readLine());
		for(int idx=1;idx<=t;idx++) {
			input();
			result = Math.min(result, bfs(0));
			result = Math.min(result, bfs(1));
			print(idx);
		}
		System.out.println(sb);
	}
	private static void print(int idx) {
		sb.append("#"+idx+" "+result+"\n");
		
	}
	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		start = new int[2];
		end = new int[2];
		start[0] = Integer.parseInt(st.nextToken())+100;
		start[1] = Integer.parseInt(st.nextToken())+100;
		end[0] = Integer.parseInt(st.nextToken())+100;
		end[1] = Integer.parseInt(st.nextToken())+100;
		
		result = Integer.MAX_VALUE;
	}
	
	public static int bfs(int pre) {
		answer = new int[201][201];
		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] {start[0],start[1],0,pre}); //r,c,cnt,가로/세로
		
		while(!dq.isEmpty()) {
			int[] now = dq.poll();
			int r = now[0];
			int c = now[1];
			int cnt = now[2];
			int prev = now[3];
			
			if(r==end[0] && c==end[1]) {
				return cnt;
			}
			for(int[] d: delta[(prev+1)%2]) {
				int nr = r+d[0];
				int nc = c+d[1];
				if(is_valid_coord(nr,nc) && answer[nr][nc]==0) {
					answer[nr][nc]=cnt+1;
					dq.add(new int[] {nr,nc,cnt+1,(prev+1)%2});
				}
			}
			
		}
		return Integer.MAX_VALUE;
	}
	private static boolean is_valid_coord(int r, int c) {
		return 0<=r && r<201 && 0<=c && c<201;
	}

}
