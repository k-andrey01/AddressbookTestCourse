package org.example.tests;

import org.example.appmanager.ApplicationManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.Browser;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(Browser.CHROME.browserName());

    @Before
    public void setUp() {
        app.init();
    }

    @After
    public void tearDown() {
        app.stop();
    }

}
