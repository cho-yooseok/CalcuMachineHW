package Lv2;

import java.util.Scanner;

public class AppLv2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CalculatorLv2 calculatorLv2 = new CalculatorLv2();

        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요 (또는 'exit'을 입력하여 종료): ");
            String input = sc.next();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("계산기를 종료합니다.");
                break;
            }

            int num1;
            try {
                num1 = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자를 입력하세요.");
                continue;
            }

            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            char operator = sc.next().charAt(0);

            if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
                System.out.println("올바른 연산 기호 입력부탁드립니다.");
                continue;
            }

            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2;
            try {
                num2 = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("올바른 숫자를 입력하세요.");
                sc.next();
                continue;
            }

            try {
                int result = calculatorLv2.calculate(num1, num2, operator);
                System.out.println("결과: " + result);
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
                continue;
            }

            System.out.print("연산 결과를 삭제하시겠습니까? (yes 입력 시 삭제, all 입력 시 전체 삭제, 아니면 계속): ");
            String deleteOption = sc.next();
            if (deleteOption.equalsIgnoreCase("yes")) {
                calculatorLv2.removeFirstResult();
                System.out.println("첫 번째 연산 결과가 삭제되었습니다.");
            } else if (deleteOption.equalsIgnoreCase("all")) {
                calculatorLv2.clearResults();
            }

            System.out.println();
        }
        sc.close();
    }
}
