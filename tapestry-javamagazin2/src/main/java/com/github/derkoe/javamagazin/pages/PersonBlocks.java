package com.github.derkoe.javamagazin.pages;

import java.util.Collection;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.tapestry5.FieldValidator;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;
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

    @Component(
        parameters = {"value=editContext.propertyValue", "label=prop:editContext.label",
            "model=countrySelectModel", "encoder=prop:countryEncoder", "validate=prop:countrySelectValidator",
            "clientId=prop:editContext.propertyId"})
    private Select countrySelect;

    public PropertyEditContext getEditContext()
    {
        return editContext;
    }

    public FieldValidator getCountrySelectValidator()
    {
        return editContext.getValidator(countrySelect);
    }

    public SelectModel getCountrySelectModel()
    {
        Collection<Country> countries = countryService.list();
        OptionModel[] optionModels = new OptionModel[countries.size()];
        int i = 0;
        for (Country country : countries)
        {
            optionModels[i++] = new OptionModelImpl(country.getName(locale), country);
        }

        return new SelectModelImpl(optionModels);
    }

    public ValueEncoder<Country> getCountryEncoder()
    {
        return new ValueEncoder<Country>()
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

    public String getCountryName()
    {
        Country country = (Country) outputContext.getPropertyValue();
        return country.getName(locale);
    }
}