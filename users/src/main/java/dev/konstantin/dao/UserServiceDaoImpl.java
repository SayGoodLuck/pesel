package dev.konstantin.dao;

import dev.konstantin.config.PeselInfo;
import dev.konstantin.entity.UserInfo;
import dev.konstantin.exceptions.IncorrectUserInfoException;
import dev.konstantin.repository.UserRepository;
import dev.konstantin.service.PeselService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserServiceDaoImpl implements UserServiceDao {

  @Autowired
  private PeselService peselService;
  @Autowired
  private UserRepository userRepository;

  private InMemoryUserServiceDao inMemoryUserServiceDao;
  /*public UserServiceDaoImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }*/

  public UserServiceDaoImpl(InMemoryUserServiceDao inMemoryUserServiceDao) {
    this.inMemoryUserServiceDao = inMemoryUserServiceDao;
  }


  public boolean saveUser(String pesel, String name, String surname, String email) {

    PeselInfo peselInfo = peselService.decode(pesel);

    if (!isStringContainOnlyAlphabetic(name) && !isStringContainOnlyAlphabetic(surname)) {
      throw new IncorrectUserInfoException(
          "Name or surname contains another symbols. Please, enter only letters!");
    }

    if (!emailChecker(email)) {
      throw new IncorrectUserInfoException("Email is invalid. try again");
    }

    userRepository.save(
        new UserInfo(pesel, name, surname, email, peselInfo.getGender(), peselInfo.getBirthday()));
    return true;
  }

  public boolean deleteUser(String pesel) {
    userRepository.deleteById(pesel);
    return true;
  }

  public UserInfo findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public UserInfo findByPesel(String pesel) {
    return userRepository.findByPesel(pesel);
  }

  /*public int getSize() {
    return userRepository.getSize();
  }*/

  // if string is ok returns true
  private boolean isStringContainOnlyAlphabetic(String string) {

    for (int i = 0; i < string.length(); i++) {
      if (!Character.isAlphabetic(string.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  // if email is ok returns true
  private boolean emailChecker(String email) {
    return Pattern.matches("^(.+)@(.+)$", email);
    // "^(.+)@(.+)$" simpliest
    // "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$"
  }
}