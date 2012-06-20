package com.github.derkoe.javamagazin.services.person;

public interface PersonService
{
    Iterable<Person> list();
    
    Person getById(String id);

    Person newPerson(Person person);

    Person changePerson(Person person);

    boolean deleteById(String id);
}
