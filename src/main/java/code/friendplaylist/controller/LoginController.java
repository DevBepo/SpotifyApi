package code.friendplaylist.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {


    @GetMapping("/")
    public String login(Model modelUser, @AuthenticationPrincipal OAuth2User spotifyUser) {
        if (spotifyUser != null) {
            String nome = spotifyUser.getAttribute("display_name");
            modelUser.addAttribute("username", nome);

            return "home";
        } else {
            return "login";

        }
    } 
}