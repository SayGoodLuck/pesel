package dev.konstantin.service;

import dev.konstantin.config.PeselInfo;
import dev.konstantin.entity.UserInfo;
import dev.konstantin.exceptions.IncorrectUserInfoException;
import dev.konstantin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends dev.konstantin.service.Service implements UserService {

  private PeselService peselService = new PeselService();
  @Autowired private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void saveUser(String pesel, String name, String lastname, String email) {

    if (pesel == "" || name == "" || lastname == "" || email == "") {
      throw new IncorrectUserInfoException("all fields are required");
    }
    if (isUserExist(pesel)) {
      throw new IncorrectUserInfoException("User already exists");
    }

    PeselInfo peselInfo = peselService.decode(pesel);

    if (!isStringContainOnlyAlphabetic(name) && !isStringContainOnlyAlphabetic(lastname)) {
      throw new IncorrectUserInfoException(
          "Name or lastname contains another symbols. Please, enter only letters!");
    }

    if (!isEmailValid(email)) {
      throw new IncorrectUserInfoException("Email is invalid. Try again");
    }

    userRepository.save(
        new UserInfo(pesel, name, lastname, email, peselInfo.getGender(), peselInfo.getBirthday()));
  }

  public void deleteUser(String pesel) {
    if (!isUserExist(pesel)) {
      throw new IncorrectUserInfoException("user is not found");
    }
    userRepository.deleteById(pesel);
  }

  public UserInfo findUserByEmail(String email) {
    UserInfo userInfo = userRepository.findByEmail(email);
    if (userInfo == null) {
      throw new IncorrectUserInfoException("no user found");
    }
    return userInfo;
  }

  public UserInfo findUserByPesel(String pesel) {
    if (!isUserExist(pesel)) {
      throw new IncorrectUserInfoException("no user found");
    }
    return userRepository.findById(pesel);
  }

  public List<UserInfo> getAllUsers() {
    List<UserInfo> users = userRepository.findAll();
    if (users.isEmpty()) {
      throw new IncorrectUserInfoException("no users found");
    }
    return users;
  }

  public boolean isUserExist(String pesel) {
    if (userRepository.findById(pesel) != null) {
      return true;
    }
    return false;
  }
}
