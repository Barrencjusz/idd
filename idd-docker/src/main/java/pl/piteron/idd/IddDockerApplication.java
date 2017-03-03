package pl.piteron.idd;

import java.util.List;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
@SpringBootApplication
public class IddDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IddDockerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public Faker faker() {
		return new Faker();
	}

	@GetMapping
	public List<ActionCopy> get() {
		final ResponseEntity<EmbeddedActions> actions = this.restTemplate.getForEntity("http://rest-repository:8080/actions", EmbeddedActions.class);
		return actions.getBody().getEmbedded().getActions();
	}
}
