package uz.pdp.websocketdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import uz.pdp.websocketdemo.dto.MessageDto;
import uz.pdp.websocketdemo.model.Message;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.services.MessageService;
import uz.pdp.websocketdemo.services.UserService;

@Controller
public class ChatControllerTest {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    MessageService messageService;


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @MessageMapping("/hello")
    public void sendMessage(MessageDto message) throws Exception {
        final User byId = userService.findById(message.getSenderId());
        Message save = messageService.save(message, message.getSenderId());

        message.setReceiverId(save.getSenderId().getId());
        message.setText(save.getText());
        message.setUserFullName(byId.getFullName());
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getSenderId()),
                "/queue/messages",
                message
        );
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getReceiverId()),
                "/queue/messages",
                message
        );

//        Thread.sleep(1000); // simulated delay
//        return new MessageTest(message.getText());
    }


//    @MessageMapping("/hello")
//    @SendTo("/user/messages")
//    public MessageTest sendMessage(MessageDto message) throws Exception {
//        messageService.save(message);
//        messagingTemplate.convertAndSendToUser(
//                String.valueOf(message.getUserId()),
//              "/user/messages",
//                message
//        );
////        Thread.sleep(1000); // simulated delay
//        return new MessageTest(message.getText());
//    }
}
