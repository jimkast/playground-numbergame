package play.numbergame.oop.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class GameDefault implements Game {
    private final Level level;
    private final QuestionsBank questionsBank;
    private final List<Question> questions;
    private final AtomicInteger score;
    private final AtomicInteger attempts;

    public GameDefault(Level level) {
        this(level, new RandomMathQuestions(level), new LinkedList<>(), new AtomicInteger(), new AtomicInteger());
    }

    public GameDefault(Level level, QuestionsBank questionsBank, List<Question> questions, AtomicInteger score, AtomicInteger attempts) {
        this.level = level;
        this.questionsBank = questionsBank;
        this.questions = questions;
        this.score = score;
        this.attempts = attempts;
    }

    @Override
    public Question current() {
        if (questions.isEmpty()) {
            questions.add(questionsBank.next());
        }
        return questions.get(questions.size() - 1);
    }

    @Override
    public Answer accept(String answer) {
        int counter = attempts.incrementAndGet();
        if (!current().correctAnswer().equals(answer)) {
            return new Answer.Wrong();
        }

        questions.add(questionsBank.next());
        int sc = level.rewardFor(counter);
        score.getAndAdd(sc);
        attempts.set(0);
        return new Answer.Correct(sc);
    }

    @Override
    public int score() {
        return score.get();
    }

    @Override
    public boolean finished() {
        return attempts.get() >= 3 || questions.size() >= 10;
    }

    @Override
    public boolean won() {
        return attempts.get() < 3;
    }
}
