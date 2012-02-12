package com.github.derkoe.javamagazin.services.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PersonServiceImpl implements PersonService
{
    private List<Person> personList;

    public Person newPerson(Person person)
    {
        if (person.getId() != null)
            throw new IllegalArgumentException("New person must not contain id");

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

    public Collection<Person> list(final String sortField)
    {
        List<Person> sortedList = new ArrayList<Person>(personList);
        Collections.sort(sortedList, new Comparator<Person>()
        {
            public int compare(Person o1, Person o2)
            {
                return 0;
            }
        });
        return sortedList;
    }

}
