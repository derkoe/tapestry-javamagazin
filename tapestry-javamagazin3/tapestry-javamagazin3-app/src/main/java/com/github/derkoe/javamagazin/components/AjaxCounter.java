package com.github.derkoe.javamagazin.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;

public class AjaxCounter
{
    @Parameter(required = true, allowNull = false, value = "literal:1")
    @Property
    private int value;

    @InjectComponent
    private Zone counterZone;

    @OnEvent("inc")
    Block increase()
    {
        value++;
        return counterZone.getBody();
    }
}
