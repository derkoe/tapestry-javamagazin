package com.github.derkoe.javamagazin.pages.person;

import static org.apache.tapestry5.EventConstants.SUCCESS;

import org.apache.tapestry5.annotations.OnEvent;

public class PersonNew
{
    @OnEvent(SUCCESS)
    Object newPerson()
    {
        return PersonList.class;
    }
}
