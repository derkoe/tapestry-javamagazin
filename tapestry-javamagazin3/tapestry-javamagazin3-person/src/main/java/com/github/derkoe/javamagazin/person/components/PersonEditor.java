package com.github.derkoe.javamagazin.person.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.github.derkoe.javamagazin.person.services.Person;

public class PersonEditor
{
    @Parameter(required = true)
    @Property
    private Person person;
}
