package com.github.derkoe.javamagazin.services.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.func.Flow;

public class PersonServiceImpl implements PersonService
{
    private List<Person> personList = new ArrayList<Person>();

    public Person newPerson(Person person)
    {
        if (person.getId() != null)
            throw new IllegalArgumentException("New person must not contain id");

        person.generateId();

        personList.add(person);

        return new Person(person);
    }

    public Person changePerson(Person person)
    {
        for(Person dbPerson : personList)
        {
            if(dbPerson.equals(person))
            {
                dbPerson.setFirstName(person.getFirstName());
                dbPerson.setLastName(person.getLastName());
                dbPerson.setDateOfBirth(person.getDateOfBirth());
                return person;
            }
        }

        throw new ArrayIndexOutOfBoundsException("Person not found in list");
    }

    public boolean deletePerson(Person person)
    {
        return personList.remove(person);
    }

    public Collection<Person> list()
    {
        return Collections.unmodifiableList(personList);
    }

    public Person getById(String id)
    {
        for (Person person : personList)
        {
            if (person.getId().equals(id))
            {
                return person;
            }
        }
        return null;
    }
}
