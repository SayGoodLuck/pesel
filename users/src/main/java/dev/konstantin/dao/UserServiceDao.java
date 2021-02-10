package dev.konstantin.dao;

import dev.konstantin.entity.UserInfo;
import dev.konstantin.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public interface UserServiceDao {

  Optional<UserInfo> get(String id);

  List<UserInfo> getAll();

  void save(UserInfo userInfo);

  UserInfo findByEmail(String email);

  UserInfo findByPesel(String pesel);
}
