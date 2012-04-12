package com.github.derkoe.javamagazin.person.pages;

import static org.apache.tapestry5.EventConstants.PREPARE;
import static org.apache.tapestry5.EventConstants.SUCCESS;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.PageActivationContext;

import com.github.derkoe.javamagazin.person.services.Person;
import com.github.derkoe.javamagazin.person.services.PersonService;

public class PersonEdit
{
    @Inject
    private PersonService personService;

    private Person person;

    @PageActivationContext
    private String personId;

    @OnEvent(PREPARE)
    void loadPerson()
    {
        person = personService.getById(personId);
    }

    @OnEvent(SUCCESS)
    Object changePerson()
    {
        personService.changePerson(person);

        return PersonList.class;
    }

    public Person getPerson()
    {
        return person;
    }
}
