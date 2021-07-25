package io.flatservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TokenizerTest {

    @Test
    public void shouldProcessSucceed() {
        List<Token> expectedTokes = Arrays.asList(Token.of("("),
                Token.of("("),
                Token.of("10"),
                Token.of(";"),
                Token.of("20"),
                Token.of(";"),
                Token.of("30"),
                Token.of(")"),
                Token.of(";"),
                Token.of("40"),
                Token.of(")")
        );
        List<Token> result = Tokenizer.getTokens("((10 ; 20 ; 30) ; 40)");
        Assertions.assertEquals(expectedTokes, result);
    }

    @Test
    public void shouldFailTokenStack() {
        List<Token> result = Tokenizer.getTokens("(((10 ; 20 ; 30) ; 40)");
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tokenizer.processSyntax(result));
    }

    @Test
    public void shouldFailTokenStack2() {
        List<Token> result = Tokenizer.getTokens("((()10 ; 20 ; 30) ; 40)");
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tokenizer.processSyntax(result));
    }

    @Test
    public void shouldFailTokenStack3() {
        List<Token> result = Tokenizer.getTokens("(10 ; 20 ; 30) ; 40");
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tokenizer.processSyntax(result));
    }
}