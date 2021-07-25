package io.flatservice.service.treesyntax;

import io.flatservice.service.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TreeTokenManager {
    public static TokenNode getTreeElements(Stack<Token> tokenQueue) {

        if (!tokenQueue.peek().equals(Token.of(")")))
            throw new IllegalArgumentException("Failed");
        var root = TokenNode.of(")");
        tokenQueue.pop();
        var tokenTree = buildTree(root, tokenQueue);
        return tokenTree;
    }

    public static List<Object> toList(TokenNode node) {
        var list = new ArrayList<>();
        transverse(node, list);
        Collections.reverse(list);
        return list;
    }


    public static void transverse(TokenNode node, List<Object> list) {
        for (TokenNode tokenNode : node.getChildNodes()) {
            transverse(tokenNode, list);
        }
        if (!node.getValue().equals(")"))
            list.add(node.getValue());
    }

    private static TokenNode buildTree(TokenNode root, Stack<Token> tokens) {
        while (!tokens.isEmpty()) {
            var currentToken = tokens.pop();
            if (currentToken.equals(Token.of("("))) {
                return root;
            }
            if (currentToken.equals(Token.of(")"))) {
                root.addChild(buildTree(TokenNode.of(")"), tokens));
                continue;
            }
            if (currentToken.equals(Token.of(";"))) continue;
            root.addChild(TokenNode.of(currentToken.getValue()));
        }
        throw new IllegalArgumentException("failed");
    }

}
