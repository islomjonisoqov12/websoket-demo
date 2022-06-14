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
public class Message {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Long chatId;

    private String text;

    @ManyToOne
    private User senderId;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", chatId='" + chatId + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
