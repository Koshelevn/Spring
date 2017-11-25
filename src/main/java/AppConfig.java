import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Client client(){
        return new Client("2", "Bill Smith");
    }
}
