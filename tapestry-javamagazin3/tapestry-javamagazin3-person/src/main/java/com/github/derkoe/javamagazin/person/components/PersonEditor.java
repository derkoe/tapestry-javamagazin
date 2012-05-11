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

    /**
     * Binding the zone parameter will cause the form submission to be handled
     * as an Ajax request that updates the
     * indicated zone. Often a Form will update the same zone that contains it.
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String zone;

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
