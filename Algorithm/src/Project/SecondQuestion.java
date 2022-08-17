package Project;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 2. 배열 회전
 * 배열을 입력받아, 입력된 방향으로 회전하고 그에 따른 배열 값을 출력하는 프로그램(L:왼쪽회전, R:오른쪽회전, T:뒤집기)
 */
public class SecondQuestion {

	static int rowCnt       = 0;  // 가로열
	static int colCnt       = 0;  // 세로열
	static int[][] d2Array  = null;	// 회전결과배열
	static int[][] intArray = null;	// 최종결과배열
	
	public static void main(String[] args) {
		SecondQuestion sq = new SecondQuestion();
		Scanner sc = null;

		String data        = ""; // 배열 데이터
		String command     = ""; // 회전 명령어
		String outPosition = ""; // 출력할 배열위치

		try {
			sc = new Scanner(System.in);
			
			System.out.println("---------- 배열 회전 프로그램 시작 ----------\n");

			// 1. 배열회전 입력 값 받기
			System.out.print("가로열 >> ");
			rowCnt = sc.nextInt();

			System.out.print("새로열 >> ");
			colCnt = sc.nextInt();

			System.out.print("배열 데이터 >> ");
			data = sc.next();

			System.out.print("회전 명령어 >> ");
			command = sc.next();

			System.out.print("출력할 배열위치 >> ");
			outPosition = sc.next();

			// 2. 입력 값 유효성 검사
			// 2-1. 가로열*세로열 = 입력받은 데이터 갯수 일치여부 체크
			// 입력받은 데이터를 콤마를 기준으로 나누어 배열에 저장
			String[] arrIptString = data.split(","); // ex) {1,2,3,4,5,6,7,8,9}
			if ((rowCnt * colCnt) != arrIptString.length) {
				System.out.println("입력받은 가로열과 세로열의 갯수가 데이터의 갯수와 일치하지 않습니다.");
				// 프로그램 종료
				sq.exitProgram();
			}
			
			// 2-2. 회전명령어 유효성 검사
			// 입력받은 회전 명령어 한글자씩 잘라서 배열에 저장
			String[] arrCommand = command.split("");
			for (int i = 0; i < arrCommand.length; i++) {
				String strCommand = arrCommand[i].toUpperCase();
				
				// 입력받은 회전명령어가 L, R, T 모두 아닌 경우 
				if (!"L".equals(strCommand) && !"R".equals(strCommand) && !"T".equals(strCommand)) {
					System.out.println("회전명령어는 L,R,T 만 입력 가능합니다.");
					// 프로그램 종료
					sq.exitProgram();
				}
			}
			
			// 2-3. 출력할 배열위치 유효성 검사
			String[] arrPosition = outPosition.split(",");
			if (arrPosition.length != 2) {
				System.out.println("출력할 배열위치를 다시 입력해주세요.");
				// 프로그램 종료
				sq.exitProgram();
			}
			
			// 입력한 값이 행과 열의 범위를 벗어난 경우
			int col = Integer.parseInt(arrPosition[0]);
			int row = Integer.parseInt(arrPosition[1]);
			if (col > colCnt || row > rowCnt) {
				System.out.println("출력할 배열위치가 입력한 가로 또는 세로열보다 큽니다.");
				// 프로그램 종료
				sq.exitProgram();
			}
			
			System.out.println(">>>>> 입력받은 문자열 2차월 배열로 변환 <<<<<");

			// 3. 입력받은 문자열을 2차원배열로 변환
			intArray = new int[rowCnt][colCnt];
			int cnt = 0;
			for (int i = 0; i < rowCnt; i++) {
				for (int j = 0; j < colCnt; j++) {
					intArray[i][j] = Integer.parseInt(arrIptString[cnt]);
					cnt++;
				}
			}

			// 2차원 배열 출력
			sq.printRotatArry(intArray);

			// 4. 회전명령어에 따른 배열 회전
			for (int i = 0; i < arrCommand.length; i++) {
				String strCommand = arrCommand[i].toUpperCase().trim();
				
				// 왼쪽회전
				if ("L".equals(strCommand)) {
					sq.degree270Rotat();
					
					System.out.println(">>>>> 왼쪽회전 후, 결과배열 출력 <<<<< ");
					sq.printRotatArry(intArray);
				}
				// 오른쪽회전
				else if ("R".equals(strCommand)) {
					sq.degree90Rotat();
					
					System.out.println(">>>>> 오른쪽회전 후, 결과배열 출력 <<<<< ");
					sq.printRotatArry(intArray);
				}
				// 뒤집기
				else if ("T".equals(strCommand)) {
					sq.reverse();
					
					System.out.println(">>>>> 배열뒤집기 후, 결과배열 출력 <<<<< ");
					sq.printRotatArry(intArray);
				}
				else {
					System.out.println("잘못된 회전명령어입니다.[입력값:" + strCommand + "]");
					sq.exitProgram();
				}
			}

			System.out.println(">>>>> [" + command + "] 회전 완료 후, 2차원 배열 출력 <<<<<");
			for (int i = 0; i < d2Array.length; i++) {
				System.out.print(Arrays.toString(d2Array[i]));
				System.out.println();
			}

			System.out.println(">>>>> 입력한 위치의 값 출력 <<<<<");
			System.out.println("결과 >> " + d2Array[row-1][col-1]);
			
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다: " + e);

		} finally {
			// 리소스 해제
			if (sc != null) {
				sc.close();
			}
			// 프로그램 종료
			sq.exitProgram();
		}
	}

	/**
 	 * 오른쪽 회전(시계방향으로 90도 회전)  
	 */
	public void degree90Rotat() {
		d2Array = new int[colCnt][rowCnt];	// 회전결과배열(행과 열의 크기가 다를수도 있으므로)
		
		for (int i = 0; i < rowCnt; i++) {
			for (int j = 0; j < colCnt; j++) {
				d2Array[j][rowCnt - i - 1] = intArray[i][j];
			}
		}
		// 회전결과배열의 크기가 바뀔 수 있으므로 변경
		int tmp = rowCnt;
		rowCnt  = colCnt;
		colCnt  = tmp;
		
		intArray = d2Array;
	}

	/**
 	 * 왼쪽 회전(반시계방향으로 90도 회전)  
	 */
	public void degree270Rotat() {
		d2Array = new int[colCnt][rowCnt];	// 회전결과배열(행과 열의 크기가 다를수도 있으므로)
		
		for (int i = 0; i < rowCnt; i++) {
			for (int j = 0; j < colCnt; j++) {
				d2Array[colCnt - j - 1][i] = intArray[i][j];
			}
		}
		
		// 회전결과배열의 크기가 바뀔 수 있으므로 변경
		int tmp = rowCnt;
		rowCnt  = colCnt;
		colCnt  = tmp;
		
		intArray = d2Array;
	}

	/**
	 * 배열뒤집기(좌우반전)
	 */
	public void reverse() {
		d2Array = new int[rowCnt][colCnt];	// 회전결과배열(좌우반전이므로 행과 열의 크기가 바뀌지 않는다.)
		
		for (int i = 0; i < rowCnt; i++) {
			for (int j = 0; j < colCnt; j++) {
				d2Array[i][colCnt - j - 1] = intArray[i][j];
			}
		}
		
		intArray = d2Array;
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
		System.out.println("\n---------- 배열 회전 프로그램 종료 ----------");
		System.exit(0);
	}

}
