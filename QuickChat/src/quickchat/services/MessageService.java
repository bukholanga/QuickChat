package quickchat.services;

import quickchat.models.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageService {
    private List<Message> messages = new ArrayList<>();
    private int totalMessages = 0;

    public boolean checkRecipientCell(String phone) {
        return phone.matches("^\\+?[0-9]{10,13}$");
    }

    public boolean validateMessageContent(String content) {
        return content != null && content.length() <= 250 && content.length() >= 1;
    }

    public String generateMessageId() {
        Random random = new Random();
        int id = random.nextInt(900000000) + 100000000;
        return String.valueOf(id);
    }

    public Message sendMessage(String recipient, String content) {
        if (!checkRecipientCell(recipient)) {
            return null;
        }
        if (!validateMessageContent(content)) {
            return null;
        }
        String id = generateMessageId();
        Message message = new Message(id, recipient, content);
        messages.add(message);
        totalMessages++;
        return message;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public int returnTotalMessages() {
        return totalMessages;
    }
}