package com.github.derkoe.javamagazin.pages.person;

import static org.apache.tapestry5.EventConstants.PREPARE;
import static org.apache.tapestry5.EventConstants.SUBMIT;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import com.github.derkoe.javamagazin.services.person.Person;
import com.github.derkoe.javamagazin.services.person.PersonService;

public class PersonEdit
{
    @Inject
    private PersonService personService;

    @Valid
    @Property
    private Person person;

    @PageActivationContext
    private String personId;

    @OnEvent(PREPARE)
    void loadPerson()
    {
        person = personService.getById(personId);
    }

    @OnEvent(SUBMIT)
    Object changePerson()
    {
        personService.changePerson(person);

        return PersonList.class;
    }
}
