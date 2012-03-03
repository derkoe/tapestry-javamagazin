package com.github.derkoe.javamagazin.pages.person;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;

import com.github.derkoe.javamagazin.services.person.Person;
import com.github.derkoe.javamagazin.services.person.PersonService;

public class PersonList
{
    @Inject
    private PersonService personService;

    @Property
    private Person person;

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
}
