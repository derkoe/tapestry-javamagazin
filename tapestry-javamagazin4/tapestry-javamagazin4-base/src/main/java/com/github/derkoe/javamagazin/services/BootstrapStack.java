package com.github.derkoe.javamagazin.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.internal.TapestryInternalUtils;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;

public class BootstrapStack implements JavaScriptStack
{
    private StylesheetLink[] stylesheets;

    public BootstrapStack(final AssetSource assetSource)
    {
        stylesheets =
            new StylesheetLink[]
            {
                TapestryInternalUtils.assetToStylesheetLink.map(assetSource
                    .getUnlocalizedAsset("/com/github/derkoe/javamagazin/bootstrap.css")),
                TapestryInternalUtils.assetToStylesheetLink.map(assetSource
                    .getUnlocalizedAsset("/com/github/derkoe/javamagazin/bootstrap-responsive.css"))
            };
    }

    public List<String> getStacks()
    {
        return Collections.emptyList();
    }

    public List<Asset> getJavaScriptLibraries()
    {
        return Collections.emptyList();
    }

    public List<StylesheetLink> getStylesheets()
    {
        return Arrays.asList(stylesheets);
    }

    public String getInitialization()
    {
        return null;
    }
}
