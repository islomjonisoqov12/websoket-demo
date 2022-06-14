package uz.pdp.websocketdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            User user1 = user.get();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user1.setPassword(passwordEncoder.encode(user1.getPassword()));
            return user1;
        }
        throw new UsernameNotFoundException(username);
//        return userRepository.findByUsername(username).orElseThrow(IllegalStateException::new);
    }


}
