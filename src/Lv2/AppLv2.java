package Lv2;

import java.util.Scanner;

public class AppLv2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체 생성
        CalculatorLv2 calculatorLv2 = new CalculatorLv2(); // 계산기 객체 생성

        while (true) { // 무한 루프를 통해 계속 입력받음
            System.out.print("첫 번째 숫자를 입력하세요 (또는 'exit'을 입력하여 종료): ");
            String input = sc.next(); // 사용자 입력 받기
            // String input = sc.next();     사용자로부터 입력받은 문자열을 변수 input 에 저장

            if (input.equalsIgnoreCase("exit")) { // 사용자가 'exit'을 입력하면 프로그램 종료
                System.out.println("계산기를 종료합니다.");
                break;
            }

            int num1;
            try {
                num1 = Integer.parseInt(input); // 문자열을 정수로 변환
            } catch (NumberFormatException e) { // 변환 실패 시 오류 메시지 출력 후 다시 입력받기
                System.out.println("올바른 숫자를 입력하세요.");
                continue;
            }

            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            char operator = sc.next().charAt(0); // 연산자 입력 받기
            //char operator = sc.next().charAt(0);  은
            // 자바에서 Scanner로 입력받은 문자열의 첫 번째 문자를 char 타입으로 변수에 저장하는 코드
            //sc 는 Scanner의 객체를 의미


            // 유효한 연산자인지 검사
            if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
                System.out.println("올바른 연산 기호 입력부탁드립니다.");
                continue;
            }

            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2;     //num2 라는 이름의 정수형 변수 선언
                          //이 변수는 사용자로부터 입력받은 두 번째 숫자를 저장
            try {                //try 블록은 예외(오류)가 발생할 수 있는 코드를 감싸는 역할
                num2 = sc.nextInt(); // 두 번째 숫자 입력 받기
            } catch (java.util.InputMismatchException e) { // 숫자가 아닐 경우 예외 처리
                System.out.println("올바른 숫자를 입력하세요.");
                sc.next(); // 입력 버퍼 비우기
                //사용자가 잘못된 입력(예: abc)을 했을 때, 입력 버퍼에 남아있는 잘못된 값을 제거하기 위해 사용됨
                //이 코드가 없으면, 잘못된 입력이 계속 남아 있어 무한 루프에 빠질 수 있음
                continue;
            }

            try {
                calculatorLv2.calculate(num1, num2, operator); // 계산 수행
            } catch (Exception e) { // 예외 발생 시 오류 메시지 출력
                System.out.println("오류: " + e.getMessage());
                // e.getMessage()는 자바에서 예외가 발생했을때,
                //발생한 예외 클래스의 인스턴스에 저장된 메시지를 얻는 코드
                //여기서는 throw new ArithmeticException("0으로 나눌 수 없습니다.");
                continue;
            }

            // 연산 결과 삭제 여부 확인
            System.out.print("연산 결과를 삭제하시겠습니까? (yes 입력 시 삭제, all 입력 시 전체 삭제, 아니면 계속): ");
            String deleteOption = sc.next();
            if (deleteOption.equalsIgnoreCase("yes")) {
                calculatorLv2.removeFirstResult(); // 첫 번째 결과 삭제
                System.out.println("첫 번째 연산 결과가 삭제되었습니다.");
            } else if (deleteOption.equalsIgnoreCase("all")) {
                calculatorLv2.clearResults(); // 모든 결과 삭제
            }

            // 현재 저장된 연산 결과 출력
            System.out.println("현재 저장된 연산 결과: ");
            for (String result : calculatorLv2.getResults()) {
                System.out.println(result);
            }
            System.out.println();
        }
        sc.close(); // Scanner 객체 닫기
    }
}
