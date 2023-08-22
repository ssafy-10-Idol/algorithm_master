import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main_2573_빙산 {
    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = new int[]{0,1,0,-1}, dy = new int[]{1,0,-1,0};
    public static void dfs(int cx, int cy){
        for(int i=0; i<4; i++){
            int nx = cx + dx[i], ny = cy + dy[i];
            if(0 > nx || nx >= N || 0 > ny || ny >= M)
                continue;
            if(grid[nx][ny] != 0 && !visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(nx,ny);
            }
        }
    }
    //얼음 녹이기
    public static void ice_melting(){
        int[][] temp = new int[N][M];
        // 격자점 탐색
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                int cnt = 0;
		// 주변에 0 인 부분 찾기
                for(int k=0; k<4; k++){
                    int nx = i + dx[k], ny = j + dy[k];
                    if(0 > nx || nx >= N || 0 > ny || ny >= M)
                        continue;
                    if(grid[nx][ny] == 0)
                        cnt++;
                }
                temp[i][j] = grid[i][j] - cnt;
                temp[i][j] = Math.max(temp[i][j], 0);
            }
        }
        grid = temp;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0, cnt = 0;
	// 빙하 녹이기 시행 - 한 덩어리면 정답 출력
        while(cnt < 2){
            cnt = 0;
            visited = new boolean[N][M];
            ice_melting();
	    //남은 빙하로 덩어리 확인
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(grid[i][j] == 0 || visited[i][j])
                        continue;
                    dfs(i,j);
                    cnt++;
                }
            }
	    // 빙하 다 녹으면 0 출력
            if(cnt == 0){
                answer = 0;
                break;
            }
            answer++;
        }
        System.out.println(answer);
    }
}