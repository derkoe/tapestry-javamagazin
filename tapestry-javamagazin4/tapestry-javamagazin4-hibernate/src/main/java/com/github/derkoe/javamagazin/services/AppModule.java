package com.github.derkoe.javamagazin.services;

import org.apache.tapestry5.hibernate.HibernateConfigurer;
import org.apache.tapestry5.hibernate.HibernateEntityPackageManager;
import org.apache.tapestry5.hibernate.HibernateSessionSource;
import org.apache.tapestry5.hibernate.HibernateTransactionAdvisor;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.hibernate.dialect.H2Dialect;

import com.github.derkoe.javamagazin.services.person.PersonServiceHibernate;
import com.github.derkoe.javamagazin.services.person.PersonServiceHibernateImpl;

@SubModule(BaseModule.class)
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        binder.bind(PersonServiceHibernate.class, PersonServiceHibernateImpl.class);
    }

    @Contribute(HibernateSessionSource.class)
    public static void configureHibernate(OrderedConfiguration<HibernateConfigurer> configuration)
    {
        configuration.add("person", new HibernateConfigurer()
        {
            public void configure(org.hibernate.cfg.Configuration configuration)
            {
                configuration
                    .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:h2:mem:test")
                    .setProperty("hibernate.connection.username", "sa")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.dialect", H2Dialect.class.getName())
                    .setProperty("hibernate.generate_statistics", "true");
            }
        });
    }

    @Contribute(HibernateEntityPackageManager.class)
    public static void providePackages(Configuration<String> configuration)
    {
        configuration.add("com.github.derkoe.javamagazin.services.person");
    }

    @Match("PersonServiceHibernate")
    public static void adviseTransactionally(HibernateTransactionAdvisor advisor, MethodAdviceReceiver receiver)
    {
        advisor.addTransactionCommitAdvice(receiver);
    }
}
