package com.github.derkoe.javamagazin.person;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.services.BeanBlockContribution;
import org.apache.tapestry5.services.BeanBlockSource;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.DisplayBlockContribution;
import org.apache.tapestry5.services.EditBlockContribution;
import org.apache.tapestry5.services.LibraryMapping;

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
    }

    @Contribute(BeanBlockSource.class)
    public static void provideBeanBlocks(Configuration<BeanBlockContribution> configuration)
    {
        configuration.add(new EditBlockContribution("country", "person/PersonBlocks", "editCountry"));
        configuration.add(new DisplayBlockContribution("country", "person/PersonBlocks", "displayCountry"));
    }

    @Contribute(ComponentClassResolver.class)
    public static void addLibraryMapping(Configuration<LibraryMapping> configuration)
    {
        configuration.add(new LibraryMapping("person", "com.github.derkoe.javamagazin.person"));
    }
}
