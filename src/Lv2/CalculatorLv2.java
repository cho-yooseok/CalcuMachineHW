package Lv2;

import java.util.ArrayList;
import java.util.List;

class CalculatorLv2 {
    private List<Integer> results; // 연산 결과를 저장하는 리스트

    public CalculatorLv2() {
        this.results = new ArrayList<>();
    }

    // 연산 수행 메서드
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
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("올바른 연산 기호 입력부탁드립니다.");
        }
        results.add(result);
        return result;
    }

    // 결과 목록 반환 메서드
    public List<Integer> getResults() {
        return new ArrayList<>(results);
    }

    // 첫 번째 저장된 결과 삭제 메서드
    public void removeFirstResult() {
        if (!results.isEmpty()) {
            results.remove(0);
        } else {
            System.out.println("저장된 연산 결과가 없습니다.");
        }
    }

    // 모든 연산 결과 삭제 메서드
    public void clearResults() {
        results.clear();
        System.out.println("모든 연산 결과가 삭제되었습니다.");
    }
}