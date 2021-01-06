package dev.konstantin.service;

import dev.konstantin.config.IncorrectPeselException;

public class DateConverter {
  public boolean incorrectMonth(int[] pesel) {
    return (pesel[2] * 10 + pesel[3]) % 20 > 12;
  }

  public int decodeYear(int[] pesel) {

    if (pesel[2] == 0 || pesel[2] == 1) {
      return 1900 + 10 * pesel[0] + pesel[1];
    } else if (pesel[2] == 2 || pesel[2] == 3) {
      return 2000 + 10 * pesel[0] + pesel[1];
    } else if (pesel[2] == 4 || pesel[2] == 5) {
      return 2100 + 10 * pesel[0] + pesel[1];
    } else if (pesel[2] == 6 || pesel[2] == 7) {
      return 2200 + 10 * pesel[0] + pesel[1];
    }
    return 1800 + 10 * pesel[0] + pesel[1];
  }

  public int decodeMonth(int[] pesel) {
    if (incorrectMonth(pesel)) {
      throw new IncorrectPeselException("Month not exist. Max value should not exceed 12");
    }
    if (pesel[2] < 2) {
      return 10 * pesel[2] + pesel[3];
    } else if (pesel[2] < 4) {
      return (10 * pesel[2] + pesel[3]) - 20;
    } else if (pesel[2] < 6) {
      return (10 * pesel[2] + pesel[3]) - 40;
    } else if (pesel[2] < 8) {
      return (10 * pesel[2] + pesel[3]) - 60;
    }
    return (10 * pesel[2] + pesel[3]) - 80;
  }

  public int decodeDay(int[] pesel) {
    return 10 * pesel[4] + pesel[5];
  }
}
