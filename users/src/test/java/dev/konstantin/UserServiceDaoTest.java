package dev.konstantin;

import dev.konstantin.dao.UserServiceDaoImpl;
import dev.konstantin.entity.UserInfo;
import dev.konstantin.exceptions.IncorrectUserInfoException;
import dev.konstantin.dao.InMemoryUserServiceDao;
//import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.util.Assert;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceDaoTest {

  private final UserServiceDaoImpl userServiceDaoImpl = new UserServiceDaoImpl(new InMemoryUserServiceDao());

  private static Stream<Arguments> provideToStringsForSaveUserTest() {
    return Stream.of(
        Arguments.of("89112275656", "Kazimierz", "Ostrowski", "Kazimierz.Ostrowski@gmail.com"),
        Arguments.of("98101975671", "Klaudiusz", "Krupa", "Klaudiusz.Krupa@rambler.com"),
        Arguments.of("55121697314", "Alfred", "Wojciechowski", "Alfred.Wojciechowski@inbox.ru"),
        Arguments.of("87122717751", "Martin", "Wysocki", "Martin.Wysocki@outlook.com"),
        Arguments.of("93072493739", "Mieszko", "Szewczyk", "paraamaatara24@yahoo.com"),
        Arguments.of("78010292461", "Krystyna", "Krawczyk", "AlfaOneGo89.01.05@icloud.com"),
        Arguments.of("93121247429", "Dorota", "Wysocka", "qmkarami828@reyzor.com"),
        Arguments.of("64040475529", "Eliza", "Woźniak", "pahtsham.saleem@gmailvn.net"),
        Arguments.of("99051758482", "Weronika", "Szymczak", "2simo-dimmax@getthemp3.com"),
        Arguments.of("60033018241", "Faustyna", "Walczak", "ltarkanha@szwdfz2.com"));
  }

  @ParameterizedTest
  @MethodSource("provideToStringsForSaveUserTest")
  public void saveUserTest(String pesel, String name, String lastname, String email) {
    userServiceDaoImpl.saveUser(pesel, name, lastname, email);

    UserInfo userInfo = userServiceDaoImpl.findByPesel(pesel);

    Assertions.assertEquals(userInfo.getEmail(), email);
    //Assert.assertEquals(10, userServiceDAO.getSize());

    Assertions.assertEquals(pesel, userInfo.getPesel());
    Assertions.assertEquals(name, userInfo.getName());
    Assertions.assertEquals(lastname, userInfo.getSurname());
    Assertions.assertEquals(email, userInfo.getEmail());
  }

  private static Stream<Arguments> provideStringsToUpdateUserTest() {
    return Stream.of(
        Arguments.of(
            "64040475529",
            "Eliza",
            "Woźniak",
            "pahtsham.saleem@gmailvn.net",
            "Monika",
            "Poźniak",
            "pahtsham.saleem@gmail.net"));
  }

  private static Stream<Arguments> provideStringsToRunWhenStringContainsNotAlphabetic() {
    return Stream.of(
        Arguments.of("89112275656", "Kazi432mierz", "Os<>trowski", "Kazimierz.Ostrowski@gmail.com"),
        Arguments.of("98101975671", "Klaudi123456789usz", "651", "Klaudiusz.Krupa@rambler.com"),
        Arguments.of("55121697314", "Alfr?/ed", "Wojcie#chowski", "Alfred.Wojciechowski@inbox.ru"),
        Arguments.of("87122717751", "M!artin", "Wy^socki", "Martin.Wysocki@outlook.com"),
        Arguments.of("93072493739", "Mies%zko", "Szew'czyk", "paraamaatara24@yahoo.com"),
        Arguments.of("78010292461", "Kr)(ystyna", "Kraw'czyk", "AlfaOneGo89.01.05@icloud.com"),
        Arguments.of("93121247429", "Do7rota", "Wy1socka", "qmkarami828@reyzor.com"),
        Arguments.of("64040475529", "El@iza", "W+źniak", "pahtsham.saleem@gmailvn.net"),
        Arguments.of("99051758482", "Wero{nika", "Szy=mczak", "2simo-dimmax@getthemp3.com"),
        Arguments.of("60033018241", "Faust[]yna", "$#%@#", "ltarkanha@szwdfz2.com"));
  }

  @ParameterizedTest
  @MethodSource("provideStringsToRunWhenStringContainsNotAlphabetic")
  void runWhenStringContainsNotAlphabetic(String pesel, String name, String surname, String email) {
    assertThatThrownBy(
            () -> {
              userServiceDaoImpl.saveUser(pesel, name, surname, email);
            })
        .isInstanceOf(IncorrectUserInfoException.class)
        .hasMessageContaining(
            "Name or surname contains another symbols. Please, enter only letters!");
  }

  private static Stream<Arguments> provideStringsToRunWhenEmailIsIncorrect() {
    return Stream.of(
        Arguments.of("89112275656", "Kazimierz", "Ostrowski", "Kazimierz.Ostrowskigmail.com"),
        Arguments.of("98101975671", "Klaudiusz", "Krupa", "1458374557"),
        Arguments.of("55121697314", "Alfred", "Wojciechowski", "233inbox"),
        Arguments.of("87122717751", "Martin", "Wysocki", ""),
        Arguments.of("93072493739", "Mieszko", "Szewczyk", "@yahoo.com"),
        Arguments.of("78010292461", "Krystyna", "Krawczyk", "@"),
        Arguments.of("93121247429", "Dorota", "Wysocka", "@JFDSKFBS"),
        Arguments.of("64040475529", "Eliza", "Woźniak", "534354gmailvnnet"),
        Arguments.of("99051758482", "Weronika", "Szymczak", "$)(*&^%$#@"),
        Arguments.of("60033018241", "Faustyna", "Walczak", "ltarkanhaszwdfz2com"));
  }

  @ParameterizedTest
  @MethodSource("provideStringsToRunWhenEmailIsIncorrect")
  void runWhenEmailIsIncorrect(String pesel, String name, String surname, String email) {
    assertThatThrownBy(
            () -> {
              userServiceDaoImpl.saveUser(pesel, name, surname, email);
            })
        .isInstanceOf(IncorrectUserInfoException.class)
        .hasMessageContaining("Email is invalid. try again");
  }

  @Test
  public void getUserByIdTest() {
    userServiceDaoImpl.saveUser("89112275656", "Kazimierz", "Ostrowski", "Kazimierz.Ostrowski@gmail.com");
    UserInfo userInfo = userServiceDaoImpl.findByPesel("89112275656");
    Assertions.assertEquals("Kazimierz", userInfo.getName());
    Assertions.assertEquals("89112275656", userInfo.getPesel());
    Assertions.assertEquals("Kazimierz.Ostrowski@gmail.com", userInfo.getEmail());
  }

  @Test
  public void getUserByEmailTest() {
    userServiceDaoImpl.saveUser("89112275656", "Kazimierz", "Ostrowski", "Kazimierz.Ostrowski@gmail.com");
    UserInfo userInfo = userServiceDaoImpl.findByEmail("Kazimierz.Ostrowski@gmail.com");
    Assertions.assertEquals("Kazimierz", userInfo.getName());
    Assertions.assertEquals("89112275656", userInfo.getPesel());
    Assertions.assertEquals("Kazimierz.Ostrowski@gmail.com", userInfo.getEmail());
  }

  @Test
  public void addUserTest() {
    boolean isUserCreated = userServiceDaoImpl.saveUser("89112275656", "Kazimierz", "Ostrowski", "Kazimierz.Ostrowski@gmail.com");
    Assertions.assertTrue(isUserCreated);
  }

  @org.junit.jupiter.api.Test
  public void editUserTest() {
    //todo
  }

  @Test
  public void deleteUserTest() {
    userServiceDaoImpl.saveUser("89112275656", "Kazimierz", "Ostrowski", "Kazimierz.Ostrowski@gmail.com");
    boolean isUserDeleted = userServiceDaoImpl.deleteUser("89112275656");
    Assertions.assertTrue(isUserDeleted);
  }
}