package play.numbergame.oop.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RandomMathQuestions implements QuestionsBank {
    private final Level level;
    private final List<IntOperation> ops;
    private final Random random;

    public RandomMathQuestions(Level level) {
        this(level, Arrays.asList(
            new IntOperation.Add(),
            new IntOperation.Sub(),
            new IntOperation.Mul(),
            new IntOperation.Div())
        );
    }

    public RandomMathQuestions(Level level, List<IntOperation> ops) {
        this(level, ops, new Random());
    }

    public RandomMathQuestions(Level level, List<IntOperation> ops, Random random) {
        this.level = level;
        this.ops = ops;
        this.random = random;
    }

    @Override
    public Question next() {
        return new QueEquation(
            level.number(),
            level.number(),
            ops.get(random.nextInt(ops.size() - 1))
        );
    }
}
