package com.github.derkoe.javamagazin.person.pages;

import static org.apache.tapestry5.EventConstants.FAILURE;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;

import com.github.derkoe.javamagazin.person.services.Person;
import com.github.derkoe.javamagazin.person.services.PersonService;

public class PersonList
{
    @Inject
    private PersonService personService;

    @SuppressWarnings("unused")
    @Property
    private Person person;

    @Inject
    private Block newPersonBlock;

    @Cached
    public Collection<Person> getPersonList()
    {
        return personService.list();
    }

    @OnEvent("delete")
    void deletePerson(String id)
    {
        personService.deleteById(id);
    }

    @OnEvent("new")
    Block newPerson()
    {
        return newPersonBlock;
    }

    @OnEvent(FAILURE)
    Block newPersonFailure()
    {
        return newPersonBlock;
    }
}
