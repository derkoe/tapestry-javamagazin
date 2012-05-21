package com.github.derkoe.javamagazin.pages;

import static org.apache.tapestry5.EventConstants.SUCCESS;
import static org.apache.tapestry5.EventConstants.FAILURE;

import javax.inject.Inject;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import com.github.derkoe.javamagazin.person.pages.PersonList;

/**
 * Start page of application tapestry-javamagazin2.
 */
public class Index
{
    @Inject
    private Block newPersonBlock;

    @Property
    @Persist
    private int counter;

    @OnEvent("new")
    Block newPerson()
    {
        return newPersonBlock;
    }

    @OnEvent(SUCCESS)
    Class<PersonList> newPersonCreated()
    {
        return PersonList.class;
    }

    @OnEvent(FAILURE)
    Block newPersonFailure()
    {
        return newPersonBlock;
    }
}
