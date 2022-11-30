package ru.netology.authorizationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.authorizationservice.repopsitory.UserRepository;
import ru.netology.authorizationservice.repopsitory.UserRepositoryImpl;
import ru.netology.authorizationservice.service.AuthorizationService;

@Configuration
public class JavaConfig {

    @Bean
    public AuthorizationService authorizationService(UserRepository repository) {
        return new AuthorizationService(repository);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

}
