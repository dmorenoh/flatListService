package io.flatservice.controller;

import io.flatservice.controller.dto.MessageRequest;
import io.flatservice.controller.dto.MessageResponse;
import io.flatservice.service.FlattenerService;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.Unirest;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTest {
    public static final String FLATTENER_URI = "http://localhost:7001/flattener";
    private static ServerApp app;

    @BeforeAll
    static void init() {
        app = new ServerApp(new FlattenerController(new FlattenerService()));
        app.start(7001);
    }

    @AfterAll
    static void end() {
        app.stop();
    }

    @Test
    @Order(1)
    public void shouldTestEndpoint() {
        var expected = JavalinJson.toJson(new MessageResponse("(10;20;30;40)", 1));
        //When
        var response = Unirest.post(FLATTENER_URI)
                .body(new MessageRequest("((10 ; 20 ; 30) ; 40)"))
                .asString();
        //Then
        Assertions.assertEquals(expected, response.getBody());
    }

    @Test
    @Order(2)
    public void shouldFailWhenParenthesisError() {
        //Given
        var invalidRequest = "((10 ; 20 ; 30)) ; 40)";
        //When
        var response = Unirest.post(FLATTENER_URI)
                .body(new MessageRequest(invalidRequest))
                .asString();
        //Then
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @Order(3)
    public void shouldReturnProcessedRequest() {
        var response = Unirest.get(FLATTENER_URI)
                .asString();

        Assertions.assertEquals(response.getStatus(), 200);
    }
}
