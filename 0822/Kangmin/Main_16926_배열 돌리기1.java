import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_배열 돌리기1{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int r=0; r<R; r++){
            // 임시 배열 선언
            int[][] temp = new int[N][M];
            boolean[][] visited = new boolean[N][M];

            int kx = 0, ky = 0;
            while(!visited[kx][ky]){
                //아래로 움직임
                for(int i=kx; i<N-1-kx; i++){
                    visited[i][ky] = true;
                    temp[i+1][ky] = grid[i][ky];
                }
                //오른쪽으로 움직임
                for(int i=ky; i<M-1-ky; i++){
                    visited[N-1-kx][i] = true;
                    temp[N-1-kx][i+1] = grid[N-1-kx][i];
                }
                //위쪽으로 움직임
                for(int i=N-1-kx; i>kx; i--){
                    visited[i][M-1-ky] = true;
                    temp[i-1][M-1-ky] = grid[i][M-1-ky];
                }
                //왼쪽으로 이동
                for(int i=M-1-ky; i>ky; i--){
                    visited[kx][i] = true;
                    temp[kx][i-1] = grid[kx][i];
                }
                kx += 1;
                ky += 1;
            }
            grid = temp;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(grid[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}