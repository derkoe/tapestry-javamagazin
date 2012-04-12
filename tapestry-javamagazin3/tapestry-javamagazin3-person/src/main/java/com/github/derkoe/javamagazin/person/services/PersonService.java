package com.github.derkoe.javamagazin.person.services;

import java.util.Collection;

public interface PersonService
{
    Person newPerson(Person person);

    Person changePerson(Person person);

    Collection<Person> list();

    Person getById(String id);

    boolean deleteById(String id);
}
