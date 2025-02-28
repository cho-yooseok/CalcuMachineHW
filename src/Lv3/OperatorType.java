package Lv3;

import java.util.function.BiFunction;

// 연산자 타입을 관리하는 Enum (덧셈, 뺄셈, 곱셈, 나눗셈)
public enum OperatorType {
    // 덧셈: "+" 기호와 덧셈 로직을 정의
    ADD("+", (a, b) -> a + b),

    // 뺄셈: "-" 기호와 뺄셈 로직을 정의
    SUBTRACT("-", (a, b) -> a - b),

    // 곱셈: "*" 기호와 곱셈 로직을 정의
    MULTIPLY("*", (a, b) -> a * b),

    // 나눗셈: "/" 기호와 나눗셈 로직을 정의
    DIVIDE("/", (a, b) -> {
        if (b == 0) throw new ArithmeticException("0으로 나눌 수 없습니다. 다른 숫자로 나누어주세요"); // 0으로 나누면 오류 발생
        return a / b;
    });

    // 연산자 기호를 저장하는 변수 (예: "+", "-", "*", "/")
    private final String symbol;

    // 연산 로직을 저장하는 변수 (람다 함수로 정의)
    private final BiFunction<Double, Double, Double> operation;
    //BiFunction은 두 개의 Double을 입력받아 하나의 Double을 반환


    // 생성자: 연산자 기호와 연산 로직을 초기화
    OperatorType(String symbol, BiFunction<Double, Double, Double> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    // 연산자 기호로부터 Enum 값을 찾는 메서드
    public static OperatorType fromSymbol(String symbol) {
        // 모든 연산자 타입을 순회하며 기호가 일치하는지 확인
        for (OperatorType type : OperatorType.values()) {
            if (type.symbol.equals(symbol)) {
                return type; // 일치하는 연산자 타입 반환
            }
        }
        // 일치하는 기호가 없으면 오류 발생
        throw new IllegalArgumentException("올바른 연산 기호 입력부탁드립니다. 올바른 연산 기호인지 확인 후 입력해주세요");
    }

    // 실제 계산을 수행하는 메서드
    public double calculate(double a, double b) {
        // 연산 로직을 사용하여 계산 결과 반환
        return operation.apply(a, b);
        //apply 는 함수형 인터페이스(Funtional Interface)에서 제공하는메서드로,
        // 람다 함수나 메서드 참조를 실행하는 역할을 합니다
    }
}