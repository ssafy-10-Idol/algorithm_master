import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution_1861_정사각형 방{
    static int N;
    static int[][] grid, step;
    static int[] dx = new int[]{0,1,0,-1}, dy = new int[]{1,0,-1,0};
    // 메모리제이션 방법 사용
    public static int dfs(int kx, int ky){
	// 처음 방문한 곳이면 0으로 초기화
        if(step[kx][ky] == -1)
            step[kx][ky] = 0;
        for(int i=0; i<4; i++){
            int nx = kx + dx[i], ny = ky + dy[i];
            if(0 > nx || nx >= N || 0 > ny || ny >= N)
                continue;
 
            // 조건을 만족하면 다음지점으로 이동
            if(grid[nx][ny] == grid[kx][ky] + 1){
                int temp;
		// 이전에 방문한 적이 있으면 다음 지점의 결과를 불러옴
                if(step[nx][ny] != -1){
                    temp = step[nx][ny];
                }
		// 방문적이 없던 곳이라면 다음 지점으로 이동
                else{
                    temp = dfs(nx,ny);
                }
		// 현재 위치는 다음 지점의 결과 + 1 이거나 기존에 저장된 값 둘중 하나로 정해진다.
                step[kx][ky] = Math.max(step[kx][ky], temp + 1);
            }
        }
        return step[kx][ky];	// 현재 위치에 저장된 값 반환
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for(int t=1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            grid = new int[N][N];
            step = new int[N][N];
 
            for(int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            for(int i=0; i<N; i++){
                Arrays.fill(step[i], -1);
            }
 
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    dfs(i,j);
                }
            }
	    // 정답 찾기
            int answer1 = Integer.MAX_VALUE, answer2 = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(step[i][j] > answer2){
                        answer1 = grid[i][j];
                        answer2 = step[i][j];
                    } else if(step[i][j] == answer2){
                        if(grid[i][j] < answer1) {
                            answer1 = grid[i][j];
                            answer2 = step[i][j];
                        }
                    }
                }
            }
            System.out.println("#" + t + " " + answer1 + " " + (answer2+1));
        }
    }
}