package io.flatservice.service;

import lombok.Value;

import java.time.Instant;
import java.util.List;

@Value
public class SummaryProcess {
    String input;
    List<Object> result;
    int depth;
    Instant processedDate;

    public static SummaryProcess of(String input, Result result) {
        return new SummaryProcess(input, result.getValues(), result.getDepth());
    }

    public SummaryProcess(String input, List<Object> result, int depth) {
        this.input = input;
        this.result = result;
        this.depth = depth;
        this.processedDate = Instant.now();
    }
}
