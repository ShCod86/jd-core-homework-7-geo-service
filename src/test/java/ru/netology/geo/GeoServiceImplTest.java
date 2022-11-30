package ru.netology.geo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    private GeoServiceImpl geos;


    @BeforeAll
    public static void initSuite() {
        System.out.println("Running StringTest");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("StringTest complete.");
    }

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
    void byIpTest(String s, Location expected) {
        geos = new GeoServiceImpl();
        Location result = geos.byIp(s);
        assertThat(expected, samePropertyValuesAs(result));
    }
    @Test
    void byIpIsNull() {
        GeoServiceImpl geo = new GeoServiceImpl();
        Location result = geo.byIp("");
        assertNull(result);
    }

    public static Stream<Arguments> methodSource() {
        return Stream.of(Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null,  0)));
    }

    @Test
    void byCoordinatesTest() {
        assertThrows(RuntimeException.class, ()-> {
           geos.byCoordinates(1, 0);
        });
    }
}