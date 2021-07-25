package io.flatservice.service;

import io.flatservice.service.treesyntax.TreeTokenManager;

import java.util.*;

public class FlattenerService {
    public static final int PROCESSED_REQUEST_MAX_SIZE = 100;
    public static final Queue<SummaryProcess> PROCESSED_REQUEST = new LinkedList<>();

    public List<SummaryProcess> fetchProcessedRequest() {
        return new ArrayList<>(PROCESSED_REQUEST);
    }

    public Result process(final String input) {
        var tokens = Tokenizer.getTokens(input);
        var tokensStack = Tokenizer.processSyntax(tokens);
        var tokenNode = TreeTokenManager.getTreeElements(tokensStack);
        var result = Result.of(tokenNode);
        saveFlattenedResult(SummaryProcess.of(input, result));
        return result;
    }

    private void saveFlattenedResult(SummaryProcess summaryProcess) {
        if (PROCESSED_REQUEST.size() == PROCESSED_REQUEST_MAX_SIZE)
            PROCESSED_REQUEST.remove();
        PROCESSED_REQUEST.add(summaryProcess);
    }
}
