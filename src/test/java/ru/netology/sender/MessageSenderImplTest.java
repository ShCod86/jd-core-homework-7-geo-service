package ru.netology.sender;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {


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
    void sendTest(String ip, Location loc, Country country, String greeting) {
        Map<String, String> headerTest = new HashMap<>();
        headerTest.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        GeoServiceImpl geo = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geo.byIp(ip)).thenReturn(loc);

        LocalizationServiceImpl local = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(local.locale(country)).thenReturn(greeting);

        String expected = greeting;

        MessageSenderImpl msg = new MessageSenderImpl(geo, local);

        String result = msg.send(headerTest);
        assertEquals(expected, result);
    }

    public static Stream<Arguments> methodSource() {
        Location rus = new Location("Moscow", Country.RUSSIA, null, 0);
        Location usa = new Location("New York", Country.USA, null, 0);
        return Stream.of(
                Arguments.of( "172.", rus, Country.RUSSIA, "Добро пожаловать"),
                Arguments.of("96.", usa, Country.USA, "Welcome")
        );
    }
}