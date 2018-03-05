package play.numbergame.oop.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Supplier;

public final class RepeatUntil<T> implements Iterable<T> {
    private final Supplier<Boolean> condition;
    private final Iterable<T> iterable;

    public RepeatUntil(Supplier<Boolean> condition, T... iterable) {
        this(condition, Arrays.asList(iterable));
    }


    public RepeatUntil(Supplier<Boolean> condition, Iterable<T> iterable) {
        this.condition = condition;
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return new RepeatUntilIterator<>(condition, iterable.iterator());
    }


    public static class RepeatUntilIterator<T> implements Iterator<T> {
        private final Supplier<Boolean> condition;
        private final Iterator<T> iterator;

        public RepeatUntilIterator(Supplier<Boolean> condition, Iterator<T> iterator) {
            this.condition = condition;
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return condition.get() && iterator.hasNext();
        }

        @Override
        public T next() {
            return iterator.next();
        }
    }
}