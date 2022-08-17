package Project;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 3. 마지막 남는 자리 구하기
 * N명의 친구가 둘러 앉아 있다. 주어진 숫자만큼 이동해서 한 명씩 탈락하고 마지막으로 남는 사람을 출력하는 프로그램
 */
public class ThirdQuestion {

	public static void main(String[] args) {
		ThirdQuestion tq = new ThirdQuestion();
		Scanner sc = null;

		try {
			sc = new Scanner(System.in);
			
			System.out.println("---------- 마지막 남는 자리 구하기 프로그램 시작 ----------\n");

			int peopleCnt = 0;	// 사람 수
			int distance  = 0;	// 이동할 거리
			int result    = 0;	// 마지막 남은 사람

			System.out.print("사람 수 >> ");
			peopleCnt = sc.nextInt();

			System.out.print("이동할 거리 >> ");
			distance = sc.nextInt();

			System.out.print("결과 >> ");
			result = tq.getFinalPeople(peopleCnt, distance);
			System.out.println(result);

		} catch (Exception e) {
			System.out.println("오류가 발생했습니다: " + e);
			
		} finally {
			// 리소스 해제
			if (sc != null) {
				sc.close();
			}
			// 프로그램 종료
			tq.exitProgram();
		}
	}

	/**
	 * 마지막 남은 사람 구하기
	 * @param peopleCnt
	 * @param distance
	 * @return int
	 */
	public int getFinalPeople(int peopleCnt, int distance) {
		int finalPeople = 0;
		Queue<Integer> queue = new LinkedList<>();	// 데이터의 삭제가 빈번하게 일어나므로 LinkedList를 선언

		// 큐에 1부터 사람 수만큼 입력
		for (int i = 1; i <= peopleCnt; i++) {
			queue.offer(i);
		}

		// 큐에 한 사람만 남을 때까지 반복
		while (queue.size() > 1) {
			// 이동할 거리번째 수가 아니라면 다시 큐에 입력
			for (int i = 1; i < distance; i++) {
				queue.offer(queue.poll());
			}
			// 이동할 거리번째 수라면 제거
			queue.poll();
		}

		// 마지막으로 남은 사람 출력
		finalPeople = queue.poll();

		return finalPeople;
	}

	/**
	 * 프로그램 종료
	 */
	public void exitProgram() {
		System.out.println("\n---------- 마지막 남는 자리 구하기 프로그램 종료 ----------");
		System.exit(0);
	}
}
