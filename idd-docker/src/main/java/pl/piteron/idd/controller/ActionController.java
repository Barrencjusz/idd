package pl.piteron.idd.controller;

import java.net.URI;
import java.util.List;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.piteron.idd.Action;
import pl.piteron.idd.EmbeddedActions;

/**
 * @author piotr.larysz
 */
@CrossOrigin
@RestController
public class ActionController {

    @Autowired
    private URI actionsURI;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Action> get() {
        return this.restTemplate.getForEntity(actionsURI, EmbeddedActions.class).getBody().getEmbedded().getActions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody Action action) {
        this.restTemplate.postForEntity(actionsURI, action, Void.class);
    }

    @Setter
    @Configuration
    @ConfigurationProperties("service.rest-repository")
    static class Config {

        private static final String ACTIONS = "actions";

        private String scheme;

        private String host;

        private int port;

        @Bean
        public URI actionsURI() {
            return UriComponentsBuilder.newInstance().scheme(scheme).host(host).path(ACTIONS).port(port).build().toUri();
        }
    }
}
