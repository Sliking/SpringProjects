package facebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import facebook.db.UserRepository;
import facebook.domain.User;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			
			log.info("");
			log.info("Populating repository....");
			log.info("");
			//repository.save(new User("miguelpinto", "miguelpinto25@hotmail.com"));
			repository.save(new User("joaquimpimenta", "joaquimpimenta@hotmail.com"));
			repository.save(new User("kimbauer", "kimbauer@gmail.com"));
			repository.save(new User("davidpalmer", "davidpalmer@yahoo.com"));
			repository.save(new User("paulotostas", "paulotostas@gmail.com"));
			
			log.info("Testing repository");
			log.info("");
			log.info("Users found with findAll():");
			log.info("-------------------------------");
			for (User User : repository.findAll()) {
				log.info(User.toString());
			}
            log.info("");

			// fetch an individual User by ID
			User User = repository.findOne(1L);
			log.info("User found with findOne(1L):");
			log.info("--------------------------------");
			log.info(User.toString());
            log.info("");

			// fetch Users by last name
			log.info("User found with findByEmail('miguelpinto25@hotmail.com'):");
			log.info("--------------------------------------------");
			for (User user : repository.findByEmail("miguelpinto25@hotmail.com")) {
				log.info(user.toString());
			}
            log.info("");
            log.info("Test ended");
            log.info("Starting application.....");
		};
	}

}