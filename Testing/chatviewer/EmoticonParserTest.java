package chatviewer;

import org.junit.jupiter.api.Test;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import javafx.embed.swing.JFXPanel;

class EmoticonParserTest {

    @BeforeAll
    static void initJFX() {
        // This initializes the JavaFX toolkit so that the tests won't fail
        new JFXPanel();
    }

    @Test
    void testEmoticonReplacement() {
        String input = "Hello :) world :( test";
        List<Object> parts = EmoticonParser.parseContentWithEmojis(input);

        boolean hasImage = parts.stream().anyMatch(p -> p instanceof ImageView);
        boolean hasText = parts.stream().anyMatch(p -> p instanceof Text);

        assertTrue(hasImage);
        assertTrue(hasText);
    }

    @Test
    void testTextOnly() {
        List<Object> parts = EmoticonParser.parseContentWithEmojis("Hello world");
        assertEquals(1, parts.size());
        assertTrue(parts.get(0) instanceof Text);
    }
}
