package code.friendplaylist.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import code.friendplaylist.domain.User;
import code.friendplaylist.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto processUserLogin(OAuth2User spotifyUser) {
        logger.info("Processing user login for spotifyUser: {}", spotifyUser.getAttributes());
        User user = toDomain(spotifyUser);
        logger.info("Mapped to domain User: {}", user);
        UserDto userDto = toDto(user);
        logger.info("Mapped to UserDto: {}", userDto);
        return userDto;
        // Lembrar de colocar a data que o usuario logou
    }

    private User toDomain(OAuth2User spotifyUser) {
        User user = new User();
        user.setId(spotifyUser.getAttribute("id"));
        user.setDisplayName(spotifyUser.getAttribute("display_name"));
        user.setEmail(spotifyUser.getAttribute("email"));
            return user;
        }


    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setDisplayName(user.getDisplayName());
        dto.setEmail(user.getEmail());
            return dto;
        }
    }

    
