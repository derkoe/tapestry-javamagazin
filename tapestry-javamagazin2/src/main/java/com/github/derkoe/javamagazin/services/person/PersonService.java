package com.github.derkoe.javamagazin.services.person;

import java.util.Collection;

public interface PersonService
{
    Person newPerson(Person person);

    Person changePerson(Person person);

    boolean deletePerson(Person person);

    Collection<Person> list();

    Person getById(String id);
}
