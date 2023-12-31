package likelion.univ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableJpaAuditing
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class LikelionClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikelionClientApplication.class, args);
	}

}
