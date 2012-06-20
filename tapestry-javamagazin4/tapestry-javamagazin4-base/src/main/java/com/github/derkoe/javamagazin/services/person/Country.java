package com.github.derkoe.javamagazin.services.person;

import java.util.Locale;

import javax.persistence.Embeddable;

@Embeddable
public class Country
{
    public String isoCode;

    public Country(String isoCode)
    {
        super();
        this.isoCode = isoCode;
    }

    Country()
    {
        super();
    }

    public String getName(Locale inLocale)
    {
        return new Locale("", isoCode).getDisplayCountry(inLocale);
    }
}
