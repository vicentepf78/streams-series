package br.com.vpf78.streams.stream;

import org.jooq.lambda.Seq;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JoolMergeStreamsUnitTest {

    //https://www.baeldung.com/java-merge-streams

    @Test
    public void givenTwoStreams_whenMergingStreams_thenResultingStreamContainsElementsFromBothStreams() {
        Stream<Integer> seq1 = Stream.of(1, 3, 5);
        Stream<Integer> seq2 = Stream.of(2, 4, 6);

        Stream<Integer> resultingSeq = Seq.ofType(seq1, Integer.class).append(seq2);

        assertEquals(Arrays.asList(1, 3, 5, 2, 4, 6), resultingSeq.collect(Collectors.toList()));
    }

    @Test
    public void givenThreeStreams_whenAppendingAndPrependingStreams_thenResultingStreamContainsElementsFromAllStreams() {
        Stream<String> seq = Stream.of("foo", "bar");
        Stream<String> openingBracketSeq = Stream.of("[");
        Stream<String> closingBracketSeq = Stream.of("]");

        Stream<String> resultingStream = Seq.ofType(seq, String.class).append(closingBracketSeq).prepend(openingBracketSeq);

        assertEquals(Arrays.asList("[", "foo", "bar", "]"), resultingStream.collect(Collectors.toList()));
    }
}
