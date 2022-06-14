package uz.pdp.websocketdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.websocketdemo.dto.MessageDto;
import uz.pdp.websocketdemo.model.ChatRoom;
import uz.pdp.websocketdemo.model.Message;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.repository.ChatRoomRepository;
import uz.pdp.websocketdemo.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatroomService chatroomService;

    @Autowired
    UserService userService;

    public Message save(MessageDto messageDto, UUID senderId) {

        Message message = new Message();

        Long chatId = chatroomService.findChatId(messageDto.getReceiverId(), senderId);

        User byId = userService.findById(messageDto.getReceiverId());
        message.setSenderId(byId);
        message.setText(messageDto.getText());
        message.setChatId(chatId);
        return messageRepository.save(message);
    }

    public List<MessageDto> getAllMessage() {
        List<Message> all = messageRepository.findAll();
        List<MessageDto> messageDtos = new ArrayList<>();
        for (Message message : all) {
            messageDtos.add(new MessageDto(message.getText(), message.getSenderId().getId()));
        }
        return messageDtos;
    }

    public List<MessageDto> getAllMessageByReceiverId(UUID id, User currentUser) {
        ChatRoom bySenderIdAndReceiverId = chatRoomRepository.findBySenderIdAndReceiverId(currentUser.getId(), id);
        List<MessageDto> messageDtoList = new ArrayList<>();
        for (Message message : messageRepository.findByChatId(bySenderIdAndReceiverId.getChatId())) {
            MessageDto messageDto = new MessageDto();
            messageDto.setUserFullName(message.getSenderId().getFullName());
            messageDto.setText(message.getText());
            messageDtoList.add(messageDto);
        }
        return messageDtoList;

    }
}
