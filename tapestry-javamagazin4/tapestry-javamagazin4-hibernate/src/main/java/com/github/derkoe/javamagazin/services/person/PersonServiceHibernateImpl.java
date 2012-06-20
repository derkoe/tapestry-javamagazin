package com.github.derkoe.javamagazin.services.person;

import org.hibernate.Session;

/**
 * {@link PersonService} implementation based on Hibernate using the {@link Session} interface.
 */
public class PersonServiceHibernateImpl implements PersonServiceHibernate
{
    private final Session session;

    public PersonServiceHibernateImpl(final Session session)
    {
        this.session = session;
    }

    public Iterable<Person> list()
    {
        return session.createQuery("FROM Person").list();
    }

    public Person getById(String id)
    {
        return (Person) session.get(Person.class, id);
    }

    public Person newPerson(Person person)
    {
        if (person.getId() != null)
            throw new IllegalArgumentException("New person must not contain id");

        person.generateId();

        session.save(person);

        return person;
    }

    public Person changePerson(Person person)
    {
        Person dbPerson = (Person) session.get(Person.class, person.getId());

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
            session.createQuery("DELETE FROM Person p WHERE p.id = :id").setParameter("id", id).executeUpdate();
        return deleted > 0;
    }
}
