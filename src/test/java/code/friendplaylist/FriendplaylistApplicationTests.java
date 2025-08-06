package code.friendplaylist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import code.friendplaylist.client.PlaylistClient;
import code.friendplaylist.client.TracksClient;

@SpringBootTest
class FriendplaylistApplicationTests {

	@MockBean
	private OAuth2AuthorizedClientService authorizedClientService;

	@MockBean
	private ClientRegistrationRepository clientRegistrationRepository;

	@MockBean
	private PlaylistClient playlistClient;

	@MockBean
	private TracksClient tracksClient;

	@Test
	void contextLoads() {
	}

}
