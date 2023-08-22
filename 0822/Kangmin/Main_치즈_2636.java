import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main_치즈_2636{
    static int[][] grid;
    static int N,M;
    static int[] dx = new int[]{0,1,0,-1}, dy = new int[]{1,0,-1,0};

    public static void find_air(){
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0});
        grid[0][0] = 2;
        visited[0][0] = true;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            for(int i=0; i<4; i++){
                int nx = curr[0] + dx[i], ny = curr[1] + dy[i];
                if(0 > nx || nx >= N || 0 > ny || ny >= M)
                    continue;
                if(grid[nx][ny] == 0 && !visited[nx][ny]){
                    grid[nx][ny] = 2;
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny});
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int total = 0, answer1 = 0, answer2 = 0;
        grid = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 1)
                    total++;
            }
        }

        while(total!= 0){
            // 공기 부분 탐색 - BFS
            find_air();

            // 녹일 치즈 찾고 지우기
            int temp1 = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(grid[i][j] == 1){
                        for(int k=0; k<4; k++){
                            int nx = i + dx[k], ny = j + dy[k];
                            if(grid[nx][ny] == 2){
                                grid[i][j] = 0;
                                temp1++;
                                break;
                            }
                        }
                    }
                }
            }

            answer1++;
            // 바뀐 내용 복구
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(grid[i][j] == 2)
                        grid[i][j] = 0;
                }
            }
            //종료 조건
            if(total - temp1 == 0){
                answer2 = temp1;
                break;
            }
            total -= temp1;
        }

        System.out.println(answer1);
        System.out.println(answer2);
    }
}