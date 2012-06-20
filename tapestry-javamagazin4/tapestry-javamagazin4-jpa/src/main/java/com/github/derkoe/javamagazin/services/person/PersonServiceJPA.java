package com.github.derkoe.javamagazin.services.person;

import org.apache.tapestry5.jpa.annotations.CommitAfter;

public interface PersonServiceJPA extends PersonService
{
    @CommitAfter
    Person newPerson(Person person);

    @CommitAfter
    Person changePerson(Person person);

    @CommitAfter
    boolean deleteById(String id);
}
