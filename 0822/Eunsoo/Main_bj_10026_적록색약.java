import java.util.*;
import java.io.*;

public class Main_bj_10026_적록색약 {
	static int N;
	static char road[][];
	static int normal, abnormal;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		road = new char[N][N];
		normal = 0;
		abnormal = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				road[i][j] = input.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (road[i][j] == 'G' || road[i][j] == 'R' || road[i][j] == 'B') {
					normal++;
					normalDFS(i, j, road[i][j]);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (road[i][j] == 'A' || road[i][j] == 'C') {
					abnormal++;
					abnormalDFS(i, j, road[i][j]);
				}

			}
		}
		System.out.println(normal + " " + abnormal);
	}

	private static void printRoad() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(road[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void abnormalDFS(int x, int y, char let) {
		road[x][y] = 'D';
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!check(nx, ny))
				continue;
			if (road[nx][ny] == let) {
				abnormalDFS(nx, ny, let);
			}
		}
	}

	private static void normalDFS(int x, int y, char let) {
		if (road[x][y] == 'R' || road[x][y] == 'G')
			road[x][y] = 'A';
		else if (road[x][y] == 'B')
			road[x][y] = 'C';
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!check(nx, ny))
				continue;
			if (road[nx][ny] == let) {
				normalDFS(nx, ny, let);
			}
		}
	}

	private static boolean check(int x, int y) {
		// TODO Auto-generated method stub
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
