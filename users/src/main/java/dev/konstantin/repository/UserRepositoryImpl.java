package dev.konstantin.repository;

import dev.konstantin.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static dev.konstantin.config.Const.USER_ID;
import static dev.konstantin.config.Const.USER_TABLE;

public class UserRepositoryImpl implements UserRepository {

  @Autowired private JdbcTemplate jdbcTemplate;



  @Override
  public void save(UserInfo userInfo) {
    jdbcTemplate.update(
        "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)",
        userInfo.getId(),
        userInfo.getName(),
        userInfo.getLastname(),
        userInfo.getEmail(),
        userInfo.getGender(),
        userInfo.getBirthday());
  }

  @Override
  public void deleteById(String id) {}

  @Override
  public UserInfo findById(String id) {
    return null;
  }

  @Override
  public UserInfo findByEmail(String email) {
    return null;
  }

  @Override
  public List<UserInfo> findAll() {
    return null;
  }

  @Override
  public boolean isUserExist(String pesel) {
    return false;
  }
}
