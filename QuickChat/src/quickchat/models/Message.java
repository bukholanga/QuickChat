package quickchat.models;

public class Message {
    private String messageId;
    private String recipient;
    private String content;

    public Message(String messageId, String recipient, String content) {
        this.messageId = messageId;
        this.recipient = recipient;
        this.content = content;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public String createMessageHash(int messageCount) {
        String first2 = messageId.substring(0, 2);
        String[] words = content.trim().split("\\s+");
        String firstLast = (words.length >= 2) ? words[0] + words[words.length - 1] : words[0];
        return String.format("%s:%d:%s", first2, messageCount, firstLast.toUpperCase());
    }
}