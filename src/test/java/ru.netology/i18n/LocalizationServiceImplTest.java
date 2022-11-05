package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class LocalizationServiceImplTest {

    @Test
    @DisplayName("Тест для проверки возвращаемого текста, если страна Россия")
    void localeIfRussia() {
        LocalizationServiceImpl test1 = new LocalizationServiceImpl();
        String testString = "Добро пожаловать";
        Assertions.assertEquals(testString, test1.locale(RUSSIA));
    }

    @Test
    @DisplayName("Тест для проверки возвращаемого текста, если страна не Россия")
    void localeIfOtherThanRussia() {
        LocalizationServiceImpl test2 = new LocalizationServiceImpl();
        String testString = "Welcome";
        Assertions.assertEquals(testString, test2.locale(USA));
    }
}
