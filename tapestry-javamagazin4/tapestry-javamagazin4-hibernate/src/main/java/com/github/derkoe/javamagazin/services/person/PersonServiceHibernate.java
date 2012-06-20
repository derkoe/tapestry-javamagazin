package com.github.derkoe.javamagazin.services.person;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

public interface PersonServiceHibernate extends PersonService
{
    @CommitAfter
    Person newPerson(Person person);

    @CommitAfter
    Person changePerson(Person person);

    @CommitAfter
    boolean deleteById(String id);
}
