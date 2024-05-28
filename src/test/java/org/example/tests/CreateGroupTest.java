package org.example.tests;// Generated by Selenium IDE
import org.example.model.GroupData;
import org.junit.Test;
import org.testng.Assert;

public class CreateGroupTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();

    app.getGroupHelper().createGroup(new GroupData("group3", null, null));

    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);
  }

}
