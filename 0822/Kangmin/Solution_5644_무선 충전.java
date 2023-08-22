import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
// 배터리 정보를 저장하기 위한 클래스
class Battery{
    int x;
    int y;
    int len;    // 배터리 영역
    int power;  // 배터리 효율
 
    Battery(int x, int y, int len, int power){
        this.x = x;
        this.y = y;
        this.len = len;
        this.power = power;
    }
}
public class Solution_5644_무선 충전{
    static int K, bNum, time;
    static int[][] grid;
    static int[] moveA, moveB;  // 이동 경로
    static ArrayList<Battery> list; // 배터리 리스트
    static int dx[] = {0,-1,0,1,0}, dy[] = {0,0,1,0,-1};    // 방향
    // 해당 좌표에서 최대 배터리 효율 탐색
    public static int getMax(int x1, int y1, int x2, int y2){   //A의 좌표, B의 좌표
        // 최고 배터리 효율을 찾기 위해 우선 순위 큐 사용
        PriorityQueue<Integer> eletronic = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<int[]> A_cal = new ArrayList<>(); // 해당 좌표에서 A가 가질 수 있는 배터리 효율 저장
        ArrayList<int[]> B_cal = new ArrayList<>(); // 해당 좌표에서 B가 가질 수 있는 배터리 효율 저장
        // 배터리 영역인지 확인 -> 현재 저장된 배터리 전부 탐색
        for(int i=0; i<list.size(); i++){
            Battery curr = list.get(i); // 현재 배터리
            int bx1 = curr.x, by1 = curr.y; // 배터리의 좌표
 
            // A와 배터리 사이 거리 구하기
            int dis1 = Math.abs(bx1 - x1) + Math.abs(by1 - y1);
            // B와 배터리 사이 거리 구하기
            int dis2 = Math.abs(bx1 - x2) + Math.abs(by1 - y2);
 
            //A와의 거리가 배터리 영역이면 포함
            if(dis1 <= curr.len){
                A_cal.add(new int[]{i,curr.power});
            }
            //B와의 거리가 배터리 영역이면 포함
            if(dis2 <= curr.len){
                B_cal.add(new int[]{i,curr.power});
            }
        }
        // 만약 현재 위치의 A만 배터리 영역에 해당하면 그 영역에 해당하는 배터리 중에 최고 효율을 찾아서 반환
        // ex T가 7인 경우 A만 BC3에 해당, B는 어떠한 영역에 해당하지 않음 + 다른 케이스에서는 영역에 해당하는 경우가 여러개가 생길 수 있음
        if(!A_cal.isEmpty() && B_cal.size() == 0){
            Collections.sort(A_cal, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[1] - o1[1];
                }
            });
            return A_cal.get(0)[1];
        }
        // 만약 현재 위치의 B만 배터리 영역에 해당하면 그 영역에 해당하는 배터리 중에 최고 효율을 찾아서 반환
        // ex T가 3인 경우 B만 BC2에 해당, A는 어떠한 영역에 해당하지 않음 + 다른 케이스에서는 영역에 해당하는 경우가 여러개가 생길 수 있음
        if(A_cal.size() == 0 && !B_cal.isEmpty()){
            Collections.sort(B_cal, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[1] - o1[1];
                }
            });
            return B_cal.get(0)[1];
        }
        // A,B로 만들수 있는 모든 경우를 찾아 우선순위 큐에 넣고 최대값 뽑기
        for(int i=0; i<A_cal.size(); i++){
            for(int j=0; j<B_cal.size(); j++){
                int[] A = A_cal.get(i);
                int[] B = B_cal.get(j);
                // 같은 영역 공유
                if(A[0] == B[0]){   // 0번 인덱스는 배터리 번호, 1번 인덱스는 해당 번호의 배터리 효율
                    eletronic.add(A[1]);    // T11인 경우 A,B가 BC1을 같이 선택한다고 가정하면 둘이 합쳐서 1개의 효율이 됨(100의 효율)
                }else{
                    eletronic.add(A[1]+B[1]);   // T11인 경우 A가 BC3을 선택하고 B가 BC1을 선택하면 100+70의 효율을 낼 수 있음
                }
            }
        }
        // A,B 둘다 어떠한 배터리 영역에 해당하지 않는다면 0을 반환
        if(eletronic.isEmpty())
            return 0;
        else
            // 최고의 효율을 뽑음
            return eletronic.poll();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            bNum = Integer.parseInt(st.nextToken());
            grid = new int[10][10];
 
            int x1 = 0, y1 = 0; //A의 시작위치
            int x2 = 9, y2 = 9; //B의 시작위치
 
            moveA = new int[K+1];   // A의 이동경로
            moveB = new int[K+1];   // B의 이동경로
             
            list = new ArrayList<>();   // 배터리를 저장하기 위한 리스트
 
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++){
                moveA[i] = Integer.parseInt(st.nextToken());
            }
 
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++){
                moveB[i] = Integer.parseInt(st.nextToken());
            }
 
            // 충전 범위
            for(int i=0; i<bNum; i++){
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;   // 문제에서 바꿔서 줌
                int x = Integer.parseInt(st.nextToken()) - 1;   // 문제에서 바꿔서 줌
                int len = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
 
                list.add(new Battery(x,y,len,power));
            }
            // 총 효율
            int sum = 0;
            // 시간이 0일때부터 탐색 시작  -> 처음에 경로 배열 0번 인덱스를 비워났기 때문에 움직이지 않음
            for(time=0; time<=K; time++) {
                x1 += dx[moveA[time]];
                y1 += dy[moveA[time]];
                x2 += dx[moveB[time]];
                y2 += dy[moveB[time]];
                sum += getMax(x1,y1,x2,y2);
            }
 
            System.out.println("#" + t + " " + sum);
        }
 
    }
}