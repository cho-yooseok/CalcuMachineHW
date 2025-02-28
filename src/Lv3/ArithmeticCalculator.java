package Lv3;

import java.util.ArrayList;
import java.util.List;

// 제네릭을 사용한 계산기 클래스 (정수, 실수 모두 가능)
public class ArithmeticCalculator<T extends Number> {
    // 연산 결과를 저장하는 리스트 (예: ["결과: 15.0", "결과: 10.0"])
    private List<String> results = new ArrayList<>();

    // 계산 메서드: 두 숫자와 연산자를 받아서 계산합니다.
    public void calculate(T num1, T num2, OperatorType operator) {
        // 첫 번째 숫자를 double 타입으로 변환 (예: 10 → 10.0)
        double a = num1.doubleValue();
        // 두 번째 숫자를 double 타입으로 변환 (예: 5 → 5.0)
        double b = num2.doubleValue();
        // 연산자로 계산 수행 (예: 10.0 + 5.0 = 15.0)
        double result = operator.calculate(a, b);
        // 결과를 문자열로 저장 (예: "결과: 15.0")
        String resultString = "결과: " + result;
        // 결과를 리스트에 추가
        results.add(resultString);
        // 결과를 화면에 출력
        System.out.println(resultString);
    }

    // 저장된 결과 중 특정 값보다 큰 결과만 출력하는 메서드 (람다 & 스트림 사용)
    public void printResultsGreaterThan(double threshold) {
        // results 리스트를 스트림으로 변환
        results.stream()
                // filter: 조건에 맞는 결과만 필터링
                .filter(result -> {
                    // 결과 문자열에서 숫자 부분 추출 (예: "결과: 15.0" → 15.0)
                    double value = Double.parseDouble(result.split(": ")[1]);
                    // 추출한 숫자가 threshold보다 큰지 확인
                    return value > threshold;
                })
                // forEach: 조건에 맞는 결과를 출력
                .forEach(System.out::println);
    }

    // 결과 목록 반환 메서드
    public List<String> getResults() {
        // results 리스트의 복사본을 반환 (원본 보호)
        return new ArrayList<>(results);
    }

    // 첫 번째 결과 삭제 메서드
    public void removeFirstResult() {
        // 리스트가 비어있지 않으면
        if (!results.isEmpty()) {
            // 첫 번째 결과 삭제
            results.remove(0);
        } else {
            // 리스트가 비어있으면 메시지 출력
            System.out.println("저장된 연산 결과가 없습니다.");
        }
    }

    // 모든 결과 삭제 메서드
    public void clearResults() {
        // 리스트 초기화 (모든 결과 삭제)
        results.clear();
        // 삭제 완료 메시지 출력
        System.out.println("모든 연산 결과가 삭제되었습니다.");
    }
}