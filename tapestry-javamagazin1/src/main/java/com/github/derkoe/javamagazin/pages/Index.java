package com.github.derkoe.javamagazin.pages;

import java.util.Date;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;

/**
 * Start page of application tapestry-javamagazin1.
 */
public class Index
{
    private static final String EVENT_RESET_COUNTER = "resetcounter";

    @Component(parameters = {"event=" + EVENT_RESET_COUNTER, "context=literal:1"})
    private EventLink resetCounterLink;

    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @Persist
    @Property
    private int clickCount;

    @InjectComponent
    private Zone zone;

    @Inject
    private AlertManager alertManager;

    public Date getCurrentTime()
    {
        return new Date();
    }

    public String getTapestryVersion()
    {
        return tapestryVersion;
    }

    Index onActionFromIncrement()
    {
        alertManager.info("Zahl erh�ht auf " + (++clickCount));

        return this;
    }

    @OnEvent(component = "incrementAjax")
    Zone incAjax()
    {
        alertManager.info("Zahl erh�ht auf " + (++clickCount) + " (mit Ajax)");

        return zone;
    }

    @OnEvent(EVENT_RESET_COUNTER)
    void setCounter(int value)
    {
        alertManager.info("Zahl zur�ckgesetzt auf " + value);
        
        clickCount = value;
    }
}
