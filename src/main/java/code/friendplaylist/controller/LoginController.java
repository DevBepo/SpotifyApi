package code.friendplaylist.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/")
    public String login(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String nome = principal.getAttribute("display_name");
            model.addAttribute("username", nome);

            return "home";
        } else {
            return "login";

        }
    } 
}