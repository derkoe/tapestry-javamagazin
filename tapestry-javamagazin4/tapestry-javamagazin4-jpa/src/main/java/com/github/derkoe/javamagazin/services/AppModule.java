package com.github.derkoe.javamagazin.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.jpa.EntityManagerSource;
import org.apache.tapestry5.jpa.JpaEntityPackageManager;
import org.apache.tapestry5.jpa.JpaTransactionAdvisor;
import org.apache.tapestry5.jpa.PersistenceUnitConfigurer;
import org.apache.tapestry5.jpa.TapestryPersistenceUnitInfo;
import org.hibernate.dialect.H2Dialect;

import com.github.derkoe.javamagazin.services.person.PersonServiceJPA;
import com.github.derkoe.javamagazin.services.person.PersonServiceJPAImpl;

@SubModule(BaseModule.class)
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        binder.bind(PersonServiceJPA.class, PersonServiceJPAImpl.class);
    }

    @Contribute(EntityManagerSource.class)
    public static void configurePersistenceUnitInfos(MappedConfiguration<String, PersistenceUnitConfigurer> cfg)
    {
        PersistenceUnitConfigurer configurer = new PersistenceUnitConfigurer()
        {
            public void configure(TapestryPersistenceUnitInfo unitInfo)
            {
                unitInfo
                    .addProperty("hibernate.connection.driver_class", "org.h2.Driver")
                    .addProperty("hibernate.connection.url", "jdbc:h2:mem:test")
                    .addProperty("hibernate.connection.username", "sa")
                    .addProperty("hibernate.hbm2ddl.auto", "update")
                    .addProperty("hibernate.dialect", H2Dialect.class.getName());
            }
        };

        cfg.add("person", configurer);
    }

    @Contribute(JpaEntityPackageManager.class)
    public static void providePackages(Configuration<String> configuration)
    {
        configuration.add("com.github.derkoe.javamagazin.services.person");
    }

    @Match("PersonServiceJPA")
    public static void adviseTransactionally(JpaTransactionAdvisor advisor, MethodAdviceReceiver receiver)
    {
        advisor.addTransactionCommitAdvice(receiver);
    }
}
