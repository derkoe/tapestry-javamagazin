package com.github.derkoe.javamagazin.person.pages;

import static org.apache.tapestry5.EventConstants.PREPARE;
import static org.apache.tapestry5.EventConstants.SUCCESS;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.OnEvent;

import com.github.derkoe.javamagazin.person.services.Person;
import com.github.derkoe.javamagazin.person.services.PersonService;

public class PersonNew
{
    @Inject
    private PersonService personService;

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
