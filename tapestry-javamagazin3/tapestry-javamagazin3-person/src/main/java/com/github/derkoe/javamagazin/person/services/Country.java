package com.github.derkoe.javamagazin.person.services;

import java.util.Locale;

public class Country
{
    public final String isoCode;

    public Country(String isoCode)
    {
        super();
        this.isoCode = isoCode;
    }

    public String getName(Locale inLocale)
    {
        return new Locale("", isoCode).getDisplayCountry(inLocale);
    }
}
