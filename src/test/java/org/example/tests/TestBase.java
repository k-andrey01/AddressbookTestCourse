package org.example.tests;

import org.example.appmanager.ApplicationManager;
import org.example.model.GroupData;
import org.example.model.Groups;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestBase {

    Logger logger = LoggerFactory.getLogger("TestBase");

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.getDbHelper().getGroups();
            Groups uiGroups = app.getGroupHelper().getAllGroups();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map(g -> new GroupData().withId(g.getId()).withGroupName(g.getGroupName()))
                    .collect(Collectors.toSet())));
        }
    }


    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));
    }

    @AfterMethod
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }
}
