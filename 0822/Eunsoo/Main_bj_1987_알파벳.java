import java.util.*;
import java.io.*;

public class Main_bj_1987_알파벳 {

	static int N, M;
	static char[][] alphaArr;
	static boolean[] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static String alphaString="";
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		alphaArr = new char[N][M];
		visited = new boolean[26];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				alphaArr[i][j] = input.charAt(j);
			}
		}
		max=1;
		visited[alphaArr[0][0]-'A']=true;
		dfs(0,0,1);
		System.out.println(max);
	}
	private static void dfs(int x,int y,int cnt) {
		max = Math.max(max, cnt);
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(!check(nx,ny)) continue;
			if(visited[alphaArr[nx][ny]-'A'])continue;
			if(!visited[alphaArr[nx][ny]-'A']) {
				visited[alphaArr[nx][ny]-'A']=true;
				dfs(nx,ny,cnt+1);
				visited[alphaArr[nx][ny]-'A']=false;
			}
		}
	}
	private static boolean check(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}

}
