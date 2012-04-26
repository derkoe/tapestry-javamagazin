package com.github.derkoe.javamagazin.person.components;

import org.apache.tapestry5.annotations.Parameter;

import com.github.derkoe.javamagazin.person.services.Person;

public class PersonEditor
{
    @Parameter(required = true)
    private Person person;

    public Person getPerson()
    {
        return person;
    }
}
