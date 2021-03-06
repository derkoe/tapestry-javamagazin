package com.github.derkoe.javamagazin.pages;

import static com.github.derkoe.javamagazin.services.person.CountrySelectHelpers.selectModel;
import static com.github.derkoe.javamagazin.services.person.CountrySelectHelpers.valueEncoder;

import java.util.Locale;

import javax.inject.Inject;

import org.apache.tapestry5.FieldValidator;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.services.PropertyEditContext;
import org.apache.tapestry5.services.PropertyOutputContext;

import com.github.derkoe.javamagazin.services.person.Country;
import com.github.derkoe.javamagazin.services.person.CountryService;

public class PersonBlocks
{
    @Inject
    private CountryService countryService;

    @Inject
    private Locale locale;

    @Environmental
    private PropertyOutputContext outputContext;

    @Environmental
    private PropertyEditContext editContext;

    @InjectComponent
    private Select countrySelect;

    public PropertyEditContext getEditContext()
    {
        return editContext;
    }

    public FieldValidator<?> getCountrySelectValidator()
    {
        return editContext.getValidator(countrySelect);
    }

    public SelectModel getCountrySelectModel()
    {
        return selectModel(countryService.list(), locale);
    }

    public ValueEncoder<Country> getCountryEncoder()
    {
        return valueEncoder();
    }

    public String getCountryName()
    {
        Country country = (Country) outputContext.getPropertyValue();
        return country.getName(locale);
    }
}