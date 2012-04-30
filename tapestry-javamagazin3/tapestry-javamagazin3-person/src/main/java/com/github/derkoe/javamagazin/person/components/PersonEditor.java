package com.github.derkoe.javamagazin.person.components;

import static org.apache.tapestry5.EventConstants.PREPARE;
import static org.apache.tapestry5.EventConstants.SUCCESS;

import javax.inject.Inject;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.github.derkoe.javamagazin.person.services.Person;
import com.github.derkoe.javamagazin.person.services.PersonService;

public class PersonEditor
{
    @Parameter
    private String personId;

    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    @Property(write = false)
    private String header;

    private Person person;

    @Inject
    private PersonService personService;

    @OnEvent(PREPARE)
    void loadOrCreatePerson()
    {
        person = personId == null ? new Person() : personService.getById(personId);
    }

    @OnEvent(SUCCESS)
    void save()
    {
        if (personId == null)
            personService.newPerson(person);
        else
            personService.changePerson(person);
    }

    public Person getPerson()
    {
        return person;
    }
}
