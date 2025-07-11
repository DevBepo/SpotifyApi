package code.friendplaylist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaylistController {

    @Autowired 
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/playlists")
    public String minhasPlaylists(Model model, OAuth2AuthenticationToken authentication) {

        OAuth2AuthorizedClient authorizedClient = this.authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName());

        String accessToken = authorizedClient.getAccessToken().getTokenValue();

        // futura biblioteca do spotify

        // buscar playlists pelo token

        List<String> playlistsSimuladas = List.of("Rock Clássico", "Indie 2010s", "Foco no Trabalho");
        model.addAttribute("playlists", playlistsSimuladas);

        return "playlists";
    }
}
