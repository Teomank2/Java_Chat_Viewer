package chatviewer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.*;

public class EmoticonParser {

    private static final Map<String, String> emoticonMap = Map.ofEntries(
            Map.entry(":)", "/images/smile_happy.gif"),
            Map.entry(":(", "/images/smile_sad.gif")
    );

    public static List<Object> parseContentWithEmojis(String content) {
        List<Object> parts = new ArrayList<>();

        int i = 0;
        while (i < content.length()) {
            boolean matched = false;

            for (String emoticon : emoticonMap.keySet()) {
                int len = emoticon.length();
                if (i + len <= content.length() && content.substring(i, i + len).equals(emoticon)) {
                    parts.add(createEmojiImage(emoticonMap.get(emoticon)));
                    i += len;
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                int nextEmojiIndex = findNextEmoji(content, i);
                String textPart = content.substring(i, nextEmojiIndex);
                parts.add(new Text(textPart));
                i = nextEmojiIndex;
            }
        }

        return parts;
    }

    private static int findNextEmoji(String text, int start) {
        for (int i = start; i < text.length(); i++) {
            for (String emoticon : emoticonMap.keySet()) {
                int len = emoticon.length();
                if (i + len <= text.length() && text.substring(i, i + len).equals(emoticon)) {
                    return i;
                }
            }
        }
        return text.length();
    }

    private static ImageView createEmojiImage(String path) {
        Image image = new Image(Objects.requireNonNull(EmoticonParser.class.getResourceAsStream(path)));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        return imageView;
    }
}
