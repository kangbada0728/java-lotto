package calculator;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Arithmetic {
    PLUS("+", (number1, number2) -> new Number(number1.getNumber() + number2.getNumber())),
    MINUS("-", (number1, number2) -> new Number(number1.getNumber() - number2.getNumber())),
    MULTIPLICATION("*", (number1, number2) -> new Number(number1.getNumber() * number2.getNumber())),
    DIVIDE("/", (number1, number2) -> {
        if (number1.getNumber() % number2.getNumber() != 0) {
            throw new IllegalArgumentException("나눗셈은 결과값이 정수로 떨어져야만 합니다.");
        }
        return new Number(number1.getNumber() / number2.getNumber());
    });

    private final String sign;
    private final BiFunction<Number, Number, Number> calculate;

    Arithmetic(String sign, BiFunction<Number, Number, Number> calculate) {
        this.sign = sign;
        this.calculate = calculate;
    }

    public BiFunction<Number, Number, Number> calculate() {
        return calculate;
    }

    public static Arithmetic findSign(String sign) {
        return Arrays.stream(Arithmetic.values())
                .filter(a -> a.sign.equals(sign))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("사칙연산 기호가 아닙니다!"));
    }

}
