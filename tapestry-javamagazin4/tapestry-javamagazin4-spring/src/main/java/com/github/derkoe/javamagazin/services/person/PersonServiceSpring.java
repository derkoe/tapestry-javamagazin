package com.github.derkoe.javamagazin.services.person;

import org.springframework.stereotype.Service;

@Service
public class PersonServiceSpring implements PersonService
{
    private final PersonRepository personRepository;

    public PersonServiceSpring(final PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    public Person newPerson(Person person)
    {
        if (person.getId() != null)
            throw new IllegalArgumentException("New person must not contain id");

        person.generateId();

        return personRepository.save(person);
    }

    public Person changePerson(Person person)
    {
        Person dbPerson = personRepository.findOne(person.getId());

        if (dbPerson != null)
        {
            dbPerson.setFirstName(person.getFirstName());
            dbPerson.setLastName(person.getLastName());
            dbPerson.setDateOfBirth(person.getDateOfBirth());
            dbPerson.setCountry(person.getCountry());
            return personRepository.save(dbPerson);
        }

        throw new ArrayIndexOutOfBoundsException("Person not found in list");
    }

    public Iterable<Person> list()
    {
        return personRepository.findAll();
    }

    public Person getById(String id)
    {
        return personRepository.findOne(id);
    }

    public boolean deleteById(String id)
    {
        personRepository.delete(id);
        return true;
    }
}
