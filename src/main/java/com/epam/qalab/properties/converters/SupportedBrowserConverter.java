package com.epam.qalab.properties.converters;

import com.epam.qalab.enumeration.SupportedBrowsers;

public class SupportedBrowserConverter {
    public static SupportedBrowsers valueOfWebBrowser(String webBrowser) {
        return switch (webBrowser){
            case "local_chrome"-> SupportedBrowsers.LOCAL_CHROME;
            case "local_firefox"-> SupportedBrowsers.LOCAL_FIREFOX;
            default -> throw new IllegalArgumentException("No appropriate browser found");
        };
    }
}
