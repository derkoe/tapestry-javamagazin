package testapp.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.SubModule;

import com.github.derkoe.javamagazin.person.PersonModule;

@SubModule(PersonModule.class)
public class AppModule
{
    public static void contributeApplicationDefaults(
        MappedConfiguration<String, Object> configuration)
    {
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
    }
}
