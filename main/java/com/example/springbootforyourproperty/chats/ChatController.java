package com.example.springbootforyourproperty.chats;

import com.example.springbootforyourproperty.yourproperty.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/save")
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> chatMessage) {
        String senderEmail = chatMessage.get("senderEmail");
        String receiverEmail = chatMessage.get("receiverEmail");
        String message = chatMessage.get("message");
        String propertyTitle = chatMessage.get("propertyTitle");
        String propertyLocation = chatMessage.get("propertyAddress");

        if(senderEmail == null && receiverEmail == null && message == null && propertyTitle == null && propertyLocation == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }

        chatService.saveChatMessage(senderEmail, receiverEmail, message, propertyTitle, propertyLocation);
        return ResponseEntity.ok("Message sent successfully");
    }

//    @GetMapping("/history/{propertyId}")
//    public ResponseEntity<List<ChatMessage>> getChatHistory(@PathVariable Long propertyId) {
//
//    }

    @PostMapping("/history")
    public ResponseEntity<?> getChatHistory(@RequestBody Map<String, String> requestBody) {
        String senderEmail = requestBody.get("senderEmail");
        String receiverEmail = requestBody.get("receiverEmail");
        String propertyTitle = requestBody.get("propertyTitle");
        String propertyLocation = requestBody.get("propertyAddress");

        if(senderEmail == null && receiverEmail == null &&propertyTitle == null && propertyLocation == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields.");
        }
//        List<ChatMessage> chatMessages = chatService.getChatMessagesBetweenUsersAboutProperty(senderEmail, receiverEmail, propertyTitle, propertyLocation);
//        return ResponseEntity.ok(chatMessages);
        List<Map<String, Object>> chatMessages = chatService.getChatAndNegotiationHistory(senderEmail, receiverEmail, propertyTitle, propertyLocation);
        return ResponseEntity.ok(chatMessages);
    }

    @GetMapping("/inbox/{email}")
    public List<Map<String, Object>> getInbox(@PathVariable String email) {
        return chatService.getInboxMessagesWithSenderAndProperty(email);
    }
}
