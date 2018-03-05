package play.numbergame.oop.domain;

public final class QueEquation implements Question {
    private final Number a;
    private final Number b;
    private final IntOperation op;

    public QueEquation(Number a, Number b, IntOperation op) {
        this.a = a;
        this.b = b;
        this.op = op;
    }

    @Override
    public String correctAnswer() {
        return String.valueOf(op.resultOf(a.intValue(), b.intValue()));
    }

    @Override
    public String ask() {
        return String.format("Calculate %d %s %d:", a.intValue(), op.symbol(), b.intValue());
    }
}
