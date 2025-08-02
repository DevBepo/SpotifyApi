package code.friendplaylist.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import code.friendplaylist.domain.User;
import code.friendplaylist.dto.UserDto;
import code.friendplaylist.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final OAuth2AuthorizedClientService authorizedClientService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDto processUserLogin(OAuth2User spotifyUser) {
        if (spotifyUser == null) {
            logger.error("OAuth2User is null. Cannot process user login.");
            throw new IllegalArgumentException("Spotify user information is not available.");
        }

        logger.info("Processing login for Spotify user: {}", spotifyUser.getName());

        // Busca o usuário pelo ID do Spotify. Se não encontrar, cria um novo.
        User user = userRepository.findById(spotifyUser.getName())
                .orElseGet(() -> {
                    logger.info("User not found in database. Creating new user for id: {}", spotifyUser.getName());
                    return new User(); // Cria uma nova instância
                });

        // Atualiza os dados do usuário com as informações mais recentes do Spotify
        updateUserFromSpotify(user, spotifyUser);

        // Salva o usuário (seja ele novo ou atualizado)
        User savedUser = userRepository.save(user);
        logger.info("User successfully saved/updated with ID: {}", savedUser.getId());

        return toDto(savedUser);
    }

    /**
     * Mapeia os dados do OAuth2User para a nossa entidade User.
     * Este método é responsável por preencher ou atualizar os campos.
     */
    private void updateUserFromSpotify(User user, OAuth2User spotifyUser) {
        user.setId(spotifyUser.getName()); // Garante que o ID está setado
        user.setDisplayName(spotifyUser.getAttribute("display_name"));
        user.setEmail(spotifyUser.getAttribute("email"));
        user.setCountry(spotifyUser.getAttribute("country"));
        user.setSpotifyUri(spotifyUser.getAttribute("uri"));

        // Extrai o número de seguidores de forma segura
        Map<String, Object> followersMap = spotifyUser.getAttribute("followers");
        if (followersMap != null && followersMap.get("total") instanceof Integer) {
            user.setFollowers((Integer) followersMap.get("total"));
        }

        // Extrai a URL da imagem de forma segura
        List<Map<String, Object>> images = spotifyUser.getAttribute("images");
        if (images != null && !images.isEmpty()) {
            Map<String, Object> firstImage = images.get(0);
            if (firstImage != null && firstImage.get("url") instanceof String) {
                user.setImageUrl((String) firstImage.get("url"));
            }
        }
    }


    private UserDto toDto(User user) {
        return new UserDto(
            user.getId(),
            user.getDisplayName(),
            user.getEmail(),
            user.getCountry(),
            user.getFollowers(),
            user.getImageUrl(),
            user.getSpotifyUri()
        );
    }

public UserDto getCurrentUser(OAuth2AuthenticationToken authentication) {
    OAuth2User spotifyUser = authentication.getPrincipal();
    return processUserLogin(spotifyUser);
}

 public String getAccessToken(OAuth2AuthenticationToken authentication) {
            OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
            authentication.getAuthorizedClientRegistrationId(),
            authentication.getName()
        );
        if (client == null || client.getAccessToken() == null) {
            throw new IllegalStateException("Cannot find access token for the user.");
        }
        return client.getAccessToken().getTokenValue();
    }

    /**
     * Lógica de negócio para buscar usuários.
     * @param query O termo da busca.
     * @return Uma lista de DTOs com os resultados da busca.
     */
    public List<UserSearchResultDTO> searchUsers(String query) {
        // 1. Chama o repositório para obter os dados brutos
        List<User> usersFound = userRepository.findByDisplayNameContainingIgnoreCase(query);

        // 2. Processa os dados (neste caso, mapeando para um DTO)
        return usersFound.stream()
                .map(user -> new UserSearchResultDTO(
                        user.getId(),
                        user.getDisplayName(),
                        user.getImageUrl()
                ))
                .collect(Collectors.toList());
    }

}


    
