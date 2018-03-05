package play.numbergame.oop.domain;

public interface Answer {
    boolean correct();

    int points();


    final class Correct implements Answer {
        private final Number points;

        public Correct(Number points) {
            this.points = points;
        }

        @Override
        public boolean correct() {
            return true;
        }

        @Override
        public int points() {
            return points.intValue();
        }
    }


    final class Wrong implements Answer {
        @Override
        public boolean correct() {
            return false;
        }

        @Override
        public int points() {
            return 0;
        }
    }
}
