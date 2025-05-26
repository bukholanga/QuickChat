package quickchat.services;

import quickchat.models.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Service class to validate and manage sending messages.
 */
public class MessageService {
    private List<Message> messages = new ArrayList<>();
    private int totalMessages = 0;

    /**
     * Validates phone number format.
     */
    public boolean checkRecipientCell(String phone) {
        return phone.matches("^\\+?[0-9]{10,13}$");
    }

    /**
     * Validates message length (50-250 chars).
     */
    public boolean validateMessageContent(String content) {
        return content != null && content.length() >= 50 && content.length() <= 250;
    }

    /**
     * Generates a 10-digit random message ID.
     */
    public String generateMessageId() {
        Random random = new Random();
        int id = random.nextInt(900000000) + 100000000;
        return String.valueOf(id);
    }

    /**
     * Sends a message if valid.
     */
    public Message sendMessage(String recipient, String content) {
        if (!checkRecipientCell(recipient) || !validateMessageContent(content)) {
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