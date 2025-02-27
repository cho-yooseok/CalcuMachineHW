package Lv3;

import java.util.Scanner;

// 메인 애플리케이션
public class AppLv3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner
        ArithmeticCalculator<Double> calculator = new ArithmeticCalculator<>(); // 계산기 객체 생성

        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요 (또는 'exit'을 입력하여 종료): ");
            String input = sc.next(); // 사용자 입력 받기

            if (input.equalsIgnoreCase("exit")) { // 'exit' 입력 시 종료
                System.out.println("계산기를 종료합니다.");
                break;
            }

            double num1;
            try {
                num1 = Double.parseDouble(input); // 문자열을 숫자로 변환
            } catch (NumberFormatException e) { // 변환 실패 시 오류 메시지 출력
                System.out.println("올바른 숫자를 입력하세요.");
                continue;
            }

            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            String operatorSymbol = sc.next(); // 연산자 입력 받기
            OperatorType operator;
            try {
                operator = OperatorType.fromSymbol(operatorSymbol); // 연산자 타입 찾기
            } catch (IllegalArgumentException e) { // 잘못된 연산자 입력 시 오류 메시지 출력
                System.out.println(e.getMessage());
                continue;
            }

            System.out.print("두 번째 숫자를 입력하세요: ");
            double num2;
            try {
                num2 = Double.parseDouble(sc.next()); // 두 번째 숫자 입력 받기
            } catch (NumberFormatException e) { // 변환 실패 시 오류 메시지 출력
                System.out.println("올바른 숫자를 입력하세요.");
                continue;
            }

            try {
                calculator.calculate(num1, num2, operator); // 계산 수행
            } catch (ArithmeticException e) { // 0으로 나누기 시 오류 메시지 출력
                System.out.println("오류: " + e.getMessage());
                continue;
            }

            // 연산 결과 삭제 여부 확인
            System.out.print("연산 결과를 삭제하시겠습니까? (yes 입력 시 삭제, all 입력 시 전체 삭제, 아니면 계속): ");
            String deleteOption = sc.next();
            if (deleteOption.equalsIgnoreCase("yes")) {
                calculator.removeFirstResult(); // 첫 번째 결과 삭제
                System.out.println("첫 번째 연산 결과가 삭제되었습니다.");
            } else if (deleteOption.equalsIgnoreCase("all")) {
                calculator.clearResults(); // 모든 결과 삭제
            }

            // 현재 저장된 연산 결과 출력
            System.out.println("현재 저장된 연산 결과: ");
            calculator.getResults().forEach(System.out::println); // 결과 출력
            System.out.println();
        }
        sc.close(); // Scanner 객체 닫기
    }
}