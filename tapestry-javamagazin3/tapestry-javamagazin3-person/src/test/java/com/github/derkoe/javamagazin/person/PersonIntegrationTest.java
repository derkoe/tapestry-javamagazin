package com.github.derkoe.javamagazin.person;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class PersonIntegrationTest extends SeleniumTestCase
{
    @Test
    public void checkFieldsArePresent()
    {
        open("/PersonEditorTestPage");
        assertTextPresent("First Name", "Last Name", "Date Of Birth");
    }

    @Test
    public void checkValidation()
    {
        open("/PersonEditorTestPage");
        click("id=submit_0");
        assertTextPresent("First Name may not be null", "Last Name may not be null");
    }
    
    @Test
    public void checkPersonCreated()
    {
        open("/PersonEditorTestPage");
        type("name=firstName", "Herbert");
        type("name=lastName", "Testname");
        type("name=dateOfBirth", "5/8/1956");
        select("name=country", "value=AT");
        clickAndWait("id=submit_0");
        assertFalse(isTextPresent("There is no data to display."), "Empty message is displayed.");
        assertTextPresent("Herbert", "Testname", "Austria");
    }
}
