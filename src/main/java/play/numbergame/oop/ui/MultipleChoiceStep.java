package play.numbergame.oop.ui;

import play.numbergame.oop.util.ConsoleStep;
import play.numbergame.oop.util.InvalidInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class MultipleChoiceStep implements ConsoleStep {
    private final Map<String, ConsoleStep> choices;

    public MultipleChoiceStep() {
        this(new HashMap<>());
    }

    public MultipleChoiceStep(Map<String, ConsoleStep> choices) {
        this.choices = choices;
    }

    public MultipleChoiceStep with(String key, ConsoleStep value) {
        choices.put(key, value);
        return this;
    }

    @Override
    public String enter() {
        StringBuilder buf = new StringBuilder();
        for (String key : choices.keySet()) {
            buf.append(key).append(": ").append(choices.get(key).enter()).append("\n");
        }
        return buf.toString();
    }

    @Override
    public String process(BufferedReader userInput) throws InvalidInputException, IOException {
        String input = userInput.readLine();
        if (!choices.containsKey(input)) {
            throw new InvalidInputException("Wrong choice...");
        }
        return choices.get(input).process(userInput);
    }
}
