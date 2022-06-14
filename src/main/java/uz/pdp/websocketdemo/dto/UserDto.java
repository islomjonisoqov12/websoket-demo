package uz.pdp.websocketdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String fullName;
    private UUID userId;
    private String status;



    public UserDto(String fullName, UUID userId) {
        this.fullName = fullName;
        this.userId = userId;
    }
}
