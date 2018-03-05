package play.numbergame.oop.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public final class ConsoleApp implements Runnable {
    private final Iterable<ConsoleStep> steps;
    private final BufferedReader input;
    private final PrintStream output;

    public ConsoleApp(Iterable<ConsoleStep> steps) {
        this(steps, new BufferedReader(new InputStreamReader(System.in)), System.out);
    }

    public ConsoleApp(Iterable<ConsoleStep> steps, BufferedReader input, PrintStream output) {
        this.steps = steps;
        this.input = input;
        this.output = output;
    }

    public void run() {
        try {
            for (ConsoleStep step : steps) {
                output.println(step.enter());
                output.println(step.process(input));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
