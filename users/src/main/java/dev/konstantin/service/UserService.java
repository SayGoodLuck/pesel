package dev.konstantin.service;

import dev.konstantin.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {

  List<UserInfo> getAllUsers();

  void saveUser(String pesel, String name, String surname, String email);

  void deleteUser(String id);

}
