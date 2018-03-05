package play.numbergame.oop.ui;

import play.numbergame.oop.util.ConsoleStep;
import play.numbergame.oop.util.InvalidInputException;

import java.io.BufferedReader;
import java.io.IOException;

public final class PrintOnlyStep implements ConsoleStep {
    private final String msg;

    public PrintOnlyStep(String msg) {
        this.msg = msg;
    }

    @Override
    public String enter() {
        return msg;
    }

    @Override
    public String process(BufferedReader input) throws InvalidInputException, IOException {
        return "";
    }
}
