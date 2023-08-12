import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SW_1233_사칙연산 유효성 검사{
    // 숫자인지 판별하는 메서드
    public static boolean isDigit(String value){
        return !value.equals("+") && !value.equals("-") && !value.equals("/") && !value.equals("*");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 10번의 테스트 케이스
        for(int t=1; t<=10; t++){
            // 정점의 갯수
            int N = Integer.parseInt(br.readLine());
            String[] tree = new String[N+1];    // 트리
            // 트리 만들기(배열)
            for(int i=1; i<=N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken()); // 정점 번호
                // 숫자 or 연산자, 자식 노드가 둘다 주어진 경우
                if(st.countTokens() == 3){
                    tree[idx] = st.nextToken();
                }
                // 숫자 or 연산자, 자식 노드가 하나만 주어진 경우
                else if(st.countTokens() == 2){
                    tree[idx] = st.nextToken();
                }
                // 숫자 or 연산자 만 주어지고 자식 노드가 주어지지 않은 경우
                else if(st.countTokens() == 1){
                    tree[idx] = st.nextToken();
                }
            }

            //1. 노드의 갯수 -> 홀수만 가능
            //-> 루트 노드가 1부터 시작했기 때문에 짝수 인 경우 피연산자가 없어 연산이 불가능함
            if(N % 2 == 0) {
                System.out.println("#" + t + " " + 0);
                continue;
            }
            //2. 리프 노드만 숫자이면 된다.
            //-> 0으로 나누는 경우는 고려하지 않는다(문제에서 주어짐)
            //-> 리프 노드가 연산자인 경우 계산이 불가, 연산자가 2개 이상이기 때문
            //-> 부모 노드가 피연산자인 경우도 계산이 불가, 피연산자로 연산이 불가

            boolean flag = true;
            int root = 1;
            Queue<Integer> q = new LinkedList<>();
            q.add(root);
            // BFS 탐색
            while(!q.isEmpty()){
                // 현재 노드
                int curr = q.poll();
                // 리프노드 인지 판별
                if(curr * 2 >= N && curr * 2 + 1 >= N){
                    if(!isDigit(tree[curr])) {
                        flag = false;
                        break;
                    }
                }
                // 리프노드가 아니면 연산자인지 판별한 다음 자식노드로 이동
                else{
                    if(!isDigit(tree[curr])){
                        q.add(curr * 2);
                        q.add(curr * 2 + 1);
                    }
                    else{
                        flag = false;
                        break;
                    }
                }
            }
            if(flag)
                System.out.println("#" + t + " " + 1);
            else
                System.out.println("#" + t + " " + 0);
        }

    }
}