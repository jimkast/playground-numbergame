package play.numbergame.oop.ui;

import play.numbergame.oop.util.ConsoleStep;
import play.numbergame.oop.util.InvalidInputException;
import play.numbergame.oop.domain.Answer;
import play.numbergame.oop.domain.Game;

import java.io.BufferedReader;
import java.io.IOException;

public final class QuestionStep implements ConsoleStep {
    private final Game game;

    public QuestionStep(Game game) {
        this.game = game;
    }

    @Override
    public String enter() {
        return game.current().ask();
    }

    @Override
    public String process(BufferedReader input) throws InvalidInputException, IOException {
        Answer answer = game.accept(input.readLine());
        if (!answer.correct()) {
            throw new InvalidInputException("Wrong!! Try again...");
        }
        return "Correct!! Current score: " + game.score();
    }
}
