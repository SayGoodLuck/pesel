package dev.konstantin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class PeselAppRunner {
  public static void main(String[] args) {
      SpringApplication.run(PeselAppRunner.class, args);
  }
    /*@Bean
    public UserServiceDaoImpl userServiceDAO() {
        return new UserServiceDaoImpl(new InMemoryUserInfoDao());
    }*/
}
