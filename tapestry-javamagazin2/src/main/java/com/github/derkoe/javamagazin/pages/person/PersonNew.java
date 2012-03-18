package com.github.derkoe.javamagazin.pages.person;

import static com.github.derkoe.javamagazin.services.person.CountrySelectHelpers.selectModel;
import static com.github.derkoe.javamagazin.services.person.CountrySelectHelpers.valueEncoder;
import static org.apache.tapestry5.EventConstants.PREPARE;
import static org.apache.tapestry5.EventConstants.SUCCESS;

import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.OnEvent;

import com.github.derkoe.javamagazin.services.person.Country;
import com.github.derkoe.javamagazin.services.person.CountryService;
import com.github.derkoe.javamagazin.services.person.Person;
import com.github.derkoe.javamagazin.services.person.PersonService;

public class PersonNew
{
    @Inject
    private PersonService personService;

    @Inject
    private CountryService countryService;

    @Inject
    private Locale locale;

    @Valid
    private Person person;

    @OnEvent(PREPARE)
    void createPersonInstance()
    {
        person = new Person();
    }

    @OnEvent(SUCCESS)
    Object newPerson()
    {
        personService.newPerson(person);

        return PersonList.class;
    }

    public Person getPerson()
    {
        return person;
    }

    public SelectModel getCountrySelectModel()
    {
        return selectModel(countryService.list(), locale);
    }

    public ValueEncoder<Country> getCountryEncoder()
    {
        return valueEncoder();
    }
}
