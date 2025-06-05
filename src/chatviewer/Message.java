package chatviewer;

public class Message {
    private final String timeStamp;
    private final String nickName;
    private final String content;

    public Message(String timeStamp, String nickName, String content) {
        this.timeStamp = timeStamp;
        this.nickName = nickName;
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getNickName() {
        return nickName;
    }

    public String getContent() {
        return content;
    }
}
