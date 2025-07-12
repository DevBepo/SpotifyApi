package code.friendplaylist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code.friendplaylist.dto.UserDto;
import code.friendplaylist.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal OAuth2User spotifyUser) {
        if (spotifyUser == null) {
            return ResponseEntity.status(401).build();
        }

        UserDto userDto = userService.processUserLogin(spotifyUser);

        return ResponseEntity.ok(userDto);
    }
}
