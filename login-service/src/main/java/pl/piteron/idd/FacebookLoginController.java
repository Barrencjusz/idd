package pl.piteron.idd;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piteron.idd.api.LongLivedToken;

/**
 * @author piotr.larysz
 */
@RestController
@RequestMapping("facebook")
public class FacebookLoginController {

    private static final String CLIENT_ID = "client_id";
    private static final String APP_SECRET = "app_secret";
    private static final String GRAPH_API_URL = "https://graph.facebook.com/v2.8/oauth/access_token";
    private static final String FB_EXCHANGE_TOKEN1 = "fb_exchange_token";
    private static final String FB_EXCHANGE_TOKEN = "fb_exchange_token";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";

    @Value("${spring.social.facebook.app-id}")
    private String appId;

    @Value("${spring.social.facebook.app-secret}")
    private String appSecret;

    @PostMapping(path = "longLivedToken")
    public String longLivedToken(@RequestBody String shortLivedToken) {
        final Facebook facebook = new FacebookTemplate(shortLivedToken);
        URI uri = URIBuilder.fromUri(GRAPH_API_URL)
                .queryParam(CLIENT_ID, appId)
                .queryParam(GRANT_TYPE, FB_EXCHANGE_TOKEN1)
                .queryParam(CLIENT_SECRET, appSecret)
                .queryParam(FB_EXCHANGE_TOKEN, shortLivedToken)
                .build();
        return facebook.restOperations().getForEntity(uri, LongLivedToken.class).getBody().getAccessToken();
    }
}
