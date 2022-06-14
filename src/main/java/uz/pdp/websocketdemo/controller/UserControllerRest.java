package uz.pdp.websocketdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.websocketdemo.dto.UserDto;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.services.UserService;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserControllerRest {

    @Autowired
    UserService userService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    SimpUserRegistry userRegistry;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUser();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserId() {
        Set<SimpUser> users = userRegistry.getUsers();
        for (SimpUser user : users) {
            System.out.println(user.getName());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User byEmail = userService.findUserName(authentication.getName());
        UserDto userDto = new UserDto(byEmail.getFullName(), byEmail.getId());

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{senderId}/{receiverId}/{status}")
    public ResponseEntity<?> statusChange(@PathVariable UUID receiverId, @PathVariable UUID senderId, @PathVariable String status) {

        User byId = userService.findById(senderId);
        UserDto userDto = new UserDto();
        userDto.setFullName(byId.getFullName());
        status = status.equals("online") ? "🟩" : "✍️";
        userDto.setStatus(status);
        userDto.setUserId(senderId);
        System.err.println(byId.getFullName() + " " + status);
        List<UserDto> users = new ArrayList<>();
        List<UserDto> allUser = userService.findAllUser();
        for (UserDto dto : allUser) {
            if (dto.getUserId().equals(userDto.getUserId())) {
                users.add(userDto);
            } else users.add(dto);
        }
        simpMessagingTemplate.convertAndSendToUser(receiverId.toString(), "/status", users);
        return ResponseEntity.ok(null);
    }

}
