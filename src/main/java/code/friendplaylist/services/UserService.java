package code.friendplaylist.services;

import java.util.List;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import code.friendplaylist.dto.UserDto;
import code.friendplaylist.dto.UserSearchResultDTO;

@Service
public interface UserService {

    UserDto processUserLogin(OAuth2User spotifyUser);

    UserDto getCurrentUser(OAuth2AuthenticationToken authentication);

    String getAccessToken(OAuth2AuthenticationToken authentication);

    List<UserSearchResultDTO> searchUsers(String query);
}
