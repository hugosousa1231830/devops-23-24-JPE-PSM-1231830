package basic_demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatServerTest {
    @Test
    void whenGivenAnInvalidPort_failsToRunThrowingIoException() {
        // Arrange
        int port = -1;
        String expected = "Invalid port";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ChatServer(port));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected, result);
    }
}
