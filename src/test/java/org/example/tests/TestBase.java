package org.example.tests;

import org.example.appmanager.ApplicationManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.Browser;

import java.io.IOException;

public class TestBase {

    protected final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));


    @Before
    public void setUp() throws IOException {
        app.init();
    }

    @After
    public void tearDown() {
        app.stop();
    }

}
