package testapp.pages;

import java.util.Collection;

import javax.inject.Inject;

import com.github.derkoe.javamagazin.person.services.Person;
import com.github.derkoe.javamagazin.person.services.PersonService;

public class PersonEditorTestPage
{
    @Inject
    private PersonService personService;

    public Collection<Person> getPersonList()
    {
        return personService.list();
    }
}
