package play.numbergame.oop.ui;

import play.numbergame.oop.util.ConsoleStep;
import play.numbergame.oop.util.InvalidInputException;
import play.numbergame.oop.domain.Game;
import play.numbergame.oop.domain.GameDefault;
import play.numbergame.oop.domain.Level;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public final class SelectLevelStep implements ConsoleStep {
    private final AtomicReference<Game> ref;
    private final String name;
    private final Level level;

    public SelectLevelStep(AtomicReference<Game> ref, String name, Level level) {
        this.ref = ref;
        this.name = name;
        this.level = level;
    }

    @Override
    public String enter() {
        return name;
    }

    @Override
    public String process(BufferedReader input) throws InvalidInputException, IOException {
        ref.set(new GameDefault(level));
        return "";
    }
}
