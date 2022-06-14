package uz.pdp.websocketdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto {
    private String text;
    private UUID receiverId;
    private UUID senderId;
    private String userFullName;
    private LocalDateTime createdAt;
    private boolean isRead;


    public MessageDto(String text, UUID receiverId) {
        this.text = text;
        this.receiverId = receiverId;
    }


}
