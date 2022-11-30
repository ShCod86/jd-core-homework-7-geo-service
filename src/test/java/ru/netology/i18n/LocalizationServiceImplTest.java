package ru.netology.i18n;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LocalizationServiceImplTest {


    @BeforeEach
    public void setUp() {
        System.out.println("Test started.");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Test complete.");
    }

    @ParameterizedTest
    @MethodSource("methodSource")
    void locale(Country country, String expected) {
        LocalizationServiceImpl local = new LocalizationServiceImpl();
        String result = local.locale(country);
        assertEquals(expected, result);

    }
    public static Stream<Arguments> methodSource() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome")
        );

    }
}