package com.github.derkoe.javamagazin.services.person;

import static java.util.Arrays.asList;
import java.util.Collection;

public class CountryService
{
    public Collection<Country> list()
    {
        return asList(new Country("DE"), new Country("AT"), new Country("GB"));
    }
}
