package blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"blog.*"})
@EnableAutoConfiguration
public class WorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkApplication.class, args);
	}
}
