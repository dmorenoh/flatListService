package io.flatservice.controller.dto;

import io.flatservice.service.FlattenedResult;
import io.flatservice.service.Result;

import java.util.stream.Collectors;

public class MessageResponse {
    private String flattenResponse;
    private int depth;

    public static MessageResponse of(final FlattenedResult flattenedResult) {
        String elements = String.join(";", flattenedResult.getValues());
        return new MessageResponse("(" + elements + ")", flattenedResult.getDepth());
    }

    public static MessageResponse of(final Result result) {
        var items = result.getValues().stream().map(Object::toString).collect(Collectors.toList());
        String elements = String.join(";", items);
        return new MessageResponse("(" + elements + ")", result.getDepth());
    }

    public MessageResponse() {
        flattenResponse = "";
        depth = -1;
    }

    public int getDepth() {
        return depth;
    }

    public String getFlattenResponse() {
        return flattenResponse;
    }

    public void setFlattenResponse(String flattenResponse) {
        this.flattenResponse = flattenResponse;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public MessageResponse(String flattenResponse, int depth) {
        this.flattenResponse = flattenResponse;
        this.depth = depth;
    }
}
