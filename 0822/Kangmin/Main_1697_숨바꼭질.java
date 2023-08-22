import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Subin{
    int dis;
    int time;

    Subin(int dis, int time){
        this.dis = dis;
        this.time = time;
    }
}
public class Main_1697_숨바꼭질{

    public static void bfs(int start, int end){
        ArrayDeque<Subin> ad = new ArrayDeque<>();
        boolean[] visited = new boolean[100001];	// 방문 기록
        ad.add(new Subin(start, 0));
        // 시작지점과 끝지점이 같은 경우 종료
	if(start == end){
            System.out.println(0);
            System.exit(0);
        }
        while(!ad.isEmpty()){
            Subin curr = ad.poll();
            for(int i=0; i<3; i++){	// -1, +1, *2 경우로 BFS 탐색(가중치가 1)
                int nx;
                if(i==0) nx = curr.dis - 1;
                else if(i==1) nx = curr.dis + 1;
                else nx = curr.dis * 2;
		// 구간을 넘어가면 제외
                if(nx < 0 || nx > 100000)
                    continue;
                if(!visited[nx]){	// 방문 기록
                    visited[nx] = true;
                    if(nx == end){	// 정답
                        System.out.println(curr.time + 1);
                        System.exit(0);
                    }
                    ad.add(new Subin(nx, curr.time + 1));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        //01-BFS 이용
        bfs(start, end);
    }
}