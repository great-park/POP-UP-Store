package park.waiting.config;

import lombok.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("{jwt.secret}")
    private String accessTokenSecret;

    @Value("${jwt.access-token-validity-in-seconds}")
    private Long accessTokenValidityInSeconds;

}
