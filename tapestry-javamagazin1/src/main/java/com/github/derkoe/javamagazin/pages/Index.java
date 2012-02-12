package com.github.derkoe.javamagazin.pages;

import java.util.Date;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.Messages;
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

    @Component(parameters = {"event=" + EVENT_RESET_COUNTER, "context=literal:0"})
    private EventLink resetCounterLink;

    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @Persist
    private int clickCount;

    @InjectComponent
    private Zone zone;

    @Inject
    private AlertManager alertManager;

    @Inject
    private Messages messages;

    public Date getCurrentTime()
    {
        return new Date();
    }

    public String getTapestryVersion()
    {
        return tapestryVersion;
    }

    public String getClickCountMessage()
    {
        return messages.format("clickCountMessage", clickCount);
    }

    Index onActionFromIncrement()
    {
        alertManager.info(messages.format("counter-increased", ++clickCount));

        return this;
    }

    @OnEvent(component = "incrementAjax")
    Zone incAjax()
    {
        alertManager.info(messages.format("counter-increased-ajax", ++clickCount));

        return zone;
    }

    @OnEvent(EVENT_RESET_COUNTER)
    void setCounter(int value)
    {
        alertManager.info(messages.format("counter-reset-to", value));
        
        clickCount = value;
    }
}
