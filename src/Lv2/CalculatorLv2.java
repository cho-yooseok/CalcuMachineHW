package Lv2;

import java.util.ArrayList;
import java.util.List;

class CalculatorLv2 {
    private List<String> results; // 연산 결과를 저장하는 리스트

    public CalculatorLv2() {
        this.results = new ArrayList<>(); // 리스트 초기화
    }

    // 연산 수행 메서드
    public void calculate(int num1, int num2, char operator) {
        String result;
        switch (operator) {
            case '+':
                result = "결과: " + (num1 + num2); // 덧셈 연산
                break;
            case '-':
                result = "결과: " + (num1 - num2); // 뺄셈 연산
                break;
            case '*':
                result = "결과: " + (num1 * num2); // 곱셈 연산
                break;
            case '/':
                if (num2 == 0) { // 0으로 나누는 경우 예외 처리
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }  //ArithmeticException이란 나누기 0을 하면 발생하는 에러
                int quotient = num1 / num2; // 몫 계산
                int remainder = num1 % num2; // 나머지 계산
                result = "결과(몫): " + quotient + "\n결과(나머지): " + remainder;
                break;
            default:
                throw new IllegalArgumentException("올바른 연산 기호 입력부탁드립니다."); // 잘못된 연산자 입력 시 예외 처리
        }
        results.add(result); // 연산 결과 리스트에 추가
        System.out.println(result); // 결과 출력
    }

    // 결과 목록 반환 메서드
    public List<String> getResults() {
        return new ArrayList<>(results); // 결과 리스트 복사본 반환 (원본 보호)
    }

    // 첫 번째 저장된 결과 삭제 메서드
    public void removeFirstResult() {
        if (!results.isEmpty()) {
            results.remove(0); // 첫 번째 요소 삭제
        } else {
            System.out.println("저장된 연산 결과가 없습니다."); // 삭제할 결과가 없는 경우 메시지 출력
        }
    }

    // 모든 연산 결과 삭제 메서드
    public void clearResults() {
        results.clear(); // 리스트 초기화
        System.out.println("모든 연산 결과가 삭제되었습니다."); // 삭제 완료 메시지 출력
    }
}
