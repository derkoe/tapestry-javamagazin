package com.github.derkoe.javamagazin.pages.person;

import static org.apache.tapestry5.EventConstants.*;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;

import com.github.derkoe.javamagazin.services.person.Person;
import com.github.derkoe.javamagazin.services.person.PersonService;

public class PersonNew
{
    @Inject
    private PersonService personService;

    @Property
    private Person person;

    @OnEvent(PREPARE)
    void createPersonInstance()
    {
        person = new Person();
    }

    @OnEvent(SUBMIT)
    Object newPerson()
    {
        personService.newPerson(person);

        return PersonList.class;
    }
}
