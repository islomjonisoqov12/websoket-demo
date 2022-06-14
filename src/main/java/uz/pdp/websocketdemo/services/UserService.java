package uz.pdp.websocketdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import uz.pdp.websocketdemo.dto.UserDto;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.repository.UserRepository;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SimpUserRegistry userRegistry;

    public UUID findUserIdByUsername(String username) {
        final UUID userIdByUsername = userRepository.getUserIdByUsername(username);
        if (userIdByUsername != null)
            return userIdByUsername;
        throw new NotFoundException(username);
    }

    public User findById(UUID id) {
        final Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    public User findUserName(String username) {
        final Optional<User> byId = userRepository.findByUsername(username);
        return byId.orElse(null);
    }

    public List<UserDto> findAllUser() {
        List<UserDto> list = new ArrayList<>();
        final List<User> all = userRepository.findAll();
        for (User user : all) {
            String status;
            long count = userRegistry.getUsers().stream().filter(simpUser -> simpUser.getName().equals(user.getUsername())).count();
//            long count = userRegistry.getUsers().stream().map(SimpUser::getName).filter(s -> s.equals(user.getUsername())).count();
//            status = count > 0 ? "online" : "offline";
            status = count > 0 ? "🟩" : "🟥";
            list.add(new UserDto(user.getFullName(), user.getId(), status));
        }
        return list;
    }
}
