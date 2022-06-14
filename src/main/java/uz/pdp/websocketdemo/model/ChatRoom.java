package uz.pdp.websocketdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

//    @GeneratedValue(generator="my_seq")
//    @SequenceGenerator(name="my_seq",sequenceName="hibernate_sequence", allocationSize=1)
    private Long chatId;

    public ChatRoom(User sender, User receiver, Long chatId) {
        this.sender = sender;
        this.receiver = receiver;
        this.chatId = chatId;
    }
    public ChatRoom(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
}
