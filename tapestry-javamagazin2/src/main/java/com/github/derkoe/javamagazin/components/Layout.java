package com.github.derkoe.javamagazin.components;

import static java.util.Arrays.asList;

import java.util.Collection;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;

/**
 * Layout component for pages of application tapestry-javamagazin1.
 */
@Import(stack = "bootstrap", stylesheet = "layout.css")
public class Layout
{
    /**
     * The page title, for the <title> element and the <h1> element.
     */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    @Inject
    private ComponentResources resources;

    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;

    public String getClassForPageName()
    {
        return resources.getPageName().equals(pageName)
                ? "active"
                : null;
    }

    public Collection<String> getPageNames()
    {
        return asList("person/List");
    }
}
