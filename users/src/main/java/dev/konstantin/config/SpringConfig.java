package dev.konstantin.config;

import dev.konstantin.dao.InMemoryUserServiceDao;
import dev.konstantin.dao.UserServiceDaoImpl;
import dev.konstantin.service.PeselService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  @Bean
  public PeselService getPeselService() {
    return new PeselService();
  }

  @Bean
  public UserServiceDaoImpl userServiceDAO() {
    return new UserServiceDaoImpl(inMemoryUserServiceDao());
  }

  @Bean
  public InMemoryUserServiceDao inMemoryUserServiceDao() {
    return new InMemoryUserServiceDao();
  }
}
