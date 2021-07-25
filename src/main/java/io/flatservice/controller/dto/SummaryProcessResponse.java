package io.flatservice.controller.dto;

import io.flatservice.service.SummaryProcess;

import java.util.stream.Collectors;

public class SummaryProcessResponse {
    private String input;
    private String result;
    private int depth;
    private String dateTime;

    public SummaryProcessResponse() {

    }

    public static SummaryProcessResponse of(final SummaryProcess summaryProcess) {
        var items = summaryProcess.getResult().stream().map(Object::toString).collect(Collectors.toList());
        return new SummaryProcessResponse(summaryProcess.getInput(),
                "(" + String.join(";", items) + ")",
                summaryProcess.getDepth(),
                summaryProcess.getProcessedDate().toString());
    }

    public SummaryProcessResponse(String input, String result, int depth, String dateTime) {
        this.input = input;
        this.result = result;
        this.depth = depth;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getDepth() {
        return depth;
    }

    public String getInput() {
        return input;
    }

    public String getResult() {
        return result;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
