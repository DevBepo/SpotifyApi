package code.friendplaylist.services;

import org.springframework.security.oauth2.core.user.OAuth2User;

import code.friendplaylist.dto.UserDto;

public interface UserService {
    UserDto processUserLogin(OAuth2User spotifyUser);
}
