package dev.konstantin.dao;

import dev.konstantin.entity.UserInfo;
import dev.konstantin.exceptions.UserInfoNotFoundException;
import dev.konstantin.repository.UserRepository;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserServiceDao {

  private ConcurrentHashMap<String, UserInfo> map = new ConcurrentHashMap<>();

  public boolean save(UserInfo userInfo) {
    Objects.requireNonNull(userInfo);
    map.put(userInfo.getPesel(), userInfo);
    return true;
  }

  public void delete(String pesel) {
    map.remove(pesel);
  }

  public UserInfo findByEmail(String email) {

    try {
      UserInfo userInfo = new UserInfo();
      for (String keys : map.keySet()) {
        if (map.get(keys).getEmail() == email) {
          return map.get(keys);
        }
      }

    } catch (UserInfoNotFoundException ex) {
      ex.getMessage();
    }

    return null;
  }

  public UserInfo findByPesel(String pesel) {
    return map.get(pesel);
  }



  public boolean deleteById(String pesel) {
    map.remove(pesel);
    return true;
  }
}
