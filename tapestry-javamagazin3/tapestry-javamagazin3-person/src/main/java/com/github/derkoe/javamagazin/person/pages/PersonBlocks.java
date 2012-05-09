package com.github.derkoe.javamagazin.person.pages;

import static com.github.derkoe.javamagazin.person.services.CountrySelectHelpers.selectModel;
import static com.github.derkoe.javamagazin.person.services.CountrySelectHelpers.valueEncoder;

import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.FieldValidator;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.services.PropertyEditContext;
import org.apache.tapestry5.services.PropertyOutputContext;

import com.github.derkoe.javamagazin.person.components.DatePicker;
import com.github.derkoe.javamagazin.person.services.Country;
import com.github.derkoe.javamagazin.person.services.CountryService;

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

    @InjectComponent
    private DatePicker dateField;

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
    
    public FieldValidator<?> getDateFieldValidator()
    {
        return editContext.getValidator(dateField);
    }

    public FieldTranslator<Date> getDateTranslator()
    {
        return dateField.getDefaultTranslate();
    }
}