package io.flatservice.controller;

import io.flatservice.service.FlattenerService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;

import static io.flatservice.service.FlattenerService.PROCESSED_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FlattenerServiceTest {
    private FlattenerService service = new FlattenerService();

    @Test
    @Order(1)
    public void shouldFailWhenInvalidInput() {
        //Given
        var invalidInput = "(10 ; 20 ; 30 ; 40)))";

        //When
        assertThrows(IllegalArgumentException.class, () -> service.process(invalidInput));

        //Given
        var invalidInput2 = "(10 ; 20 ; 30 ; 40) ; 50";

        //When
        assertThrows(IllegalArgumentException.class, () -> service.process(invalidInput2));

        //Given
        var invalidInput3 = "10 ; 20 ; 30 ; 40";

        //When
        assertThrows(IllegalArgumentException.class, () -> service.process(invalidInput3));

        //Given
        var invalidInput4 = "(10 20 30 40)";

        //When
        assertThrows(IllegalArgumentException.class, () -> service.process(invalidInput4));
    }

    @Test
    @Order(2)
    public void shouldProcessInputAsList() {
        //Given
        var validInput = "((10 ; ((20 ; (30))) ; (40)))";

        //When
        var rename = service.process(validInput);

        //Then
        assertEquals(Arrays.asList(10, 20, 30, 40), rename.getValues());
        assertEquals(4, rename.getDepth());
        assertEquals(validInput, PROCESSED_REQUEST.peek().getInput());
        assertEquals(rename.getValues(), PROCESSED_REQUEST.peek().getResult());
        assertNotNull(PROCESSED_REQUEST.peek().getProcessedDate());
    }

    @Test
    @Order(3)
    public void shouldProcessValidInputs() {
        var result = service.process("((10 ; 20 ; 30) ; 40)");
        assertEquals(Arrays.asList(10, 20, 30, 40), result.getValues());
        assertEquals(1, result.getDepth());

        String input = "((10 ; ((20 ; (30))) ; (40)))";
        var result2 = service.process(input);
        assertEquals(Arrays.asList(10, 20, 30, 40), result2.getValues());
        assertEquals(4, result2.getDepth());

        var result3 = service.process("((A ; 20 ; (B)) ; 40)");
        assertEquals(Arrays.asList("A", 20, "B", 40), result3.getValues());
        assertEquals(2, result3.getDepth());
    }
}