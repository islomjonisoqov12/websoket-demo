package uz.pdp.websocketdemo.lestener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import uz.pdp.websocketdemo.dto.MessageDto;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.repository.UserRepository;
import uz.pdp.websocketdemo.services.MessageService;
import uz.pdp.websocketdemo.services.UserService;

import java.util.Optional;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);


    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    SimpUserRegistry simpUserRegistry;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.error("Received a new web socket connection");
        System.out.println("user :" + event.getUser());
        String username = event.getUser().getName();

        for (String contact : userRepository.getContacts(username)) {
            messagingTemplate.convertAndSendToUser(contact, "/contact","changed");
        }

        Optional<User> userOptional = userRepository.findByUsername(username);
        User currentUser = null;

        for (SimpUser user : simpUserRegistry.getUsers()) {
            System.out.println(user.getName());
        }

        if (userOptional.isPresent()) {

            User user = userOptional.get();
            currentUser = user;

            MessageDto messageDto = new MessageDto();
            messageDto.setText(user.getFullName()+" online");
            messageDto.setSenderId(user.getId());
            messageDto.setReceiverId(currentUser.getId());
            messageDto.setUserFullName(currentUser.getFullName());


//        String username = (String) headerAccessor.getSessionAttributes().get("username");


            messagingTemplate.convertAndSendToUser(
                    "c45ab69a-057f-43ae-95b4-e8e7300c4e84",
                    "/queue/messages",
                    messageDto
            );

        }
    }


    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event ) {


//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        logger.info("user disconnect");
        String username = event.getUser().getName();


        Optional<User> userOptional = userRepository.findByUsername(username);
        User currentUser = null;

        for (String contact : userRepository.getContacts(username)) {
            messagingTemplate.convertAndSendToUser(contact, "/contact","changed");
        }

        if (userOptional.isPresent()) {

            User user = userOptional.get();
            currentUser = user;

            MessageDto messageDto = new MessageDto();
            messageDto.setText(user.getFullName()+" offline");
            messageDto.setSenderId(user.getId());
            messageDto.setReceiverId(currentUser.getId());
            messageDto.setUserFullName(currentUser.getFullName());


//        String username = (String) headerAccessor.getSessionAttributes().get("username");


            messagingTemplate.convertAndSendToUser(
                    "c45ab69a-057f-43ae-95b4-e8e7300c4e84",
                    "/queue/messages",
                    messageDto
            );

        }
    }


//    @EventListener
//    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//
//        if(username != null) {
//            logger.error("User Disconnected : " + username);
//            System.out.println("User Disconnected : " + username);

//            ChatMessage chatMessage = new ChatMessage();
//            chatMessage.setType(ChatMessage.MessageType.LEAVE);
//            chatMessage.setSender(username);
//
//            messagingTemplate.convertAndSend("/user/"+username+"/chatRoom", chatMessage);
//        }
//    }

}