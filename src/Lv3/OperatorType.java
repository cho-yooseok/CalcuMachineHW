package Lv3;

import java.util.function.BiFunction;

// 연산자 타입을 관리하는 Enum
public enum OperatorType {
    ADD("+", (a, b) -> a + b), // 덧셈
    SUBTRACT("-", (a, b) -> a - b), // 뺄셈
    MULTIPLY("*", (a, b) -> a * b), // 곱셈
    DIVIDE("/", (a, b) -> {
        if (b == 0) throw new ArithmeticException("0으로 나눌 수 없습니다."); // 0으로 나누기 방지
        return a / b;
    }); // 나눗셈

    private final String symbol; // 연산자 기호
    private final BiFunction<Double, Double, Double> operation; // 연산 로직

    // 생성자
    OperatorType(String symbol, BiFunction<Double, Double, Double> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    // 연산자 기호로부터 Enum 값을 찾는 메서드
    public static OperatorType fromSymbol(String symbol) {
        for (OperatorType type : OperatorType.values()) {
            if (type.symbol.equals(symbol)) {
                return type;
            }
        }
        throw new IllegalArgumentException("올바른 연산 기호 입력부탁드립니다."); // 잘못된 기호 입력 시 오류
    }

    // 실제 계산을 수행하는 메서드
    public double calculate(double a, double b) {
        return operation.apply(a, b);
    }
}