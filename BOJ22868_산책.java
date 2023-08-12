package algorithm_per;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ22868_산책 {
	static int N;
	static int M;
	
	static int s;
	static int e;
	
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int answer;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N+1;i++)
			graph.add(new ArrayList<>());
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int n1= Integer.parseInt(st.nextToken());
			int n2= Integer.parseInt(st.nextToken());
			
			graph.get(n1).add(n2);
			graph.get(n2).add(n1);
		}
		
		for(int i=0;i<N+1;i++) 
			Collections.sort(graph.get(i));
		
		st = new StringTokenizer(br.readLine());
		s= Integer.parseInt(st.nextToken());
		e= Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		
		bfs(s,e);
		visited[s]=false;
		visited[e]=false;
		bfs(e,s);
		System.out.println(answer);
		
	}
	
	public static void bfs(int from, int to) {
		
		
		Deque<Integer> dq = new ArrayDeque<Integer>();
		dq.add(from);
		visited[from]=true;
		
		boolean isArrive=false;
		
		int[] parent = new int[N+1];
		
		while(!dq.isEmpty()) {
			int now = dq.poll();
			
			for(int node:graph.get(now)) {
				if(!visited[node]) {		
				parent[node]=now;
				visited[node]=true;
				
				if(node==to) {
					isArrive=true;
					break;
				}
				
				dq.add(node);
			}
			}
			if(isArrive) break;
		}
		
		boolean[] temp = new boolean[N+1];
		int idx = to;
		while(idx!=from) {
			temp[idx]=true;
			answer++;
			idx = parent[idx];
		}
		visited=temp;
	}


}
