package liga.medical.messageanalyzer.core;

import liga.medical.messageanalyzer.core.configuration.RabbitConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Service application.
 */
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan("liga.medical.messageanalyzer")
@Import(RabbitConfiguration.class)
public class MessageAnalyzerServiceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MessageAnalyzerServiceApplication.class, args);
    }
}
