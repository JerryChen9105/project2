




public class Calculator {


    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int devide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Zero denominator is not allowed");
        return a / b;
    }


}
