package uz.pdp.websocketdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.websocketdemo.model.ChatRoom;

import java.util.Optional;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {

    @Query(nativeQuery = true,
    value = "select chat_id\n" +
            "from chat_room\n" +
            "where sender_id = :senderId\n" +
            "and receiver_id = :receiverId")
    Long chatId(UUID receiverId, UUID senderId);

    ChatRoom findBySenderIdAndReceiverId(UUID senderId, UUID receiverId);

    Optional<ChatRoom> findById(UUID uuid);

    @Query(nativeQuery = true, value = "select nextval('hibernate_sequence')")
    Long selectNextHS();
}
