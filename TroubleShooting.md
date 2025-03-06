# 계산기 프로젝트 트러블 슈팅 (수정완료)

## 계산기 프로젝트 깃 허브 주소
[GitHub Repository](https://github.com/cho-yooseok/CalcuMachineHW)

## 소감
이번 과제는 처음 해보는 자바 프로젝트라서 많이 어려웠습니다. 단순히 코드를 작성하는 것이 아니라, 오류를 찾고 해결하는 과정이 생각보다 복잡하다는 걸 깨달았습니다. 특히, 프로그램이 멈추지 않도록 예외를 처리하는 방법이 중요하다는 걸 배웠습니다. 아직 배워야 할 것이 많지만, 더 열심히 공부해서 좋은 프로그램을 만들고 싶습니다.

## 1. 숫자 입력 시 오류 발생
### 문제 상황
사용자가 숫자가 아닌 값을 입력하면 `NumberFormatException`이 발생하여 프로그램이 중단될 가능성이 있음.

### 원인
`Double.parseDouble()` 또는 `Integer.parseInt()`를 사용하여 숫자로 변환할 때, 숫자가 아닌 값이 입력되면 예외 발생.

### 해결 방법
`try-catch` 블록을 사용하여 예외 발생 시 사용자에게 재입력을 요청.

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

## 2. 잘못된 연산자 입력 시 오류 발생
### 문제 상황
사칙연산 기호(`+`, `-`, `*`, `/`)가 아닌 다른 기호(`&`, `#`, `@` 등)를 입력하면 `IllegalArgumentException`이 발생.

### 원인
`OperatorType.fromSymbol()` 메서드에서 허용되지 않은 연산자를 입력하면 예외가 발생하여 프로그램 중단 가능.

### 해결 방법
입력값을 사전에 검증하여 예외 발생을 방지.

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

## 3. 0으로 나눌 때 오류 발생
### 문제 상황
나눗셈 연산에서 두 번째 숫자로 0을 입력하면 `ArithmeticException`이 발생.

### 원인
수학적으로 0으로 나누는 것은 불가능하며, 프로그램에서도 이를 방지하기 위해 예외를 발생시킴.

### 해결 방법
- 나눗셈 연산 전 `if (num2 == 0)`을 추가하여 0 입력 방지.
- `try-catch`를 활용하여 예외 발생 시 사용자에게 올바른 입력을 요청.

```java
if (num2 == 0) {
    System.out.println("오류: 0으로 나눌 수 없습니다. 다른 숫자를 입력하세요.");
} else {
    double result = num1 / num2;
    System.out.println("결과: " + result);
}
```

## 4. 연산 결과 삭제 시 오류 발생
### 문제 상황
연산 결과 리스트에서 값을 삭제할 때, 저장된 결과가 없으면 `IndexOutOfBoundsException`이 발생.

### 원인
리스트가 비어 있는 상태에서 `remove(0)`을 호출하면 예외 발생.

### 해결 방법
`if (!results.isEmpty())` 검사를 수행하여 리스트가 비어 있는 경우 삭제 시도를 하지 않음.

```java
if (!results.isEmpty()) {
    results.remove(0);
    System.out.println("첫 번째 결과 삭제 완료!");
} else {
    System.out.println("오류: 저장된 연산 결과가 없습니다!");
}
```

## 마무리
너무나도 어려웠습니다.ㅠㅠ 앞으로 더 열심히 공부해야겠다는 생각이 들었습니다. 그래도 이번 과제를 하나씩 진행해 나가면서 자바와 아주 조금은 친해진(?) 것 같아 뿌듯했습니다.
