package park.waiting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class WaitingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaitingApplication.class, args);
	}

}
