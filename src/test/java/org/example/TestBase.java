package org.example;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {
    private WebDriver driver;

    @Before
    public void setUp() {
      driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

      driver.get("http://localhost/addressbook/group.php");
      driver.manage().window().setSize(new Dimension(1604, 865));

      login("admin", "secret");
    }

    private void login(String username, String password) {
      driver.findElement(By.name("user")).sendKeys(username);
      driver.findElement(By.name("pass")).sendKeys(password);
      driver.findElement(By.cssSelector("input:nth-child(7)")).click();
    }

    @After
    public void tearDown() {
      driver.quit();
    }

    protected void returnToGroupPage() {
      driver.findElement(By.linkText("group page")).click();
    }

    protected void submitGroupCreation() {
      driver.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
      driver.findElement(By.name("group_name")).click();
      driver.findElement(By.name("group_name")).sendKeys(groupData.getGroupName());
      driver.findElement(By.name("group_header")).click();
      driver.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      driver.findElement(By.name("group_footer")).click();
      driver.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initGroupCreation() {
      driver.findElement(By.name("new")).click();
    }

    protected void goToGroupPage() {
      driver.findElement(By.linkText("groups")).click();
    }

    protected void deleteSelectedGroups() {
      driver.findElement(By.name("delete")).click();
    }

    protected void selectGroup() {
      driver.findElement(By.name("selected[]")).click();
    }
}
