package play.numbergame.oop.domain;

public interface IntOperation {
    int resultOf(int a, int b);

    String symbol();


    final class Add implements IntOperation {
        @Override
        public int resultOf(int a, int b) {
            return a + b;
        }

        @Override
        public String symbol() {
            return "+";
        }
    }

    final class Sub implements IntOperation {
        @Override
        public int resultOf(int a, int b) {
            return a - b;
        }

        @Override
        public String symbol() {
            return "-";
        }
    }

    final class Mul implements IntOperation {
        @Override
        public int resultOf(int a, int b) {
            return a * b;
        }

        @Override
        public String symbol() {
            return "x";
        }
    }


    final class Div implements IntOperation {
        @Override
        public int resultOf(int a, int b) {
            return a / b;
        }

        @Override
        public String symbol() {
            return "÷";
        }
    }
}
