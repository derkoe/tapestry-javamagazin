package com.github.derkoe.javamagazin.person.components;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.corelib.base.AbstractTextField;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@SupportsInformalParameters
@Import(library = {"datepicker/bootstrap-datepicker.js", "datepicker/locales/bootstrap-datepicker.de.js"},
    stylesheet = "datepicker/datepicker.css")
public class DatePicker extends AbstractTextField
{
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String formatString;
    
    @Inject
    private JavaScriptSupport scriptSupport;

    @Inject
    private Locale locale;

    private SimpleDateFormat dateFormat;

    SimpleDateFormat getDateFormat()
    {
        if (dateFormat != null)
        {
            return dateFormat;
        }
        
        dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, locale);

        String pattern = dateFormat.toPattern();

        String revised = pattern.replaceAll("([^y])yy$", "$1yyyy");

        dateFormat = new SimpleDateFormat(revised);

        return dateFormat;
    }

    String defaultFormatString()
    {
        return getDateFormat().toPattern().toLowerCase();
    }

    public FieldTranslator<Date> getDefaultTranslate()
    {
        return new FieldTranslator<Date>()
        {
            public Class<Date> getType()
            {
                return Date.class;
            }

            public String toClient(Date value)
            {
                return getDateFormat().format(value);
            }

            public Date parse(String input) throws ValidationException
            {
                try
                {
                    return getDateFormat().parse(input);
                }
                catch (ParseException e)
                {
                    throw new ValidationException("date-value-not-parseable");
                }
            }

            public void render(MarkupWriter writer)
            {
            }
        };
    }
    
    @Override
    protected void writeFieldTag(MarkupWriter writer, String value)
    {
        writer.element("input",

            "type", "date",

            "name", getControlName(),

            "id", getClientId(),

            "value", value,

            "size", getWidth());

        scriptSupport.addScript("jQuery('#%s').datepicker({language: '%s', weekStart: 1, autoclose: true, format:'%s'});",
            getClientId(),
            locale.getLanguage(),
            formatString);
    }
 
    final void afterRender(MarkupWriter writer)
    {
        writer.end(); // input
    }
}