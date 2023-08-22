import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
 
class Pair{
    int x;
    int y;
    int dir;
 
    Pair(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
public class Solution_8382_방향 전환{
 
    static int[] dx1 = new int[]{0,0}, dy1 = new int[]{1,-1};
    static int[] dx2 = new int[]{1,-1}, dy2 = new int[]{0,0};
 
    public static int bfs(int x1, int y1, int x2, int y2) {
        boolean[][][] visited = new boolean[2][201][201];
        int[][][] step = new int[2][201][201];
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(x1, y1, 0));
        q.add(new Pair(x1, y1, 1));
        visited[0][x1][y1] = true;
        visited[1][x1][y1] = true;
 
        while(!q.isEmpty()){
            Pair curr = q.poll();
 
            // 가로 이동
            if(curr.dir == 1){
                for(int i=0; i<2; i++){
                    int nx = curr.x + dx1[i], ny = curr.y + dy1[i];
                    if(0>nx || nx >= 201 || 0 > ny || ny >= 201)
                        continue;
                    if(!visited[1][nx][ny]){
                        visited[1][nx][ny] = true;
                        step[1][nx][ny] = step[0][curr.x][curr.y] + 1;
                        q.add(new Pair(nx,ny,0));
                    }
                }
            }
            // 세로 이동
            else{
                for(int i=0; i<2; i++){
                    int nx = curr.x + dx2[i], ny = curr.y + dy2[i];
                    if(0>nx || nx >= 201 || 0 > ny || ny >= 201)
                        continue;
                    if(!visited[0][nx][ny]){
                        visited[0][nx][ny] = true;
                        step[0][nx][ny] = step[1][curr.x][curr.y] + 1;
                        q.add(new Pair(nx,ny,1));
                    }
                }
            }
 
        }
        return Math.min(step[0][x2][y2], step[1][x2][y2]);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            int x1 = Integer.parseInt(st.nextToken()) + 100;
            int y1 = Integer.parseInt(st.nextToken()) + 100;
 
            int x2 = Integer.parseInt(st.nextToken()) + 100;
            int y2 = Integer.parseInt(st.nextToken()) + 100;
 
            System.out.println("#" + t + " " + bfs(x1, y1, x2, y2));
        }
    }
}