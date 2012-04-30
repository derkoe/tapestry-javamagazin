package com.github.derkoe.javamagazin.person.pages;

import static org.apache.tapestry5.EventConstants.SUCCESS;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

public class PersonEdit
{
    @PageActivationContext
    @Property(write = false)
    private String personId;

    @OnEvent(SUCCESS)
    Object changePerson()
    {
        return PersonList.class;
    }
}
