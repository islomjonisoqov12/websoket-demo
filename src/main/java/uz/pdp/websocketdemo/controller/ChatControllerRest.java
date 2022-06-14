package uz.pdp.websocketdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.websocketdemo.dto.MessageDto;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.services.MessageService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/message")
public class ChatControllerRest {

    @Autowired
    MessageService messageService;

//    @GetMapping
//    public List<MessageDto> getAllMessage() {
//        return messageService.getAllMessage();
//
//    }

    @GetMapping("/{receiverId}")
    public List<MessageDto> getAllMessage(@PathVariable UUID receiverId, @AuthenticationPrincipal User currentUser) {
        return messageService.getAllMessageByReceiverId(receiverId, currentUser);
    }
}
