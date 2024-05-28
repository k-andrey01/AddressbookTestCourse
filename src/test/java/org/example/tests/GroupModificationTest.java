package org.example.tests;

import org.example.model.GroupData;
import org.junit.Test;
import org.testng.Assert;

public class GroupModificationTest extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupCount();

        if (!app.getGroupHelper().isWhereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("group3", "mod", "modr"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }
}
