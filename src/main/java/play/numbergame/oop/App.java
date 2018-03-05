package play.numbergame.oop;


import org.cactoos.iterable.Cycled;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import play.numbergame.oop.util.ConsoleApp;
import play.numbergame.oop.domain.Answer;
import play.numbergame.oop.domain.Game;
import play.numbergame.oop.domain.Level;
import play.numbergame.oop.domain.Question;
import play.numbergame.oop.ui.*;
import play.numbergame.oop.util.RepeatUntil;

import java.util.concurrent.atomic.AtomicReference;

public final class App {
    public static void main(String... args) {
        AtomicReference<Game> ref = new AtomicReference<>();
        Game game = new GameInMemory(ref);

        new ConsoleApp(
            new Joined<>(
                new IterableOf<>(new PrintOnlyStep("Welcome... Please answer the following questions")),
                new RepeatOnErrorStep(
                    10,
                    new MultipleChoiceStep()
                        .with("A", new SelectLevelStep(ref, "EASY", Level.EASY))
                        .with("B", new SelectLevelStep(ref, "MEDIUM", Level.MEDIUM))
                        .with("C", new SelectLevelStep(ref, "HARD", Level.HARD))
                ),
                new RepeatUntil<>(
                    () -> !game.finished(),
                    new Cycled<>(new RepeatOnErrorStep(3, new QuestionStep(game)))
                ),
                new IterableOf<>(new GameFinishedStep(game))
            )
        ).run();
    }



    public static final class GameInMemory implements Game {
        private final AtomicReference<Game> ref;

        public GameInMemory(AtomicReference<Game> ref) {
            this.ref = ref;
        }


        @Override
        public Question current() {
            return ref.get().current();
        }

        @Override
        public Answer accept(String answer) {
            return ref.get().accept(answer);
        }

        @Override
        public int score() {
            return ref.get().score();
        }

        @Override
        public boolean finished() {
            return ref.get().finished();
        }

        @Override
        public boolean won() {
            return ref.get().won();
        }
    }
}
