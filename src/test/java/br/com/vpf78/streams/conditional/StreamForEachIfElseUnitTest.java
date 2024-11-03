package br.com.vpf78.streams.conditional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamForEachIfElseUnitTest {

    //https://www.baeldung.com/java-8-streams-if-else-logic

    @Test
    public final void givenIntegerStream_whenCheckingIntegerParityWithIfElse_thenEnsureCorrectParity() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        ints.stream()
                .forEach(i -> {
                    if (i.intValue() % 2 == 0) {
                        assertTrue(i.intValue() % 2 == 0, i.intValue() + " is not even");
                    } else {
                        assertTrue(i.intValue() % 2 != 0, i.intValue() + " is not odd");
                    }
                });

    }

    @Test
    public final void givenIntegerStream_whenCheckingIntegerParityWithCustomConsumer_thenEnsureCorrectParity() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Consumer<Integer> integerParityAsserter = i -> {
            if (i % 2 == 0) {
                assertTrue(i % 2 == 0, i + " is not even");
            } else {
                assertTrue(i % 2 != 0, i + " is not odd");
            }
        };

        ints.stream()
                .forEach(integerParityAsserter);
    }

    @Test
    public final void givenIntegerStream_whenCheckingIntegerParityWithStreamFilter_thenEnsureCorrectParity() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Stream<Integer> evenIntegers = ints.stream()
                .filter(i -> i.intValue() % 2 == 0);
        Stream<Integer> oddIntegers = ints.stream()
                .filter(i -> i.intValue() % 2 != 0);

        evenIntegers.forEach(i -> assertTrue(i.intValue() % 2 == 0, i.intValue() + " is not even"));
        oddIntegers.forEach(i -> assertTrue(i.intValue() % 2 != 0, i.intValue() + " is not odd"));

    }

    @Test
    public final void givenIntegerStream_whenCheckingIntegerParityWithStreamPartitioningBy_thenEnsureCorrectParity() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean, List<Integer>> resultMap = ints.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));

        assertEquals(Arrays.asList(2, 4, 6, 8, 10), resultMap.get(true));
        assertEquals(Arrays.asList(1, 3, 5, 7, 9), resultMap.get(false));
    }

    @Test
    public final void givenIntegerStream_whenCheckingIntegerParityWithStreamGroupingBy_thenEnsureCorrectParity() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<String, List<Integer>> resultMap = ints.stream()
                .collect(Collectors.groupingBy(i -> i % 2 == 0 ? "even" : "odd"));

        assertEquals(Arrays.asList(2, 4, 6, 8, 10), resultMap.get("even"));
        assertEquals(Arrays.asList(1, 3, 5, 7, 9), resultMap.get("odd"));
    }
}
