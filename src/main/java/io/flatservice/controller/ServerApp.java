package io.flatservice.controller;

import io.flatservice.service.UnmatchedParenthesisException;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ServerApp {

    private final FlattenerController flattenerController;
    private final Javalin app;

    public ServerApp(final FlattenerController controller) {
        flattenerController = controller;
        app = Javalin.create()
                .routes(() -> {
                    path("flattener", () -> {
                        post(flattenerController::flatten);
                        get(flattenerController::fetchFlattenedRequest);
                    });
                });

        app.exception(IllegalArgumentException.class, (e, ctx) ->
                ctx.status(400)).error(400, ctx -> ctx.result("Bad request"));
    }

    public void start(int port) {
        this.app.start(port);
    }

    public void stop() {
        this.app.stop();
    }
}
