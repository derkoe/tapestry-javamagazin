package com.github.derkoe.javamagazin.person;

import java.util.Date;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.BeanBlockContribution;
import org.apache.tapestry5.services.BeanBlockSource;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.DisplayBlockContribution;
import org.apache.tapestry5.services.EditBlockContribution;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.messages.ComponentMessagesSource;

import com.github.derkoe.javamagazin.person.services.Country;
import com.github.derkoe.javamagazin.person.services.CountryService;
import com.github.derkoe.javamagazin.person.services.PersonService;
import com.github.derkoe.javamagazin.person.services.PersonServiceImpl;

public class PersonModule
{
    public static void bind(ServiceBinder binder)
    {
        binder.bind(PersonService.class, PersonServiceImpl.class);
        binder.bind(CountryService.class);
    }

    public static void contributeDefaultDataTypeAnalyzer(MappedConfiguration<Class, String> configuration)
    {
        configuration.add(Country.class, "country");
        configuration.override(Date.class, "jmdate");
    }

    @Contribute(BeanBlockSource.class)
    public static void provideBeanBlocks(Configuration<BeanBlockContribution> configuration)
    {
        configuration.add(new EditBlockContribution("country", "person/PersonBlocks", "editCountry"));
        configuration.add(new DisplayBlockContribution("country", "person/PersonBlocks", "displayCountry"));

        configuration.add(new EditBlockContribution("jmdate", "person/PersonBlocks", "editDate"));
        configuration.add(new DisplayBlockContribution("jmdate", "PropertyDisplayBlocks", "date"));
    }

    @Contribute(ComponentClassResolver.class)
    public static void addLibraryMapping(Configuration<LibraryMapping> configuration)
    {
        configuration.add(new LibraryMapping("person", "com.github.derkoe.javamagazin.person"));
    }

    @Contribute(ComponentMessagesSource.class)
    public static void addI18n(OrderedConfiguration<Resource> configuration,
        @Value("/com/github/derkoe/javamagazin/person/I18n.properties") Resource personI18n)
    {
        configuration.add("person", personI18n);
    }
}
