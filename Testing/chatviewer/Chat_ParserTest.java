package chatviewer;

import chatviewer.Error_Handler.FileReadException;
import chatviewer.Error_Handler.ParseException;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Chat_ParserTest {
    @Test
    void testParseValidFile() throws FileReadException, ParseException {
        File file = new File("src/test_1.msg");
        List<Message> messages = Chat_Parser.parse(file);

        assertNotNull(messages);
        assertFalse(messages.isEmpty());
    }

    @Test
    void testParseInvalidFile() {
        File invalidFile = new File("non_existent_file.msg");
        assertThrows(FileReadException.class, () -> Chat_Parser.parse(invalidFile));
    }
}