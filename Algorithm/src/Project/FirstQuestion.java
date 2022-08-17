package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 1. 배열 탐색
 * 배열을 다음 절차대로 탐색하여 그 결과를 예시처럼 출력하는 프로그램
 * - 전체조건 : 가로열과 세로열의 값은 동일하다.
 */
public class FirstQuestion {

	public static void main(String[] args) {
		FirstQuestion fq = new FirstQuestion();
		Scanner sc = null;
		
		try {
			sc = new Scanner(System.in);
			
			System.out.println("---------- 배열 탐색 프로그램 시작 ----------\n");
			
			int rowCnt  = 0;	// 가로열
			int colCnt  = 0;	// 세로열
			String data = "";	// 배열 데이터
			
			System.out.print("가로열 >> ");
			rowCnt = sc.nextInt();

			System.out.print("세로열 >> ");
			colCnt = sc.nextInt();
			
			System.out.print("배열 데이터 >> ");
			data = sc.next();
			
			// 1. 유효성 검사
			// 1-1. 가로열, 세로열 동일 여부 체크
			if (rowCnt != colCnt) {
				System.out.println("입력받은 가로열과 세로열의 값이 일치하지 않습니다.");
				// 프로그램 종료
				fq.exitProgram();
			}
			
			// 1-2. 가로열과 세로열의 곱이 데이터의 갯수와의 일치여부 체크
			String[] arrIptString = data.split(","); // ex) {1,2,3,4,5,6,7,8,9}
			if ((rowCnt * colCnt) != arrIptString.length) {
				System.out.println("입력받은 가로열과 세로열의 갯수가 데이터의 갯수와 일치하지 않습니다.");
				// 프로그램 종료
				fq.exitProgram();
			}
			
			System.out.println(">>>>> 입력받은 문자열 2차월 배열로 변환 <<<<<");

			// 2. 입력받은 문자열을 2차원배열로 변환
			int[][] intArray = new int[rowCnt][colCnt];
			int cnt = 0;
			for (int i = 0; i < rowCnt; i++) {
				for (int j = 0; j < colCnt; j++) {
					intArray[i][j] = Integer.parseInt(arrIptString[cnt]);
					cnt++;
				}
			}
			
			// 2차원 배열 출력
			fq.printRotatArry(intArray);
			
			// 3. 2차원 배열을 대각선 지그재그로 읽어 출력하기
			List<Integer> rstArray = fq.zigzagArray(rowCnt, colCnt, intArray);
			
			System.out.println(">>>>> 결과출력 <<<<<");
			// 콤마로 연결된 문자열로 출력하기 위해, ArrayList를 Array로 변환
			Integer[] array = rstArray.toArray(new Integer[rstArray.size()]);
			System.out.println(Arrays.toString(array));
			
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다: " + e);
			
		} finally {
			// 리소스 해제
			if (sc != null) {
				sc.close();
			}
			// 프로그램 종료
			fq.exitProgram();
		}
	}
	
	/**
	 * 2차원배열 지그재그
	 * @param rowCnt
	 * @param colCnt
	 * @param intArray
	 * @return List<Integer>
	 */
	public List<Integer> zigzagArray(int rowCnt, int colCnt, int[][] intArray) {
		// 현재 row, col index
		int currRow = 0;
		int currCol = 0;
		
		List<Integer> resultArry = new ArrayList<>();	// 결과 1차원 배열
		
		// 배열 0번째 초기화
		resultArry.add(intArray[currRow][currCol]);
		
		while (true) {
			if (currCol + 1 < rowCnt) {
				currCol++;
			} else {	// 최대 col일 경우, row 증가
				currRow++;
			}
			resultArry.add(intArray[currRow][currCol]);
			
			// 2차원 배열의 좌하향으로 내려가면서 값 저장
			while (currCol - 1 > -1 && currRow + 1 < rowCnt) {
				resultArry.add(intArray[++currRow][--currCol]);
			}
			
			if (currRow + 1 < rowCnt) {
				currRow++;
			} else {
				currCol++;	// 최대 row일 경우, col 증가
			}
			resultArry.add(intArray[currRow][currCol]);
			
			// 2차원 배열의 우상향으로 올라가면서 값 저장
			while (currRow - 1 > -1 && currCol + 1 < rowCnt) {
				resultArry.add(intArray[--currRow][++currCol]);
			}
			
			// row, col index가 최대값일 경우 종료
			if (currRow == (rowCnt - 1) && currCol == (colCnt - 1)) {
				break;
			}
		} // end while
		
		return resultArry;
	}
	
	/**
	 * 2차원 배열 출력
	 * @param outputArray
	 */
	public void printRotatArry(int[][] outputArray) {
		for (int i = 0; i < outputArray.length; i++) {
			System.out.print(Arrays.toString(outputArray[i]));
			System.out.println();
		}
	}
	
	/**
	 * 프로그램 종료
	 */
	public void exitProgram() {
		System.out.println("\n---------- 배열탐색 프로그램 종료 ----------");
		System.exit(0);
	}

}
