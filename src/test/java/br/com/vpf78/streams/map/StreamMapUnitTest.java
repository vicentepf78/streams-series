package br.com.vpf78.streams.map;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamMapUnitTest {

//    https://www.baeldung.com/java-maps-streams

    private Map<String, String> books;


    @BeforeEach
    public void setup() {
        books = new HashMap<>();
        books.put("978-0201633610", "Design patterns : elements of reusable object-oriented software");
        books.put("978-1617291999", "Java 8 in Action: Lambdas, Streams, and functional-style programming");
        books.put("978-0134685991", "Effective Java");
    }


    @Test
    public void whenOptionalVersionCalledForExistingTitle_thenReturnOptionalWithISBN() {
        Optional<String> optionalIsbn = books.entrySet().stream()
                .filter(e -> "Effective Java".equals(e.getValue()))
                .map(Map.Entry::getKey).findFirst();

        assertEquals("978-0134685991", optionalIsbn.get());
    }

    @Test
    public void whenOptionalVersionCalledForNonExistingTitle_thenReturnEmptyOptionalForISBN() {
        Optional<String> optionalIsbn = books.entrySet().stream()
                .filter(e -> "Non Existent Title".equals(e.getValue()))
                .map(Map.Entry::getKey).findFirst();

        assertEquals(false, optionalIsbn.isPresent());
    }

    @Test
    public void whenMultipleResultsVersionCalledForExistingTitle_aCollectionWithMultipleValuesIsReturned() {
        books.put("978-0321356680", "Effective Java: Second Edition");

        List<String> isbnCodes = books.entrySet().stream()
                .filter(e -> e.getValue().startsWith("Effective Java"))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        assertTrue(isbnCodes.contains("978-0134685991"));
        assertTrue(isbnCodes.contains("978-0321356680"));
    }


    @Test
    public void whenMultipleResultsVersionCalledForNonExistingTitle_aCollectionWithNoValuesIsReturned() {
        List<String> isbnCodes = books.entrySet().stream()
                .filter(e -> e.getValue().startsWith("Spring"))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        assertTrue(isbnCodes.isEmpty());
    }

    @Test
    public void whenKeysFollowingPatternReturnsAllValuesForThoseKeys() {
        List<String> titlesForKeyPattern = books.entrySet().stream()
                .filter(e -> e.getKey().startsWith("978-0"))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        assertEquals(2, titlesForKeyPattern.size());
        assertTrue(titlesForKeyPattern.contains("Design patterns : elements of reusable object-oriented software"));
        assertTrue(titlesForKeyPattern.contains("Effective Java"));
    }


}
