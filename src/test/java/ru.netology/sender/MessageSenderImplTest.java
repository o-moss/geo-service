package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {

    public static final String IP_ADDRESS_HEADER = "x-real-ip";

    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;


    @Test
    @DisplayName("Тест, если ip относится к российскому сегменту адресов - отправляет русский текст")
    void sendIfRussianIP() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, "172.0.32.11");
        String ipAddress = String.valueOf(headers.get(IP_ADDRESS_HEADER));
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Location locationRussia = new Location("Moscow", Country.RUSSIA, null, 0);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(locationRussia);
        Mockito.when(localizationService.locale(locationRussia.getCountry())).thenReturn("Добро пожаловать");
        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    @DisplayName("Тест, если ip относится к американскому сегменту адресов - отправляет английский текст")
    void sendIfAmericanIP() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, "96.44.183.149");
        String ipAddress = String.valueOf(headers.get(IP_ADDRESS_HEADER));
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Location locationUSA = new Location("New York", Country.USA, null, 0);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(locationUSA);
        Mockito.when(localizationService.locale(locationUSA.getCountry())).thenReturn("Welcome");
        Assertions.assertEquals("Welcome", messageSender.send(headers));
    }
}
