package com.github.derkoe.javamagazin.components;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class Counter
{
    @Parameter(required = true, allowNull = false, value = "literal:1")
    @Property
    private int value;

    @OnEvent("inc")
    void increse()
    {
        value++;
    }
}
