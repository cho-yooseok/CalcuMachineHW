package Lv1;

import java.util.Scanner; // 사용자 입력을 받기 위한 Scanner를 사용하기 위해 import

public class AppLv1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체 생성

        while (true) { // 무한 반복문: 사용자가 'exit'을 입력하기 전까지 반복

            // 첫 번째 숫자 입력
            System.out.print("첫 번째 숫자를 입력하세요 (또는 'exit'을 입력하여 종료): ");
            String input1 = sc.next(); // 사용자 입력을 문자열로 받음

            // 사용자가 'exit'을 입력하면 프로그램 종료
            if (input1.equals("exit")) {
                System.out.println("계산기를 종료합니다.");
                break;
            }

            int num1;
            try {
                // 문자열을 정수로 변환 (예외 처리 추가)
                num1 = Integer.parseInt(input1);
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자 입력 부탁드립니다.");
                continue; // 다음 반복으로 이동
            }

            // 연산 기호 입력
            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            char operator = sc.next().charAt(0); // 첫 번째 문자만 가져오기

            // 연산 기호 유효성 검사
            if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
                System.out.println("올바른 연산기호 입력부탁드립니다.");
                continue; // 다음 반복으로 이동
            }

            int result = 0; // 결과값을 저장할 변수
            int remainder = 0; // 나머지를 저장할 변수

            // 두 번째 숫자 입력
            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2;
            try {
                num2 = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("올바른 숫자 입력 부탁드립니다.");
                sc.next(); // 잘못된 입력을 버림
                continue; // 다음 반복으로 이동
            }

            // 연산 수행
            switch (operator) {
                case '+':
                    result = num1 + num2; // 덧셈
                    break;
                case '-':
                    result = num1 - num2; // 뺄셈
                    break;
                case '*':
                    result = num1 * num2; // 곱셈
                    break;
                case '/':
                    if (num2 == 0) { // 나눗셈에서 0으로 나누는 경우 방지
                        System.out.println("오류: 나눗셈에서 분모(두 번째 숫자)에 0이 입력될 수 없습니다.");
                        continue; // 다음 반복으로 이동
                    }
                    result = num1 / num2; // 나눗셈 (몫)
                    remainder = num1 % num2; // 나머지
                    break;
            }

            // 결과 출력
            if (operator == '/') {
                System.out.println("결과(몫): " + result);
                System.out.println("결과 (나머지): " + remainder);
            } else {
                System.out.println("결과: " + result);
            }

            System.out.println(); // 줄바꿈
        }

        sc.close(); // Scanner 닫기 (메모리 누수 방지)
    }
}

