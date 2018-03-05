package play.numbergame.oop.domain;

public interface Game {
    Question current();

    Answer accept(String answer);

    int score();

    boolean finished();

    boolean won();
}
