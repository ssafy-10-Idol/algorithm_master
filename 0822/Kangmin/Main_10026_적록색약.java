import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_10026_적록색약 {
    static int N;
    static int[] dx = new int[]{0,1,0,-1}, dy = new int[]{1,0,-1,0};
    static char[][] grid;
    static boolean[][] visited;
    // 적록 색약이 아닌 경우의 영역 탐색
    public static void dfs1(int kx, int ky, char color){
        for(int i=0; i<4; i++){
            int nx = kx + dx[i], ny = ky + dy[i];
            if(0 > nx || nx >= N || 0 > ny || ny >= N)
                continue;
            if(grid[nx][ny] == color && !visited[nx][ny]){
                visited[nx][ny] = true;
                dfs1(nx,ny,color);
            }
        }
    }
    // 적록 색약인 경우의 영역 탐색
    public static void dfs2(int kx, int ky, char color){
        for(int i=0; i<4; i++){
            int nx = kx + dx[i], ny = ky + dy[i];
            if(0 > nx || nx >= N || 0 > ny || ny >= N)
                continue;
            if(!visited[nx][ny]){
                if(color == 'B' && grid[nx][ny] == 'B'){
                    visited[nx][ny] = true;
                    dfs2(nx,ny,color);
                }
                else if((color == 'R' || color == 'G') && (grid[nx][ny] == 'R' || grid[nx][ny] == 'G')){
                    visited[nx][ny] = true;
                    dfs2(nx,ny,color);
                }

            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new char[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<N; j++){
                grid[i][j] = input.charAt(j);
            }
        }
        int answer1 = 0, answer2 = 0;
        // 적록색약 아님
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j])
                    continue;
                visited[i][j] = true;
                dfs1(i,j,grid[i][j]);
                answer1++;
            }
        }

        visited = new boolean[N][N];
        // 적록색약
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j])
                    continue;
                visited[i][j] = true;
                dfs2(i,j,grid[i][j]);
                answer2++;
            }
        }

        System.out.println(answer1 + " " + answer2);
    }
}