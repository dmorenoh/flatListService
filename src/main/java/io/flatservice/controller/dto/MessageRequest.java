package io.flatservice.controller.dto;

public class MessageRequest {
    private String input;

    public MessageRequest() {
        input = "";
    }

    public MessageRequest(String input) {
        this.input = input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
