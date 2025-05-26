package quickchat.ui;

import quickchat.models.Message;
import quickchat.services.MessageService;

import javax.swing.*;
import java.util.List;

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
        String recipient = JOptionPane.showInputDialog("Enter recipient number (e.g., +27712345678):");
        if (!messageService.checkRecipientCell(recipient)) {
            JOptionPane.showMessageDialog(null, "Invalid phone number. Must have international code and 10-13 digits.");
            return;
        }

        String content = JOptionPane.showInputDialog("Enter message (max 250 chars):");
        if (content.length() > 250) {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
            return;
        }
        if (content.length() < 50) {
            JOptionPane.showMessageDialog(null, "Please enter a message of at least 50 characters.");
            return;
        }

        int choice = JOptionPane.showOptionDialog(null,
                "Do you want to:\n1) Send\n2) Disregard\n3) Store to send later",
                "Send Option", 0, JOptionPane.QUESTION_MESSAGE, null,
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
        } else if (choice == 2) {
            // store message later - ChatGPT can help with JSON storage
        }
    }
}