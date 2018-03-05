package play.numbergame.oop.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public interface Level {
    int number();

    int rewardFor(int attempt);


    Level EASY = new Default(10, Arrays.asList(10, 8, 6));
    Level MEDIUM = new Default(10, Arrays.asList(10, 8, 6));
    Level HARD = new Default(10, Arrays.asList(10, 8, 6));


    final class Default implements Level {
        private final Random random;
        private final int bound;
        private final List<Integer> rewards;

        public Default(int bound, List<Integer> rewards) {
            this(new Random(), bound, rewards);
        }

        public Default(Random random, int bound, List<Integer> rewards) {
            this.random = random;
            this.bound = bound;
            this.rewards = rewards;
        }

        @Override
        public int number() {
            return random.nextInt(bound);
        }

        @Override
        public int rewardFor(int attempt) {
            return rewards.get(attempt - 1);
        }
    }
}
