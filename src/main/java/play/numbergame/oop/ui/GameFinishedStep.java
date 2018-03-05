package play.numbergame.oop.ui;

import play.numbergame.oop.util.ConsoleStep;
import play.numbergame.oop.util.InvalidInputException;
import play.numbergame.oop.domain.Game;

import java.io.BufferedReader;
import java.io.IOException;

public final class GameFinishedStep implements ConsoleStep {
    private final Game game;

    public GameFinishedStep(Game game) {
        this.game = game;
    }

    @Override
    public String enter() {
        return game.won() ?
            "You finished successfully!!\nTotal points: " + game.score() :
            "GAME OVER....";
    }

    @Override
    public String process(BufferedReader input) throws InvalidInputException, IOException {
        return "";
    }
}
