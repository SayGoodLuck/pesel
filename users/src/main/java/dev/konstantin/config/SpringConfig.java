package dev.konstantin.config;

import dev.konstantin.dao.InMemoryUserServiceDao;
import dev.konstantin.dao.UserServiceDao;
import dev.konstantin.dao.UserServiceDaoImpl;
import dev.konstantin.repository.UserRepository;
import dev.konstantin.service.PeselService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  @Bean
  public PeselService getPeselService() {
    return new PeselService();
  }

//  @Bean
//  public UserServiceDaoImpl userServiceDaoImpl() {
//    return UserServiceDaoImpl(new InMemoryUserServiceDao());
//  }
//
//  @Bean
//  public InMemoryUserServiceDao inMemoryUserServiceDao() {
//    return new InMemoryUserServiceDao();
//  }
}
