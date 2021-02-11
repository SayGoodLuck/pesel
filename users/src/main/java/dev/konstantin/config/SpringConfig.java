package dev.konstantin.config;

import dev.konstantin.service.PeselService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static dev.konstantin.config.DBConfig.*;

@Configuration
public class SpringConfig {

  @Bean
  public PeselService getPeselService() {
    return new PeselService();
  }

  @Bean
  public DataSource postgresDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(dbDriver);
    dataSource.setUsername(dbUser);
    dataSource.setPassword(dbPassword);
    dataSource.setUrl(dbUrl);
    return dataSource;
  }
  @Bean
  public void setDataSource
}
