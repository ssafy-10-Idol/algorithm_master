import java.util.*;
import java.io.*;

public class Main_bj_1697_숨바꼭질 {
	static Queue<Integer> que =new ArrayDeque<>();
	static int dauter;
	static int count;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		que.add(Integer.parseInt(st.nextToken()));
		dauter=Integer.parseInt(st.nextToken());
		count=0;
		visited=new boolean[200000];
		bfs();
	}
	private static void bfs() {
		while(true) {
			int queSize = que.size();
			for (int i = 0; i < queSize; i++) {
				int num = que.poll();
				if(num==dauter) {
					System.out.println(count);
					return;
				}
				if(num>0 && visited[num-1]==false) {
					visited[num-1]=true;
					que.add(num-1);
				}
				if(num<dauter && visited[num+1]==false) {
					visited[num+1]=true;
					que.add(num+1);
				}
				if(num<dauter && visited[num*2]==false) {
					visited[num*2]=true;
					que.add(num*2);
				}
			}
			count++;
		}
	}

}
