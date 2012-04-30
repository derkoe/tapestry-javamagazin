package com.github.derkoe.javamagazin.pages;

import static org.apache.tapestry5.EventConstants.SUCCESS;

import javax.inject.Inject;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.OnEvent;

import com.github.derkoe.javamagazin.person.pages.PersonList;

/**
 * Start page of application tapestry-javamagazin2.
 */
public class Index
{
    @Inject
    private Block newPersonBlock;

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
}
