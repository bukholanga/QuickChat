package quickchat.ui;

import quickchat.models.Message;
import quickchat.services.MessageService;

import javax.swing.*;
import java.util.List;

/**
 * GUI for QuickChat app.
 */
public class ChatUI {
    private final MessageService messageService = new MessageService();

    public ChatUI() {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        while (true) {
            String option = JOptionPane.showInputDialog("""
                    Choose an option:
                    1) Send Message
                    2) Show Sent Messages (Coming Soon)
                    3) Quit""");
            if (option == null) continue;

            switch (option) {
                case "1" -> sendMessage();
                case "2" -> JOptionPane.showMessageDialog(null, "Coming Soon.");
                case "3" -> System.exit(0);
                default -> JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }

    private void sendMessage() {
        String recipient = JOptionPane.showInputDialog("Enter recipient number (+XX...):");
        if (!messageService.checkRecipientCell(recipient)) {
            JOptionPane.showMessageDialog(null, "Invalid phone number format.");
            return;
        }

        String content = JOptionPane.showInputDialog("Enter message (50-250 chars):");
        if (!messageService.validateMessageContent(content)) {
            JOptionPane.showMessageDialog(null, "Message must be 50-250 characters.");
            return;
        }

        int choice = JOptionPane.showOptionDialog(null,
                "What would you like to do?",
                "Options", 0, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Send", "Disregard", "Store"}, "Send");

        if (choice == 0) {
            Message message = messageService.sendMessage(recipient, content);
            String messageHash = message.createMessageHash(messageService.returnTotalMessages());
            JOptionPane.showMessageDialog(null, String.format("""
                    Message Sent!
                    Message ID: %s
                    Message Hash: %s
                    Recipient: %s
                    Content: %s
                    """, message.getMessageId(), messageHash, message.getRecipient(), message.getContent()));
        }
    }
}