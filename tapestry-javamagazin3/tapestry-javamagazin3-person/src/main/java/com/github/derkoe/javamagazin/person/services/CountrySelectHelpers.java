package com.github.derkoe.javamagazin.person.services;

import java.util.Collection;
import java.util.Locale;

import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;

public final class CountrySelectHelpers
{
    private static ValueEncoder<Country> INSTANCE;

    public static ValueEncoder<Country> valueEncoder()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new ValueEncoder<Country>()
            {
                public Country toValue(String clientValue)
                {
                    return clientValue == null ? null : new Country(clientValue);
                }

                public String toClient(Country value)
                {
                    return value == null ? "" : value.isoCode;
                }
            };
        }

        return INSTANCE;
    }

    public static SelectModel selectModel(Collection<Country> countries, Locale locale)
    {
        OptionModel[] optionModels = new OptionModel[countries.size()];
        int i = 0;
        for (Country country : countries)
        {
            optionModels[i++] = new OptionModelImpl(country.getName(locale), country);
        }

        return new SelectModelImpl(optionModels);
    }

    private CountrySelectHelpers()
    {
    }
}