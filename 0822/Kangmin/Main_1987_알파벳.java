import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1987_알파벳{
    static int N,M,answer = 1;
    static int[] dx = new int[]{-1,1,0,0}, dy = new int[]{0,0,-1,1};
    static char[][] grid;
    static boolean[] alpha = new boolean[26];	// 알파벳 방문 확인을 위한 배열

    public static void dfs(int kx, int ky, int temp){
        if(temp >= answer)
            answer = temp;
        for(int i=0; i<4; i++){
            int nx = kx + dx[i], ny = ky + dy[i];
            if(0 > nx || nx >= N || 0 > ny || ny >= M)
                continue;
            if(!alpha[grid[nx][ny] - 'A']){	// 방문기록 확인
                alpha[grid[nx][ny] - 'A'] = true;
                dfs(nx, ny, temp+1);	// 백트래킹
                alpha[grid[nx][ny] - 'A'] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new char[N][M];

        // 지도 입력
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                grid[i][j] = input.charAt(j);
            }
        }

        // 탐색 시작
        int curr = grid[0][0] - 'A';
        alpha[curr] = true;
        dfs(0,0, 1);

        System.out.println(answer);
    }
}