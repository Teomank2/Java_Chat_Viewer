package chatviewer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testMessageFields() {
        Message msg = new Message("12:00", "Alice", "Hello!");
        assertEquals("12:00", msg.getTimeStamp());
        assertEquals("Alice", msg.getNickName());
        assertEquals("Hello!", msg.getContent());
    }

}