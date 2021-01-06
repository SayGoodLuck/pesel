package dev.konstantin.config;

import java.time.LocalDate;

public class PeselInfo {

  private final LocalDate birthday;
  private final Gender gender;
  private final String pesel;

  public PeselInfo(LocalDate birthday, Gender gender, String pesel) {
    this.birthday = birthday;
    this.gender = gender;
    this.pesel = pesel;
  }

  public String getPesel() {
    return pesel;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public Gender getGender() {
    return gender;
  }
}
