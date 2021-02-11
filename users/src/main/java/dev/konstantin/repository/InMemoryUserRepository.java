package dev.konstantin.repository;

import dev.konstantin.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {

  private ConcurrentHashMap<String, UserInfo> map = new ConcurrentHashMap<>();

  public List<UserInfo> findAll() {
    if (map.isEmpty()) {
      return null;
    }
    List<UserInfo> users = new ArrayList<>();
    for (UserInfo user : map.values()) {
      users.add(user);
    }
    return users;
  }


  public boolean isUserExist(String pesel) {
    if(map.get(pesel) != null) {
      return true;
    }
    return false;
  }

  public void save(UserInfo userInfo) {
    Objects.requireNonNull(userInfo);
    map.put(userInfo.getPesel(), userInfo);
  }

  public void deleteById(String pesel) {
    map.remove(pesel);
  }

  public UserInfo findByEmail(String email) {

    for (String keys : map.keySet()) {
      if (map.get(keys).getEmail() == email) {
        return map.get(keys);
      }
    }
    return null;
  }

  public UserInfo findById(String pesel) {
    return map.get(pesel);
  }
}
