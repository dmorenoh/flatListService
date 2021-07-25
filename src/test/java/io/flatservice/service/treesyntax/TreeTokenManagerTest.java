package io.flatservice.service.treesyntax;

import io.flatservice.service.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class TreeTokenManagerTest {

    @Test
    public void shouldProcess() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(Token.of("("));
        tokens.push(Token.of("A"));
        tokens.push(Token.of(";"));
        tokens.push(Token.of("("));
        tokens.push(Token.of("B"));
        tokens.push(Token.of(";"));
        tokens.push(Token.of("C"));
        tokens.push(Token.of(")"));
        tokens.push(Token.of(")"));

        TokenNode process = TreeTokenManager.getTreeElements(tokens);
        List<Object> objects = TreeTokenManager.toList(process);
        Assertions.assertNotNull(process);
        Assertions.assertEquals(Arrays.asList("A", "B","C"), objects);
    }
}