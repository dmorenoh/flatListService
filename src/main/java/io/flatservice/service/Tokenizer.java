package io.flatservice.service;

import org.eclipse.jetty.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tokenizer {
    public static List<Token> getTokens(final String expression) {
        var tokens = new ArrayList<Token>();
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '(':
                    tokens.add(Token.of("("));
                    break;
                case ')':
                    tokens.add(Token.of(")"));
                    break;
                case ';':
                    tokens.add(Token.of(";"));
                    break;
                case ' ':
                    break;
                default: {
                    var subStr = expression.substring(i);
                    var endOfToken = endOfToken(subStr);
                    String possibleToken = expression.substring(i, i + endOfToken).trim();
                    if (!StringUtil.isBlank(possibleToken)) {
                        tokens.add(Token.of(possibleToken));
                    }
                    i = i + endOfToken - 1;
                }
            }
        }

        return tokens;
    }

    public static Stack<Token> processSyntax(final List<Token> tokens) {
        var tokensStack = new Stack<Token>();
        var parenthesis = new Stack<Token>();
        for (int i = 0; i < tokens.size(); i++) {
            var currentToken = tokens.get(i);
            switch (currentToken.getValue()) {
                case "(": {
                    if (tokensStack.isEmpty() || tokensStack.peek().getValue().equals("(") || tokensStack.peek().getValue().equals(";")) {
                        tokensStack.push(currentToken);
                        parenthesis.push(currentToken);
                    } else throw new IllegalArgumentException("Syntax failed");
                    break;
                }
                case ")": {
                    if (!tokensStack.isEmpty() && (!tokensStack.peek().getValue().equals(";") && !tokensStack.peek().getValue().equals("("))) {
                        tokensStack.add(currentToken);
                        if (!parenthesis.isEmpty() && parenthesis.peek().getValue().equals("("))
                            parenthesis.pop();
                        else parenthesis.push(currentToken);
                    } else throw new IllegalArgumentException("Syntax failed");
                    break;
                }
                case ";": {
                    if (!tokensStack.isEmpty() && (!tokensStack.peek().getValue().equals(";") && !tokensStack.peek().getValue().equals("(")))
                        tokensStack.push(currentToken);
                    else throw new IllegalArgumentException("Syntax failed");
                    break;
                }
                default: {
                    if (!tokensStack.isEmpty() && (tokensStack.peek().getValue().equals(";") || tokensStack.peek().getValue().equals("("))
                            && (i < tokens.size() - 1 && (tokens.get(i + 1).getValue().equals(")") || tokens.get(i + 1).getValue().equals(";")))
                    )
                        tokensStack.push(currentToken);
                    else throw new IllegalArgumentException("Syntax failed");
                }
            }
        }
        if (!parenthesis.isEmpty()) throw new IllegalArgumentException("Parenthesis error");
        return tokensStack;
    }

    private static int endOfToken(String subStr) {
        for (int i = 0; i < subStr.length(); i++) {
            char currentChar = subStr.charAt(i);
            if (currentChar == ')' || currentChar == ';' || currentChar == ' ')
                return i;
        }
        return subStr.length();
    }
}
