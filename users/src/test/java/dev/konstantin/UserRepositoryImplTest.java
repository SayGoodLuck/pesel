package dev.konstantin;

import dev.konstantin.entity.UserInfo;
import dev.konstantin.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class UserRepositoryImplTest {
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();

    private static Stream<Arguments> provideStringsToSaveToDatabase() {
        return Stream.of(
                //Arguments.of(new UserInfo("89112275656", "Kazimierz", "Ostrowski", "Kazimierz.Ostrowski@gmail.com"))
        );
    }
    @ParameterizedTest
    @MethodSource("provideStringsToSaveToDatabase")
    void saveToDataVase(UserInfo userInfo) {
        userRepository.save(userInfo);
    }
}
