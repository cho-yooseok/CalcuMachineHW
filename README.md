# 자바 콘솔 계산기 프로젝트

## 소개
이 프로젝트는 내일배움캠프 과제로 진행한 [spring 6기] CH2 계산기 과제입니다.

1. **Lv1**: 클래스 없이 기본적인 연산 수행하는 계산기 - 사칙연산을 수행하는 간단한 계산기.
2. **Lv2**: 클래스를 적용해 기본적인 연산을 수행할 수 있는 계산기 - 예외 처리 및 연산 결과 저장 기능 추가.
3. **Lv3**: Enum, 제네릭, 람다 & 스트림을 이해한 계산기 - 제네릭을 활용한 다양한 숫자 타입 지원 및 결과 관리 기능 강화.

---

## 주요 기능
### Lv1 - 기본 계산기
- 더하기(+), 빼기(-), 곱하기(*), 나누기(/) 연산 지원
- 잘못된 입력 예외 처리
- 'exit' 입력 시 프로그램 종료
  - **예외 처리**:
    - 숫자가 아닌 값 입력 시 `NumberFormatException` 처리 → "올바른 숫자 입력 부탁드립니다." 출력
    - 0으로 나누기 방지 → "오류: 나눗셈에서 분모(두 번째 숫자)에 0이 입력될 수 없습니다." 출력

### Lv2 - 고급 계산기
- 잘못된 입력과 0으로 나누는 경우 예외 처리
- 연산 결과를 저장하고 관리하는 기능 추가
- 연산 결과 삭제(첫 번째 또는 전체 삭제) 기능 추가
  - **예외 처리**:
    - 연산자가 올바르지 않은 경우 `IllegalArgumentException` 처리 → "올바른 연산기호 입력 부탁드립니다." 출력
    - 입력값이 숫자가 아닐 경우 `InputMismatchException` 방지 → "올바른 숫자 입력 부탁드립니다." 출력

### Lv3 - 고급 확장형 계산기
- 제네릭을 활용해 정수와 실수를 모두 지원
- 람다 함수(`BiFunction`)를 이용한 연산 구현
  - **예외 처리**:
    - 연산 기호가 잘못 입력된 경우 `IllegalArgumentException` 발생 → "올바른 연산 기호 입력 부탁드립니다." 출력
    - 0으로 나누는 경우 `ArithmeticException` 방지 → "0으로 나눌 수 없습니다. 다른 숫자로 나누어주세요." 출력

---

## 프로젝트 구조
```
프로젝트-폴더
├── Lv1/
│   ├── AppLv1.java  # 기본 계산기
│
├── Lv2/
│   ├── AppLv2.java        # 연산 결과 저장 기능 포함
│   ├── CalculatorLv2.java # 계산 및 결과 관리
│
├── Lv3/
│   ├── AppLv3.java           # 제네릭 기반 고급 계산기
│   ├── ArithmeticCalculator.java  # 제네릭 계산 클래스
│   ├── OperatorType.java     # 연산자 Enum 관리
```

---

## 트러블 슈팅
### 1. 숫자 입력 시 오류 발생
- **문제 상황**: 사용자가 숫자가 아닌 값을 입력하면 `NumberFormatException` 발생.
- **해결 방법**: `try-catch` 블록을 사용하여 예외 발생 시 사용자에게 재입력 요청.

```java
import java.util.Scanner;

public class ExceptionExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("숫자를 입력하세요: ");
            String input = scanner.nextLine();
            int number = Integer.parseInt(input);
            System.out.println("입력한 숫자: " + number);
        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자만 입력하세요!");
        }
    }
}
```

### 2. 잘못된 연산자 입력 시 오류 발생
- **문제 상황**: 사칙연산 기호(`+`, `-`, `*`, `/`)가 아닌 다른 기호(`&`, `#`, `@` 등`) 입력 시 `IllegalArgumentException` 발생.
- **해결 방법**: 입력값을 사전에 검증하여 예외 발생 방지.

```java
import java.util.Scanner;

enum Operator {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/');
    
    private char symbol;
    Operator(char symbol) { this.symbol = symbol; }
    
    public static Operator fromSymbol(char symbol) {
        for (Operator op : Operator.values()) {
            if (op.symbol == symbol) {
                return op;
            }
        }
        throw new IllegalArgumentException("오류: 허용되지 않은 연산자입니다!");
    }
}

public class OperatorExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
        char input = scanner.next().charAt(0);
        
        try {
            Operator operator = Operator.fromSymbol(input);
            System.out.println("선택한 연산자: " + operator);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

### 3. 0으로 나눌 때 오류 발생
- **문제 상황**: 나눗셈 연산에서 두 번째 숫자로 0을 입력하면 `ArithmeticException` 발생.
- **해결 방법**: 0 입력 방지 및 `try-catch` 활용.

```java
if (num2 == 0) {
    System.out.println("오류: 0으로 나눌 수 없습니다. 다른 숫자를 입력하세요.");
} else {
    double result = num1 / num2;
    System.out.println("결과: " + result);
}
```

### 4. 연산 결과 삭제 시 오류 발생
- **문제 상황**: 연산 결과 리스트가 비어 있을 때 `remove(0)`을 호출하면 `IndexOutOfBoundsException` 발생.
- **해결 방법**: 리스트가 비어 있는 경우 삭제 시도 방지.

```java
if (!results.isEmpty()) {
    results.remove(0);
    System.out.println("첫 번째 결과 삭제 완료!");
} else {
    System.out.println("오류: 저장된 연산 결과가 없습니다!");
}
```

---

## 향후 개선 사항
- **GUI 추가**: 그래픽 사용자 인터페이스(GUI)로 더 직관적인 계산기 제공.
- **고급 수학 기능 지원**: 제곱근, 지수, 로그 연산 추가.
- **결과 저장 기능 강화**: 연산 내역을 파일로 저장 및 불러오기 지원.

---

## 프로젝트 깃허브 주소
[GitHub Repository](https://github.com/cho-yooseok/CalcuMachineHW)

## 소감
이번 과제는 처음 해보는 자바 프로젝트라서 많이 어려웠습니다. 단순히 코드를 작성하는 것이 아니라, 오류를 찾고 해결하는 과정이 복잡하다는 걸 깨달았습니다. 앞으로 더 열심히 공부해서 좋은 프로그램을 만들고 싶습니다.

