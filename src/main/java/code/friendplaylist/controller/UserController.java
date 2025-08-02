package code.friendplaylist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import code.friendplaylist.dto.UserDto;
import code.friendplaylist.dto.UserSearchResultDTO;
import code.friendplaylist.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(OAuth2AuthenticationToken authentication) {
        try {
            UserDto userDto = userService.getCurrentUser(authentication);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/auth-status")
    public ResponseEntity<Boolean> getAuthStatus(@AuthenticationPrincipal OAuth2User spotifyUser) {
        return ResponseEntity.ok(spotifyUser != null);
    }

    @GetMapping("/search")
    public List<UserSearchResultDTO> searchUsers(@RequestParam("q") String query) {
        return userService.searchUsers(query);
    }
    
}