package dev.konstantin.service;

import dev.konstantin.config.PeselInfo;
import dev.konstantin.config.Gender;
import dev.konstantin.config.IncorrectPeselException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class PeselService {

  public PeselInfo decode(String pesel) {

    if (!onlyDigits(pesel)) {
      throw new IncorrectPeselException("Pesel contains some symbols");
    }

    if (!isPeselLength11(pesel)) {
      throw new IncorrectPeselException("Pesel contains no 11 digits");
    }

    int[] peselToDigits = splittingToNumberArray(pesel);

    if (!isCheckSumCorrect(peselToDigits)) {
      throw new IncorrectPeselException("Invalid checksum");
    }

    return new PeselInfo(convertToLocalDate(peselToDigits), checkGender(peselToDigits[9]), pesel);
  }

  private int[] splittingToNumberArray(String pesel) {
    char[] chars = pesel.toCharArray();
    int[] numbers = new int[chars.length];
    for (int i = 0; i < chars.length; i++) {
      numbers[i] = Character.getNumericValue(chars[i]);
    }
    return numbers;
  }

  private boolean isCheckSumCorrect(int[] peselToDigits) {
    // if checksum is ok returns true
    int sumValid =
        peselToDigits[0]
            + 3 * peselToDigits[1]
            + 7 * peselToDigits[2]
            + 9 * peselToDigits[3]
            + peselToDigits[4]
            + 3 * peselToDigits[5]
            + 7 * peselToDigits[6]
            + 9 * peselToDigits[7]
            + peselToDigits[8]
            + 3 * peselToDigits[9]
            + peselToDigits[10];
    return sumValid % 10 == 0;
  }

  private boolean isPeselLength11(String pesel) {
    // if 11 digits returns true
    return pesel.length() == 11;
  }

  private boolean onlyDigits(String pesel) {
    // if ok return true
    // else false
    for (int i = 0; i < pesel.length(); i++) {
      if (!Character.isDigit(pesel.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  private LocalDate convertToLocalDate(int[] peselToDigits) {
    try {
      DateConverter dateConverter = new DateConverter();
      return LocalDate.of(
          dateConverter.decodeYear(peselToDigits),
          dateConverter.decodeMonth(peselToDigits),
          dateConverter.decodeDay(peselToDigits));
    } catch (DateTimeException dateEx) {
      throw new IncorrectPeselException("invalid date");
    }
  }

  private Gender checkGender(final int peselDigit) {
    return peselDigit % 2 == 0 ? Gender.FEMALE : Gender.MALE;
  }
}
