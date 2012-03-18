package com.github.derkoe.javamagazin.services;


import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.services.BeanBlockContribution;
import org.apache.tapestry5.services.BeanBlockSource;
import org.apache.tapestry5.services.DisplayBlockContribution;
import org.apache.tapestry5.services.EditBlockContribution;
import org.apache.tapestry5.services.javascript.JavaScriptStack;

import com.github.derkoe.javamagazin.services.person.Country;
import com.github.derkoe.javamagazin.services.person.CountryService;
import com.github.derkoe.javamagazin.services.person.PersonService;
import com.github.derkoe.javamagazin.services.person.PersonServiceImpl;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        binder.bind(PersonService.class, PersonServiceImpl.class);
        binder.bind(CountryService.class);
    }

    public static void contributeFactoryDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
    }

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "de,en");
        configuration.add(SymbolConstants.DEFAULT_STYLESHEET, "classpath:/com/github/derkoe/javamagazin/tapestry.css");
    }

    public static void contributeJavaScriptStackSource(MappedConfiguration<String, JavaScriptStack> configuration)
    {
        configuration.addInstance("bootstrap", BootstrapStack.class);
    }

    public static void contributeDefaultDataTypeAnalyzer(MappedConfiguration<Class, String> configuration)
    {
        configuration.add(Country.class, "country");
    }

    @Contribute(BeanBlockSource.class)
    public static void provideBeanBlocks(Configuration<BeanBlockContribution> configuration)
    {
        configuration.add(new EditBlockContribution("country", "PersonBlocks", "editCountry"));
        configuration.add(new DisplayBlockContribution("country", "PersonBlocks", "displayCountry"));
    }
}
