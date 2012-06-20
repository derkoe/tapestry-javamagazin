package com.github.derkoe.javamagazin.services.person;

import javax.persistence.EntityManager;

/**
 * {@link PersonService} implementation using JPA 2.0.
 */
public class PersonServiceJPAImpl implements PersonServiceJPA
{
    private final EntityManager entityManager;

    public PersonServiceJPAImpl(final EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public Iterable<Person> list()
    {
        return entityManager.createQuery("FROM Person", Person.class).getResultList();
    }

    public Person getById(String id)
    {
        return entityManager.find(Person.class, id);
    }

    public Person newPerson(Person person)
    {
        if (person.getId() != null)
            throw new IllegalArgumentException("New person must not contain id");

        person.generateId();

        entityManager.persist(person);

        return person;
    }

    public Person changePerson(Person person)
    {
        Person dbPerson = entityManager.find(Person.class, person.getId());

        if (dbPerson != null)
        {
            dbPerson.setFirstName(person.getFirstName());
            dbPerson.setLastName(person.getLastName());
            dbPerson.setDateOfBirth(person.getDateOfBirth());
            dbPerson.setCountry(person.getCountry());
            return dbPerson;
        }

        throw new ArrayIndexOutOfBoundsException("Person not found in list");
    }

    public boolean deleteById(String id)
    {
        int deleted =
            entityManager.createQuery("DELETE FROM Person p WHERE p.id = :id").setParameter("id", id).executeUpdate();
        return deleted > 0;
    }
}
