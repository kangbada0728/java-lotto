package calculator;

import java.util.Objects;

public class Number {

    private final int number;

    public Number(String number) {
        if (number == null) {
            throw new IllegalArgumentException("숫자가 NULL 입니다!");
        }
        if (number.length() == 0) {
            throw new IllegalArgumentException("빈 공백 문자입니다!");
        }
        this.number = Integer.parseInt(number);
    }

    public Number(int number) {
        this.number = number;
    }

    public Number plus(Number other) {
        return new Number(this.number + other.number);
    }

    public Number minus(Number other) {
        return new Number(this.number - other.number);
    }

    public Number multiplication(Number other) {
        return new Number(this.number * other.number);
    }

    public Number divide(Number other) {
        if (this.number % other.number != 0) {
            throw new IllegalArgumentException("나눗셈이 정수로 떨어지지 않습니다!");
        }
        return new Number(this.number / other.number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return number == number1.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
