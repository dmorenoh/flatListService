package io.flatservice.controller;

import io.flatservice.controller.dto.MessageRequest;
import io.flatservice.controller.dto.MessageResponse;
import io.flatservice.controller.dto.SummaryProcessResponse;
import io.flatservice.service.FlattenerService;
import io.flatservice.service.SummaryProcess;
import io.javalin.http.Context;

import java.util.List;
import java.util.stream.Collectors;

public class FlattenerController {
    private final FlattenerService flattnerService;

    public FlattenerController(FlattenerService flattnerService) {
        this.flattnerService = flattnerService;
    }

    public void flatten(Context context) {
        var messageRequest = context.bodyAsClass(MessageRequest.class);
        context.json(MessageResponse.of(flattnerService.process(messageRequest.getInput())));
    }

    public void fetchFlattenedRequest(Context context) {
        List<SummaryProcess> results = flattnerService.fetchProcessedRequest();
        var response = results.stream()
                .map(SummaryProcessResponse::of)
                .collect(Collectors.toList());
        context.json(response);
    }
}
