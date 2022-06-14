package uz.pdp.websocketdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.websocketdemo.model.ChatRoom;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.repository.ChatRoomRepository;

import java.util.UUID;

@Service
public class ChatroomService {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserService userService;

    public Long findChatId(UUID receiverId, UUID senderId) {
        ChatRoom chatRoom = chatRoomRepository.findBySenderIdAndReceiverId(senderId,receiverId);
        if (chatRoom == null) {
            User receivedUser = userService.findById(receiverId);
            User senderUser = userService.findById(senderId);
            if (receiverId.equals(senderId)) {
                chatRoomRepository.save(new ChatRoom(senderUser, receivedUser));
            } else {
                Long aLong = chatRoomRepository.selectNextHS();
                chatRoomRepository.save(new ChatRoom(senderUser, receivedUser, aLong));
                chatRoomRepository.save(new ChatRoom(receivedUser, senderUser, aLong));
            }
        }
        return chatRoomRepository.findBySenderIdAndReceiverId(senderId,receiverId).getChatId();
    }
}
