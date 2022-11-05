package ru.netology.i18n;

import ru.netology.entity.Country;

public class LocalizationServiceMock implements LocalizationService {

    private String value;

    @Override
    public String locale(Country country) {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
