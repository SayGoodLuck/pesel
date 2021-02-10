package dev.konstantin;

import dev.konstantin.config.Gender;
import dev.konstantin.config.IncorrectPeselException;
import dev.konstantin.config.PeselInfo;
import dev.konstantin.service.PeselService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PeselServiceTest {
  private PeselService peselService = new PeselService();

  private static Stream<Arguments> provideStringsForDecodeTest() {
    return Stream.of(
        Arguments.of("88020344588", "1988-02-03", "FEMALE"), // 1900-2000
        Arguments.of("56122837552", "1956-12-28", "MALE"), // 1900-2000
        Arguments.of("96272717591", "2096-07-27", "MALE"), // 2000-2100
        Arguments.of("15811057109", "1815-01-10", "FEMALE"), // 1815
        Arguments.of("15441089903", "2115-04-10", "FEMALE"), // 2115
        Arguments.of("62713026108", "2262-11-30", "FEMALE"), // 2262
        Arguments.of("46831354011", "1846-03-13", "MALE"), // 1846
        Arguments.of("00452350414", "2100-05-23", "MALE"), // 2100
        Arguments.of("00252304110", "2000-05-23", "MALE"), // 2000
        Arguments.of("80093023106", "1980-09-30", "FEMALE") // 1980
        );
  }

  @ParameterizedTest
  @MethodSource("provideStringsForDecodeTest")
  void decodeTest(String pesel, LocalDate expectedBirthday, Gender gender) {
    PeselInfo peselInfo = peselService.decode(pesel);

    Assertions.assertEquals(expectedBirthday, peselInfo.getBirthday());
    Assertions.assertEquals(gender, peselInfo.getGender());
    Assertions.assertEquals(pesel, peselInfo.getPesel());
  }

  private static Stream<Arguments> provideStringsForExceptionWhenPeselRunWithNo11Digits() {
    return Stream.of(
        Arguments.of("11111"),
        Arguments.of("31243243243242323"),
        Arguments.of("435456543"),
        Arguments.of("34567897654334"),
        Arguments.of("42325"),
        Arguments.of("3"),
        Arguments.of("33"),
        Arguments.of("33333333333333333333333"),
        Arguments.of("435678987"),
        Arguments.of("5465433645"));
  }

  @ParameterizedTest
  @MethodSource("provideStringsForExceptionWhenPeselRunWithNo11Digits")
  void exceptionWhenPeselRunWithNo11Digits(String pesel) {
    assertThatThrownBy(
            () -> {
              peselService.decode(pesel);
            })
        .isInstanceOf(IncorrectPeselException.class)
        .hasMessageContaining("Pesel contains no 11 digits");
  }

  private static Stream<Arguments> provideStringsForExceptionWhenPeselRunWithSymbols() {
    return Stream.of(
        Arguments.of("880203445a8"),
        Arguments.of("770924367^9"),
        Arguments.of("1$581057109"),
        Arguments.of("7709a4367^9"),
        Arguments.of("770924&'7^9"),
        Arguments.of("770924367^9"),
        Arguments.of("7#)924367^9"),
        Arguments.of("77092&367^9"),
        Arguments.of("770-24367^9"),
        Arguments.of("770924367=9"));
  }

  @ParameterizedTest
  @MethodSource("provideStringsForExceptionWhenPeselRunWithSymbols")
  void exceptionWhenPeselRunWithSymbols(String pesel) {
    assertThatThrownBy(
            () -> {
              peselService.decode(pesel);
            })
        .isInstanceOf(IncorrectPeselException.class)
        .hasMessageContaining("Pesel contains some symbols");
  }

  private static Stream<Arguments> provideStringsForCheckInvalidCheckSum() {
    return Stream.of(
        Arguments.of("88020344589"),
        Arguments.of("77092436727"),
        Arguments.of("56122837551"),
        Arguments.of("96272717599"),
        Arguments.of("15811057108"),
        Arguments.of("15441089906"),
        Arguments.of("62713026105"),
        Arguments.of("48091468466"),
        Arguments.of("78031327367"),
        Arguments.of("98070971714"));
  }

  @ParameterizedTest
  @MethodSource("provideStringsForCheckInvalidCheckSum")
  void checkInvalidCheckSum(String pesel) {
    assertThatThrownBy(
            () -> {
              peselService.decode(pesel);
            })
        .isInstanceOf(IncorrectPeselException.class)
        .hasMessageContaining("Invalid checksum");
  }
}
