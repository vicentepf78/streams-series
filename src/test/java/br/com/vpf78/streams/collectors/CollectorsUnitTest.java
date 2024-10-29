package br.com.vpf78.streams.collectors;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CollectorsUnitTest {

    //https://www.baeldung.com/java-collectors

    @Test
    public void whenTeeing_ItShouldCombineTheResultsAsExpected() {
        List<Integer> numbers = Arrays.asList(42, 4, 2, 24);
        Range range = numbers.stream()
                .collect(teeing(minBy(Integer::compareTo), maxBy(Integer::compareTo), (min, max) -> new Range(min.orElse(null), max.orElse(null))));

        assertThat(range).isEqualTo(new Range(2, 42));
    }

    /**
     * Represents a closed range of numbers between {@link #min} and
     * {@link #max}, both inclusive.
     */
    private static class Range {

        private final Integer min;

        private final Integer max;

        Range(Integer min, Integer max) {
            this.min = min;
            this.max = max;
        }

        Integer getMin() {
            return min;
        }

        Integer getMax() {
            return max;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Range range = (Range) o;
            return Objects.equals(getMin(), range.getMin()) && Objects.equals(getMax(), range.getMax());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getMin(), getMax());
        }

        @Override
        public String toString() {
            return "Range{" + "min=" + min + ", max=" + max + '}';
        }
    }
}
