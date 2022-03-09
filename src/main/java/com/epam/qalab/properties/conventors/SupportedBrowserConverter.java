package com.epam.qalab.properties.conventors;

import com.epam.qalab.enumeration.SupportedBrowsers;

import static com.epam.qalab.enumeration.SupportedBrowsers.LOCAL_CHROME;
import static com.epam.qalab.enumeration.SupportedBrowsers.LOCAL_FIREFOX;


public class SupportedBrowserConverter {
    public static SupportedBrowsers valueOfWebBrowser(String webBrowserName) {


            if (webBrowserName.equals("local_chrome")) {
                return LOCAL_CHROME;
            } else if (webBrowserName.equals("local_firefox")) {
                return LOCAL_FIREFOX;
            } else {
                throw new IllegalArgumentException();
            }

    }
}
