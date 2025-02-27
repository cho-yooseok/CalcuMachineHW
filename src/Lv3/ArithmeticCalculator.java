package Lv3;

import java.util.ArrayList;
import java.util.List;

// 제네릭을 사용한 계산기 클래스
public class ArithmeticCalculator<T extends Number> {
    private List<String> results = new ArrayList<>(); // 연산 결과 저장 리스트

    // 계산 메서드
    public void calculate(T num1, T num2, OperatorType operator) {
        double a = num1.doubleValue(); // 첫 번째 숫자
        double b = num2.doubleValue(); // 두 번째 숫자
        double result = operator.calculate(a, b); // 연산 수행
        String resultString = "결과: " + result; // 결과 문자열
        results.add(resultString); // 결과 저장
        System.out.println(resultString); // 결과 출력
    }

    // 저장된 결과 중 특정 값보다 큰 결과만 출력하는 메서드 (람다 & 스트림 사용)
    public void printResultsGreaterThan(double threshold) {
        results.stream()
                .filter(result -> {
                    double value = Double.parseDouble(result.split(": ")[1]); // 결과에서 숫자 추출
                    return value > threshold; // 조건에 맞는지 확인
                })
                .forEach(System.out::println); // 조건에 맞는 결과 출력
    }

    // 결과 목록 반환 메서드
    public List<String> getResults() {
        return new ArrayList<>(results); // 결과 리스트 복사본 반환
    }

    // 첫 번째 결과 삭제 메서드
    public void removeFirstResult() {
        if (!results.isEmpty()) {
            results.remove(0); // 첫 번째 결과 삭제
        } else {
            System.out.println("저장된 연산 결과가 없습니다."); // 결과가 없을 때 메시지 출력
        }
    }

    // 모든 결과 삭제 메서드
    public void clearResults() {
        results.clear(); // 리스트 초기화
        System.out.println("모든 연산 결과가 삭제되었습니다."); // 삭제 완료 메시지 출력
    }
}