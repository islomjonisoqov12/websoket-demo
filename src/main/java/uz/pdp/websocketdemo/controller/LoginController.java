package uz.pdp.websocketdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.repository.UserRepository;
import uz.pdp.websocketdemo.services.UserService;
import uz.pdp.websocketdemo.services.UserServiceDetail;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {


    @Autowired
    UserServiceDetail userDetailsService;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    //    @GetMapping("register")
//    public String register(Model model) {
//        UserRegisterDto userRegisterDto = new UserRegisterDto();
//        model.addAttribute("user", userRegisterDto);
//        return "register";
//    }
//
//    @PostMapping("register")
//    public String registered(@ModelAttribute UserRegisterDto userRegisterDto){
//        userService.saveNewUser(userRegisterDto);
//        userDetailsService.loadUserByUsername(userRegisterDto.getEmail());
//        return "redirect:/api/chat";
//    }
    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage(@AuthenticationPrincipal User currentUser) {
        return "/";
    }


}
