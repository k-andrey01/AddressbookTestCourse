package org.example.tests;

import org.example.model.GroupData;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase{

    @Before
    public void ensurePreconditions(){
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isWhereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupModification(){
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size() -1;
        GroupData group = new GroupData(before.get(index).getId(), "group3", "mod", "modr");
        app.getGroupHelper().modifyGroup(index, group);

        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}