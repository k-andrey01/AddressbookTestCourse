package org.example.tests;

import org.example.model.GroupData;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.util.Set;

public class GroupModificationTest extends TestBase{

    @Before
    public void ensurePreconditions(){
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isWhereAGroup()){
            app.getGroupHelper().createGroup(new GroupData().withGroupName("test1"));
        }
    }

    @Test
    public void testGroupModification(){
        Set<GroupData> before = app.getGroupHelper().getGroupSet();

        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId())
                .withGroupName("group3").withHeader("mod").withFooter("modr");
        app.getGroupHelper().modifyGroup(group);

        Set<GroupData> after = app.getGroupHelper().getGroupSet();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }
}