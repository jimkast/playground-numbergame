package play.numbergame.oop.ui;

import play.numbergame.oop.util.ConsoleStep;
import play.numbergame.oop.util.InvalidInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class RepeatOnErrorStep implements Iterable<ConsoleStep> {
    private final int max;
    private final ConsoleStep step;

    public RepeatOnErrorStep(int max, ConsoleStep step) {
        this.max = max;
        this.step = step;
    }

    @Override
    public Iterator<ConsoleStep> iterator() {
        return new ConsoleStepCheckForError(max, step);
    }


    public static final class ConsoleStepCheckForError implements ConsoleStep, Iterator<ConsoleStep> {
        private final int max;
        private final ConsoleStep step;
        private final AtomicInteger counter = new AtomicInteger();
        private final AtomicBoolean correct = new AtomicBoolean();


        public ConsoleStepCheckForError(int max, ConsoleStep step) {
            this.max = max;
            this.step = step;
        }

        @Override
        public String enter() {
            return step.enter();
        }

        @Override
        public String process(BufferedReader input) throws InvalidInputException, IOException {
            try {
                String result = step.process(input);
                correct.set(true);
                counter.incrementAndGet();
                return result;
            } catch (InvalidInputException e) {
                return e.getMessage();
            }
        }

        @Override
        public boolean hasNext() {
            return !correct.get() && counter.get() < max;
        }

        @Override
        public ConsoleStep next() {
            return this;
        }
    }
}
