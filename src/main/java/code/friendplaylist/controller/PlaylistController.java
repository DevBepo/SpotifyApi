package code.friendplaylist.controller;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaylistController {

    @GetMapping("/playlists")
    public String minhasPlaylists(Model model, @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {

        
        String accessToken = authorizedClient.getAccessToken().getTokenValue();

        
        System.out.println("Token de Acesso Spotify: " + accessToken);

        
        List<String> playlistsSimuladas = List.of("Rock", "Indie 2010s", "MPB Anos 90");
        model.addAttribute("playlists", playlistsSimuladas);

        return "playlists";
    }
}
