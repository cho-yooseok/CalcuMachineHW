TroubleShooting
유지보수 내용 기록

---


1. 숫자 입력 시 오류 발생

문제:
- 숫자를 입력해야 하는데, 문자나 특수 기호를 입력하면 NumberFormatException이 발생합니다.
  예: 첫 번째 숫자를 입력하세요: abc → 오류 발생

원인:
- Double.parseDouble()은 숫자로 변환할 수 없는 문자열이 입력되면 예외를 던집니다.

해결 방법:
- 입력값이 숫자인지 확인하는 로직을 추가합니다.
  try {
  num1 = Double.parseDouble(input);
  } catch (NumberFormatException e) {
  System.out.println("올바른 숫자를 입력하세요.");
  continue;
  }
- 사용자에게 숫자만 입력하도록 안내 메시지를 출력합니다.

---

2. 잘못된 연산자 입력 시 오류 발생

문제:
- +, -, *, / 외의 기호를 입력하면 IllegalArgumentException이 발생합니다.
  예: 사칙연산 기호를 입력하세요: & → 오류 발생

원인:
- OperatorType.fromSymbol() 메서드에서 정의되지 않은 기호를 입력하면 예외가 발생합니다.

해결 방법:
- 입력값이 유효한 연산자인지 확인하는 로직을 추가합니다.
  try {
  operator = OperatorType.fromSymbol(operatorSymbol);
  } catch (IllegalArgumentException e) {
  System.out.println(e.getMessage());
  continue;
  }
- 사용자에게 올바른 연산자를 입력하도록 안내 메시지를 출력합니다.

---

3. 0으로 나눌 때 오류 발생

문제:
- 나눗셈 연산에서 두 번째 숫자로 0을 입력하면 ArithmeticException이 발생합니다.
  예: 두 번째 숫자를 입력하세요: 0 → 오류 발생

원인:
- 수학적으로 0으로 나누는 것은 불가능하며, 프로그램에서도 이를 방지하기 위해 예외를 던집니다.

해결 방법:
- 나눗셈 연산 전에 두 번째 숫자가 0인지 확인합니다.
  try {
  calculator.calculate(num1, num2, operator);
  } catch (ArithmeticException e) {
  System.out.println("오류: " + e.getMessage());
  continue;
  }
- 사용자에게 0이 아닌 다른 숫자를 입력하도록 안내합니다.

---

4. 연산 결과 삭제 시 오류 발생

문제:
- 저장된 연산 결과가 없는데 removeFirstResult()를 호출하면 오류가 발생합니다.

원인:
- results 리스트가 비어 있는 상태에서 첫 번째 결과를 삭제하려고 할 때 발생합니다.

해결 방법:
- 리스트가 비어 있는지 확인하는 로직을 추가합니다.
  if (!results.isEmpty()) {
  results.remove(0);
  } else {
  System.out.println("저장된 연산 결과가 없습니다.");
  }
- 사용자에게 저장된 결과가 없다는 메시지를 출력합니다.