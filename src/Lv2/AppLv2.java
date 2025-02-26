package Lv2;

import java.util.*; // 컬렉션(List) 사용을 위해 import

// Calculator 클래스: 연산 수행 & 결과 저장 역할
class Calculator {
    private List<Integer> results = new ArrayList<>(); // 연산 결과를 저장할 리스트

    // 사칙연산 수행 메서드
    public int calculate(int num1, int num2, char operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("오류: 0으로 나눌 수 없습니다.");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("오류: 잘못된 연산 기호입니다.");
        }
        results.add(result); // 결과 저장
        return result;
    }

    // 연산 결과 리스트 반환 (Getter)
    public List<Integer> getResults() {
        return new ArrayList<>(results); // 캡슐화를 위해 새 리스트 반환
    }

    // 가장 먼저 저장된 연산 결과 삭제
    public void removeResult() {
        if (!results.isEmpty()) {
            results.remove(0);
        } else {
            System.out.println("삭제할 연산 결과가 없습니다.");
        }
    }
}

// App 클래스: 사용자 입력을 받고, Calculator를 사용하여 연산 수행
public class AppLv2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator(); // Calculator 객체 생성

        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요 (또는 'exit'을 입력하여 종료): ");
            String input1 = sc.next();
            if (input1.equals("exit")) {
                System.out.println("계산기를 종료합니다.");
                break;
            }
            int num1 = Integer.parseInt(input1);

            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            char operator = sc.next().charAt(0);

            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2 = sc.nextInt();

            try {
                int result = calculator.calculate(num1, num2, operator);
                System.out.println("결과: " + result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.print("연산 결과를 삭제하시겠습니까? (yes 입력 시 삭제, 아니면 계속 진행): ");
            String deleteInput = sc.next();
            if (deleteInput.equals("yes")) {
                calculator.removeResult();
                System.out.println("가장 오래된 연산 결과가 삭제되었습니다.");
            }

            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료): ");
            if (sc.next().equals("exit")) {
                break;
            }
        }

        sc.close();
    }
}
