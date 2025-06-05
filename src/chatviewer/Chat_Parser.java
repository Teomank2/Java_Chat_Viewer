package chatviewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Chat_Parser {

    public static List<Message> parse(File file) throws IOException {
        List<Message> messages = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String timestamp = null;
            String nickname = null;
            String content = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("Time:")) {
                    timestamp = line.substring(5).trim();
                } else if (line.startsWith("Name:")) {
                    nickname = line.substring(5).trim();
                } else if (line.startsWith("Message:")) {
                    content = line.substring(8).trim();
                    if (timestamp != null && nickname != null) {
                        messages.add(new Message(timestamp, nickname, content));
                        // Reset for next message
                        timestamp = null;
                        nickname = null;
                        content = null;
                    }
                }
                // Skip empty lines between messages
            }
        }

        return messages;
    }
}

