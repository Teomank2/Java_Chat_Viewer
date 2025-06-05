package chatviewer;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Chat_ParserTest {

    @Test
    void testParseValidFile() throws IOException {
        File testFile = new File("src/chatviewer/test_1.msg");
        List<Message> messages = Chat_Parser.parse(testFile);
        assertFalse(messages.isEmpty(), "Parsed message list should not be empty");
    }
}