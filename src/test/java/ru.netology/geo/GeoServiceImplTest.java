package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {

    @Test
    @DisplayName("Тест, если ip - local")
    void byIpLocal() {
        GeoServiceImpl test1 = new GeoServiceImpl();
        String ip = "127.0.0.1";
        Location local = new Location(null, null, null, 0);
        Assertions.assertSame(local.getCountry(), test1.byIp(ip).getCountry());
    }

    @Test
    @DisplayName("Тест, если ip - Moscow")
    void byIpMoscow() {
        GeoServiceImpl test2 = new GeoServiceImpl();
        String ip = "172.0.32.11";
        Location moscow = new Location("Moscow", Country.RUSSIA, null, 0);
        Assertions.assertSame(moscow.getCountry(), test2.byIp(ip).getCountry());
    }

    @Test
    @DisplayName("Тест, если ip - New-York")
    void byIpNewYork() {
        GeoServiceImpl test3 = new GeoServiceImpl();
        String ip = "96.44.183.149";
        Location newYork = new Location("New York", Country.USA, null, 0);
        Assertions.assertSame(newYork.getCountry(), test3.byIp(ip).getCountry());
    }
}

