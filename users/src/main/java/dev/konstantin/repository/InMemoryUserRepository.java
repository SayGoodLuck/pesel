package dev.konstantin.repository;

import dev.konstantin.entity.UserInfo;
import dev.konstantin.exceptions.IncorrectUserInfoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {

  private ConcurrentHashMap<String, UserInfo> map = new ConcurrentHashMap<>();

  public List<UserInfo> findAll() {
    if (map.isEmpty()) {
      throw new IncorrectUserInfoException("no users found");
    }
    List<UserInfo> users = new ArrayList<>();
    for (UserInfo user : map.values()) {
      users.add(user);
    }
    return users;
  }

  public boolean isUserExist(String id) {
    if (map.get(id) != null) {
      return true;
    }
    return false;
  }

  public void save(UserInfo userInfo) {
    Objects.requireNonNull(userInfo);
    map.put(userInfo.getId(), userInfo);
  }

  public void deleteById(String id) {
    if (isUserExist(id)) {
      map.remove(id);
    }
  }

  public UserInfo findByEmail(String email) {
    for (String keys : map.keySet()) {
      if (map.get(keys).getEmail() == email) {
        return map.get(keys);
      }
    }
    return null;
  }

  public UserInfo findById(String id) {
    if (isUserExist(id)) {
      return map.get(id);
    }
    return null;
  }
}
