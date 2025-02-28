package Lv3;

import java.util.Scanner;

// 메인 애플리케이션 (계산기 프로그램)
public class AppLv3 {
    public static void main(String[] args) {
        // 사용자 입력을 받기 위한 Scanner 객체 생성 (키보드 입력을 받을 준비)
        Scanner sc = new Scanner(System.in);

        // 계산기 객체 생성 (Double 타입을 사용하는 계산기)
        ArithmeticCalculator<Double> calculator = new ArithmeticCalculator<>();
        //Double 타입을 사용하는 계산기를 만듬(정수와 실수 모두 가능)



        // 무한 루프: 사용자가 'exit'을 입력할 때까지 계속 실행
        while (true) {
            // 첫 번째 숫자 입력 요청
            System.out.print("첫 번째 숫자를 입력하세요 (또는 'exit'을 입력하여 종료): ");
            String input = sc.next(); // 사용자 입력 받기 (예: "10" 또는 "exit")

            // 'exit' 입력 시 프로그램 종료
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("계산기를 종료합니다.");
                break; // 무한 루프 탈출
            }

            double num1; // 첫 번째 숫자를 저장할 변수
            try {
                // 입력받은 문자열을 숫자로 변환 (예: "10" → 10.0)
                num1 = Double.parseDouble(input);
                // Double.parseDouble(input) 으로 문자열을 숫자로 변환

            } catch (NumberFormatException e) {
                // 숫자로 변환할 수 없는 경우 (예: "abc" 입력)
                System.out.println("올바른 숫자를 입력하세요.");
                continue; // 다시 입력 받기
            }

            // 연산자 입력 요청
            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            String operatorSymbol = sc.next(); // 사용자 입력 받기 (예: "+")

            OperatorType operator; // 연산자 타입을 저장할 변수
            try {
                // 입력받은 기호로부터 연산자 타입 찾기 (예: "+" → ADD)
                operator = OperatorType.fromSymbol(operatorSymbol);
            } catch (IllegalArgumentException e) {
                // 잘못된 기호 입력 시 (예: "&" 입력)
                System.out.println(e.getMessage()); // 오류 메시지 출력
                continue; // 다시 입력 받기
            }

            // 두 번째 숫자 입력 요청
            System.out.print("두 번째 숫자를 입력하세요: ");
            double num2; // 두 번째 숫자를 저장할 변수
            try {
                // 입력받은 문자열을 숫자로 변환 (예: "5" → 5.0)
                num2 = Double.parseDouble(sc.next());
            } catch (NumberFormatException e) {
                // 숫자로 변환할 수 없는 경우 (예: "abc" 입력)
                System.out.println("올바른 숫자를 입력하세요.");
                continue; // 다시 입력 받기
            }

            try {
                // 계산기로 연산 수행 (예: 10.0 + 5.0)
                calculator.calculate(num1, num2, operator);
            } catch (ArithmeticException e) {
                // 0으로 나누는 경우 (예: 10.0 / 0.0)
                System.out.println("오류: " + e.getMessage()); // 오류 메시지 출력
                continue; // 다시 입력 받기
            }

            // 연산 결과 삭제 여부 확인
            System.out.print("연산 결과를 삭제하시겠습니까? (yes 입력 시 삭제, all 입력 시 전체 삭제, 아니면 계속): ");
            String deleteOption = sc.next(); // 사용자 입력 받기 (예: "yes")

            if (deleteOption.equalsIgnoreCase("yes")) {
                // 첫 번째 결과 삭제
                calculator.removeFirstResult();
                System.out.println("첫 번째 연산 결과가 삭제되었습니다.");
            } else if (deleteOption.equalsIgnoreCase("all")) {
                // 모든 결과 삭제
                calculator.clearResults();
            }

            // 현재 저장된 연산 결과 출력
            System.out.println("현재 저장된 연산 결과: ");
            calculator.getResults().forEach(System.out::println); // 결과 출력
            System.out.println(); // 한 줄 띄우기
        }

        // Scanner 객체 닫기 (프로그램 종료 전 리소스 정리)
        sc.close();
    }
}