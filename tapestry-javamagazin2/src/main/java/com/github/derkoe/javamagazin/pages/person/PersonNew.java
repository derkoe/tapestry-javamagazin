package com.github.derkoe.javamagazin.pages.person;

import static org.apache.tapestry5.EventConstants.PREPARE;
import static org.apache.tapestry5.EventConstants.SUCCESS;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.tapestry5.annotations.OnEvent;

import com.github.derkoe.javamagazin.services.person.Person;
import com.github.derkoe.javamagazin.services.person.PersonService;

public class PersonNew
{
    @Inject
    private PersonService personService;

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
}
