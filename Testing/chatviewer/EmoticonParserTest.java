package chatviewer;

import org.junit.jupiter.api.Test;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EmoticonParserTest {

    @Test
    void testEmojiParsing() {
        String input = "Hello :)";
        List<Object> parts = EmoticonParser.parseContentWithEmojis(input);

        assertEquals(2, parts.size());
        assertTrue(parts.get(0) instanceof Text);
        assertTrue(parts.get(1) instanceof ImageView);
    }
}
