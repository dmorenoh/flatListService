package io.flatservice.service;

import io.flatservice.service.treesyntax.TokenNode;
import lombok.Value;

import java.util.List;

@Value
public class Result {
    List<Object> values;
    int depth;
    public static Result of(final TokenNode tokenNode){
        return new Result(tokenNode.getLeaf(), tokenNode.getDepth() - 2);
    }
}
