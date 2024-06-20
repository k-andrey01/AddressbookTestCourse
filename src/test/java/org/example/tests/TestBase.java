package org.example.tests;

import org.example.appmanager.ApplicationManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {

    Logger logger = LoggerFactory.getLogger("TestBase");

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

//    @Before
//    public void logTestStart(Method m, Object[] p) {
//        logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));
//    }
//
//    @After
//    public void logTestStop(Method m) {
//        logger.info("Stop test " + m.getName());
//    }
}
